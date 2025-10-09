// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Konstants.OIConstants.kSlowModePercent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ctre.phoenix6.Utils;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.bindings.CommandBinder;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.SC25Drivetrain;
import frc.robot.utils.SK25AutoBuilder;
import frc.robot.utils.SubsystemControls;
import frc.robot.utils.filters.FilteredJoystick;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer 
{
    public final SC25Drivetrain m_drive = new SC25Drivetrain();
    private final CommandXboxController m_driverController =
      new CommandXboxController(0);

    // An option box on shuffleboard to choose the auto path
    SendableChooser<Command> autoCommandSelector = new SendableChooser<Command>();

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() 
    {
        configurePathPlannerCommands();
        configureBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link FilteredJoystick}), and then
     * calling passing it to a {@link JoystickButton}.
     */
    private void configureBindings()
    {
        m_drive.setDefaultCommand(new DriveCommand(m_drive,
        () -> -m_driverController.getLeftY(),
        () -> -m_driverController.getRightX()));

        m_driverController.leftBumper().whileTrue(new DriveCommand(m_drive, 
        () -> -m_driverController.getLeftY() * kSlowModePercent,  
        () -> -m_driverController.getRightX() * kSlowModePercent));

    }

    private void configurePathPlannerCommands()
    {
        // Always check to see if the drivetrain is present for auto
        // It's kinda useless to create autonomous commands if there's no drivebase
        // to move the robot around the field...

        /* ex:
         * Nest the other subsystem checking if-statements inside the drivetrain if-statement
         * 
         * if(m_driveContainer.isPresent()) {
         *      if(m_launcherContainer.isPresent()) {
         *            // This line configures a launching command to be used in autonomous and feeds in
         *            // a medium motor speed value
         *            NamedCommands.registerCommand("RunLauncherMediumCommand", new RunLauncherCommand(kMediumSpeed));
         *      }
         * }
         */
    }

  /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     * <p>
     * This method loads the auto when it is called, however, it is recommended
     * to first load your paths/autos when code starts, then return the
     * pre-loaded auto/path.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand()
    {
        return Commands.sequence(Commands.waitSeconds(0.01), autoCommandSelector.getSelected());
    }

    public void testPeriodic(){

    }
    public void testInit(){

    }

    public void matchInit()
    {
    
    }

    public void teleopInit()
    {
       
    }
    public void autonomousInit()
    {
     
    }
}
