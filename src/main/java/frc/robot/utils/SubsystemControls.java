package frc.robot.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class holds the subsystem control values as imported from the subsystem control
 * JSON file. This is made for the 2022 season
 */
public class SubsystemControls
{

    private final boolean intake;
    private final boolean launcher;
    private final boolean drive;

     /**  
     * @param intake
     *            indicates if the intake subsystem is present and should be enabled
     * @param launcher
     *            indicates if the launcher subsystem is present and should be enabled
     * @param drive
     *            indicates if the launcher subsystem is present and should be enabled
     */
    public SubsystemControls
    (
        @JsonProperty(required = true, value = "intake")      boolean intake,
        @JsonProperty(required = true, value = "launcher")      boolean launcher,
        @JsonProperty(required = true, value = "drive")      boolean drive
    )

    {
        this.intake = intake;
        this.launcher = launcher;
        this.drive = drive;
    }


    /**
     * Returns true if the drive subsystem is indicated as present and should be enabled.
     * 
     * @return true if the drive subsystem is indicated as present and should be enabled; false
     *         otherwise
     */
    public boolean isIntakePresent()
    {
        return intake;
    }
    public boolean isLauncherPresent() {
        return launcher;
    }
    public boolean isDrivePresent() {
        return drive;
    }
}