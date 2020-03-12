/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.Settings.Variables;
import com.frc2491.clank.subsystems.Shooter;
import com.frc2491.clank.subsystems.Indexer;

public class ShooterRoutine extends CommandBase {

  private Indexer indexer;
  private Shooter shooter;
  Timer timer;

  /**
   * Creates a new ShooterRoutine.
   */
  public ShooterRoutine(Shooter shooter, Indexer indexer) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.indexer = indexer;
    this.shooter = shooter;
    addRequirements(indexer);
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.runLeftShooterVelocity(Constants.Shooter.shootSpeedRpm);

    if(shooter.getEncoderRate() >= Constants.Shooter.shootSpeedRps && 21000 >= shooter.getEncoderRate()){
      indexer.runConnectorMotor(Constants.Indexer.connectorTalonSpeed);
      indexer.runIndexMotor(.8);
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
    return timer.get() >= 5;
  }
}
