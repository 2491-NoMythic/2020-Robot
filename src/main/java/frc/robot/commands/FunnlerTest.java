/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class FunnlerTest extends CommandBase {
  /**
   * Creates a new FunnlerTest.
   */
  Indexer mIndexer;

  public FunnlerTest(Indexer index) {
    // Use addRequirements() here to declare subsystem dependencies.
    mIndexer = index;
    addRequirements(index);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("LeftFunnel", 0);
    SmartDashboard.putNumber("RightFunnel", 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mIndexer.runFunnelMotor1(SmartDashboard.getNumber("LeftFunnel", 0));
    mIndexer.runFunnelMotor2(SmartDashboard.getNumber("RightFunnel", 0));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mIndexer.runFunnelMotor1(0);
    mIndexer.runFunnelMotor2(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
