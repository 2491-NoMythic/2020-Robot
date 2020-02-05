/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Controllers;

/**
 * Add your docs here.
 */
public interface IDriveController {

    public double getRawDriveAxis();

    public double getDriveAxisDeadzone();

    public double getRawTurnAxis();
 
}
        