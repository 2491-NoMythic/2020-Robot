/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.frc2491.clank.subsystems.Drivetrain;
import com.frc2491.clank.subsystems.Indexer;
import com.frc2491.clank.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Simple3Ball extends CommandBase {
  /**
   * Creates a new Simple3Ball.
   */
  Drivetrain mDrive;
  Shooter mShoot;
  Indexer mDex;
  Timer timer;
  int state;

  public Simple3Ball(Drivetrain drivetrain, Shooter shooter, Indexer indexer) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain, shooter, indexer);
    mDrive = drivetrain;
    mShoot = shooter;
    mDex = indexer;
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    state = 0;
    SmartDashboard.putBoolean("Hello", true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch(state){
      case 0:
        mShoot.runLeftShooterVelocity(16500);
        if(timer.get() > 2){
          state = 1;
        }
        break;
      case 1:
        mDex.runConnectorMotor(1);
        mDex.runIndexMotor(-0.5);
        if(timer.get()>7){
          state = 2;
          mShoot.runLeftShooterVelocity(0);
          mDex.runConnectorMotor(0);
          mDex.runIndexMotor(0);
        }
        break;
      case 2:
        mDrive.drivePercentOutput(0.25, 0.25);
        if(timer.get()>7.5){
          state = 3;
        }
        break;
      case 3:
        mDrive.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
