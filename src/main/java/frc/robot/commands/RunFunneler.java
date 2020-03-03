/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class RunFunneler extends CommandBase {
  /**
   * Creates a new RunFunneler.
   */
  Indexer m_indexer;
  public RunFunneler(Indexer indexer) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_indexer = indexer;
    addRequirements(indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_indexer.runFunnelMotorLeft(0.5);
    m_indexer.runFunnelMotorRight(0.75);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_indexer.stopFunnel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_indexer.getSensorPositionTwo() || m_indexer.getSensorPositionThree();
  }
}
