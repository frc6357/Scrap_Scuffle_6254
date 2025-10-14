package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SC25Intake;

import edu.wpi.first.wpilibj.DriverStation;

import frc.robot.Konstants.IntakeConstants.ArmPosition;

public class IntakePosButtonCommand extends Command
{
    private final SC25Intake intake;
    private final ArmPosition angle;


    public IntakePosButtonCommand(ArmPosition angle, SC25Intake intake)
    {
        this.angle = angle;
        this.intake = intake;

        addRequirements(intake);
    }

    @Override
    public void initialize()
    {
        intake.setTargetAngle(angle);
        intake.isRunning = true;
    }

    @Override
    public boolean isFinished()
    {
        if(DriverStation.isAutonomousEnabled())
        {
            if(intake.isArmAtTargetPosition())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        
        else
        {
            if(intake.isArmAtTargetPosition())
            {
                
                return true;
            }
            else
            {
                return false;
            }
        }

    }
}
