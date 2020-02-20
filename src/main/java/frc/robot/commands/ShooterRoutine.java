/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShooterRoutine extends ParallelCommandGroup {

  Shooter m_Shooter = new Shooter();
  Indexer m_Indexer = new Indexer();
  /**
   * Creates a new ShooterRoutine.
   */
  public ShooterRoutine() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    addCommands(
      new RevShooter(m_Shooter),
      new FeedShooter(m_Shooter, m_Indexer));
  }
}
