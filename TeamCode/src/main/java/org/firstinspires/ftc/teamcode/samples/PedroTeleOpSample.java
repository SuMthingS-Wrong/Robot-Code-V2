package org.firstinspires.ftc.teamcode.samples;


import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@TeleOp
public class PedroTeleOpSample extends CommandOpMode {
//    Follower follower;
//    TelemetryData telemetryData = new TelemetryData(telemetry);
    MecanumDrive mecanum;
    private GamepadEx driverOp;
    @Override
    public void initialize() {
        Motor frontLeft = new Motor(hardwareMap, "frontLeft");
        Motor backLeft = new Motor(hardwareMap, "backLeft");
        Motor frontRight = new Motor(hardwareMap, "frontRight");
        Motor backRight = new Motor(hardwareMap, "backRight");
            frontLeft.setInverted(true);
        backLeft.setInverted(true);

        mecanum = new MecanumDrive(frontLeft, frontRight,backLeft, backRight);
        driverOp = new GamepadEx(gamepad1);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void run() {
        super.run();

        /* Robot-Centric Drive
        follower.setTeleOpDrive(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, true);
        */
        mecanum.driveRobotCentric(driverOp.getLeftX(),
                driverOp.getLeftY(),
                driverOp.getRightY());
        // Field-Centric Drive
//        follower.setTeleOpDrive(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, false);
//        follower.update();
//
//        telemetryData.addData("X", follower.getPose().getX());
//        telemetryData.addData("Y", follower.getPose().getY());
//        telemetryData.addData("Heading", follower.getPose().getHeading());
//        telemetryData.update();
    }
}