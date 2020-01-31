/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Settings.Constants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class Indexer extends SubsystemBase {
  TalonFX index1, index2, funnel1, funnel2;
  Solenoid indexSolenoid;

  /**
   * Creates a new Indexer.
   */
  public Indexer() {
    index1 = new TalonFX(Constants.Indexer.index1TalonID);
    index2 = new TalonFX(Constants.Indexer.index2TalonID);
    funnel1 = new TalonFX(Constants.Indexer.funnel1TalonID);
    funnel2 = new TalonFX(Constants.Indexer.funnel2TalonID);
    indexSolenoid = new Solenoid(Constants.Indexer.indexSolenoidID);

    index2.follow(index2);

    indexSolenoid.set(true);
  }

  // runs the index motors using percent output
  public void runIndexMotors(double speed) {
    index1.set(ControlMode.PercentOutput, speed);
  }

  // runs funnel motor using percent output
  public void runFunnelMotor1(double speed) {
    funnel1.set(ControlMode.PercentOutput, speed);
  }

  // runs funnel motor using percent output
  public void runFunnelMotor2(double speed) {
    funnel2.set(ControlMode.PercentOutput, speed);
  }

  // checks the index solenoid
  public boolean checkIndexSolenoid() {
    return indexSolenoid.get();
  }

  // toggles index solenoid and updates a variable
  public void toggleIndexSolenoid() {
    boolean state = !indexSolenoid.get();
    indexSolenoid.set(state);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}