package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.utils.SKTrigger;
import frc.robot.utils.filters.FilteredXboxController;
import static frc.robot.Konstants.IntakeConstants.kJoystickDeadband;
import static frc.robot.utils.SKTrigger.INPUT_TYPE.AXIS;
import static frc.robot.utils.SKTrigger.INPUT_TYPE.BUTTON;
import frc.robot.utils.CANPort;
import frc.robot.utils.filters.DeadbandFilter;
import frc.robot.utils.filters.FilteredAxis;
import static edu.wpi.first.wpilibj.XboxController.Button.*;
import static edu.wpi.first.wpilibj.XboxController.Axis.*;
// import static frc.robot.utils.SKTrigger.INPUT_TYPE.POV;

public class Ports
{
    // Ports associated with the driver controller.
    public static class DriverPorts
    {
        // Driver Controller set to Xbox Controller.
        public static final GenericHID kDriver = new FilteredXboxController(0).getHID();
        
        // Drive
        public static final FilteredAxis kTranslationXPort = new FilteredAxis(() -> kDriver.getRawAxis(kLeftY.value));
        public static final FilteredAxis kTranslationYPort = new FilteredAxis(() -> kDriver.getRawAxis(kLeftX.value));
        public static final FilteredAxis kRotationPort = new FilteredAxis(() -> kDriver.getRawAxis(kRightX.value));
        public static final SKTrigger kResetGyroPos = new SKTrigger(kDriver, kLeftStick.value, BUTTON);
        public static final SKTrigger kRobotCentricMode = new SKTrigger(kDriver, kRightBumper.value, BUTTON);
        public static final SKTrigger kSlowMode = new SKTrigger(kDriver, kLeftBumper.value, BUTTON);
        
        // Intake
        public static final SKTrigger kIntake = new SKTrigger(kDriver, kRightTrigger.value, AXIS);
        public static final SKTrigger kEject = new SKTrigger(kDriver, kLeftTrigger.value, AXIS);
    }

    // Ports associated with the operator controller.
    public static class OperatorPorts
    {
        // Operator Controller set to Xbox Controller.
        public static final GenericHID kOperator = new FilteredXboxController(1).getHID();

        // Intake
        public static final SKTrigger kIntake = new SKTrigger(kOperator, kRightTrigger.value, AXIS);
        public static final SKTrigger kEject = new SKTrigger(kOperator, kLeftTrigger.value, AXIS);

        // Intake position
        public static final FilteredAxis kIntakeAxis = new FilteredAxis(() -> kOperator.getRawAxis(kRightY.value), new DeadbandFilter(kJoystickDeadband));
        public static final SKTrigger kFloorAngle = new SKTrigger(kOperator, kA.value, BUTTON);
        public static final SKTrigger kZeroAngle = new SKTrigger(kOperator, kY.value, BUTTON);
        public static final SKTrigger kFreightAngle = new SKTrigger(kOperator, kX.value, BUTTON);

        // Launcher
        public static final SKTrigger kLaunchScrap = new SKTrigger(kOperator, kRightBumper.value, BUTTON);
    }

    /* 
     * Assign CAN ports to drive motors.
     * Currently made for a swerve drive, to be changed once we impliment tank drive.
    */
    public static class drivePorts
    {
        private static final String busName = "";

        // CAN IDs for the drive motors on the swerve module.
        public static final CANPort kLeftLeader = new CANPort(10, busName);
        public static final CANPort kLeftFollower = new CANPort(11, busName);
        public static final CANPort kRightLeader = new CANPort(12, busName);
        public static final CANPort kRightFollower = new CANPort(13, busName);

        // CAN ID for IMU.
        //public static final CANPort kPigeonPort = new CANPort(25, busName);
    }

    //Assign CAN ports to intake motors.
    public static class intakePorts 
    {
        private static final String busName = "";
        public static final CANPort kIntakeMotor = new CANPort(40, busName);
        public static final CANPort kArmMotor = new CANPort(41, busName);
    }
    
    //Assign CAN ports to launcher motors.
    public static class launcherPorts
    {
        private static final String busName = "";
        public static final CANPort kLauncherMotor = new CANPort(50, busName);
    }
}