/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Settings.Constants;

public class Shooter extends SubsystemBase {

  WPI_TalonFX shooterLeftMotor;
  WPI_TalonFX shooterRightMotor;

  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    shooterLeftMotor = new WPI_TalonFX(Constants.Shooter.shooterTalonLeftMotor);
    shooterRightMotor = new WPI_TalonFX(Constants.Shooter.shooterTalonRightMotor);

    shooterRightMotor.follow(shooterLeftMotor);
    shooterLeftMotor.setInverted(false);
    shooterRightMotor.setInverted(InvertType.FollowMaster);

    //PID

    //Fac default to prevent unexpected behavour
    shooterLeftMotor.configFactoryDefault();

    //Config sensor used for velocity pid
    shooterLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, Constants.Shooter.PIDLoopIdx, Constants.Shooter.TimeoutMs);

    //Phase sensor
    shooterLeftMotor.setSensorPhase(true);

    //Config peak and nominal outputs
    shooterLeftMotor.configNominalOutputForward(0, Constants.Shooter.TimeoutMs);
    shooterLeftMotor.configNominalOutputReverse(0, Constants.Shooter.TimeoutMs);
    shooterLeftMotor.configPeakOutputForward(1, Constants.Shooter.TimeoutMs);
    shooterLeftMotor.configPeakOutputReverse(0, Constants.Shooter.TimeoutMs);

    //Config velocity closed loop gains in slot0
    shooterLeftMotor.config_kF(Constants.Shooter.PIDLoopIdx, Constants.Shooter.kF, Constants.Shooter.TimeoutMs);
    shooterLeftMotor.config_kP(Constants.Shooter.PIDLoopIdx, Constants.Shooter.kP, Constants.Shooter.TimeoutMs);
    shooterLeftMotor.config_kI(Constants.Shooter.PIDLoopIdx, Constants.Shooter.kI, Constants.Shooter.TimeoutMs);
    shooterLeftMotor.config_kD(Constants.Shooter.PIDLoopIdx, Constants.Shooter.kD, Constants.Shooter.TimeoutMs);
  }

  //Creating Drive Velocity for Motors
  public void runShooterVelocity(double leftSpeed, double rightSpeed) {
    runLeftShooterVelocity(leftSpeed);
    runRightShooterVelocity(rightSpeed);
  }

  public void runLeftShooterVelocity(double speed) {
    shooterLeftMotor.set(ControlMode.Velocity, speed);
  }

  public void runRightShooterVelocity(double speed) {
    shooterRightMotor.set(ControlMode.Velocity, speed);
  }

  //Getting encoder distance and rate
  public double getRightEncoderDistance() {
    return shooterRightMotor.getSelectedSensorPosition(0) * Constants.Shooter.shooterEncoderToInches;
  }

  public double getLeftEncoderDistance() {
    return shooterLeftMotor.getSelectedSensorPosition(0) * Constants.Shooter.shooterEncoderToInches;
  }

  public double getRightEncoderDistanceRaw() {
    return shooterRightMotor.getSelectedSensorPosition(0);
  }

  public double getLeftEncoderDistanceRaw() {
    return shooterLeftMotor.getSelectedSensorPosition(0);
  }

  public double getEncoderDistance() {
    return ((getLeftEncoderDistance() + getRightEncoderDistance()) / 2);
  }

  public double getRightEncoderRate() {
    return shooterRightMotor.getSelectedSensorVelocity(0) * Constants.Shooter.shooterEncoderVelocityToRPS;
  }

  public double getLeftEncoderRate() {
    return shooterLeftMotor.getSelectedSensorVelocity(0) * Constants.Shooter.shooterEncoderVelocityToRPS;
  }
  
  public double getEncoderRate() {
    return((getRightEncoderRate() + getLeftEncoderRate()) / 2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
