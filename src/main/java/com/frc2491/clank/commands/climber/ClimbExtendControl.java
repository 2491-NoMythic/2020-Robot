/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands.climber;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.ControlBoard;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.subsystems.Climber;
import com.frc2491.clank.subsystems.Indexer;

public class ClimbExtendControl extends CommandBase {
  /**
   * Creates a new ClimbExtendControl.
   */
  Climber m_Climber;
  ControlBoard mBoard;
  private UpClimberState currentState;
  double count = 0;

  private enum UpClimberState{
    Moving, Stopped;
  }

  public ClimbExtendControl(Climber climber, Indexer index, ControlBoard board) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Climber = climber;
    mBoard = board;
    addRequirements(climber, index);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("Nicew", mBoard.climbSaftey());
    currentState = UpClimberState.Stopped;
    m_Climber.disengageRightBreak();
    m_Climber.disengageLeftBreak();
    count = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println(currentState.toString());
    switch(currentState){
      case Moving:
        m_Climber.setBrakeOff();
        if(count > 5){
          m_Climber.runLift(mBoard.getLeftClimbAxis()/2);
        }
        if(Math.abs(mBoard.getLeftClimbAxis()) <= 0.05){
          currentState = UpClimberState.Stopped;
        }
        count++;
        break;
      case Stopped:
        count = 0;
        m_Climber.runLift(0);
        m_Climber.setBrakeOn();
        if(Math.abs(mBoard.getLeftClimbAxis()) > 0.05){
          currentState = UpClimberState.Moving;
        }
        break;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Climber.engageRightBreak();
    m_Climber.engageLeftBreak();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
