package org.firstinspires.ftc.teamcode.samples;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@TeleOp
public class PedroTeleOpSample extends CommandOpMode {
    Follower follower;
    TelemetryData telemetryData = new TelemetryData(telemetry);

    private GamepadEx driverOp;
    @Override
    public void initialize() {
        super.reset();
       follower = Constants.createFollower(hardwareMap);
       follower.setStartingPose(new Pose(0,0,0));
       follower.startTeleOpDrive();
    }

    @Override
    public void run() {
        super.run();

        follower.setTeleOpDrive(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, true);
        follower.update();
        telemetryData.addData("X", follower.getPose().getX());
        telemetryData.addData("Y", follower.getPose().getY());
        telemetryData.addData("Heading", follower.getPose().getHeading());
        telemetryData.update();
    }
}