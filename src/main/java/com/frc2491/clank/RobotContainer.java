/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frc2491.clank;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.frc2491.clank.commands.drivetrain.Drive;
import com.frc2491.clank.commands.drivetrain.LineupDrive;
import com.frc2491.clank.commands.drivetrain.Rotate;
import com.frc2491.clank.commands.intake.AutoIntake;
import com.frc2491.clank.commands.ConnectorAndIndex;
import com.frc2491.clank.commands.DefaultIntakeRoutine;
import com.frc2491.clank.commands.FunnlerTest;
import com.frc2491.clank.commands.RunIndexer;
import com.frc2491.clank.commands.ShiftLol;
import com.frc2491.clank.commands.Simple3Ball;
import com.frc2491.clank.commands.funnelOnlyDefaultCommand;
import com.frc2491.clank.commands.climber.ClimbExtendControl;
import com.frc2491.clank.commands.climber.RobotUp;
import com.frc2491.clank.commands.shooter.RunConnector;
import com.frc2491.clank.commands.shooter.RunFullSpeed;
import com.frc2491.clank.commands.shooter.RunShooterAtSpeedPID;
import com.frc2491.clank.Settings.Constants;
import com.frc2491.clank.commands.AutonomousCommand;
import com.frc2491.clank.subsystems.Climber;
import com.frc2491.clank.subsystems.Drivetrain;
import com.frc2491.clank.subsystems.Shooter;
import com.frc2491.clank.subsystems.Indexer;
import com.frc2491.clank.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined.

  /**
   * Here is where we create the instances of the subsystems. These static instances reflect the physical systems on the
   * robot. When trying to interface with the physical systems of the robot, these need to be used.
   */
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Shooter m_Shooter = new Shooter();
  private final Indexer m_Indexer = new Indexer();
  private final Intake m_Intake = new Intake();
  private final Climber m_Climber = new Climber();

  /**
   * Here is where we get the current instace of the controlboard that we are using. There can only be one instance of
   * the controlboard ever, so the control board in this class is a refrence to the singleton controlboard.
   */
  private final ControlBoard m_ControlBoard = ControlBoard.getInstance();

  /**
   * Here is where we declare instances of the commands that we want to run. Notice that these will only run the
   * instanciation of the class once.
   */
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
    //Data that is put on the smart dashboard will appear as a UI element.
    SmartDashboard.putData(new RunShooterAtSpeedPID(m_Shooter, m_ControlBoard));
    SmartDashboard.putData(new RunConnector(m_Indexer));
    SmartDashboard.putData(new FunnlerTest(m_Indexer));
    SmartDashboard.putData(new ShiftLol(m_Climber, m_drivetrain));
    SmartDashboard.putNumber("Axis", m_ControlBoard.getLeftClimbAxis());
    //Button assignments
    //.and is used to create the safteys. Note that in current form safteys are not neccesary for turining off the system.
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
    SmartDashboard.putData("TurnUp", new Rotate(m_drivetrain, 30));
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
