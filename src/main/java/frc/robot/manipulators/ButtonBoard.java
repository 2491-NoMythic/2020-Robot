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

/**
 * Add your docs here.
 */
public class ButtonBoard implements IOperatorController {

    private Joystick m_Joystick;
    private static ButtonBoard m_Instance = null;
    private JoystickButton intakeButton;
    private JoystickButton raiseClimbExtensionButton;
    private JoystickButton lowerClimbExtensionButton;

    public static ButtonBoard getInstance(){
        if (m_Instance == null){
            m_Instance = new ButtonBoard();
        }
        return m_Instance;
    }

    private ButtonBoard(){
        m_Joystick = new Joystick(Constants.Controller.opertatorControllerID);
        intakeButton = new JoystickButton(m_Joystick, Constants.Controller.ButtonBoard.intakeButtonID);
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
        return getRightClimbAxis();
    }

    @Override
    public double getLeftClimbAxis() {
       return getLeftClimbAxis();
    }
}
