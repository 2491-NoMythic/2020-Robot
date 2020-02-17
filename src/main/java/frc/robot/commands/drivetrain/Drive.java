/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ControlBoard;
import frc.robot.Settings.Constants;
import frc.robot.subsystems.Drivetrain;

public class Drive extends CommandBase {

  private Drivetrain drivetrain;
  private ControlBoard m_ControlBoard;
  double turnSpeed, lastLeftSpeed, lastRightSpeed;
  double currentLeftSpeed = 0;
  double currentRightSpeed = 0;

  /**
   * Creates a new Drive.
   */
  public Drive(ControlBoard controlBoard, Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    m_ControlBoard = controlBoard;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    turnSpeed = 0.5 * m_ControlBoard.getRawTurnAxis();
		
		lastLeftSpeed = currentLeftSpeed;
		lastRightSpeed = currentRightSpeed;
		
		currentLeftSpeed = m_ControlBoard.getDriveAxisDeadzone() - turnSpeed;
    currentRightSpeed = m_ControlBoard.getDriveAxisDeadzone() + turnSpeed;
    
    System.out.println("CurrentLeft = " + currentLeftSpeed + ", CurrentLast = " + lastLeftSpeed);

		
		if (Constants.Drivetrain.useLinerAcceleration) {
			double leftAcceleration = (currentLeftSpeed - lastLeftSpeed);
			double signOfLeftAcceleration = leftAcceleration / Math.abs(leftAcceleration);
			if (Math.abs(leftAcceleration) > Constants.Drivetrain.accelerationSpeed) { // otherwise the power is below accel and is fine
				if (Math.abs(currentLeftSpeed) - Math.abs(lastLeftSpeed) > 0) {
					//System.out.println(currentLeftSpeed + " was too high, setting to " + (lastLeftSpeed + (Variables.accelerationSpeed * signOfLeftAcceleration)));
					currentLeftSpeed = lastLeftSpeed + (Constants.Drivetrain.accelerationSpeed * signOfLeftAcceleration);
					
				}
				// if the difference between the numbers is positive it is going up
			}
			double rightAcceleration = (currentRightSpeed - lastRightSpeed);
			double signOfRightAcceleration = rightAcceleration / Math.abs(rightAcceleration);
			if (Math.abs(rightAcceleration) > Constants.Drivetrain.accelerationSpeed) { // otherwise the power is below 0.05 accel and is fine
				if (Math.abs(currentRightSpeed) - Math.abs(lastRightSpeed) > 0) {
					//System.out.println(currentRightSpeed + " was too high, setting to " + (lastRightSpeed + (Variables.accelerationSpeed * signOfRightAcceleration)));
					currentRightSpeed = lastRightSpeed + (Constants.Drivetrain.accelerationSpeed * signOfRightAcceleration);
				}
				// if the difference between the numbers is positive it is going up
			}
		}
		
		
		drivetrain.drivePercentOutput(currentLeftSpeed, currentRightSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
