package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SC25Intake;
import static frc.robot.Konstants.IntakeConstants.kRollerStop;

public class IntakeStopCommand extends Command 
{
    private final SC25Intake intake;

    // Constructor for the intake subsystem eject command.
    public IntakeStopCommand(SC25Intake intake)
    {
        this.intake = intake;
    }

    // This method will run when the command becomes the highest priority in queue.
    public void initialize()
    {
        intake.setIntakeSpeed(kRollerStop);
    }

    // This method will run when the command is finished.
    public boolean isFinished()
    {
        return true;
    }
}
