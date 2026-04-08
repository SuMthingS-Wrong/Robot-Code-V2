package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class Drive extends CommandBase {
    DoubleSupplier leftY;
    DoubleSupplier leftX;
    DoubleSupplier rightX;
    private final DriveSubsystem m_drive;

    public Drive(DriveSubsystem subsystem, DoubleSupplier left_stick_y, DoubleSupplier left_stick_x, DoubleSupplier right_stick_x){
        m_drive = subsystem;
        leftY = left_stick_y;
        leftX = left_stick_x;
        rightX = right_stick_x;
        addRequirements(m_drive);
    }
    @Override
    public void execute(){
        m_drive.drive(leftY.getAsDouble(), leftX.getAsDouble(), rightX.getAsDouble());
    }

}
