package frc.robot.bindings;

import java.util.Optional;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Ports;
import frc.robot.commands.LaunchCommand;
import frc.robot.commands.LauncherStopCommand;
import frc.robot.subsystems.SC25Launcher;

public class SC25LauncherBinder implements CommandBinder
{
    Optional<SC25Launcher> launcherSubsystem;

    Trigger launchOperatorButton;

    public  SC25LauncherBinder(Optional<SC25Launcher> launcherSubsystem)
    {
        this.launcherSubsystem = launcherSubsystem;

        this.launchOperatorButton = Ports.OperatorPorts.kLaunchScrap.button;
    }

    @Override
    public void bindButtons()
    {
        // If subsystem is present then this method will bind the buttons
        if (!launcherSubsystem.isPresent())
        {
            return;
        }
        SC25Launcher launcher = launcherSubsystem.get();

        launchOperatorButton.whileTrue(new LaunchCommand(launcher));
        launchOperatorButton.onFalse(new LauncherStopCommand(launcher));
    }
}
