/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.manipulators;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Settings.Constants.ShooterSpeeds;
import frc.robot.Controllers.IOperatorController;
import frc.robot.Settings.Constants;
import frc.robot.Settings.Variables;

/**
 * Add your docs here.
 */
public class PS4 implements IOperatorController {

    private final Joystick m_joystick;
    private static PS4 m_Instance = null;
    private JoystickButton activateIntakeButton, activateClimbButton, deactivateClimbButton, activateRobotUpButton,
            deactivateRobotUpButton, climbSaftey1, climbSaftey2, funnelerAndIndexer, shooterButton, runIndexer, slowModeButton;

    public static PS4 getInstance() {
        if (m_Instance == null) {
            m_Instance = new PS4();
        }
        return m_Instance;
    }

    private PS4() {
        m_joystick = new Joystick(Constants.Controller.opertatorControllerID);
        activateIntakeButton = new JoystickButton(m_joystick, Constants.Controller.PS4.activateIntakeButtonID);
        activateClimbButton = new JoystickButton(m_joystick, Constants.Controller.PS4.activateClimberButtonID);
        deactivateClimbButton = new JoystickButton(m_joystick, Constants.Controller.PS4.deactivateClimbButtonID);
        activateRobotUpButton = new JoystickButton(m_joystick, Constants.Controller.PS4.activateRobotUpButtonID);
        deactivateRobotUpButton = new JoystickButton(m_joystick, Constants.Controller.PS4.deactivateRobotUpButtonID);
        climbSaftey1 = new JoystickButton(m_joystick, Constants.Controller.PS4.climbSaftey1ID);
        climbSaftey2 = new JoystickButton(m_joystick, Constants.Controller.PS4.climbSaftey2ID);
        funnelerAndIndexer = new JoystickButton(m_joystick, Constants.Controller.PS4.funnelerAndIndexerID);
        shooterButton = new JoystickButton(m_joystick, Constants.Controller.PS4.shooterButtonID);
        runIndexer = new JoystickButton(m_joystick, Constants.Controller.PS4.runIndexerButtonID);
        slowModeButton = new JoystickButton(m_joystick, Constants.Controller.PS4.slowModeButtonID);
    }

    @Override
    public JoystickButton getActivateIntakeButton() {
        return activateIntakeButton;
    }

    @Override
    public double getIntakeAxis() {
        return m_joystick.getRawAxis(Constants.Intake.intakeAxisID);
    }

    @Override
    public double getLeftClimbAxis() {
        return m_joystick.getRawAxis(Constants.Climber.rightAxisID);
    }

    @Override
    public JoystickButton getActivateLiftButton() {
        return activateClimbButton;
    }

    @Override
    public JoystickButton getActivateRobotUp() {
        // TODO Auto-generated method stub
        return activateRobotUpButton;
    }

    @Override
    public JoystickButton getDisableRobotUp() {
        // TODO Auto-generated method stub
        return deactivateRobotUpButton;
    }

    @Override
    public boolean climbSaftey() {
        // TODO Auto-generated method stub
        return climbSaftey1.get() && climbSaftey2.get();
    }

    @Override
    public JoystickButton getDeactivateLiftButton() {
        // TODO Auto-generated method stub
        return deactivateClimbButton;
    }

    @Override
    public JoystickButton getClimbCheck1() {
        // TODO Auto-generated method stub
        return climbSaftey1;
    }

    @Override
    public JoystickButton getClimbCheck2() {
        // TODO Auto-generated method stub
        return climbSaftey2;
    }

    @Override
    public JoystickButton getShooterButton() {
        // TODO Auto-generated method stub
        return shooterButton;
    }

    @Override
    public JoystickButton getFunnelerAndIndexer() {
        // TODO Auto-generated method stub
        return funnelerAndIndexer;
    }

    @Override
    public JoystickButton runIndexer() {
        // TODO Auto-generated method stub
        return runIndexer;
    }

    @Override
    public JoystickButton getSlowModeButton() {
        // TODO Auto-generated method stub
        return slowModeButton;
    }

    @Override
    public void setShooterSpeed() {
        if (m_joystick.getPOV() == 0){
            Variables.Shooter.shooterSpeed = ShooterSpeeds.lowSpeed;
    }   else if (m_joystick.getPOV() == 270){
            Variables.Shooter.shooterSpeed = ShooterSpeeds.midSpeed;
    }   else if (m_joystick.getPOV() == 180){
            Variables.Shooter.shooterSpeed = ShooterSpeeds.highSpeed;
    }   else {
            Variables.Shooter.shooterSpeed = ShooterSpeeds.stop;
    }
    }

    @Override
    public double getShooterSpeed() {
        // TODO Auto-generated method stub
        return getShooterSpeed();
    }

}