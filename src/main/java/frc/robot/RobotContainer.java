/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.commands.drivetrain.LineupDrive;
import frc.robot.commands.intake.AutoIntake;
import frc.robot.commands.ConnectorAndIndex;
import frc.robot.commands.DefaultIntakeRoutine;
import frc.robot.commands.FunnlerTest;
import frc.robot.commands.RunIndexer;
import frc.robot.commands.ShiftLol;
import frc.robot.commands.Simple3Ball;
import frc.robot.commands.funnelOnlyDefaultCommand;
import frc.robot.commands.climber.ClimbExtendControl;
import frc.robot.commands.climber.RobotUp;
import frc.robot.commands.shooter.RunConnector;
import frc.robot.commands.shooter.RunFullSpeed;
import frc.robot.commands.shooter.RunShooterAtSpeedPID;
import frc.robot.Settings.Constants;
import frc.robot.commands.AutonomousCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;

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

  private final RunShooterAtSpeedPID shooterAtSpeedPID = new RunShooterAtSpeedPID(m_Shooter, m_ControlBoard);
  private final RunConnector runConnector = new RunConnector(m_Indexer);
  private final ClimbExtendControl climbExtendControl = new ClimbExtendControl(m_Climber, m_Indexer, m_ControlBoard);
  private final RobotUp robotUp = new RobotUp(m_drivetrain, m_Climber, m_ControlBoard);
  private final FunnlerTest funnelTest = new FunnlerTest(m_Indexer);
  private final ConnectorAndIndex connectorAndIndex = new ConnectorAndIndex(m_Indexer);
  private final AutonomousCommand autonomousCommand = new AutonomousCommand(m_drivetrain, m_Shooter, m_Indexer, Constants.Drivetrain.timeDriveSpeed, Constants.Drivetrain.timeDriveTime);
  
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
      new funnelOnlyDefaultCommand(
        m_Indexer,
        m_ControlBoard)
    );
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    SmartDashboard.putData(new RunShooterAtSpeedPID(m_Shooter, m_ControlBoard));
    SmartDashboard.putData(new RunConnector(m_Indexer));
    SmartDashboard.putData(new FunnlerTest(m_Indexer));
    SmartDashboard.putData(new ShiftLol(m_Climber, m_drivetrain));
    SmartDashboard.putNumber("Axis", m_ControlBoard.getLeftClimbAxis());
    m_ControlBoard.getActivateLiftButton().and(m_ControlBoard.getClimbCheck1()).and(m_ControlBoard.getClimbCheck2()).whenActive(climbExtendControl);
    m_ControlBoard.getDeactivateLiftButton().cancelWhenPressed(climbExtendControl);
    m_ControlBoard.getActivateIntakeButton().whileHeld(new AutoIntake(m_Intake, m_ControlBoard, m_Indexer));
    m_ControlBoard.getActivateRobotUp().and(m_ControlBoard.getClimbCheck1()).and(m_ControlBoard.getClimbCheck2()).whenActive(robotUp);
    m_ControlBoard.getDisableRobotUp().cancelWhenPressed(robotUp);
    m_ControlBoard.getShooterButton().whileHeld(shooterAtSpeedPID);
    m_ControlBoard.getConnectorAndIndexer().whileHeld(connectorAndIndex);
    m_ControlBoard.runIndexer().whileHeld(new RunIndexer(m_Indexer, true));
    m_ControlBoard.getSlowDrive().whileHeld(new LineupDrive(m_drivetrain,m_ControlBoard));
    m_ControlBoard.backIndexer().whileHeld(new RunIndexer(m_Indexer,false));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new Simple3Ball(m_drivetrain, m_Shooter, m_Indexer);
  }
}
