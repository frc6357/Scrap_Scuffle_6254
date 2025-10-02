package frc.robot.bindings;

import java.util.Optional;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Ports;
import frc.robot.commands.ClimbStopCommand;
import frc.robot.commands.ClimbUpCommand;
import frc.robot.subsystems.SC25Climb;

public class SC25ClimbBinder implements CommandBinder
{
    Optional<SC25Climb> climbSubsystem;
    Trigger climbButton;

    // Constructor for the intake binder.
    public SC25ClimbBinder(Optional<SC25Climb> climbSubsystem)
    {
        this.climbSubsystem = climbSubsystem;
        this.climbButton = Ports.DriverPorts.kIntake.button;
    }

    public void bindButtons()
    {
        // If the subsystem is present, then this method will bind the buttons.
        if (climbSubsystem.isPresent())
        {
            SC25Climb climb = climbSubsystem.get();
            climbButton.whileTrue(new ClimbUpCommand(climb));
            climbButton.onFalse(new ClimbStopCommand(climb));
        }
    }
}
