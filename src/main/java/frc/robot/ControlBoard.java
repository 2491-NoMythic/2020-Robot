package frc.robot;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Controllers.IDriveController;
import frc.robot.Controllers.IOperatorController;
import frc.robot.manipulators.ButtonBoard;
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
        // TODO Auto-generated method stub
        return mOperatorController.getActivateRobotUp();
    }

    @Override
    public JoystickButton getDisableRobotUp() {
        // TODO Auto-generated method stub
        return mOperatorController.getDisableRobotUp();
    }

    @Override
    public boolean climbSaftey() {
        // TODO Auto-generated method stub
        return mOperatorController.climbSaftey();
    }

    @Override
    public JoystickButton getDeactivateLiftButton() {
        // TODO Auto-generated method stub
        return mOperatorController.getDeactivateLiftButton();
    }

    @Override
    public JoystickButton getClimbCheck1() {
        // TODO Auto-generated method stub
        return mOperatorController.getClimbCheck1();
    }

    @Override
    public JoystickButton getClimbCheck2() {
        // TODO Auto-generated method stub
        return mOperatorController.getClimbCheck2();
    }

    @Override
    public JoystickButton getShooterButton() {
        // TODO Auto-generated method stub
        return mOperatorController.getShooterButton();
    }

    @Override
    public JoystickButton getFunnelerAndIndexer() {
        // TODO Auto-generated method stub
        return mOperatorController.getFunnelerAndIndexer();
    }

    @Override
    public JoystickButton getConnectorAndIndexer() {
        // TODO Auto-generated method stub
        return mDriveController.getConnectorAndIndexer();
    }

    @Override
    public JoystickButton runIndexer() {
        // TODO Auto-generated method stub
        return mOperatorController.runIndexer();
    }


}