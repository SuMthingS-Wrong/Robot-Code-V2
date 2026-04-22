package org.firstinspires.ftc.teamcode.opmodes.TeleOp;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.teamcode.commands.RobotConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems.VisionSubsystem;

public class BlueForwardTeleOp extends CommandOpMode {
    Follower follower;
    TelemetryData telemetryData = new TelemetryData(telemetry);
    private IntakeSubsystem intake;
    private ShooterSubsystem shooter;
    private VisionSubsystem vision;

    private GamepadEx driver1;
    private Button m_shootButton, m_outtakeButton, increaseVelocityButton, decreaseVelocityButton, increaseAngleButton, decreaseAngleButton;
    private Trigger m_intakeButton;

    @Override
    public void initialize() {
        super.reset();
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(RobotConstants.BLUE_FORWARD_PARK_POSE);
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
