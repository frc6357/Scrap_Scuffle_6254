package frc.robot.subsystems;

import static frc.robot.Ports.drivePorts.*;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SC25Drivetrain extends SubsystemBase {

    private final SparkMax leftLeader   = new SparkMax(kFrontLeftDriveMotorPort.ID, MotorType.kBrushless);
    private final SparkMax leftFollower = new SparkMax(kRearLeftDriveMotorPort.ID,  MotorType.kBrushless);
    private final SparkMax rightLeader  = new SparkMax(kFrontRightDriveMotorPort.ID, MotorType.kBrushless);
    private final SparkMax rightFollower= new SparkMax(kRearRightDriveMotorPort.ID,  MotorType.kBrushless);

    private final DifferentialDrive diffDrive = new DifferentialDrive(leftLeader, rightLeader);

    public SC25Drivetrain() {
        // build a base config we can clone into each motor config
        SparkMaxConfig base = new SparkMaxConfig();
        base.smartCurrentLimit(30);           // keep motors safe, can tune higher later
        base.idleMode(IdleMode.kBrake);       // brake mode = robot resists motion when stopped
        base.openLoopRampRate(0.12);          // ramp inputs so it doesn’t jerk

        // configs for leaders
        SparkMaxConfig leftCfg = new SparkMaxConfig();
        leftCfg.apply(base);
        leftCfg.inverted(false);              // left side is normal

        SparkMaxConfig rightCfg = new SparkMaxConfig();
        rightCfg.apply(base);
        rightCfg.inverted(true);              // right side is mirrored

        // configs for followers
        SparkMaxConfig leftFollowCfg = new SparkMaxConfig();
        leftFollowCfg.apply(base);
        leftFollowCfg.follow(leftLeader, false);   // follow leader’s inversion

        SparkMaxConfig rightFollowCfg = new SparkMaxConfig();
        rightFollowCfg.apply(base);
        rightFollowCfg.follow(rightLeader, false); // follow leader’s inversion

        // push configs into actual controllers
        leftLeader.configure(leftCfg, null, null);
        rightLeader.configure(rightCfg, null, null);
        leftFollower.configure(leftFollowCfg, null, null);
        rightFollower.configure(rightFollowCfg, null, null);

        // diff drive setup so WPILib arcade/tank calls behave right
        diffDrive.setDeadband(0.06);          // ignore tiny joystick noise
        diffDrive.setSafetyEnabled(true);     // enables watchdog safety
    }

    // one stick controls forward + turning
    public void driveArcade(double forward, double omega) {
        diffDrive.arcadeDrive(forward, omega, true);
    }

    // tank drive = left stick for left, right stick for right
    public void driveTank(double left, double right) {
        diffDrive.tankDrive(left, right, true);
    }

    // kill all motion
    public void stop() {
        diffDrive.stopMotor();
    }
}
