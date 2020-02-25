/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ControlBoard;
import frc.robot.subsystems.Climber;

public class ClimbUp extends CommandBase {
  /**
   * Creates a new ClimbUp.
   */
  Climber m_Climber;
  ControlBoard m_ControlBoard;

  public ClimbUp(Climber climber, ControlBoard controlBoard) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Climber = climber;
    m_ControlBoard = controlBoard;
    addRequirements(m_Climber);
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
