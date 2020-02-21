/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Settings.Constants;
import frc.robot.Settings.Variables;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class DefaultIntakeRoutine extends CommandBase {

  private Indexer indexer;
  private Intake intake;

  boolean firstSensorTrigger = false;

  /**
   * Creates a new IntakeRoutine.
   */
  public DefaultIntakeRoutine(Indexer indexer, Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.indexer = indexer;
    this.intake = intake;
    addRequirements(indexer);
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(indexer.getSensorBallEnter() & !Variables.Indexer.finalBallLoaded){
      indexer.runIndexMotor(Constants.Indexer.indexIntakeSpeed);
      indexer.setIndexSolenoid(false);
    }
    else{
      indexer.setIndexSolenoid(true);
    }

    if(indexer.getSensorPositionOne() & !firstSensorTrigger){
      indexer.runIndexMotor(0);
      firstSensorTrigger = true;
      Variables.Indexer.ballsLoaded ++;
    }
    else{
      firstSensorTrigger = false;
    }

    if(indexer.getSensorBallLeave() || Variables.Indexer.ballsLoaded <= 4){
      Variables.Indexer.finalBallLoaded = true;
      indexer.setIndexSolenoid(false);
    }
    else{
      Variables.Indexer.finalBallLoaded = false;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    indexer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
