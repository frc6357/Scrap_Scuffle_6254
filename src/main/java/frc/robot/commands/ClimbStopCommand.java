package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SC25Climb;
import static frc.robot.Konstants.ClimbConstants.kClimbRollerStop;

public class ClimbStopCommand extends Command 
{
    private final SC25Climb climb;

    // Constructor for the intake subsystem eject command.
    public ClimbStopCommand(SC25Climb climb)
    {
        this.climb = climb;
    }

    // This method will run when the command becomes the highest priority in queue.
    public void initialize()
    {
        climb.setClimbSpeed(kClimbRollerStop);
    }

    // This method will run when the command is finished.
    public boolean isFinished()
    {
        return true;
    }
}
