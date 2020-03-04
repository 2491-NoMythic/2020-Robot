package frc.robot;


import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Controllers.IDriveController;
import frc.robot.Controllers.IOperatorController;
import frc.robot.Settings.Constants.ShooterSpeeds;
import frc.robot.manipulators.PS4;
import frc.robot.manipulators.TM;

public class ControlBoard implements IControlBoard {

    private static ControlBoard mInstance = null;

    // Creates a new control board
    public static ControlBoard getInstance() {
        if (mInstance == null) {
            mInstance = new ControlBoard();
        }
        return mInstance;
    }

    // Creates controller interfaces
    private final IDriveController mDriveController;
    private final IOperatorController mOperatorController;

    private ControlBoard() {
        mDriveController = TM.getInstance();

        mOperatorController = PS4.getInstance();
    }

    @Override
    public double getRawDriveAxis() {
        return mDriveController.getRawDriveAxis();
    }

    @Override
    public double getDriveAxisDeadzone() {
        return mDriveController.getDriveAxisDeadzone();
    }

    @Override
    public double getRawTurnAxis() {
        return mDriveController.getRawTurnAxis();
    }

    @Override
    public JoystickButton getActivateIntakeButton() {
        return mOperatorController.getActivateIntakeButton();
    }

    public double getIntakeAxis() {
        return mOperatorController.getIntakeAxis();
    }

    public double getLeftClimbAxis() {
        return mOperatorController.getLeftClimbAxis();
    }

    @Override
    public JoystickButton getActivateLiftButton() {
        return mOperatorController.getActivateLiftButton();
    }

    @Override
    public double getHorizontalClimbAxis() {
        return mDriveController.getHorizontalClimbAxis();
    }

    @Override
    public JoystickButton getActivateRobotUp() {
        return mOperatorController.getActivateRobotUp();
    }

    @Override
    public JoystickButton getDisableRobotUp() {
        return mOperatorController.getDisableRobotUp();
    }

    @Override
    public boolean climbSaftey() {
        return mOperatorController.climbSaftey();
    }

    @Override
    public JoystickButton getDeactivateLiftButton() {
        return mOperatorController.getDeactivateLiftButton();
    }

    @Override
    public JoystickButton getClimbCheck1() {
        return mOperatorController.getClimbCheck1();
    }

    @Override
    public JoystickButton getClimbCheck2() {
        return mOperatorController.getClimbCheck2();
    }

    @Override
    public JoystickButton getShooterButton() {
        return mOperatorController.getShooterButton();
    }

    @Override
    public JoystickButton getFunnelerAndIndexer() {
        return mOperatorController.getFunnelerAndIndexer();
    }

    @Override
    public JoystickButton getConnectorAndIndexer() {
        return mDriveController.getConnectorAndIndexer();
    }

    @Override
    public JoystickButton runIndexer() {
        return mOperatorController.runIndexer();
    }

    @Override
    public JoystickButton getSlowDrive() {
        // TODO Auto-generated method stub
        return mDriveController.getSlowDrive();
    }

    @Override
    public double getShooterSpeed() {
        // TODO Auto-generated method stub
        return mOperatorController.getShooterSpeed();
    }
    @Override
    public JoystickButton backIndexer() {
        // TODO Auto-generated method stub
        return mOperatorController.backIndexer();
    }

    @Override
    public void setShooterSpeed() {
        // TODO Auto-generated method stub
    }



}