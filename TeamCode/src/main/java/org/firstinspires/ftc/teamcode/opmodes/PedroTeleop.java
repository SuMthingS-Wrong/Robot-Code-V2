package org.firstinspires.ftc.teamcode.opmodes;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.commands.Drive;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems.VisionSubsystem;

import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.util.TelemetryData;

@TeleOp
public class PedroTeleop extends CommandOpMode {
    private DriveSubsystem m_drive;
    Follower follower;
    TelemetryData telemetryData = new TelemetryData(telemetry);
    private IntakeSubsystem intake;
    private ShooterSubsystem shooter;
    private VisionSubsystem vision;
    private TurretSubsystem turret;
    private Drive m_driveCommand;
    private GamepadEx driver1 = new GamepadEx(gamepad1);
    private Button m_shootButton, m_outtakeButton, increaseVelocityButton, decreaseVelocityButton, increaseAngleButton, decreaseAngleButton;
    private Trigger m_intakeButton;

    @Override
    public void initialize() {
        follower = Constants.createFollower(hardwareMap);
        super.reset();
        follower.startTeleopDrive();
        m_drive = new DriveSubsystem(hardwareMap);
        intake = new IntakeSubsystem(hardwareMap);
        vision = new VisionSubsystem(hardwareMap);
        turret = new TurretSubsystem(hardwareMap);
        shooter = new ShooterSubsystem(hardwareMap);
        driver1 = new GamepadEx(gamepad1);
        m_driveCommand = new Drive(m_drive, () -> driver1.getLeftY(), ()-> driver1.getLeftX(), ()->driver1.getRightX());



        m_outtakeButton = (new GamepadButton(driver1, GamepadKeys.Button.LEFT_BUMPER))
                .whileHeld(new InstantCommand(intake::outtake, intake));
        m_intakeButton = (new Trigger(()->driver1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0))
                .whenActive(new InstantCommand((()->{
                    intake.controlledIntake(driver1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER));
                } ), intake));
        increaseAngleButton = (new GamepadButton(driver1, GamepadKeys.Button.DPAD_UP))
                .whileHeld(new InstantCommand((()-> {
                    shooter.increaseAngle();
                }), shooter));
        decreaseAngleButton = (new GamepadButton(driver1, GamepadKeys.Button.DPAD_DOWN))
                .whileHeld(new InstantCommand((()-> {
                    shooter.decreaseAngle();
                }), shooter));
        increaseVelocityButton = (new GamepadButton(driver1, GamepadKeys.Button.DPAD_RIGHT))
                .whenPressed(new InstantCommand((()-> {
                    shooter.increaseRPM();
                }), shooter));
        decreaseVelocityButton = (new GamepadButton(driver1, GamepadKeys.Button.DPAD_LEFT))
                .whenPressed(new InstantCommand((()-> {
                    shooter.decreaseRPM();
                }), shooter));


        register(m_drive, shooter, intake);
        m_drive.setDefaultCommand(m_driveCommand);
    }
    @Override
    public void run(){
        super.run();

        follower.setTeleOpDrive(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, true);
        follower.update();
    }

}
