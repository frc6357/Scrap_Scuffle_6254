package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SC25Launcher;
import static frc.robot.Konstants.LauncherConstants.kStopSpeed;

public class LauncherStopCommand extends Command 
{
    private final SC25Launcher launcher;

    // Constructor for the intake subsystem eject command.
    public LauncherStopCommand(SC25Launcher launcher)
    {
        this.launcher = launcher;
    }

    // This method will run when the command becomes the highest priority in queue.
    public void initialize()
    {
        launcher.setLauncherSpeed(kStopSpeed);
    }

    // This method will run when the command is finished.
    public boolean isFinished()
    {
        return true;
    }
}