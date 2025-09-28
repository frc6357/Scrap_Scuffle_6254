package frc.robot.subsystems;

import static frc.robot.Konstants.LauncherConstants.kSpeedTolerance;
import static frc.robot.Konstants.LauncherConstants.kLauncherMotorConfigs;
import static frc.robot.Konstants.LauncherConstants.kLauncherRampDown;
import static frc.robot.Konstants.LauncherConstants.kLauncherRestRate;
import static frc.robot.Konstants.LauncherConstants.kLauncherQuickRate;
import static frc.robot.Ports.launcherPorts.kLeftLauncherMotor;
import static frc.robot.Ports.launcherPorts.kRightLauncherMotor;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkBaseConfigAccessor;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkBase.ResetMode;

public class SC25Launcher extends SubsystemBase
{
    // Create memory objects for both motors for public use.
    SparkMax leftMotor;
    SparkMax rightMotor;
    RelativeEncoder encoderL;
    RelativeEncoder encoderR;
    double leftTargetSpeed;
    double rightTargetSpeed;
    boolean running;
    private double currentRampRate = 0.0;

    // Constructor for launcher subsystem.
    public SC25Launcher()
    {
        //Initialize motor objects.
        leftMotor = new SparkMax(kLeftLauncherMotor.ID, MotorType.kBrushless);
        rightMotor = new SparkMax(kRightLauncherMotor.ID, MotorType.kBrushless);

        encoderL = leftMotor.getEncoder();
        encoderR = rightMotor.getEncoder();
    }

    public double getLeftTargetSpeed()
    {
        return leftTargetSpeed;
    }

    public double getRightTargetSpeed()
    {
        return rightTargetSpeed;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }

    public boolean getRunning()
    {
        return running;
    }  

    /**
     * Sets the speed of the launcher
     * @param speedLeft The speed to set for left. Value should be between -1.0 and 1.0.
     * @param speedRight The speed to set for right. Value should be between -1.0 and 1.0.
     */
    public void setLauncherSpeed (double speedLeft, double speedRight)
    {
        leftTargetSpeed = speedLeft;
        rightTargetSpeed = speedRight;

        rightMotor.set(speedRight);
        leftMotor.set(speedLeft);
    }

    //Return motor speeds
    public double getLeftMotorSpeed()
    {
        return leftMotor.get();
    }

    //Return motor speeds
    public double getRightMotorSpeed()
    {
        return rightMotor.get();
    }

    public boolean isFullSpeed()
    {
        return (Math.abs(getRightMotorSpeed() - getRightTargetSpeed()) < kSpeedTolerance) && (Math.abs(getLeftMotorSpeed() - getLeftTargetSpeed()) < kSpeedTolerance);
    }

    public void setScrapRampRate()
    {
        currentRampRate = 1.2; // This value MUST match the rate from Konstants.
        leftMotor.configure(kLauncherMotorConfigs, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        rightMotor.configure(kLauncherMotorConfigs, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    public void setRestingRampRate()
    {
        currentRampRate = 1.0; // This value MUST match the rate from Konstants.
        leftMotor.configure(kLauncherRestRate, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        rightMotor.configure(kLauncherRestRate, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }
    public void setQuickRampRate()
    {
        currentRampRate = 0.4; // This value MUST match the rate from Konstants.
        rightMotor.configure(kLauncherQuickRate, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        rightMotor.configure(kLauncherQuickRate, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    public void rampDown()
    {
        currentRampRate = 12.0; // This value MUST match the rate from Konstants.
        rightMotor.configure(kLauncherRampDown, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        rightMotor.configure(kLauncherRampDown, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    public double getCurrentRampRate()
    {
        return currentRampRate;
    }
    
    // Method to stop motors.
    public void stopLauncher()
    {
        leftMotor.stopMotor();
        rightMotor.stopMotor();
    }

    public void periodic()
    {
        
        SmartDashboard.putNumber("Left Launcher Speed", getLeftMotorSpeed());
        SmartDashboard.putNumber("Right Launcher Speed", getRightMotorSpeed());

        SmartDashboard.putNumber("Left Launcher Target Speed", getLeftTargetSpeed());
        SmartDashboard.putNumber("Right Launcher Target Speed", getRightTargetSpeed());
        SmartDashboard.putBoolean("Launcher Full Speed", isFullSpeed());

    }
    
    public void testPeriodic()
    {
    }
    
    public void testInit()
    {
    }
}

