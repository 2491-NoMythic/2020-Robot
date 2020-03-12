/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.subsystems.Shooter;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.subsystems.Indexer;

public class ShootThreeBalls extends CommandBase {

  Shooter m_shooter;
  Indexer m_indexer;
  /**
   * Creates a new AutonomousCommand.
   */
  public ShootThreeBalls(Shooter shooter, Indexer indexer) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = shooter;
    m_indexer = indexer;
    addRequirements(m_shooter, m_indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    m_shooter.runLeftShooterVelocity(Constants.Shooter.shootSpeedRpm);

    if(m_shooter.getEncoderRate() >= Constants.Shooter.shootSpeedRpm){
      m_indexer.runIndexMotor(Constants.Indexer.indexIntakeSpeed);
      m_indexer.runConnectorMotor(Constants.Indexer.connectorTalonSpeed);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.stop();
    m_indexer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
