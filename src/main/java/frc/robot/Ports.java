package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.utils.SKTrigger;
import frc.robot.utils.filters.FilteredXboxController;
import static frc.robot.utils.SKTrigger.INPUT_TYPE.AXIS;
import static frc.robot.utils.SKTrigger.INPUT_TYPE.BUTTON;
import static frc.robot.utils.SKTrigger.INPUT_TYPE.POV;
import frc.robot.utils.CANPort;
import frc.robot.utils.filters.FilteredAxis;
import static edu.wpi.first.wpilibj.XboxController.Button.*;
import static edu.wpi.first.wpilibj.XboxController.Axis.*;



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
        public static final FilteredAxis kVelocityOmegaPort = new FilteredAxis(() -> kDriver.getRawAxis(kRightX.value)); 
        public static final SKTrigger kResetGyroPos = new SKTrigger(kDriver, kLeftStick.value, BUTTON);
        public static final SKTrigger kRobotCentricMode = new SKTrigger(kDriver, kRightBumper.value, BUTTON);
        public static final SKTrigger kSlowMode = new SKTrigger(kDriver, kLeftBumper.value, BUTTON);
        
        // Intake
        public static final SKTrigger kIntake = new SKTrigger(kDriver, kRightTrigger.value, AXIS);
        public static final SKTrigger kEject = new SKTrigger(kDriver, kLeftTrigger.value, AXIS);

        // Climb
        public static final SKTrigger kClimbUp = new SKTrigger(kDriver, kY.value, BUTTON);
        public static final SKTrigger kClimbDown = new SKTrigger(kDriver, kX.value, BUTTON);
        public static final FilteredAxis kClimbAxis = new FilteredAxis(() -> kDriver.getRawAxis(kRightY.value));
    }

    // Ports associated with the operator controller.
    public static class OperatorPorts
    {
        // Operator Controller set to Xbox Controller.
        public static final GenericHID kOperator = new FilteredXboxController(1).getHID();

        // Intake
        public static final SKTrigger kIntake = new SKTrigger(kOperator, kRightTrigger.value, AXIS);
        public static final SKTrigger kEject = new SKTrigger(kOperator, kLeftTrigger.value, AXIS);

        // Launcher
        public static final SKTrigger kLaunchScrap = new SKTrigger(kOperator, kRightBumper.value, BUTTON);

        // Climb
        public static final SKTrigger kClimbUp = new SKTrigger(kOperator, kY.value, BUTTON);
        public static final SKTrigger kClimbDown = new SKTrigger(kOperator, kX.value, BUTTON);
        public static final FilteredAxis kClimbAxis = new FilteredAxis(() -> kOperator.getRawAxis(kRightY.value));
    }

    /* 
     * Assign CAN ports to drive motors.
     * Currently made for a swerve drive, to be changed once we impliment tank drive.
    */
    public static class drivePorts
    {
        private static final String busName = "DriveCAN";

        // CAN IDs for the drive motors on the swerve module.
        public static final CANPort kFrontLeftDriveMotorPort  = new CANPort(13, busName);
        public static final CANPort kRearLeftDriveMotorPort   = new CANPort(12, busName);
        public static final CANPort kFrontRightDriveMotorPort = new CANPort(11, busName);
        public static final CANPort kRearRightDriveMotorPort  = new CANPort(10, busName);

        // CAN IDs for the turning motors on the swerve module.
        public static final CANPort kFrontLeftTurningMotorPort  = new CANPort(23, busName);
        public static final CANPort kRearLeftTurningMotorPort   = new CANPort(22, busName);
        public static final CANPort kFrontRightTurningMotorPort = new CANPort(21, busName);
        public static final CANPort kRearRightTurningMotorPort  = new CANPort(20, busName);

        // CAN IDs for the CANCoders.
        public static final CANPort kFrontLeftTurningEncoderPort  = new CANPort(33, busName);
        public static final CANPort kRearLeftTurningEncoderPort   = new CANPort(32, busName);
        public static final CANPort kFrontRightTurningEncoderPort = new CANPort(31, busName);
        public static final CANPort kRearRightTurningEncoderPort  = new CANPort(30, busName);
        
        // CAN ID for IMU.
        public static final CANPort kPigeonPort = new CANPort(25, busName);
    }

    //Assign CAN ports to climb motors.
    public static class intakePorts 
    {
        private static final String busName = "";
        public static final CANPort kIntakeMotor = new CANPort(40, busName);
    }
    
    //Assign CAN ports to launcher motors.
    public static class launcherPorts
    {
        private static final String busName = "";
        public static final CANPort kLeftLauncherMotor = new CANPort(50, busName);
        public static final CANPort kRightLauncherMotor = new CANPort(50, busName);
    }

    //Assign CAN ports to climb motors.
    public static class climbPorts
    {
        private static final String busName = "";
        public static final CANPort kClimbMotor = new CANPort(60, busName);
    }
}