package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SC25Drivetrain;

// import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

// Command to drive the robot with joystick inputs
public class DriveCommand extends Command {
  private final DoubleSupplier m_xSpeed;
  private final DoubleSupplier m_zRotation;
  private final SC25Drivetrain m_drive;

  /**
   * Used to drive the robot, uses arcade drive by default, you will need to modify
   * this command to use tank if desired.
   * 
   * @param driveSubsystem 
   * @param xSpeed The speed forwards and backwards
   * @param zRotation The speed to turn the drivetrain at
   */
  public DriveCommand(SC25Drivetrain driveSubsystem, 
      DoubleSupplier xSpeed, DoubleSupplier zRotation) {
    // Save parameters to local variables for use later
    m_xSpeed = xSpeed;
    m_zRotation = zRotation;
    m_drive = driveSubsystem;

    // Declare subsystems required by this command. This should include any
    // subsystem this command sets and output of
    addRequirements(m_drive);
  }

  // Runs each time the command is scheduled.
  @Override
  public void initialize() {}

  // Runs every cycle while the command is scheduled (~50 times per second)
  // In teleop we square the drive command to help improve handling, play
  // around with it off, this is driver preference
  @Override
  public void execute() 
  {
    m_drive.driveArcade(m_xSpeed.getAsDouble(), m_zRotation.getAsDouble());
  }

  // Runs each time the command ends via isFinished or being interrupted.
  @Override
  public void end(boolean isInterrupted) {}

  // Runs every cycle while the command is scheduled to check if the command is
  // finished
  @Override
  public boolean isFinished() 
  {
    // Return false to indicate that this command never ends. It can be interrupted
    // by another command needing the same subsystem.
    return false;
  }
}