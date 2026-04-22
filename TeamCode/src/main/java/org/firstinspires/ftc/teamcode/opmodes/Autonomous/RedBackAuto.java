package org.firstinspires.ftc.teamcode.opmodes.Autonomous;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import com.seattlesolvers.solverslib.pedroCommand.TurnToCommand;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.teamcode.commands.RobotConstants;
import org.firstinspires.ftc.teamcode.utils.Shooting;

@Autonomous
public class RedBackAuto extends CommandOpMode {
    private Follower follower;
    Shooting ShooterFunctions = new Shooting();
    TelemetryData telemetryData = new TelemetryData(telemetry);

    // Poses
    private Pose scorePose;
    private final Pose startPose = new Pose(56.22429906542056, 7.6, Math.toRadians(90)).mirror(144);
    private final Pose pickup1Pose = new Pose(7.327102803738322, 7.738317757009346, Math.toRadians(180)).mirror(144);
    private final Pose pickup2Pose = new Pose(23.719626168224305, 35.98130841121495, Math.toRadians(180)).mirror(144);
    private final Pose pickup2Control = new Pose(56.808411214953274,38.64485981308411).mirror(144);


    private final Pose parkPose = new Pose(56.22429906542056, 30.39252336448599, Math.toRadians(90)).mirror(144);

    // Path chains
    private PathChain  grabPickup1, grabPickup2, grabPickup3, grabPickup4;
    private PathChain scorePickup1, scorePickup2, scorePickup3, park;

    public void buildPaths() {
        scorePose = startPose.setHeading(ShooterFunctions.getAlignedHeading(startPose, RobotConstants.GOAL_POS_RED));
        grabPickup1 = follower.pathBuilder()
                .addPath(new BezierLine(scorePose, pickup1Pose))
                .setLinearHeadingInterpolation(scorePose.getHeading(), pickup1Pose.getHeading())
                .build();

        scorePickup1 = follower.pathBuilder()
                .addPath(new BezierLine(pickup1Pose, scorePose))
                .setLinearHeadingInterpolation(pickup1Pose.getHeading(), scorePose.getHeading())
                .build();

        grabPickup2 = follower.pathBuilder()
                .addPath(new BezierCurve(scorePose,pickup2Control, pickup2Pose))
                .setLinearHeadingInterpolation(scorePose.getHeading(), pickup2Pose.getHeading())
                .build();

        scorePickup2 = follower.pathBuilder()
                .addPath(new BezierLine(pickup2Pose, scorePose))
                .setLinearHeadingInterpolation(pickup2Pose.getHeading(), scorePose.getHeading())
                .build();

        grabPickup3 = follower.pathBuilder()
                .addPath(new BezierLine(scorePose, pickup1Pose))
                .setLinearHeadingInterpolation(scorePose.getHeading(), pickup1Pose.getHeading())
                .build();

        scorePickup3 = follower.pathBuilder()
                .addPath(new BezierLine(pickup1Pose, scorePose))
                .setLinearHeadingInterpolation(pickup1Pose.getHeading(), scorePose.getHeading())
                .build();

        park = follower.pathBuilder()
                .addPath(new BezierLine(
                        scorePose,
                        parkPose)
                )
                .setLinearHeadingInterpolation(scorePose.getHeading(), parkPose.getHeading())
                .build();
    }

    // Mechanism commands - replace these with your actual subsystem commands
    private InstantCommand shoot() {
        return new InstantCommand(() -> {
            // Example: outtakeSubsystem.openClaw();
        });
    }

    private InstantCommand intake() {
        return new InstantCommand(() -> {
            // Example: intakeSubsystem.grabSample();
        });
    }

    @Override
    public void initialize() {
        super.reset();

        // Initialize follower
        follower = org.firstinspires.ftc.teamcode.pedroPathing.Constants.createFollower(hardwareMap);
        follower.setStartingPose(startPose);
        buildPaths();

        // Create the autonomous command sequence
        SequentialCommandGroup autonomousSequence = new SequentialCommandGroup(
                // Score preload
                new TurnToCommand(follower, ShooterFunctions.getAlignedHeading(startPose, RobotConstants.GOAL_POS_BLUE)),
                shoot(),
                // First pickup cycle
                new FollowPathCommand(follower, grabPickup1),
                intake(),
                new FollowPathCommand(follower, scorePickup1),
                shoot(),
                // Second pickup cycle
                new FollowPathCommand(follower, grabPickup2),
                intake(),

                new FollowPathCommand(follower, scorePickup2),
                shoot(),

                // Third pickup cycle
                new FollowPathCommand(follower, grabPickup3),
                intake(),
                new FollowPathCommand(follower, scorePickup3),
                shoot(),

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