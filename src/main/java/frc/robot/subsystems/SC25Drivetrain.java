package frc.robot.subsystems;

import static frc.robot.Ports.drivePorts.kLeftFollower;
import static frc.robot.Ports.drivePorts.kLeftLeader;
import static frc.robot.Ports.drivePorts.kRightFollower;
import static frc.robot.Ports.drivePorts.kRightLeader;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SC25Drivetrain extends SubsystemBase {

    private final SparkMax leftLeader;
    private final SparkMax leftFollower;
    private final SparkMax rightLeader;
    private final SparkMax rightFollower;

    private final DifferentialDrive diffDrive;

    public SC25Drivetrain() 
    {
        leftLeader = new SparkMax(kLeftLeader.ID, MotorType.kBrushless);
        leftFollower = new SparkMax(kLeftFollower.ID,  MotorType.kBrushless);
        rightLeader  = new SparkMax(kRightLeader.ID, MotorType.kBrushless);
        rightFollower = new SparkMax(kRightFollower.ID,  MotorType.kBrushless);

        diffDrive = new DifferentialDrive(leftLeader, rightLeader);

        // build a base config we can clone into each motor config
        SparkMaxConfig base = new SparkMaxConfig();
        base.smartCurrentLimit(30);
        base.idleMode(IdleMode.kBrake);
        base.openLoopRampRate(0.12);

        // Set configuration to follow leader and then apply it to corresponding
        // follower. Resetting in case a new controller is swapped
        // in and persisting in case of a controller reset due to breaker trip
        base.follow(leftLeader);
        leftFollower.configure(base, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        base.follow(rightLeader);
        rightFollower.configure(base, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        // Remove following, then apply config to right leader
        base.disableFollowerMode();
        rightLeader.configure(base, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        // Set conifg to inverted and then apply to left leader. Set Left side inverted
        // so that postive values drive both sides forward
        base.inverted(true);
        leftLeader.configure(base, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        // diff drive setup so WPILib arcade/tank calls behave right
        diffDrive.setDeadband(0.06);          // ignore tiny joystick noise
        //diffDrive.setSafetyEnabled(true);     // enables watchdog safety
    }

    // one stick controls forward + turning
    public void driveArcade(double xSpeed, double zRotation, boolean squared) 
    {
        diffDrive.arcadeDrive(xSpeed, zRotation, squared);
    }

    // kill all motion
    public void stop() {
        diffDrive.stopMotor();
    }
}
