/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ControlBoard;
import frc.robot.Settings.Constants;

public class AutoIntake extends CommandBase {
  /**
   * Creates a new AutoIntake.
   */
  Intake m_Intake;
  ControlBoard m_ControlBoard;

  public AutoIntake(Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Intake = intake;
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (m_Intake.checkIntakeSolenoid() == false) {
      m_Intake.toggleIntakeSolenoid();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double intakeSpeed;

    intakeSpeed = m_ControlBoard.getIntakeAxis();

    m_Intake.StartIntakeMotor(intakeSpeed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Intake.toggleIntakeSolenoid();
    m_Intake.StopIntakeMotor();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
