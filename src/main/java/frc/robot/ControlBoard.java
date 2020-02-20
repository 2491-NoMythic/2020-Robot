package frc.robot;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Controllers.IDriveController;
import frc.robot.Controllers.IOperatorController;
import frc.robot.manipulators.ButtonBoard;
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
    }

    @Override
    public double getRawDriveAxis() {
        // TODO Auto-generated method stub
        return mDriveController.getRawDriveAxis();
    }

    @Override
    public double getDriveAxisDeadzone() {
        // TODO Auto-generated method stub
        return mDriveController.getDriveAxisDeadzone();
    }

    @Override
    public double getRawTurnAxis() {
        // TODO Auto-generated method stub
        return mDriveController.getRawTurnAxis();
    }

    @Override
    public JoystickButton returnIntakeButton() {
        // TODO Auto-generated method stub
        return mOperatorController.returnIntakeButton();
    }

    @Override
    public JoystickButton returnRevShooterButton() {
        return mOperatorController.returnRevShooterButton();
    }
}