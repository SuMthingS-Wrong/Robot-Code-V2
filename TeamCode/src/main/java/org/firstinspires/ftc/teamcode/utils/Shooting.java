package org.firstinspires.ftc.teamcode.utils;

import com.pedropathing.geometry.Pose;

public class Shooting {
    public double getAlignedHeading(Pose pose, Pose goalPose){
        return Math.atan2(goalPose.getY()-pose.getY(), goalPose.getX()-pose.getX());
    }
}
