/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //controllers
    public final static int driveController = 0;
    public final static double deadzone = 0.05;

    //drive
    public static boolean useLinerAcceleration = true;
    public static double accelerationSpeed = 0;
    public final static int driveTurnAxis = 0;
    public final static int driveMainAxis = 0;
    public final static int driveTalonLeftMotor1 = 0;
    public final static int driveTalonLeftMotor2 = 0;
    public final static int driveTalonRightMotor1 = 0;
    public final static int driveTalonRightMotor2 = 0;  //0 should be replaced

    //encoders
    public final static double encoderTicks = 2048.0;   //encoder ticks per wheel rotation is 2048
    public final static double wheelDiameter = 6.0;
    public final static double driveEncoderToInches = 1 / wheelDiameter * Math.PI / encoderTicks;   //makes number inches
    public final static double speedModeRPSToTalonOutput = encoderTicks / 10.0; 
    public final static double driveEncoderVelocityToRPS = 1.0 / encoderTicks * 10;
    public final static double driveMaxSpeedRPS = 8.0;
}

