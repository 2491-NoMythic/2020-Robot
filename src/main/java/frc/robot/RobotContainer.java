/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.commands.intake.AutoIntake;
import frc.robot.Controllers.IOperatorController;
import frc.robot.Settings.Constants;
import frc.robot.Settings.Constants.Controller.ButtonBoard;
import frc.robot.commands.DefaultIntakeRoutine;
import frc.robot.commands.climber.LowerClimbExtension;
import frc.robot.commands.climber.RaiseClimbExtension;
import frc.robot.commands.shooter.RunShooterAtSpeedPID;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Shooter m_Shooter = new Shooter();
  private final Indexer m_Indexer = new Indexer();
  private final Intake m_Intake = new Intake();
  private final Climber m_Climber = new Climber();

  private final ControlBoard m_ControlBoard = ControlBoard.getInstance();

  private final RunShooterAtSpeedPID shooterAtSpeedPID = new RunShooterAtSpeedPID(m_Shooter);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    //Set the default command to grab controller axis
    m_drivetrain.setDefaultCommand(
      new Drive(
        m_ControlBoard,
        m_drivetrain)
    );
    m_Indexer.setDefaultCommand(
      new DefaultIntakeRoutine(
        m_Indexer,
        m_Intake)
    );
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    SmartDashboard.putData(shooterAtSpeedPID);
    m_ControlBoard.returnIntakeButton().whenPressed(new AutoIntake(m_Intake));
    m_ControlBoard.returnRaiseClimbExtensionButton().whenPressed(new RaiseClimbExtension(m_Climber));
    m_ControlBoard.returnLowerClimbExtensionButton().whenPressed(new LowerClimbExtension(m_Climber));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
    //TODO: change null to m_autonomousCommand once it has been coded
  }
}
