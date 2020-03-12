/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import com.frc2491.clank.commands.shooter.ShootThreeBalls;
import com.frc2491.clank.subsystems.Drivetrain;
import com.frc2491.clank.subsystems.Shooter;
import com.frc2491.clank.subsystems.Indexer;
import com.frc2491.clank.commands.drivetrain.timeDrive;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutonomousCommand extends SequentialCommandGroup {
  /**
   * Creates a new AutonomousCommand.
   */
  public AutonomousCommand(Drivetrain drivetrain, Shooter shooter, Indexer indexer, double speedDriveTransfer, double timeTransfer) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    addCommands(
      new timeDrive(drivetrain, speedDriveTransfer, timeTransfer),
      new ShootThreeBalls(shooter, indexer));
  }
}
