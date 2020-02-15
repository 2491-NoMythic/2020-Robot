/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Settings.Constants;
import frc.robot.Settings.Variables;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Indexer;

public class FeedShooter extends CommandBase {

  private Indexer indexer;
  private Shooter shooter;

  /**
   * Creates a new ShooterRoutine.
   */
  public FeedShooter(Shooter shooter, Indexer indexer) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.indexer = indexer;
    this.shooter = shooter;
    addRequirements(indexer);
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(shooter.getEncoderRate() >= Constants.Shooter.shootSpeedRps & Variables.Indexer.ballsLoaded >= 1){
      indexer.runShooterFeederMotor(Constants.Indexer.shooterFeederSpeed);
      if(indexer.getSensorBallLeave()){
        indexer.runIndexMotor(Constants.Indexer.indexIntakeSpeed);
      }
      Variables.Indexer.ballsLoaded --;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    indexer.stop();
    shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
