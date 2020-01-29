/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.manipulators;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class NoMythicJoystick {

    private Joystick m_joystick = new Joystick(0);

    public NoMythicJoystick(){
        m_joystick = new Joystick(Constants.Drivetrain.driveController);
    }

    private double getAxisDeadzoned(int axisID) {
        double result = m_joystick.getRawAxis(axisID);
        result = result * Math.abs(result);
        return Math.abs(result) > Constants.Drivetrain.deadzone ? result : 0;
    }

    public double getTurnAxis() {
        return getAxisDeadzoned(Constants.Drivetrain.driveTurnAxis);
    }

    public double getDriveAxis() {
        return getAxisDeadzoned(Constants.Drivetrain.driveMainAxis);
    }
}
