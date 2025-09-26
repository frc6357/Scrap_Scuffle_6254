package frc.robot.bindings;

import java.util.Optional;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Ports;
import frc.robot.commands.IntakeEjectCommand;
import frc.robot.commands.IntakeRollerCommand;
import frc.robot.commands.IntakeStopCommand;
import frc.robot.subsystems.SC25Intake;

public class SC25IntakeBinder implements CommandBinder
{
    Optional<SC25Intake> intakeSubsystem;
    Trigger intakeDriverButton;
    Trigger intakeOperatorButton;
    Trigger ejectDriverButton;
    Trigger ejectOperatorButton;
    Trigger stopButton;

    // Constructor for the intake binder.
    public SC25IntakeBinder(Optional<SC25Intake> intakeSubsystem)
    {
        this.intakeSubsystem = intakeSubsystem;
        this.intakeDriverButton = Ports.DriverPorts.kIntake.button;
        this.intakeOperatorButton = Ports.OperatorPorts.kIntake.button;
        this.ejectDriverButton = Ports.DriverPorts.kEject.button;
        this.ejectOperatorButton = Ports.OperatorPorts.kEject.button;
        //this.stopButton = Ports.DriverPorts.kStop.button;
    }

    public void bindButtons()
    {
        // If the subsystem is present, then this method will bind the buttons.
        if (intakeSubsystem.isPresent())
        {
            SC25Intake intake = intakeSubsystem.get();
            intakeDriverButton.whileTrue(new IntakeRollerCommand(intake));
            intakeDriverButton.onFalse(new IntakeStopCommand(intake));
            ejectDriverButton.whileTrue(new IntakeEjectCommand(intake));
            ejectDriverButton.onFalse(new IntakeStopCommand(intake));

            intakeOperatorButton.whileTrue(new IntakeRollerCommand(intake));
            intakeOperatorButton.onFalse(new IntakeStopCommand(intake));
            ejectOperatorButton.whileTrue(new IntakeEjectCommand(intake));
            ejectOperatorButton.onFalse(new IntakeStopCommand(intake));
        }
    }
}