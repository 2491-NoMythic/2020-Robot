/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.Controllers;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import com.frc2491.clank.Settings.Constants.ShooterSpeeds;

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

	public JoystickButton getFunnelerAndIndexer();

	public JoystickButton runIndexer();

	public JoystickButton backIndexer();

	public double getIntakeAxis();

	public double getLeftClimbAxis();

	public boolean climbSaftey();

	public void setShooterSpeed();

	public double getShooterSpeed();

}
