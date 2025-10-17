package frc.robot.autos;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SC25Drivetrain;

// This auton has NOT been tested!

public class TaxiAuto extends Command {
    // Defining 

    private SC25Drivetrain m_drive;
    private Timer m_timer;
    private double drive_time_threshold = 2.75;
    private double eject_time_threshold = 3.75;

    // This auton will make it drive forward for 3.25 seconds (adjustable) and then eject coral into L1

    public TaxiAuto(SC25Drivetrain drive) 
    {
        m_drive = drive;
        m_timer = new Timer();

        addRequirements(m_drive);
    }

    @Override
    public void initialize() {
        // Restart the timer when the command is initialized.

        m_timer.restart();

    }

    @Override
    public void execute() {
        // If the timer is less than the drive time threshold, it drives forward at 0.5 speed.
        // When it reaches the roller time threshold, it stops the drivetrain and ejects the coral.

        if (m_timer.get() < drive_time_threshold) 
        {
            m_drive.driveArcade(0.6, 0);
        } 
        else if (m_timer.get() > drive_time_threshold);
        {
            m_drive.driveArcade(0.0, 0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        // When interrupted, stop the drivetrain, roller, and the timer.

        m_drive.driveArcade(0, 0);
        m_timer.stop();

    }

    @Override
    public boolean isFinished() 
    {
        // If the timer is greater than the eject time threshold, return true.

        return m_timer.get() >= drive_time_threshold;
    }


}