// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autos.TaxiAuto;
import frc.robot.bindings.CommandBinder;
import frc.robot.bindings.SC25IntakeBinder;
import frc.robot.bindings.SC25LauncherBinder;
import frc.robot.bindings.SC25DrivetrainBinder;
import frc.robot.subsystems.SC25Intake;
import frc.robot.subsystems.SC25Launcher;
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
public class RobotContainer extends Robot
{
    public Optional<SC25Intake> m_intakeContainer = Optional.empty();
    public Optional<SC25Launcher> m_launcherContainer = Optional.empty();
    public Optional<SC25Drivetrain> m_driveContainer = Optional.empty();

    public static SC25Intake m_intake;
    public static SC25Launcher m_launcher;
    public static SC25Drivetrain m_drive;

    // The list containing all the command binding classes
    private List<CommandBinder> buttonBinders = new ArrayList<CommandBinder>();

    // An option box on shuffleboard to choose the auto path
    SendableChooser<Command> autoChooser = new SendableChooser<Command>();

    // Initializing Autons
    //private final TaxiAuto m_taxiAuto = new TaxiAuto(m_drive);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() 
    {
        // Creates all subsystems that are on the robot
        configureSubsystems();
        // sets up autos needed for pathplanner
        configurePathPlannerCommands();
        // Configure the trigger bindings
        configureButtonBindings();

        //autoChooser.setDefaultOption("Taxi Auto", m_taxiAuto);
        SmartDashboard.putData(autoChooser);
    }

    /**
     * Will create all the optional subsystems using the json file in the deploy directory
     */
    private void configureSubsystems()
    {
        File deployDirectory = Filesystem.getDeployDirectory();
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = new JsonFactory();

        try
        {
            JsonParser parser =
                    factory.createParser(new File(deployDirectory, Konstants.SUBSYSTEMFILE));
                    SubsystemControls subsystems = mapper.readValue(parser, SubsystemControls.class);
            
            if(subsystems.isIntakePresent())
            {
                m_intakeContainer = Optional.of(new SC25Intake());
                m_intake = m_intakeContainer.get();
            }
            if(subsystems.isLauncherPresent())
            {
                m_launcherContainer = Optional.of(new SC25Launcher());
                m_launcher = m_launcherContainer.get();
            }
            if(subsystems.isDrivePresent())
            {
                m_driveContainer = Optional.of(new SC25Drivetrain());
                m_drive = m_driveContainer.get();
            }
        }
        catch (IOException e)
        {
            DriverStation.reportError("Failure to read Subsystem Control File!", e.getStackTrace());
        }
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link FilteredJoystick}), and then
     * calling passing it to a {@link JoystickButton}.
     */
    private void configureButtonBindings()
    {
        buttonBinders.add(new SC25IntakeBinder(m_intakeContainer));
        buttonBinders.add(new SC25LauncherBinder(m_launcherContainer));
        buttonBinders.add(new SC25DrivetrainBinder(m_driveContainer));

        for (CommandBinder subsystemGroup : buttonBinders)
        {
            subsystemGroup.bindButtons();
        }
    }

    private void configurePathPlannerCommands(){}

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
        // return Commands.sequence(Commands.waitSeconds(0.01), autoChooser.getSelected());
        return Commands.none();
    }

    public void testPeriodic()
    {
        // if(m_intakeContainer.isPresent())
        // {
        //     m_intakeContainer.get().testPeriodic();
        // }
        // if(m_launcherContainer.isPresent())
        // {
        //     m_launcherContainer.get().testPeriodic();
        // }
    }
    public void testInit()
    {
        // if(m_intakeContainer.isPresent())
        // {
        //     m_intakeContainer.get().testInit();
        // }
        // if(m_launcherContainer.isPresent())
        // {
        //     m_launcherContainer.get().testInit();
        // }
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
