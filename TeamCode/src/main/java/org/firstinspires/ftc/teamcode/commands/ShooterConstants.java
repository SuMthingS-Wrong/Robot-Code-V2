package org.firstinspires.ftc.teamcode.commands;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.pedropathing.math.MathFunctions;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@Configurable
public class ShooterConstants {

    public static Pose GOAL_POS_RED = new Pose(138, 138);
    public static Pose GOAL_POS_BLUE = GOAL_POS_RED.mirror();

    public static double SCORE_HEIGHT = 31; // inches
    public static double SCORE_ANGLE = Math.toRadians(-30); // radians
    public static double HOOD_MAX_ANGLE=90;
    public static double HOOD_MIN_ANGLE=0;

    public static double PASS_THROUGH_POINT_RADIUS = 5; // inches
}

