
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Settings.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

public class Drivetrain extends SubsystemBase {
  private WPI_TalonFX driveLeftMotor1,driveLeftMotor2,driveRightMotor1,driveRightMotor2;
  private AHRS gyro;
  private Relay spike;

  public Drivetrain() {

    //creating motors
    driveLeftMotor1 = new WPI_TalonFX(Constants.Drivetrain.driveTalonLeftMotor1);
    driveLeftMotor2 = new WPI_TalonFX(Constants.Drivetrain.driveTalonLeftMotor2);
    driveRightMotor1 = new WPI_TalonFX(Constants.Drivetrain.driveTalonRightMotor1);
    driveRightMotor2 = new WPI_TalonFX(Constants.Drivetrain.driveTalonRightMotor2);

    driveRightMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    driveLeftMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

    //Setting Followers
    driveLeftMotor2.follow(driveLeftMotor1);
    driveRightMotor2.follow(driveRightMotor1);

    //making right motors go right
    driveRightMotor1.setInverted(true);
    driveRightMotor2.setInverted(true);
    driveLeftMotor1.setInverted(false);
    driveLeftMotor2.setInverted(false);

    gyro = new AHRS(Port.kUSB);
    spike = new Relay(Constants.Drivetrain.spikeRelayChannel);

    resetGyro();
  }


  //gyro tingz
  public void resetGyro(){
    gyro.reset();
  }

  public double getGyroAngle(){
    return(getRawGyroAngle() % 360 + 360) % 360;
  }

  public double getRawGyroAngle() {
    return gyro.getAngle();
  }

  public TalonFX[] getTalonFX(){
    return new TalonFX[]{driveLeftMotor1,driveLeftMotor2,driveRightMotor1,driveRightMotor2};
  }

  //creating percent output for both right and left
  public void drivePercentOutput(double speed){
    drivePercentOutput(speed, speed);
  }

  public void drivePercentOutput(double leftSpeed, double rightSpeed){
    driveLeftPercentOutput(leftSpeed);
    driveRightPercentOutput(rightSpeed);
  }

  public void driveLeftPercentOutput(double speed){
    driveLeftMotor1.set(ControlMode.PercentOutput, speed);
  }

  public void driveRightPercentOutput(double speed){
    driveRightMotor1.set(ControlMode.PercentOutput, speed);
  }

  //creative drive voltage for both right and left
  public void driveVoltage(double outputVolts){
    driveVoltage(outputVolts, outputVolts);
  }

  public void driveVoltage(double leftOutputVolts, double rightOutputVolts){
    driveRightVoltage(rightOutputVolts);
    driveLeftVoltage(leftOutputVolts);
  }

  public void driveRightVoltage(double outputVolts){
    driveRightMotor1.setVoltage(outputVolts);
  }

  public void driveLeftVoltage(double outputVolts){
    driveLeftMotor1.setVoltage(outputVolts);
  }

  //creating drive velocity for both right and left
  public void driveVelocity(double speed){
    driveVelocity(speed, speed);
  }

  public void driveVelocity(double leftSpeed, double rightSpeed){
    driveLeftVelocity(leftSpeed);
    driveRightVelocity(rightSpeed);
  }

  public void driveLeftVelocity(double speed){
    driveLeftMotor1.set(ControlMode.Velocity, speed);
  }

  public void driveRightVelocity(double speed){
    driveRightMotor1.set(ControlMode.Velocity, speed);
  }

  public double getRightDriveSpeed() {
    return driveRightMotor1.get();
  }

  public double getLeftDriveSpeed(){
    return driveLeftMotor1.get();
  }

  //robot can stop
  public void stop(){
    drivePercentOutput(0, 0);
  }

  public void breakModeOn(Boolean b){
    if(b){
      driveLeftMotor1.setNeutralMode(NeutralMode.Brake);
      driveRightMotor1.setNeutralMode(NeutralMode.Brake);
    } else {
      driveLeftMotor1.setNeutralMode(NeutralMode.Coast);
      driveRightMotor1.setNeutralMode(NeutralMode.Coast);
    }
  }

  //getting encoder distance and rate
  public double getRightEncoderDistance() {
    return driveRightMotor1.getSelectedSensorPosition(0) * Constants.Drivetrain.driveEncoderToInches;
  }

  public double getLeftEncoderDistance() {
    return driveLeftMotor1.getSelectedSensorPosition(0) * Constants.Drivetrain.driveEncoderToInches;
  }

  public double getLeftEncoderDistanceRaw() {
    return driveLeftMotor1.getSelectedSensorPosition(0);
  }

  public double getRightEncoderDistanceRaw() {
    return driveRightMotor1.getSelectedSensorPosition(0);
  }

  public double getEncoderDistance() {
    return ((getLeftEncoderDistance() + getRightEncoderDistance()) / 2);
  }

  public double getLeftEncoderRate() {
    return driveLeftMotor1.getSelectedSensorVelocity(0) * Constants.Drivetrain.driveEncoderVelocityToRPS;
  }

  public double getRightEncoderRate() {
    return driveRightMotor1.getSelectedSensorVelocity(0) * Constants.Drivetrain.driveEncoderVelocityToRPS;
  }

  public double getEncoderRate() {
    return ((getRightEncoderRate() + getLeftEncoderRate()) / 2);
  }

  //Relay Toggles
  public void powerSpike() {
    spike.set(Value.kOn);
  }

  public void stopSpike() {
    spike.set(Value.kOff);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
