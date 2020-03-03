/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ControlBoard;
import frc.robot.subsystems.Drivetrain;

public class LineupDrive extends CommandBase {
  /**
   * Creates a new LineupDrive.
   */
  Drivetrain mDrive;
  private ControlBoard m_ControlBoard;
  double turnSpeed;
  double currentLeftSpeed = 0;
  double currentRightSpeed = 0;

  public LineupDrive(Drivetrain drivetrain, ControlBoard board) {
    // Use addRequirements() here to declare subsystem dependencies.
    mDrive = drivetrain;
    m_ControlBoard = board;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    turnSpeed = m_ControlBoard.getRawTurnAxis()/5;
    
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
