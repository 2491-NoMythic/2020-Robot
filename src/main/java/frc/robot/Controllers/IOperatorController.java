/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Controllers;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Settings.Constants;

/**
 * Add your docs here.
 */
public interface IOperatorController {

    public JoystickButton getActivateIntakeButton();

	public JoystickButton getActivateLiftButton();

	public JoystickButton getDeactivateLiftButton();

	public JoystickButton getActivateRobotUp();

	public JoystickButton getClimbCheck1();

	public JoystickButton getClimbCheck2();

	public JoystickButton getDisableRobotUp();

	public JoystickButton getShooterButton();

	public JoystickButton getSlowModeButton();

	public JoystickButton getFunnelerAndIndexer();

	public JoystickButton runIndexer();

	public double getIntakeAxis();

	public double getLeftClimbAxis();

	public boolean climbSaftey();

}
