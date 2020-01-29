
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

public class Drivetrain extends SubsystemBase {
  private WPI_TalonFX driveLeftMotor1,driveLeftMotor2,driveRightMotor1,driveRightMotor2;
  private AHRS gyro;

  public Drivetrain() {

    //creating motors
    driveLeftMotor1 = new WPI_TalonFX(Constants.driveTalonLeftMotor1);
    driveLeftMotor2 = new WPI_TalonFX(Constants.driveTalonLeftMotor2);
    driveRightMotor1 = new WPI_TalonFX(Constants.driveTalonRightMotor1);
    driveRightMotor2 = new WPI_TalonFX(Constants.driveTalonRightMotor2);

    //making right motors go right
    driveRightMotor1.setInverted(true);
    driveRightMotor2.setInverted(true);
    driveLeftMotor1.setInverted(false);
    driveLeftMotor2.setInverted(false);

    gyro = new AHRS(Port.kUSB);

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

  //robot can stop
  public void stop(){
    driveVelocity(0, 0);
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
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
