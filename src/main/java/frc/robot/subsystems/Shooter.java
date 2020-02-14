/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Settings.Constants;

public class Shooter extends SubsystemBase {

  WPI_TalonFX shooterLeftMotor;
  WPI_TalonFX shooterRightMotor;

  double fGain, pGain, iGain, dGain;
  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    shooterLeftMotor = new WPI_TalonFX(Constants.Shooter.shooterTalonLeftMotor);
    shooterRightMotor = new WPI_TalonFX(Constants.Shooter.shooterTalonRightMotor);

    shooterRightMotor.follow(shooterLeftMotor);
    shooterRightMotor.setInverted(InvertType.OpposeMaster);

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

    fGain = Constants.Shooter.kF;
    pGain = Constants.Shooter.kP;
    iGain = Constants.Shooter.kI;
    dGain = Constants.Shooter.kD;

    //Config velocity closed loop gains in slot0
    shooterLeftMotor.config_kF(Constants.Shooter.PIDLoopIdx, fGain, Constants.Shooter.TimeoutMs);
    shooterLeftMotor.config_kP(Constants.Shooter.PIDLoopIdx, pGain, Constants.Shooter.TimeoutMs);
    shooterLeftMotor.config_kI(Constants.Shooter.PIDLoopIdx, iGain, Constants.Shooter.TimeoutMs);
    shooterLeftMotor.config_kD(Constants.Shooter.PIDLoopIdx, dGain, Constants.Shooter.TimeoutMs);

    //Put PID values on SmartDashboard
    SmartDashboard.putNumber("kF", fGain);
    SmartDashboard.putNumber("kP", pGain);
    SmartDashboard.putNumber("kI", iGain);
    SmartDashboard.putNumber("kD", dGain);
    SmartDashboard.putNumber("SpeedRightNow", getLeftEncoderRate());
  }

  //Creating Drive Velocity for Motors
  public void runLeftShooterVelocity(final double speed) {
    shooterLeftMotor.set(ControlMode.Velocity, speed);
  }

  //Stop the shooter motors
  public void stop() {
    shooterLeftMotor.set(ControlMode.PercentOutput, 0);
  }

  /**
   * DO NOT USE UNLESS FOR JUKEBOX
   * @return Talons
   */
  public TalonFX[] getTalonFX(){
    return new TalonFX[]{shooterLeftMotor, shooterRightMotor};
  }

  //Getting encoder distance and rate
  // Getting encoder distance and rate
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
    return shooterLeftMotor.getSelectedSensorVelocity(0);
  }

  public double getEncoderRate() {
    return ((getRightEncoderRate() + getLeftEncoderRate()) / 2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    final double p = SmartDashboard.getNumber("kP", 0);
    final double i = SmartDashboard.getNumber("kI", 0);
    final double d = SmartDashboard.getNumber("kD", 0);
    final double f = SmartDashboard.getNumber("kF", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != pGain)) { shooterLeftMotor.config_kP(Constants.Shooter.PIDLoopIdx, p, Constants.Shooter.TimeoutMs); pGain = p; }
    if((i != iGain)) { shooterLeftMotor.config_kI(Constants.Shooter.PIDLoopIdx, i, Constants.Shooter.TimeoutMs); iGain = i; }
    if((d != dGain)) { shooterLeftMotor.config_kD(Constants.Shooter.PIDLoopIdx, d, Constants.Shooter.TimeoutMs); dGain = d; }
    if((f != fGain)) { shooterLeftMotor.config_kF(Constants.Shooter.PIDLoopIdx, f, Constants.Shooter.TimeoutMs); fGain = f; }
  }
}
