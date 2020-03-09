/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.Rotate;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ProjectSnobbbyShores extends SequentialCommandGroup {
  /**
   * Creates a new ProjectSnobbbyShores.
   */
  public ProjectSnobbbyShores(Shooter shoot, Indexer index, Drivetrain drivetrain, Intake intake) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    addCommands(new Shoot(shoot, index), new Rotate(drivetrain, 22), new DriveyBoi(drivetrain, intake, index, 4.4), new Rotate(drivetrain, -11), new Shoot(shoot, index));
  }
}
