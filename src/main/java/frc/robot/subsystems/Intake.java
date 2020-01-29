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
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  Talon CoreIntakeMotor;
  Solenoid IntakeSolenoid;
  /**
   * Creates a new Intake.
   */
  public Intake() {
    CoreIntakeMotor = new Talon(Constants.intakeMotorPort);
    IntakeSolenoid = new Solenoid(Constants.intakeSolenoidPort);
    IntakeSolenoid.set(false);
  }
  
  public void StartIntakeMotor(double motorPercent) {
    CoreIntakeMotor.set(motorPercent);
  }

  public void StopIntakeMotor() {
    CoreIntakeMotor.set(0);
  }

  public void toggleIntakeSolenoid() {
    boolean a = !IntakeSolenoid.get();
    IntakeSolenoid.set(a);
  }

  public boolean checkIntakeSolenoid() {
    return IntakeSolenoid.get();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}