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
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Settings.Constants;

public class Climber extends SubsystemBase {
  TalonSRX lift;
  DoubleSolenoid shifter;
  Solenoid climbBreak;

  /**
   * Creates a new Climber.
   */
  public Climber() {
    lift = new TalonSRX(Constants.Climber.liftMotorID);
    lift.configFactoryDefault();
    lift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    shifter = new DoubleSolenoid(Constants.Climber.shifterSolenoidForwardID,Constants.Climber.shifterSolenoidBackID);
    climbBreak = new Solenoid(Constants.Climber.breakSolenoidID);
  }

  public void runLift(double position) {
    lift.set(ControlMode.PercentOutput, position);
  }

  public boolean getClimbBreakPosition(){
    return climbBreak.get();
  }

  public void climbBreakOn(){
    climbBreak.set(true);
  }

  public void climbBreakOff(){
    climbBreak.set(false);
    
  }

  public void toggleClimbBreak(){
    if(climbBreak.get()){
      climbBreak.set(false);
    }else{
      climbBreak.set(true);
    }
  }

  public Value getShifterPosition(){
    return shifter.get();
  }

  public void shifterEngaged(){
    shifter.set(Value.kOff);
  }

  public void shifterDisengaged(){
    shifter.set(Value.kForward);
  }

  public void toggleShifter(){
    if(shifter.get() == Value.kOff){
      shifter.set(Value.kForward);
    }
    else{
      shifter.set(Value.kOff);
    }
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
