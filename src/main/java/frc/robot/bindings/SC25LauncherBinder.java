package frc.robot.bindings;

import java.util.Optional;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Ports;
import frc.robot.commands.commandGroups.LaunchScrapCommandGroup;
import frc.robot.preferences.Pref;
import frc.robot.preferences.SKPreferences;
import frc.robot.subsystems.SC25Launcher;
import frc.robot.utils.filters.DeadbandFilter;

public class SC25LauncherBinder implements CommandBinder
{
    Optional<SC25Launcher> launcherSubsystem;

    Trigger intakeDriverButton;
    Trigger intakeOperatorButton;
    Trigger launchScrapButton;


    public  SC25LauncherBinder(Optional<SC25Launcher> launcherSubsystem)
    {
        this.launcherSubsystem = launcherSubsystem;

        this.intakeDriverButton = Ports.DriverPorts.kIntake.button;
        this.intakeOperatorButton = Ports.OperatorPorts.kIntake.button;
        this.launchScrapButton = Ports.OperatorPorts.kLaunchScrap.button;
        //readyShoot = kDriverShoot.button;
    }

    public void bindButtons()
    {
        // If subsystem is present then this method will bind the buttons
        if (launcherSubsystem.isPresent())
        {
            SC25Launcher launcher = launcherSubsystem.get();

            // Button to launch scrap.
            launchScrapButton.onTrue(new LaunchScrapCommandGroup(launcher));
            launchScrapButton.onFalse(new InstantCommand(() -> {
                launcher.rampDown();
                launcher.setLauncherSpeed(0.0);
            }, launcher));
            
            if (launcherSubsystem.isPresent())
            {
                launchScrapButton.onTrue(new InstantCommand(() -> launcher.setRunning(true)));
                launchScrapButton.onFalse(new InstantCommand(() -> launcher.setRunning(false)));
            }
        }
    }
}
