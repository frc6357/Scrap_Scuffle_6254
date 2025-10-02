package frc.robot.subsystems;

import static frc.robot.Ports.climbPorts.kClimbMotor;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SC25Climb extends SubsystemBase
{
    SparkMax climbMotor;
    SparkMaxConfig climbConfig;

    // Intake constructor (used to initialize our motor object).
    public SC25Climb()
    {
        //Initialize our motor object.
        climbMotor = new SparkMax(kClimbMotor.ID, MotorType.kBrushless);
    }
    
    // Method that sets the intake speed to rollerSpeed (speed value).
    public void setClimbSpeed(double rollerSpeed)
    {
        climbMotor.set(rollerSpeed);
    }

    // Method to return motor speed, if requested.
    public double getMotorSpeed ()
    {
        return climbMotor.get();
    }
    
    //Stop motors
    public void stopIntake()
    {
        climbMotor.stopMotor();
    }

    public void periodic()
    {
    }

    public void testInit()
    {
    }
    
    public void testPeriodic()
    {
    }
}
