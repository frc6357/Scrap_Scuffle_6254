package frc.robot.subsystems;

import static frc.robot.Ports.intakePorts.kIntakeMotor;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import au.grapplerobotics.LaserCan;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class SC25Intake extends SubsystemBase
{
    SparkMax intakeMotor;
    SparkMaxConfig intakeConfig;

    // Intake constructor (used to initialize our motor object).
    public SC25Intake()
    {
        //Initialize our motor object.
        intakeMotor = new SparkMax(kIntakeMotor.ID, MotorType.kBrushless);
    }
    
    // Method that sets the intake speed to rollerSpeed (speed value).
    public void setIntakeSpeed(double rollerSpeed)
    {
        intakeMotor.set(rollerSpeed);
    }

    // Method to return motor speed, if requested.
    public double getMotorSpeed ()
    {
        return intakeMotor.get();
    }
    
    //Stop motors
    public void stopIntake()
    {
        intakeMotor.stopMotor();
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
