/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.time.StopWatch;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class timeDrive extends CommandBase {
  /**
   * Creates a new timeDrive.
   */
  Drivetrain m_drivetrain;
  double speedOfDrive;
  double timeOfDrive;
  Timer activeTimer;
  boolean timeDriveFinished;
  public timeDrive(Drivetrain driveTransfer, double speedDriveTransfer, double timeTransfer) {
    m_drivetrain = driveTransfer;
    addRequirements(m_drivetrain);
    speedOfDrive = speedDriveTransfer;
    timeOfDrive = timeTransfer;
    activeTimer = new Timer();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.drivePercentOutput(speedOfDrive);
    activeTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }
  // I have no idea what's parameter should be in end(???)
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timeOfDrive <= activeTimer.get();
  }
}
