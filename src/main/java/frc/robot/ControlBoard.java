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
    private IDriveController mDriveController;
    private IOperatorController mOperatorController;

    private ControlBoard() {
        mDriveController = TM.getInstance();

        mOperatorController = ButtonBoard.getInstance();

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

	public double getRightClimbAxis() {
		return mOperatorController.getRightClimbAxis();
	}

	public double getLeftClimbAxis() {
		return mOperatorController.getLeftClimbAxis();
	}

}