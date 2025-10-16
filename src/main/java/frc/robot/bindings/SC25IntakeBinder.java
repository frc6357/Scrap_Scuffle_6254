package frc.robot.bindings;

import java.util.Optional;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Konstants.IntakeConstants.ArmPosition;
import frc.robot.Ports;
import frc.robot.commands.IntakeEjectCommand;
import frc.robot.commands.IntakeJoystickCommand;
import frc.robot.commands.IntakePosButtonCommand;
import frc.robot.commands.IntakeRollerCommand;
import frc.robot.commands.IntakeStopCommand;
import frc.robot.subsystems.SC25Intake;

public class SC25IntakeBinder implements CommandBinder
{
    Optional<SC25Intake> intakeSubsystem;

    // Intaking buttons.
    Trigger intakeDriverButton;
    Trigger intakeOperatorButton;
    Trigger ejectDriverButton;
    Trigger ejectOperatorButton;
    Trigger stopButton;

    // Arm buttons.
    Trigger zeroPositionButton;
    Trigger freightPositionButton;
    Trigger floorPositionButton;

    // Constructor for the intake binder.
    public SC25IntakeBinder(Optional<SC25Intake> intakeSubsystem)
    {
        this.intakeSubsystem = intakeSubsystem;
        this.intakeDriverButton = Ports.DriverPorts.kIntake.button;
        this.intakeOperatorButton = Ports.OperatorPorts.kIntake.button;
        this.ejectDriverButton = Ports.DriverPorts.kEject.button;
        this.ejectOperatorButton = Ports.OperatorPorts.kEject.button;

        this.zeroPositionButton = Ports.OperatorPorts.kZeroAngle.button;
        this.freightPositionButton = Ports.OperatorPorts.kFreightAngle.button;
        this.floorPositionButton = Ports.OperatorPorts.kFloorAngle.button;
    }

    @Override
    public void bindButtons()
    {
        // If the subsystem is present, then this method will bind the buttons.
        if (!intakeSubsystem.isPresent())
        {
            return;
        }
        SC25Intake intake = intakeSubsystem.get();

        zeroPositionButton.onTrue(Commands.sequence(new WaitCommand(0.5), new IntakePosButtonCommand(ArmPosition.kZeroPositionAngle, intake)));
        freightPositionButton.onTrue(Commands.sequence(new WaitCommand(0.5), new IntakePosButtonCommand(ArmPosition.kFreightAngle, intake)));
        floorPositionButton.onTrue(Commands.sequence(new WaitCommand(0.5), new IntakePosButtonCommand(ArmPosition.kFloorAngle, intake)));

        intakeDriverButton.whileTrue(new IntakeRollerCommand(intake));
        intakeDriverButton.onFalse(new IntakeStopCommand(intake));
        ejectDriverButton.whileTrue(new IntakeEjectCommand(intake));
        ejectDriverButton.onFalse(new IntakeStopCommand(intake));

        intakeOperatorButton.whileTrue(new IntakeRollerCommand(intake));
        intakeOperatorButton.onFalse(new IntakeStopCommand(intake));
        ejectOperatorButton.whileTrue(new IntakeEjectCommand(intake));
        ejectOperatorButton.onFalse(new IntakeStopCommand(intake));

        intake.setDefaultCommand(
            // Vertical movement of the arm is controlled by the Y axis of the right stick.
            // Up on joystick moving arm up and down on stick moving arm down.
            new IntakeJoystickCommand(
                () -> {return Ports.OperatorPorts.kIntakeAxis.getFilteredAxis();},
                intake));
    }
}