package frc.robot;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.KilogramSquareMeters;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Volts;
import java.util.HashMap;
import java.util.List;
import com.ctre.phoenix6.CANBus;    
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.pathplanner.lib.config.PIDConstants;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;
import com.pathplanner.lib.controllers.PathFollowingController;
import com.pathplanner.lib.config.PIDConstants;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.LimitSwitchConfig.Type;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Angle;

@SuppressWarnings("unused")
public final class Konstants
{
    // Constants for the intake subsystem.
    public static final class IntakeConstants
    {
        public static enum ArmPosition
        {
            /** Set the height to reach the bottom */
            kZeroPositionAngle(0), //TODO Tune this height
            /** Set the height to reach the bottom */
            kFreightAngle(0), //TODO Tune this height
            /** Set the height to reach the bottom */
            kFloorAngle(0); //TODO Tune this height
        
            public final double angle;
            ArmPosition(double angle)
            {
                this.angle = angle;
            }
        }

        /* PID values for arm motion control */
        public static final double kArmP = 0.3;  //0.3
        public static final double kArmI = 0.0; //0.0002
        public static final double kArmD = 0.0; //2.1
        public static final double kArmV = 0.0; // 1/5767
        public static final double kArmFF = 0.0;

        /* Maximum motion limits for motion control */
        public static final double kArmCruiseVel = .15; // rot/sec
        public static final double kArmTargetAccel = .65; // rot/sec^2
        public static final double kArmTargetJerk = 4.5; // rot/sec^3
        public static final double kArmTolerance = 2.5; // degrees

        public static final double kJoystickChange   = 0.05; // Manual setpoint value for units from 0.0 - 1.0 moved per second
        public static final double kJoystickDeadband = 0.3;  // Manual arm movement axis deadband
        public static final boolean kJoystickReversed = true;

        /* Values for default motor speed*/
        public static final double kArmSpeed = 0.3; //.1 // rot/sec; often only used in Joystick control; Button control uses PID
        public static final double kRollerSpeed = 0.7;
        public static final double kRollerSlowSpeed = 0.50;
        public static final double kRollerSuperSpeed = 0.8;
        public static final double kRollerStop = 0;

        /* Current Limits */
        public static final CurrentLimitsConfigs kIntakeCurrentLimitsConfigs = 
          new CurrentLimitsConfigs() // Limits in Amps; time in seconds
              .withStatorCurrentLimitEnable(true)
              .withStatorCurrentLimit(50)
              .withSupplyCurrentLimitEnable(true)
              .withSupplyCurrentLimit(40)
              .withSupplyCurrentLowerLimit(20)
              .withSupplyCurrentLowerTime(0.3);
    }

    // Constants for the launcher subsystem.
    public static final class LauncherConstants
    {
        public static final double kLauncherSpeed = -0.7; 
        public static final double kStopSpeed = 0.0;
        
        public static final double kSpeedTolerance = 0.03;
        public static final SparkBaseConfig kLauncherMotorConfigs = 
            new SparkMaxConfig()
            .openLoopRampRate(1.2);
        public static final SparkBaseConfig kLauncherRampDown = 
            new SparkMaxConfig()
            .openLoopRampRate(12.0);
        public static final SparkBaseConfig kLauncherRestRate = 
            new SparkMaxConfig()
            .openLoopRampRate(1.0);
        public static final SparkBaseConfig kLauncherQuickRate = 
            new SparkMaxConfig()
            .openLoopRampRate(0.4);
    }

    public static final class ExampleConstants
    {
        //percentage based where 1.0 is max power and 0.0 is minimum
        public static final double kExampleSpeed = 0.5;
    }

    // Constants that are used when defining filters for controllers.
    public static final class OIConstants
    {
        // Controller constraints
        public static final double kDriveCoeff = 1;
        public static final double kRotationCoeff = 1;
        public static final double kJoystickDeadband = 0.15;
        public static final double kSlowModePercent = 0.6;
        public static final double kSlowModeRotationPercent = 0.5;
        public static final double kAccelLimit = 2;
    }   

    // The file that is used for system instantiation at runtime
    public static final String SUBSYSTEMFILE = "Subsystems.json";
}