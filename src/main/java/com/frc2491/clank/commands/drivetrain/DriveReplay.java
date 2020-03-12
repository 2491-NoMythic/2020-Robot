/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.subsystems.Drivetrain;

public class DriveReplay extends CommandBase {
  /**
   * Creates a new DriveReplay.
   */
  Drivetrain m_drivetrain;
  int count;
  double currentLeftSpeed, currentRightSpeed;
  double lastLeftSpeed, lastRightSpeed;
  double[][] m_recording;

  public DriveReplay(Drivetrain drivetrain, double[][] recording) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    addRequirements(drivetrain);
    m_recording = recording;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    count = 0;
    currentRightSpeed = 0;
    currentLeftSpeed = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    lastLeftSpeed = currentLeftSpeed;
    lastRightSpeed = currentRightSpeed;

    currentLeftSpeed = m_recording[count][0];
    currentRightSpeed = m_recording[count][1];

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
    m_drivetrain.drivePercentOutput(currentLeftSpeed, currentRightSpeed);
    count++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return count>=(m_recording.length-1);
  }
}
