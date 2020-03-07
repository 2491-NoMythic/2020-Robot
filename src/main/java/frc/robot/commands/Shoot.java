/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
  /**
   * Creates a new Shoot.
   */
  Shooter shoot;
  Indexer dex;
  Timer timer;
  int state;

  public Shoot(Shooter shooter, Indexer index) {
    // Use addRequirements() here to declare subsystem dependencies.
    shoot = shooter;
    dex = index;
    addRequirements(shooter, index);
    timer = new Timer();
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
        shoot.runLeftShooterVelocity(16500);
        if(timer.get() > 1.2){
          state++;
        }
        break;
      case 1:
        dex.runConnectorMotor(1);
        dex.runIndexMotor(-1);
        if(timer.get()>3.2){
          state++;
          dex.runConnectorMotor(0);
          dex.runIndexMotor(0);
        }
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
    return state >= 2;
  }
}
