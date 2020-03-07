/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class DriveyBoi extends CommandBase {
  /**
   * Creates a new DriveyBoi.
   */
  Intake tak;
  Drivetrain dri;
  Indexer dex;
  Timer time;
  int state;
  double driveBackTime;
  public DriveyBoi(Drivetrain drive, Intake take, Indexer index, double driveBackTime) {
    // Use addRequirements() here to declare subsystem dependencies.
    dri = drive;
    tak = take;
    dex = index;
    this.driveBackTime = driveBackTime;
    addRequirements(drive, take, index);
    time = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.reset();
    time.start();
    state = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch(state){
      case 0:
        dri.drivePercentOutput(-0.3, -0.3);
        tak.toggleIntakeSolenoid();
        tak.StartIntakeMotor(-1);
        dex.runFunnelMotorLeft(0.75);
        dex.runFunnelMotorRight(1);
        dex.runIndexMotor(-0.5);
        if(time.get() > driveBackTime){
          state = 1;
        }
        break;
      case 1:
        dri.drivePercentOutput(0.3, 0.3);
        tak.toggleIntakeSolenoid();
        tak.StartIntakeMotor(0);
        dex.runFunnelMotorLeft(0);
        dex.runFunnelMotorRight(0);
        dex.runIndexMotor(0);
        if(time.get() > driveBackTime){
          state = 2;
        }
        break;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    dri.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return state >= 2;
  }
}
