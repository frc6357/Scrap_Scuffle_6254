package frc.robot.bindings;

import java.util.Optional;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Ports;
import frc.robot.subsystems.SC25Launcher;

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

    @Override
    public void bindButtons()
    {
        // If subsystem is present then this method will bind the buttons
        if (!launcherSubsystem.isPresent())
        {
            return;
        }
        SC25Launcher launcher = launcherSubsystem.get();

        launchScrapButton.onTrue(new InstantCommand(() -> launcher.setRunning(true)));
        launchScrapButton.onFalse(new InstantCommand(() -> launcher.setRunning(false)));
    }
}
