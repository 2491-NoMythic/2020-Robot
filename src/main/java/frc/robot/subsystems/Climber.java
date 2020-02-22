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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Settings.Constants;

public class Climber extends SubsystemBase {
  TalonSRX lift;
  DoubleSolenoid shifter;


  /**
   * Creates a new Climber.
   */
  public Climber() {
    lift = new TalonSRX(Constants.Climber.liftMotorID);
    lift.configFactoryDefault();

    lift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    shifter = new DoubleSolenoid(Constants.Climber.shifterForwardChannel, Constants.Climber.shifterReverseChannel);
    shifter.set(Value.kForward);

  }

  public void runLift(double speed) {
    lift.set(ControlMode.PercentOutput, speed);
  }

  public void stop() {
    lift.set(ControlMode.PercentOutput, 0);
  }

  public void setShifterOn(){
    shifter.set(Value.kReverse);
  }

  public void setShifterOff(){
    shifter.set(Value.kForward);
  }

  public boolean shifterCheck(){
    return shifter.get() == Value.kReverse;
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
