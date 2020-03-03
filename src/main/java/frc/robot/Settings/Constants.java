/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Settings;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    
    public final class Drivetrain {
        //controllers
        public final static int driveController = 0;
        public final static double deadzone = 0.05;

        //drive
        public final static boolean useLinerAcceleration = true;
        public final static double accelerationSpeed = .05;
        public final static int driveTurnAxis = 2;
        public final static int driveMainAxis = 1;
        public final static int driveHorizontalAxis = 0;
        public final static int driveTalonLeftMotor1 = 2;
        public final static int driveTalonLeftMotor2 = 3;
        public final static int driveTalonRightMotor1 = 0;
        public final static int driveTalonRightMotor2 = 1;  //0 should be replaced

        //encoders
        public final static double driveEncoderTicks = 2048.0;   //encoder ticks per wheel rotation is 2048
        public final static double driveWheelDiameter = 6.0;     //inches
        public final static double driveEncoderToInches = driveWheelDiameter * Math.PI / driveEncoderTicks;   //makes number inches
        public final static double speedModeRPSToTalonOutput = driveEncoderTicks / 10.0; 
        public final static double driveEncoderVelocityToRPS = 1.0 / driveEncoderTicks * 10;
        public final static double driveMaxSpeedRPS = 8.0;

        //breaks
        public final static int rightBreakChannelID = 4;
        public final static int leftBreakChannelID = 5;
        //Distance
        public final class DistanceDrive {
            public final static double kP = 0;
            public final static double kI = 0;
            public final static double kD = 0;
        }


        public final class RotationCommand {
            public final static double kP = 0;
            public final static double kI = 0;
            public final static double kD = 0;
        }
    }

    public final class Controller{
        public static final int driveControllerID = 0; //TODO change to actual value (If its not zero I will literally die)
        public static final int opertatorControllerID = 1; //TODO change to actual value

        public final class ButtonBoard{
            public static final int intakeButtonID = 0; //TODO change to actual value
        }

		public final class PS4{
            public static final int activateIntakeButtonID = 6; //TODO change to value
            public static final int rightClimbAxisID = 5;
            public static final int leftClimbAxisID = 1;
            public static final int activateClimberButtonID = 2;
            public static final int deactivateClimbButtonID = 3;
            public static final int activateRobotUpButtonID = 1;
            public static final int deactivateRobotUpButtonID = 4;
            public static final int climbSaftey1ID = 7;
            public static final int climbSaftey2ID = 8;
			public static final int funnelerAndIndexerID = 14;
			public static final int shooterButtonID = 5;
            public static final int runIndexerButtonID = 0;
            public static final int slowModeButtonID = 10;
        }
    }

    public final class Intake {
        public static final int intakeMotorPort = 6;
        public static final int intakeSolenoidPortForward = 1;
        public static final int intakeSolenoidPortBackward = 0;
        //Speed
        public static final int autoIntakeSpeed = 0;
		public static final int intakeAxisID = 5;
    }
    public final class Indexer {
        //Motors
        public static final int indexBeltTalonID = 9;
        public static final int connectorTalonID = 11;
        public static final int funnelLeftTalonID = 8;
        public static final int funnelRightTalonID = 7;
        public static final int indexSolenoidID = 0;
        //Motor Speeds
        public static final double indexIntakeSpeed = 0;
        public static final double connectorTalonSpeed = 1;
        public static final double leftFunnelSpeed = 0;
        public static final double rightFunnelSpeed = 0;
        //Sensors
        public static final int sensorOnePin = 17;
        public static final int sensorTwoPin = 16;
        public static final int sensorThreePin = 15;
        public static final int sensorFourPin = 14;
        public static final int sensorFivePin = 13;
        public static final int sensorSixPin = 12;
        public static final int sensorSevenPin = 11;
        public static final int sensorEightPin = 10;
    }
  
    public final class Shooter {
        //shooter
        public static final int shooterTalonLeftMotor = 4;
        public static final int shooterTalonRightMotor = 5;
        //encoders
        public final static double shooterEncoderTicks = 2048.0; //Encoder ticks per wheel rotation is 2048
        public final static double shooterWheelDiameter = 4.0; //Inches
        public final static double shooterEncoderToInches = shooterWheelDiameter * Math.PI / shooterEncoderTicks; //Makes number inches
        public final static double shooterEncoderVelocityToRPS = 1.0 / shooterEncoderTicks * 10;
        //Speeds
        public static final double shootSpeedRpm = 20000; //Rpm
        public static final double shootSpeedRps = 19500;//TODO change for reality.
        //PID
        public final static int SlotIdx = 0;
        public final static int PIDLoopIdx = 0;
        public final static int TimeoutMs = 0;
        public final static double kP = 0.275;
	    public final static double kI = 0.0006;
	    public final static double kD = 0;
	    public final static double kF = 0.0455;
	    public final static int kIzone = 100    ;
        public final static double PeakOutput = 0;
        
        //Values
        public final static double testSpeed = 1000;
    }

    public final class Climber {
        public static final int liftMotorID = 10;
		public static final int shifterForwardChannel = 3;
		public static final int shifterReverseChannel = 2;
		public static final double manualLiftExtensionSpeed = 0;
		public static final int brakeChannel = 6;
        public static final int rightAxisID = 1
        ;
		public static final int leftAxisID = 0;
    }
}


