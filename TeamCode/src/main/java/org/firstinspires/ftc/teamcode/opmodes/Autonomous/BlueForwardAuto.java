package org.firstinspires.ftc.teamcode.opmodes.Autonomous;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.teamcode.commands.RobotConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.utils.Shooting;

@Autonomous
public class BlueForwardAuto extends CommandOpMode {
    private Follower follower;
    Shooting ShooterFunctions = new Shooting();
    TelemetryData telemetryData = new TelemetryData(telemetry);

    // Poses
    private Pose scorePreload = new Pose(90.3177570093458, 84.71028037383178).mirror(144);
    private final Pose startPose = new Pose(117.68224299065422, 128.67289719626166, Math.toRadians(45)).mirror(144);
    private final Pose pickup1Pose = new Pose(120.14953271028038, 59.10280373831775, Math.toRadians(0)).mirror(144);
    private final Pose pickup1Control = new Pose(84.49532710280371, 82.86915887850468).mirror(144);
    private Pose shoot1 = new Pose(97.00000000000001, 90.06542056074771).mirror(144);
    private final Pose pickup2 = new Pose(120.14953271028038, 59.10280373831775, Math.toRadians(0)).mirror(144);
    private final Pose pickup2Control = new Pose(191.46491256752866, 56.35276302654658).mirror(144);

    private Pose shoot2 = new Pose(90.49532710280374, 83.33644859813084).mirror(144);
    private final Pose classifier = new Pose(132.15887850467288, 60.803738317756995, Math.toRadians(21)).mirror(144);
    private final Pose parkPose = RobotConstants.BLUE_FORWARD_PARK_POSE;
    // Path chains
    private PathChain grabPickupReusable, startingShot, grabPickup1, grabPickup2, grabPickup3;
    private PathChain scorePickup1, scorePickup2, scorePickup3, park;

    public void buildPaths() {
        scorePreload = scorePreload.setHeading(ShooterFunctions.getAlignedHeading(scorePreload, RobotConstants.GOAL_POS_BLUE));

        shoot1 = shoot1.setHeading(ShooterFunctions.getAlignedHeading(shoot1, RobotConstants.GOAL_POS_BLUE));
        shoot2 = shoot2.setHeading(ShooterFunctions.getAlignedHeading(shoot2, RobotConstants.GOAL_POS_BLUE));
        startingShot = follower.pathBuilder()
                .addPath(new BezierLine(startPose, scorePreload))
                .setLinearHeadingInterpolation(startPose.getHeading(), scorePreload.getHeading())
                .build();
        grabPickup1 = follower.pathBuilder()
                .addPath(new BezierLine(scorePreload, pickup1Pose))
                .setLinearHeadingInterpolation(startPose.getHeading(), pickup1Pose.getHeading())
                .build();

        scorePickup1 = follower.pathBuilder()
                .addPath(new BezierLine(pickup1Pose, shoot1))
                .setLinearHeadingInterpolation(pickup1Pose.getHeading(), shoot1.getHeading())
                .build();
        grabPickup2 = follower.pathBuilder()
                .addPath(new BezierCurve(shoot1, pickup2Control, pickup2))
                .setLinearHeadingInterpolation(shoot1.getHeading(), pickup2.getHeading())
                .build();

        scorePickup2 = follower.pathBuilder()
                .addPath(new BezierLine(pickup2, shoot2))
                .setLinearHeadingInterpolation(pickup2.getHeading(), shoot2.getHeading())
                .build();

        grabPickup3 = follower.pathBuilder()
                .addPath(new BezierLine(shoot2, classifier))
                .setLinearHeadingInterpolation(shoot2.getHeading(), classifier.getHeading())
                .build();

        scorePickup3 = follower.pathBuilder()
                .addPath(new BezierLine(classifier, shoot1))
                .setLinearHeadingInterpolation(classifier.getHeading(), shoot1.getHeading())
                .build();
        grabPickupReusable = follower.pathBuilder()
                .addPath(new BezierLine(shoot1, classifier))
                .setLinearHeadingInterpolation(shoot1.getHeading(), classifier.getHeading())
                .build();



        park = follower.pathBuilder()
                .addPath(new BezierLine(
                        shoot1,
                        parkPose)
                )
                .setLinearHeadingInterpolation(shoot1.getHeading(), parkPose.getHeading())
                .build();
    }

    private InstantCommand shootFromStart() {
        return new InstantCommand(() -> {
            // Example: outtakeSubsystem.openClaw();
        });
    }

    private InstantCommand intakeArtefacts() {
        return new InstantCommand(() -> {
            // Example: intakeSubsystem.grabSample();
        });
    }

    private InstantCommand shootArtefacts() {
        return new InstantCommand(() -> {
            // Example: outtakeSubsystem.scoreSample();
        });
    }

    private InstantCommand level1Ascent() {
        return new InstantCommand(() -> {
            // Example: hangSubsystem.level1Ascent();
        });
    }

    @Override
    public void initialize() {
        super.reset();

        // Initialize follower
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startPose);
        buildPaths();

        // Create the autonomous command sequence
        SequentialCommandGroup autonomousSequence = new SequentialCommandGroup(

                new FollowPathCommand(follower, startingShot),

                // First pickup cycle
                new ParallelCommandGroup(new FollowPathCommand(follower, grabPickup1).setGlobalMaxPower(0.5), intakeArtefacts()),

                new FollowPathCommand(follower, scorePickup1),
                shootArtefacts(),

                // Second pickup cycle
                new FollowPathCommand(follower, grabPickup2),
                intakeArtefacts(),
                new FollowPathCommand(follower, scorePickup2, 1.0), // Overrides maxPower to 100% for this path only
                shootArtefacts(),

                // Third pickup cycle
                new FollowPathCommand(follower, grabPickup3),
                intakeArtefacts(),
                new FollowPathCommand(follower, scorePickup3),
                shootArtefacts(),
                // Fourth pickup
                new FollowPathCommand(follower, grabPickupReusable),
                intakeArtefacts(),
                new FollowPathCommand(follower, scorePickup3),
                shootArtefacts(),
                // Park
                new FollowPathCommand(follower, park)
        );

        // Schedule the autonomous sequence
        schedule(autonomousSequence);
    }

    @Override
    public void run() {
        super.run();

        telemetryData.addData("X", follower.getPose().getX());
        telemetryData.addData("Y", follower.getPose().getY());
        telemetryData.addData("Heading", follower.getPose().getHeading());
        telemetryData.update();
    }
}