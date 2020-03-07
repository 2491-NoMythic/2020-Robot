/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
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
  Rotate turnLeft, turnRight;

  public GamerAuto6Ball(Drivetrain drive, Shooter shoot, Indexer index, Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive,shoot,index,intake);
    mDrive = drive;
    mShoot = shoot;
    mDex = index;
    mTak = intake;
    timer = new Timer();
    turnLeft = new Rotate(mDrive, -30);
    turnRight = new Rotate(mDrive, 30);          
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    state = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch(state){
      case 0:
        mShoot.runLeftShooterVelocity(2000);
        if(timer.get() > 1.2){
          state++;
        }
        break;
      case 1:
        mDex.runConnectorMotor(1);
        mDex.runIndexMotor(-1);
        if(timer.get()>3.2){
          state++;
          mShoot.runLeftShooterVelocity(0);
          mDex.runConnectorMotor(0);
          mDex.runIndexMotor(0);
        }
        break;
      case 2:
        turnRight.schedule();
        if(timer.get() > 5.2){
          state++;
        }
        
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
}
