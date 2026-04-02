package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems.DriveSubsystem;

public class Drive extends CommandBase {
    double axial;
    double lateral;
    double yaw;
    private final DriveSubsystem m_drive;

    public Drive(DriveSubsystem subsystem,double left_stick_y, double left_stick_x, double right_stick_x){
        m_drive = subsystem;
        axial = -left_stick_y;
        lateral = -left_stick_x;
        yaw = -right_stick_x;
        addRequirements(m_drive);
    }
    @Override
    public void execute(){
        m_drive.drive(axial, lateral, yaw);
    }
}
