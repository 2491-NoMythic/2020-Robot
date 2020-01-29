/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Settings.Constants;

public class Intake extends SubsystemBase {
  TalonSRX CoreIntakeMotor;
  Solenoid IntakeSolenoid;
  /**
   * Creates a new Intake.
   */
  public Intake() {
    CoreIntakeMotor = new TalonSRX(Constants.Intake.intakeMotorPort);
    IntakeSolenoid = new Solenoid(Constants.Intake.intakeSolenoidPort);
    IntakeSolenoid.set(false);
  }
  
  public void StartIntakeMotor(double motorPercent) {
    CoreIntakeMotor.set(ControlMode.PercentOutput, motorPercent);
  }

  public void StopIntakeMotor() {
    CoreIntakeMotor.set(ControlMode.PercentOutput,0);
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