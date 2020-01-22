/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  Talon CoreMotor;
  Solenoid IntakeSolenoid;
  /**
   * Creates a new Intake.
   */
  public Intake() {
    CoreMotor = new Talon(2);
    IntakeSolenoid = new Solenoid(2491);
    IntakeSolenoid.set(false);
  }
  
  public void StartMotor(double motorPercent) {
    CoreMotor.set(motorPercent);
  }

  public void StopMotor() {
    CoreMotor.set(0);
  }

  public void toggleSolenoid() {
    boolean a = !IntakeSolenoid.get();
    IntakeSolenoid.set(a);
  }

  public boolean checkSolenoid() {
    return IntakeSolenoid.get();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}