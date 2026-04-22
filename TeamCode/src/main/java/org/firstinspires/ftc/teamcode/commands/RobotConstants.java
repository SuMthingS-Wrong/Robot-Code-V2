package org.firstinspires.ftc.teamcode.commands;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;

@Configurable
public class RobotConstants {

    public static Pose GOAL_POS_RED = new Pose(138, 138);
    public static Pose GOAL_POS_BLUE = GOAL_POS_RED.mirror(144);

    public static double SCORE_HEIGHT = 31; // inches
    public static double SCORE_ANGLE = Math.toRadians(-30); // radians
    public static double HOOD_MAX_ANGLE=90;
    public static double HOOD_MIN_ANGLE=0;
    public static double GATE_OPEN_POSE = 0.05;
    public static double GATE_CLOSE_POSE = 0.21;
    public static double HOOD_MAX_POS = 0.8;
    public static double HOOD_MIN_POS = 0;
    public static double PASS_THROUGH_POINT_RADIUS = 5; // inches
    public static Pose RED_FORWARD_PARK_POSE = new Pose(97, 75.94392523364486,Math.toRadians(90));
    public static Pose BLUE_FORWARD_PARK_POSE = RED_FORWARD_PARK_POSE.mirror(144);

    public static Pose BLUE_BACK_PARK_POSE = new Pose(56.22429906542056, 30.39252336448599, Math.toRadians(90));
    public static Pose RED_BACK_PARK_POSE = BLUE_BACK_PARK_POSE.mirror(144);


}

