package org.firstinspires.ftc.teamcode.subsystemsBase.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems.VisionSubsystem;

public class GoalTracking extends CommandBase {
    private final VisionSubsystem vision

    public GoalTrack() {
        vision.periodic();
    }
}
