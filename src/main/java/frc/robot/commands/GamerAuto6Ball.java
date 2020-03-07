/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Settings.Variables;
import frc.robot.commands.drivetrain.Rotate;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class GamerAuto6Ball extends CommandBase {
  /**
   * Creates a new GamerAuto6Ball.
   */
  Drivetrain mDrive;
  Shooter mShoot;
  Indexer mDex;
  Intake mTak;
  Timer timer;
  int state;
  double currentTim;
  Rotate turnLeft, turnRight;
  double firstTime, secondTime, thirdTime, forthTime, beltTime, whenToDriveBack, driveSpeed;
  boolean iDontKnow, secTimLOl, thirdTimLOL;
  public GamerAuto6Ball(Drivetrain drive, Shooter shoot, Indexer index, Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive,shoot,index,intake);
    mDrive = drive;
    mShoot = shoot;
    mDex = index;
    mTak = intake;
    timer = new Timer();
    turnLeft = new Rotate(mDrive, -25);
    turnRight = new Rotate(mDrive, 25);    
    SmartDashboard.putNumber("firstTime", 0);
    SmartDashboard.putNumber("secondTime", 0);  
    SmartDashboard.putNumber("thirdTime", 0);  
    SmartDashboard.putNumber("forthTime", 0);  
    SmartDashboard.putNumber("beltTime", 0);   
    SmartDashboard.putNumber("driveSpeed", 0);       
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    firstTime = 3;
    driveSpeed = 0.3;
    secondTime = SmartDashboard.getNumber("secondTime", 0);
    thirdTime = SmartDashboard.getNumber("thirdTIme", 0);
    forthTime = SmartDashboard.getNumber("forthTime", 0);
    beltTime = SmartDashboard.getNumber("beltTime", 0);
    driveSpeed = SmartDashboard.getNumber("driveSpeed", 0);
    timer.reset();
    timer.start();
    state = 0;
    iDontKnow = true;
    currentTim = 0;
    secTimLOl = false;
    thirdTimLOL = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("state", state);
    SmartDashboard.putNumber("tyimer", timer.get());
    switch(state){
      case 0:
        mShoot.runLeftShooterVelocity(16500);
        if(timer.get() > 1.2){
          state++;
        }
        break;
      case 1:
        mDex.runConnectorMotor(1);
        mDex.runIndexMotor(-1);
        if(timer.get()>3.2){
          state++;
          mDex.runConnectorMotor(0);
          mDex.runIndexMotor(0);
        }
        break;
      case 2:
        turnRight.schedule();
        if(timer.get()>5.2){
          state++;
          turnRight.cancel();
        }
        break;
      case 3:
        mDrive.drivePercentOutput(-driveSpeed, -driveSpeed);
        mTak.toggleIntakeSolenoid();
        mTak.StartIntakeMotor(-1);
        runBallIn();
        if(timer.get() > (5.2 + firstTime)){
          state = 7;
        }
        break;
      case 4:
        if(iDontKnow){
          iDontKnow = false;
          currentTim = timer.get();
        }
        runBallIn();
        if(timer.get() > (currentTim + beltTime)){
          state = 5;
          iDontKnow = true;
          currentTim = 0;
          stopBallIn();
          if(secTimLOl){
            thirdTimLOL = true;
          }
          secTimLOl = true;
          if(thirdTimLOL){
            state = 7;
            break;
          } else if(secTimLOl){
            state = 6;
            break;
          } else{
            state = 7;
            break;
          }
        }
      case 5:
        if(iDontKnow){
          iDontKnow = false;
          currentTim = timer.get();
        }
        if(timer.get() > (currentTim + secondTime)){
          state = 4;
          iDontKnow = true;
        }
        break;
      case 6:
        if(iDontKnow){
          iDontKnow = false;
          currentTim = timer.get();
        }
        if(timer.get() > (currentTim + thirdTime)){
          state = 4;
          iDontKnow = true;
        }
        break;
      case 7:
        if(iDontKnow){
          iDontKnow = false;
          currentTim = timer.get();
        }
        mDrive.drivePercentOutput(driveSpeed, driveSpeed);
        if(timer.get() > (currentTim + firstTime)){
          state = 8;
          iDontKnow = true;
        }
        break; 
      case 8:
        if(iDontKnow){
          iDontKnow = false;
          currentTim = timer.get();
        }
        turnLeft.schedule();
        mShoot.runLeftShooterVelocity(16500);
        if(timer.get() > (currentTim + 2)){
          state = 9;
          iDontKnow = true;
        }
        break;
      case 9:
        if(iDontKnow){
          iDontKnow = false;
          currentTim = timer.get();
        }
        mDex.runConnectorMotor(1);
        mDex.runIndexMotor(-1);
        break;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return state >= 3;
  }

  private void runBallIn(){
    mDex.runFunnelMotorLeft(1);
    mDex.runFunnelMotorRight(0.75);
    mDex.runIndexMotor(-0.5);
  }

  private void stopBallIn(){
    mDex.runFunnelMotorLeft(0);
    mDex.runFunnelMotorRight(0);
    mDex.runIndexMotor(0);
  }
}
