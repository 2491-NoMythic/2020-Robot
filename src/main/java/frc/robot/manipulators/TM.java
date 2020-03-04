/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.manipulators;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Controllers.IDriveController;
import frc.robot.Settings.Constants;

/**
 * Add your docs here.
 */
public class TM implements IDriveController {

    private Joystick m_Joystick;
    private static TM m_Instance = null;
    private JoystickButton connectorAndIndexer, slowDrive;

    public static TM getInstance() {
        if (m_Instance == null) {
            m_Instance = new TM();
        }
        return m_Instance;
    }

    private TM() {
        m_Joystick = new Joystick(Constants.Controller.driveControllerID);
        connectorAndIndexer = new JoystickButton(m_Joystick, 7);
        slowDrive = new JoystickButton(m_Joystick, 8);
    }

    @Override
    public double getRawDriveAxis() {
        return m_Joystick.getRawAxis(Constants.Drivetrain.driveMainAxis);
    }

    @Override
    public double getDriveAxisDeadzone() {
        return getAxisDeadzoned(m_Joystick.getRawAxis(Constants.Drivetrain.driveMainAxis));
    }

    @Override
    public double getRawTurnAxis() {
        return m_Joystick.getRawAxis(Constants.Drivetrain.driveTurnAxis);
    }

    @Override
    public double getHorizontalClimbAxis() {
        return m_Joystick.getRawAxis(Constants.Drivetrain.driveHorizontalAxis);
    }

    private double getAxisDeadzoned(double value) {
        value = value * Math.abs(value);
        return Math.abs(value) > Constants.Drivetrain.deadzone ? value : 0;
    }

    @Override
    public JoystickButton getConnectorAndIndexer() {
        // TODO Auto-generated method stub
        return connectorAndIndexer;
    }
}