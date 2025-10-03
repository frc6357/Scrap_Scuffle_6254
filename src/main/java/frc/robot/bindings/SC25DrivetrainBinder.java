package frc.robot.bindings;

import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.SC25Drivetrain;

import static frc.robot.Ports.DriverPorts.*;   
import static frc.robot.Konstants.OIConstants.*;

public class SC25DrivetrainBinder {

    private final SC25Drivetrain drive;

    public SC25DrivetrainBinder(SC25Drivetrain drive) {
        this.drive = drive;

        drive.setDefaultCommand(new RunCommand(() -> {
            double fwd = -kDriver.getRawAxis(1);  
            double rot =  kDriver.getRawAxis(4);  

            // apply deadband before scaling so tiny noise doesn’t move the robot
            fwd = (Math.abs(fwd) > kJoystickDeadband) ? fwd * kDriveCoeff : 0.0;
            rot = (Math.abs(rot) > kJoystickDeadband) ? rot * kRotationCoeff : 0.0;

            // slow mode toggle comes from Ports (LB bumper)
            // when pressed, drop both linear and rotational speed multipliers
            double slow    = kSlowMode.getAsBoolean() ? kSlowModePercent : 1.0;
            double slowRot = kSlowMode.getAsBoolean() ? kSlowModeRotationPercent : 1.0;

            // final drive call
            drive.driveArcade(fwd * slow, rot * slowRot);
        }, drive));
    }
}