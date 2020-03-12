/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands.drivetrain;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.subsystems.Drivetrain;

public class DistanceDrive extends CommandBase {

  private Drivetrain drivetrain;
  private double distance;
  private PIDController pid;

  /**
   * Creates a new Distance command.
   * @param distance distance to drive in inches
   */
  public DistanceDrive(Drivetrain drivetrain, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
    this.distance  = distance;
    pid = new PIDController(Constants.Drivetrain.DistanceDrive.kP, Constants.Drivetrain.DistanceDrive.kI, Constants.Drivetrain.DistanceDrive.kD);
    pid.setTolerance(2,10);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pid.setSetpoint(drivetrain.getEncoderDistance() + distance);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = pid.calculate(drivetrain.getEncoderDistance());
    drivetrain.drivePercentOutput(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return pid.atSetpoint();
  }
}