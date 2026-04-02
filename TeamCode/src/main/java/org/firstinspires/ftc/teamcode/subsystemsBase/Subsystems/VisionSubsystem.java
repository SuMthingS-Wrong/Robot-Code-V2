package org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {
    private final Limelight3A limelight;
    private int currentPipeline = 0;
    LLResult llResult;
    // CHANGE THESE CONSTANTS!!!!
    public double CAMERA_HEIGHT_CM = 25.5;
    public double CAMERA_ANGLE = 7.5;
    public double GOAL_HEIGHT = 74.95;
    public VisionSubsystem(final HardwareMap hwMap) {
        limelight = hwMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(currentPipeline);
        limelight.start();
    }

    @Override
    public void periodic(){
        llResult = limelight.getLatestResult();
    }

    public boolean hasTarget() {
        return llResult != null && llResult.isValid();
    }

    public void setPipeline(int pipeline) {
        if (pipeline != currentPipeline){
            currentPipeline = pipeline;
            limelight.pipelineSwitch(pipeline);
            llResult = null;
        }
    }
    public double getDistanceToGoal() {
        setPipeline(0);
        if (hasTarget()) {
            double ty = llResult.getTy();
            double angleToTarget = CAMERA_ANGLE + ty;
            double heightDifference = GOAL_HEIGHT - CAMERA_HEIGHT_CM;
            return (heightDifference / Math.tan(Math.toRadians(angleToTarget)));
        }
        return 0;
    }


}
