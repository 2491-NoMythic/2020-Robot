/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Settings.Constants;
import frc.robot.Settings.Variables;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class AutoShootThreeBalls extends CommandBase {
  private Drivetrain drivetrain;
  private Timer timer;
  private ShooterRoutine ShooterRoutine;

  /**
   * Creates a new AutoShootThreeBalls.
   */
  public AutoShootThreeBalls(Drivetrain drivetrain, ShooterRoutine ShooterRoutine) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;

    timer = new Timer();
    addRequirements(drivetrain);
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ShooterRoutine.();
    }
    if (Variables.Indexer.ballsLoaded < 0) {
      timer.start();
        if(timer.get() < Constants.Drivetrain.driveLength){
          drivetrain.drivePercentOutput(Constants.Drivetrain.autoDriveSpeed);
        }
        else drivetrain.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
    indexer.stop();
    shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
