/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;
import frc.robot.ControlBoard;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RobotUp extends CommandBase {

  private Drivetrain m_Drivetrain;
  private Climber m_Climber;
  private ControlBoard m_ControlBoard;
  private RobotUpState currentState;
  private ControlBoard mControlBoard;
  private double rightSpeed, leftSpeed;
  /**
   * Creates a new RobotUp.
   */
  private enum RobotUpState{
    Stopped, Moving;
  }

  public RobotUp(Drivetrain drivetrain, Climber climber, ControlBoard controlBoard) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    addRequirements(climber);
    m_Drivetrain = drivetrain;
    m_Climber = climber;
    mControlBoard = controlBoard;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Climber.setShifterOn();
    currentState = RobotUpState.Stopped;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch(currentState){
      case Moving:
        rightSpeed = (mControlBoard.getRawDriveAxis()/2) + (getRightStickScaled()/2);
        leftSpeed = (mControlBoard.getRawDriveAxis()/2) + (getLeftStickScaled()/2);  
        m_Drivetrain.drivePercentOutput(leftSpeed, rightSpeed);
        if(rightSpeed == 0 && leftSpeed == 0){
          currentState = RobotUpState.Stopped;
        } else if(rightSpeed == 0){
          m_Drivetrain.engageRightBreak();
        } else if(leftSpeed == 0){
          m_Drivetrain.engageLeftBreak();
        } 
        if(rightSpeed != 0){
          m_Drivetrain.disengageRightBreak();
        } else if (leftSpeed != 0){
          m_Drivetrain.disengageLeftBreak();
        }
      case Stopped:
        rightSpeed = (mControlBoard.getRawDriveAxis()/2) + (getRightStickScaled()/2);
        leftSpeed = (mControlBoard.getRawDriveAxis()/2) + (getLeftStickScaled()/2);  
        m_Drivetrain.engageLeftBreak();
        m_Drivetrain.engageRightBreak();
        if(rightSpeed != 0 || leftSpeed != 0){
          currentState = RobotUpState.Moving;
        }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public double getLeftStickScaled()
  {
    if(mControlBoard.getHorizontalClimbAxis() > 0){
      return 0;
    }else{
      return mControlBoard.getHorizontalClimbAxis() * -1;
    }
  }

  public double getRightStickScaled()
  {
    if(mControlBoard.getHorizontalClimbAxis() < 0){
      return 0;
    }else{
      return mControlBoard.getHorizontalClimbAxis();
    }
  }
}
