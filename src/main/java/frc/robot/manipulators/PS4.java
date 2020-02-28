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
public class PS4 implements IOperatorController {

    private Joystick m_joystick;
    private static PS4 m_Instance = null;
    private JoystickButton activateIntakeButton, activateClimbButton, activateRobotUpButton, deactivateRobotUpButton;


    public static PS4 getInstance(){
        if (m_Instance == null){
            m_Instance = new PS4();
        }
        return m_Instance;
    }

    private PS4(){

    m_joystick = new Joystick(Constants.Controller.opertatorControllerID);
    activateIntakeButton = new JoystickButton(m_joystick, Constants.Controller.PS4.activateIntakeButtonID);
    activateClimbButton = new JoystickButton(m_joystick, Constants.Controller.PS4.activateClimberButtonID);
    activateRobotUpButton = new JoystickButton(m_joystick, Constants.Controller.PS4.activateRobotUpButtonID);
    deactivateRobotUpButton = new JoystickButton(m_joystick, Constants.Controller.PS4.deactivateRobotUpButtonID);
    }

    @Override
    public JoystickButton getActivateIntakeButton() {
        return activateIntakeButton;
    }


	@Override
	public double getIntakeAxis() {
		return m_joystick.getRawAxis(Constants.Intake.intakeAxisID);
    }

    @Override
    public double getLeftClimbAxis() {
        return m_joystick.getRawAxis(Constants.Climber.rightAxisID);
    }

    @Override
    public JoystickButton getActivateLiftButton() {
        return activateClimbButton;
    }

    @Override
    public JoystickButton getActivateRobotUp() {
        // TODO Auto-generated method stub
        return activateRobotUpButton;
    }

    @Override
    public JoystickButton getDisableRobotUp() {
        // TODO Auto-generated method stub
        return deactivateRobotUpButton;
    }
}
