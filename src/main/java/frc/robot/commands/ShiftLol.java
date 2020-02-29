/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;

public class ShiftLol extends CommandBase {
  /**
   * Creates a new ShiftLol.
   */
  Climber mClimb;
  Drivetrain mDrivetrain;

  public ShiftLol(Climber climb, Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    mClimb = climb;
    mDrivetrain = drivetrain;
    addRequirements(climb);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    mClimb.setShifterOn();
    mClimb.disengageLeftBreak();
    mClimb.disengageRightBreak();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mClimb.setShifterOff();
    mClimb.engageLeftBreak();
    mClimb.engageRightBreak();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
