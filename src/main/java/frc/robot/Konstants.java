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
        public static final double kRollerSpeed = 0.6;
        public static final double kRollerSlowSpeed = 0.50;
        public static final double kRollerSuperSpeed = 0.8;
        public static final double kRollerStop = 0;

        /* Current Limits */
        public static final CurrentLimitsConfigs kIntakeCurrentLimitsConfigs = 
          new CurrentLimitsConfigs() // Limits in Amps; time in seconds
              .withStatorCurrentLimitEnable(true)
              .withStatorCurrentLimit(100)
              .withSupplyCurrentLimitEnable(true)
              .withSupplyCurrentLimit(80)
              .withSupplyCurrentLowerLimit(50)
              .withSupplyCurrentLowerTime(0.3);
    }

    // Constants for the launcher subsystem.
    public static final class LauncherConstants
    {
        public static final double kLauncherLeftSpeed = 0.5; 
        public static final double kLauncherRightSpeed = 0.5; 
        
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


    // Constants for the climb subsystem.
    public static final class ClimbConstants
    {
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
        public static final double kSlowModePercent = 0.3;
        public static final double kSlowModeRotationPercent = 0.5;
        public static final double kAccelLimit = 2;
    }   
    public static final String kCANivoreName = "SwerveCANivore";

    // The file that is used for system instantiation at runtime
    public static final String SUBSYSTEMFILE = "Subsystems.json";
}