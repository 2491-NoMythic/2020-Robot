package frc.robot;

import frc.robot.Controllers.IDriveController;
import frc.robot.Controllers.IOperatorController;

public class ControlBoard implements IControlBoard{
    
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
}