
package frc.robot.bindings;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Ports;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeRollerCommand;
import frc.robot.subsystems.SC25Drivetrain;
import frc.robot.subsystems.SC25Intake;
import frc.robot.subsystems.SC25Launcher;

import static frc.robot.Ports.DriverPorts.*;

import java.util.Optional;

import static frc.robot.Konstants.OIConstants.*;

public class SC25DrivetrainBinder implements CommandBinder{

    Optional<SC25Drivetrain> driveSubsystem;

    Trigger slowMode;

    public SC25DrivetrainBinder(Optional<SC25Drivetrain> driveSubsystem) 
    {
        this.driveSubsystem = driveSubsystem;
        this.slowMode = Ports.DriverPorts.kSlowMode.button;
    }

    @Override
    public void bindButtons() 
    {
        if (!driveSubsystem.isPresent())
        {
            return;
        }
        SC25Drivetrain drive = driveSubsystem.get();

        drive.setDefaultCommand(new DriveCommand(drive, 
            () -> {return Ports.DriverPorts.kTranslationXPort.getFilteredAxis();},
            () -> {return Ports.DriverPorts.kTranslationYPort.getFilteredAxis();}));

        slowMode.whileTrue(new DriveCommand(drive,
            () -> Ports.DriverPorts.kTranslationXPort.getFilteredAxis() * kSlowModePercent,
            () -> Ports.DriverPorts.kTranslationYPort.getFilteredAxis() * kSlowModePercent));
    }
}
