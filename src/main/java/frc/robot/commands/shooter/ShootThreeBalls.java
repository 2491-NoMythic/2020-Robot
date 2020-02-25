/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ShooterRoutine;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.drivetrain.timeDrive;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShootThreeBalls extends SequentialCommandGroup {
  /**
   * Shoots three balls as an auto 
   */
  public ShootThreeBalls(Drivetrain drivetrain, Shooter shooter, Indexer indexer, Drivetrain driveTransfer, double speedDriveTransfer, double timeTransfer) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    addCommands(
      new ShooterRoutine(shooter, indexer),
      new timeDrive(driveTransfer, speedDriveTransfer, timeTransfer));
  }
}
