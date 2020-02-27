/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ControlBoard;
import frc.robot.Settings.Constants;
import frc.robot.subsystems.Climber;

public class ClimbExtendControl extends CommandBase {
  /**
   * Creates a new ClimbExtendControl.
   */
  Climber m_Climber;
  ControlBoard mBoard;
  private UpClimberState currentState;

  private enum UpClimberState{
    Moving, Stopped;
  }

  public ClimbExtendControl(Climber climber, ControlBoard board) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Climber = climber;
    mBoard = board;
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    currentState = UpClimberState.Stopped;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch(currentState){
      case Moving:
        m_Climber.runLift(mBoard.getLeftClimbAxis());
        if(mBoard.getLeftClimbAxis() == 0){
          currentState = UpClimberState.Stopped;
        }
      case Stopped:
        m_Climber.setBrakeOn();
        if(Math.abs(mBoard.getLeftClimbAxis()) > 0){
          currentState = UpClimberState.Moving;
        }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
