/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ControlBoard;
import frc.robot.Settings.Constants;
import frc.robot.Settings.Variables;
import frc.robot.Settings.Constants.ShooterSpeeds;
import frc.robot.subsystems.Shooter;

public class RunShooterAtSpeedPID extends CommandBase {
  /**
   * Creates a new RunShooterAtSpeedPID.
   */
  Shooter mShooter;
  double currentSpeed;
  ControlBoard mBoard;

  public RunShooterAtSpeedPID(Shooter shooter, ControlBoard cBoard) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
    mShooter = shooter;
    mBoard = cBoard;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mBoard.setShooterSpeed();
    switch(Variables.Shooter.shooterSpeed){
      case lowSpeed:
        currentSpeed = 16500;
        break;
      case midSpeed:
        currentSpeed = 19000;
        break;
      case highSpeed:
        currentSpeed = 21000;
        break;
      case stop:
        currentSpeed = 0;
        break;
      case sleepSpeed:
        currentSpeed = 2000;
        break;
    }
    mShooter.runLeftShooterVelocity(currentSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mShooter.runLeftShooterVelocity(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
