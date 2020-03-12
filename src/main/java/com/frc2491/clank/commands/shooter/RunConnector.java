/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.subsystems.Indexer;

public class RunConnector extends CommandBase {
  /**
   * Creates a new RunConnector.
   */
  Indexer index;
  public RunConnector(Indexer index) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.index = index;
    addRequirements(this.index);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("BeltSpeed", 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    index.runConnectorMotor(1);
    index.runIndexMotor(SmartDashboard.getNumber("BeltSpeed", 0));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    index.runConnectorMotor(0);
    index.runIndexMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
