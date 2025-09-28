package frc.robot.commands.commandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import static frc.robot.Konstants.LauncherConstants.kLauncherLeftSpeed;
import static frc.robot.Konstants.LauncherConstants.kLauncherRightSpeed;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.SC25Launcher;

public class LaunchScrapCommandGroup extends SequentialCommandGroup
{
    double LauncherRightSpeed;
    double LauncherLeftSpeed;

    public LaunchScrapCommandGroup(SC25Launcher launcher)
    {
        addCommands(
            new InstantCommand(() -> launcher.setScrapRampRate(), launcher),
            new InstantCommand(() -> launcher.setLauncherSpeed(kLauncherLeftSpeed, kLauncherRightSpeed), launcher),
            new WaitCommand(launcher.getCurrentRampRate())
        );
    }
    
}
