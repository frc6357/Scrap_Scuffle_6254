package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SC25Intake;
import static frc.robot.Konstants.IntakeConstants.kArmSpeed;

// import com.revrobotics.RelativeEncoder;

public class IntakeJoystickCommand extends Command {
    private final SC25Intake intake;
    private final Supplier<Double> joystickInput;


    public IntakeJoystickCommand(Supplier<Double> setpointChange, SC25Intake intake)
    {
        this.joystickInput = setpointChange;
        this.intake = intake;

        addRequirements(intake);
    }

    @Override 
    public void initialize(){}

    @Override
    public void execute()
    {
       if (joystickInput.get() > 0)
       {
        double armspeed = kArmSpeed;
        double armdividend = joystickInput.get();
        armspeed = armspeed * armdividend;
         intake.runArm(armspeed);
         intake.isRunning = true;         
       }

       else if (joystickInput.get() < 0)
       {
        double armdividend = joystickInput.get();
        double armspeed = -kArmSpeed;
        armspeed = armspeed * armdividend;
        intake.runArm(-armspeed);
        intake.isRunning = true;
       }
       else
       {
        intake.hold();
       }
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished()
    {
        return false;
    }
    
}
