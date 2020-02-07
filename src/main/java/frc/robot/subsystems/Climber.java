/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Settings.Constants;

public class Climber extends SubsystemBase {
  TalonSRX lift;

  /**
   * Creates a new Climber.
   */
  public Climber() {
    lift = new TalonSRX(Constants.Climber.liftMotorID);
    lift.configFactoryDefault();

    lift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
  }

  public void runLift(double position) {
    lift.set(ControlMode.Position, position);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
