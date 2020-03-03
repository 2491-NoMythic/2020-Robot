/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class ConnectorAndIndex extends CommandBase {
  /**
   * Creates a new ConnecterAndIndex.
   */
  Indexer m_Indexer;
  public ConnectorAndIndex(Indexer indexer) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Indexer = indexer;
    addRequirements(indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Indexer.runConnectorMotor(1);
    m_Indexer.runIndexMotor(-0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Indexer.runConnectorMotor(0);
    m_Indexer.runIndexMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
