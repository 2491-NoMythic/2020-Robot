/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands;

import java.lang.Math;

/**
 * Add your docs here.
 */
public class GetShooterValues {
    double shooterHeight, shooterAngle, maxShooterSpeed, targetHeight;

    /**
     * A class for getting the speed for a flywheel shooter to hit a target consistantly.
     * @param shooterHeight the height of the shooter from the ground
     * @param shooterAngle the angle of the shooter relative to the top of the shooter
     * @param maxShooterSpeed the max speed of the shooter in RPM
     * @param targetHeight the height of the target you are trying to hit.
     */
    public GetShooterValues(double shooterHeight, double shooterAngle, double maxShooterSpeed, double targetHeight){
        this.shooterHeight = shooterHeight;
        this.shooterAngle = shooterAngle;
        this.maxShooterSpeed = maxShooterSpeed;
        this.targetHeight = targetHeight;
    }

    /**
     * Gives the shooter speed for a given distance from target.
     * @param distance distance from target in Meters
     * @return the value of speed in RPM
     */
    public double getShooterSpeed(double distance){
        return 0.0;
    }
}
