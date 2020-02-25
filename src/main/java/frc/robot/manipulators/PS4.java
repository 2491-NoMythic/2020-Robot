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

/**
 * Add your docs here.
 */
public class PS4 implements IOperatorController {

    private Joystick m_Joystick;
    private static PS4 m_Instance = null;
    private JoystickButton intakeButton;

    public static PS4 getInstance(){
        if (m_Instance == null){
            m_Instance = new PS4();
        }
        return m_Instance;
    }

    @Override
    public JoystickButton returnIntakeButton() {
        // TODO Auto-generated method stub
        return null;
    }
}
