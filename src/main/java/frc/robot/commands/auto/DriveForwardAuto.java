package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.timeDrive;
import frc.robot.subsystems.Drivetrain;

public class DriveForwardAuto extends SequentialCommandGroup {
    /** Drives forward at 60% power for 3 seconds */
    public DriveForwardAuto(Drivetrain drivetrain) {
        addCommands(new timeDrive(drivetrain, 0.6, 3));
    }
}