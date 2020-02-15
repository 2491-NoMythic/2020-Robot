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
     public JoystickButton returnIntakeButton();
     public JoystickButton returnRaiseClimbExtensionButton();
     public JoystickButton returnLowerClimbExtensionButton();

     public JoystickButton returnClimbInitializeButton();

     

}

