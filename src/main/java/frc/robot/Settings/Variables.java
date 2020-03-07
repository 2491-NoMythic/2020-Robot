/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Settings;

import frc.robot.Settings.Constants.ShooterSpeeds;

/**
 * Add your docs here.
 */
public class Variables {

    public static class Indexer {
        public static int ballsLoaded = 0;
        public static boolean finalBallLoaded = false;
    }

    public static class Shooter{
        public static ShooterSpeeds shooterSpeed = ShooterSpeeds.lowSpeed;
    }

    public static class Drivetrain{
        public static class RotationCommand{
            public static double kP = 0.015;
            public static double kI = 0.028;
            public static double kD = 0;
        }

        public static class Auto{
            public static boolean isRunningFirstTurn = false;
        }
    }
}
