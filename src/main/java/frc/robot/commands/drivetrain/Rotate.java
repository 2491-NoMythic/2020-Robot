/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Settings.Constants;
import frc.robot.Settings.Variables;
import frc.robot.subsystems.Drivetrain;

public class Rotate extends CommandBase {

  private Drivetrain drivetrain;
  private double degrees;
  private PIDController pid;
  private Timer timer;

  /**
   * Creates a new Rotate command.
   */
  public Rotate(Drivetrain drivetrain, double degrees) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    this.degrees = degrees;
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pid = new PIDController(Variables.Drivetrain.RotationCommand.kP, Variables.Drivetrain.RotationCommand.kI, Variables.Drivetrain.RotationCommand.kD);
    pid.setTolerance(1,10);
    pid.setSetpoint(drivetrain.getRawGyroAngle() + degrees);
    Variables.Drivetrain.Auto.isRunningFirstTurn = true;
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double turnrate = pid.calculate(drivetrain.getRawGyroAngle());
    drivetrain.drivePercentOutput(turnrate*-1, turnrate);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
    Variables.Drivetrain.Auto.isRunningFirstTurn = false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return pid.atSetpoint() || (timer.get() > 2);
  }
}
