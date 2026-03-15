package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {
    private final Limelight3A limelight;
    public VisionSubsystem(final HardwareMap hwMap) {
        limelight = hwMap.get(Limelight3A.class, "limelight");
        limelight.start();
    }


    public void getDistance(final HardwareMap hwMap){

    }
    @Override
    public void periodic(){
        LLResult llResult = limelight.getLatestResult();
    }

}
