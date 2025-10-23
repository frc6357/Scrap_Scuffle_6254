package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.Rotations;


import static frc.robot.Ports.intakePorts.kArmMotor;
import static frc.robot.Ports.intakePorts.kIntakeMotor;
import static frc.robot.Konstants.IntakeConstants.*;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Konstants.IntakeConstants.ArmPosition;

public class SC25Intake extends SubsystemBase
{
    final double motorRatio = 12.5;
    final int gear1Rotation = 1;
    final int gear2Rotation = 1;
    final int degrees = 360;

    SparkMax intakeMotor;
    SparkMax armMotor;

    SparkMaxConfig armConfig;
    SparkClosedLoopController mPID;

    double mTargetAngle;
    double mCurrentAngle;

    RelativeEncoder mEncoder;

    ArmFeedforward armFeedforward;

    public boolean isRunning;

    // Intake constructor (used to initialize our motor object).
    public SC25Intake()
    {
        //Initialize our motor object.
        intakeMotor = new SparkMax(kIntakeMotor.ID, MotorType.kBrushless);
        armMotor = new SparkMax(kArmMotor.ID, MotorType.kBrushless);

        armConfig = new SparkMaxConfig();

        armConfig = new SparkMaxConfig();
        armConfig.closedLoop
            .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
            .p(kArmP)
            .i(kArmI)
            .d(kArmD)
            .outputRange(-1, 1)
            .velocityFF(kArmFF)
            .maxMotion.maxVelocity(kArmV);
        armConfig.closedLoop.maxMotion
            .maxAcceleration(300)
            .maxVelocity(150)
            .allowedClosedLoopError(0.1);
        armConfig
            .idleMode(IdleMode.kBrake)
            .smartCurrentLimit(40);
        
        mPID = armMotor.getClosedLoopController();
        mEncoder = armMotor.getEncoder();

        mTargetAngle = 0.0;
        mCurrentAngle = 0.0;

        mEncoder.setPosition(0);
    }

    /*
     *  ARM (Not intaking related)
     */
    
    public void resetEncoder()
    {
        mEncoder.setPosition(0);
    }

    public void setTargetAngle(ArmPosition position)
    {
        setTargetAngle(position.angle);
    }

    public double getArmPosition()
    {
        //Set conversion factor
        double motorRotations = mEncoder.getPosition();
        double angle = motorRotations / motorRatio * degrees;
        return angle;
    }

    private void setTargetAngle(double angleDegrees)
    {
        mTargetAngle = angleDegrees;
        SmartDashboard.putNumber("EffectorTargetAngle", mTargetAngle);
        SmartDashboard.putNumber("EffectorCurrentAngle", getArmPosition());

        double motorRotations = angleDegrees * motorRatio / degrees;

        //Come back and change this, need fraction for Encoder Rotations in place of angle
        double targetAngleRadians = 
            Degrees.of(angleDegrees)
            .plus(Degrees.of(90))
            .in(Radians);
        mPID.setReference(motorRotations, ControlType.kPosition,ClosedLoopSlot.kSlot0);
    }

    public double getTargetArmPosition()
    {
       return mTargetAngle;
    }

    public boolean isArmAtTargetPosition()
    {
        if (DriverStation.isTeleop())
        {
            return Math.abs(getTargetArmPosition() - getArmPosition()) < kArmTolerance;
        } 
        else 
        {
            return Math.abs(getTargetArmPosition() - getArmPosition()) < Rotations.of(0.1).in(Degrees);
        }
    }

    // Hold the intake in plce at its current angle.
    public void hold()
    { 
        if(isRunning == true)
        {
            mTargetAngle = getArmPosition();
            isRunning = false;
        }
        setTargetAngle(mTargetAngle);   
    }

    public void runArm(double armspeed)
    {
        double angleDegrees = getArmPosition();
        double targetAngleRadians = 
            Degrees.of(angleDegrees)
            .plus(Degrees.of(90))
            .in(Radians);
        
        double offset = .05 * Math.cos(targetAngleRadians);
        armMotor.set(armspeed + offset);
    }

    // Method to deploy the intake outward at the start of a match.
    public void leave() 
    {
        setTargetAngle(ArmPosition.kZeroPositionAngle.angle);
    }

    /*
     *  INTAKING (Not arm related)
     */

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
