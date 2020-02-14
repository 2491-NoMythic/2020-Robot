/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

public class Jukebox extends CommandBase {
  /**
   * Creates a new Jukebox.
   */
  Drivetrain m_drivetrain;
  Shooter m_shooter;
  ArrayList<TalonFX> instruments;
  Orchestra orchestra;
  int loadup;

  public Jukebox(Drivetrain drivetrain, Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    m_shooter = shooter;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    instruments.addAll(Arrays.asList(m_shooter.getTalonFX()));
    instruments.addAll(Arrays.asList(m_drivetrain.getTalonFX()));
    orchestra= new Orchestra(instruments);
    orchestra.loadMusic("Mii.chrp");
    loadup = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!orchestra.isPlaying() && loadup < 11){
      orchestra.play();
      loadup = 0;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    loadup++;
    return false;
  }
}
