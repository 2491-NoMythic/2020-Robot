/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.manipulators;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Controllers.IOperatorController;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Settings.Constants;

/**
 * Add your docs here.
 */
public class ps4 implements IOperatorController {

    private Joystick m_joystick;
    private static ps4 m_Instance = null;
    private JoystickButton intakeButton, climbInitializeButton;
    private JoystickButton raiseClimbExtensionButton;
    private JoystickButton lowerClimbExtensionButton;

    public static ps4 getInstance(){
        if (m_Instance == null){
            m_Instance = new ps4();
        }
        return m_Instance;
    }

    private ps4(){

    m_joystick = new Joystick(Constants.Controller.opertatorControllerID);
    intakeButton = new JoystickButton(m_joystick, Constants.Controller.ButtonBoard.intakeButtonID);
}

    @Override
    public JoystickButton returnIntakeButton() {
        return intakeButton;
    }

    @Override
    public JoystickButton returnRaiseClimbExtensionButton() {
        return raiseClimbExtensionButton;
    }

    @Override
    public JoystickButton returnLowerClimbExtensionButton() {
        return lowerClimbExtensionButton;
    }


    @Override
    public double getRightClimbAxis() {
        return m_joystick.getZ();
    }

    @Override
    public double getLeftClimbAxis() {
            return m_joystick.getY();
    }
}
