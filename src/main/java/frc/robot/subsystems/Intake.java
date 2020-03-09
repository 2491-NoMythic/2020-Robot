/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Settings.Constants;

public class Intake extends SubsystemBase {
  TalonSRX CoreIntakeMotor;
  DoubleSolenoid IntakeSolenoid;
  /**
   * Creates a new Intake.
   */
  public Intake() {
    CoreIntakeMotor = new TalonSRX(Constants.Intake.intakeMotorPort);
    IntakeSolenoid = new DoubleSolenoid(Constants.Intake.intakeSolenoidPortForward,Constants.Intake.intakeSolenoidPortBackward);
    pullIntakeIn();
  }
  
  public void StartIntakeMotor(double motorPercent) {
    CoreIntakeMotor.set(ControlMode.PercentOutput, motorPercent);
  }

  public void StopIntakeMotor() {
    CoreIntakeMotor.set(ControlMode.PercentOutput,0);
  }

  public void toggleIntakeSolenoid() {
    Value a = IntakeSolenoid.get();
    if(a == Value.kReverse){
      IntakeSolenoid.set(Value.kForward);
    } else {
      IntakeSolenoid.set(Value.kReverse);
    }  
  }

  public void pushIntake(){
    IntakeSolenoid.set(Value.kForward);
  }

  public void pullIntakeIn(){
    IntakeSolenoid.set(Value.kReverse);
  }

  public Value checkIntakeSolenoid() {
    return IntakeSolenoid.get();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}