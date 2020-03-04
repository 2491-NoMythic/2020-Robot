/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.manipulators;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Controllers.IOperatorController;
import frc.robot.Settings.Constants;
import frc.robot.Settings.Constants.ShooterSpeeds;

/**
 * Add your docs here.
 */
public class ButtonBoard implements IOperatorController {

    private final Joystick m_Joystick;
    private static ButtonBoard m_Instance = null;
    private final JoystickButton intakeButton;

    public static ButtonBoard getInstance() {
        if (m_Instance == null) {
            m_Instance = new ButtonBoard();
        }
        return m_Instance;
    }

    private ButtonBoard() {
        m_Joystick = new Joystick(Constants.Controller.opertatorControllerID);
        intakeButton = new JoystickButton(m_Joystick, Constants.Controller.ButtonBoard.intakeButtonID);
    }

    @Override
    public JoystickButton getActivateIntakeButton() {
        return intakeButton;
    }

    @Override
    public double getIntakeAxis() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getLeftClimbAxis() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public JoystickButton getActivateLiftButton() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JoystickButton getActivateRobotUp() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JoystickButton getDisableRobotUp() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean climbSaftey() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public JoystickButton getDeactivateLiftButton() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JoystickButton getClimbCheck1() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JoystickButton getClimbCheck2() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JoystickButton getShooterButton() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JoystickButton getFunnelerAndIndexer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JoystickButton runIndexer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JoystickButton backIndexer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setShooterSpeed() {
        // TODO Auto-generated method stub
    }

    @Override
    public double getShooterSpeed() {
        // TODO Auto-generated method stub
        return getShooterSpeed();
    }

}