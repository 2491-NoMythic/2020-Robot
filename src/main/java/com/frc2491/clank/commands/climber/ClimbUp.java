/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.ControlBoard;
import com.frc2491.clank.subsystems.Climber;
import com.frc2491.clank.subsystems.Indexer;

public class ClimbUp extends CommandBase {
  /**
   * Creates a new ClimbUp.
   */
  Climber m_Climber;
  ControlBoard m_ControlBoard;

  public ClimbUp(Climber climber, Indexer indexer, ControlBoard controlBoard) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Climber = climber;
    m_ControlBoard = controlBoard;
    addRequirements(m_Climber);
    addRequirements(indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
