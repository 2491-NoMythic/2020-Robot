/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Settings.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Indexer extends SubsystemBase {
  TalonSRX indexerBelt, connector, funnel1, funnel2;
  Solenoid indexSolenoid;
  DigitalInput sensorBallEnter, sensorBallLeave, sensorPositionOne, sensorPositionTwo, sensorPositionThree, sensorPositionFour, sensorPositionFive, sensorPositionSix;

  /**
   * Creates a new Indexer.
   */
  public Indexer() {
    indexerBelt = new TalonSRX(Constants.Indexer.indexBeltTalonID);
    connector = new TalonSRX(Constants.Indexer.connectorTalonID);
    funnel1 = new TalonSRX(Constants.Indexer.funnelLeftTalonID);
    funnel2 = new TalonSRX(Constants.Indexer.funnelRightTalonID);
    indexSolenoid = new Solenoid(Constants.Indexer.indexSolenoidID);

    SmartDashboard.putNumber("RightFunnlerSpeed", 0);
    SmartDashboard.putNumber("LeftFunnlerSpeed", 0);

    configSensors();

    indexSolenoid.set(true);
  }

  // runs the index motors using percent output
  public void runIndexMotor(final double speed) {
    indexerBelt.set(ControlMode.PercentOutput, speed);
  }

  // runs the shooter feeder using percent output
  public void runConnectorMotor(final double speed) {
    connector.set(ControlMode.PercentOutput, speed);
  }

  // runs funnel motor using percent output
  public void runFunnelMotor1(final double speed) {
    funnel1.set(ControlMode.PercentOutput, speed);
  }

  // runs funnel motor using percent output
  public void runFunnelMotor2(final double speed) {
    funnel2.set(ControlMode.PercentOutput, speed);
  }

  // checks the index solenoid
  public boolean checkIndexSolenoid() {
    return indexSolenoid.get();
  }

  // toggles index solenoid and updates a variable
  public void toggleIndexSolenoid() {
    final boolean state = !indexSolenoid.get();
    indexSolenoid.set(state);
  }

  public void stop() {
    runIndexMotor(0);
    runConnectorMotor(0);
    runFunnelMotor1(0);
    runFunnelMotor2(0);
  }

  public void configSensors(){
    sensorBallEnter = new DigitalInput(Constants.Indexer.sensorOnePin);
    sensorBallLeave = new DigitalInput(Constants.Indexer.sensorTwoPin);
    sensorPositionOne = new DigitalInput(Constants.Indexer.sensorThreePin);
    sensorPositionTwo = new DigitalInput(Constants.Indexer.sensorFourPin);
    sensorPositionThree = new DigitalInput(Constants.Indexer.sensorFivePin);
    sensorPositionFour = new DigitalInput(Constants.Indexer.sensorSixPin);
    sensorPositionFive = new DigitalInput(Constants.Indexer.sensorSevenPin);
    sensorPositionSix = new DigitalInput(Constants.Indexer.sensorEightPin);
  }
  public boolean getSensorBallEnter(){
    return sensorBallEnter.get();
  }
  public boolean getSensorBallLeave(){
    return sensorBallLeave.get();
  }
  public boolean getSensorPositionOne(){
    return sensorPositionOne.get();
  }
  public boolean getSensorPositionTwo(){
    return sensorPositionTwo.get();
  }
  public boolean getSensorPositionThree(){
    return sensorPositionThree.get();
  }
  public boolean getSensorPositionFour(){
    return sensorPositionFour.get();
  }
  public boolean getSensorPositionFive(){
    return sensorPositionFive.get();
  }
  public boolean getSensorPositionSix(){
    return sensorPositionSix.get();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler
    double z = SmartDashboard.getNumber("LeftFunnlerSpeed", 0);
    double y = SmartDashboard.getNumber("RightFunnlerSpeed", 0);
    
    funnel1.set(ControlMode.PercentOutput, z);
    funnel2.set(ControlMode.PercentOutput, y);
  }
};