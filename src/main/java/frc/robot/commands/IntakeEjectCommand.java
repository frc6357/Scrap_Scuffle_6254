package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SC25Intake;
import static frc.robot.Konstants.IntakeConstants.kRollerSpeed;

public class IntakeEjectCommand extends Command 
{
    private final SC25Intake intake;

    // Constructor for the intake subsystem eject command.
    public IntakeEjectCommand(SC25Intake intake)
    {
        this.intake = intake;
    }

    // This method will run when the command becomes the highest priority in queue.
    public void initialize()
    {
        intake.setIntakeSpeed(-kRollerSpeed);
    }

    // This method will run when the command is finished.
    public boolean isFinished()
    {
        return true;
    }
}