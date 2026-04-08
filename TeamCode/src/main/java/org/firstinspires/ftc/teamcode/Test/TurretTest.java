package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.ParallelDeadlineGroup;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems.TurretSecond;
import org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems.TurretSubsystem;

@TeleOp
public class TurretTest extends CommandOpMode {
    private TurretSubsystem turret;
    private TurretSecond turret2;
    TelemetryData telemetryData = new TelemetryData(telemetry);

    private GamepadEx driver1;
    private Button up, down, fullRotate, fullRetrun;
    @Override
    public void initialize(){
        super.reset();
        driver1 = new GamepadEx(gamepad1);
        turret = new TurretSubsystem(hardwareMap);
        turret2 = new TurretSecond(hardwareMap);
//        up  = (new GamepadButton(driver1, GamepadKeys.Button.DPAD_UP)).whenHeld(new ParallelDeadlineGroup(new InstantCommand((()-> {
//            turret.increasePos1();
//        }), turret), new InstantCommand((()->{turret2.increasePos2();}), turret2)));
//        down  = (new GamepadButton(driver1, GamepadKeys.Button.DPAD_DOWN)).whenHeld(new ParallelDeadlineGroup(new InstantCommand((()-> {
//            turret.decreasePos1();
//        }), turret), new InstantCommand((()->{turret2.decreasePos2();}), turret2)));
        fullRotate = (new GamepadButton(driver1, GamepadKeys.Button.A)).whenHeld(new ParallelDeadlineGroup(new InstantCommand((()->turret.set360()), turret), new InstantCommand((()->turret2.set360()), turret2)));
        up = (new GamepadButton(driver1, GamepadKeys.Button.X)).whenHeld(new ParallelDeadlineGroup(new InstantCommand((()->turret.set90()), turret), new InstantCommand((()->turret2.set90()), turret2)));
        down = (new GamepadButton(driver1, GamepadKeys.Button.CROSS)).whenHeld(new ParallelDeadlineGroup(new InstantCommand((()->turret.set180()), turret), new InstantCommand((()->turret2.set180()), turret2)));
        fullRetrun = (new GamepadButton(driver1, GamepadKeys.Button.B)).whenHeld(new ParallelDeadlineGroup(new InstantCommand((()->turret.set0()), turret), new InstantCommand((()->turret2.set0()), turret2)));
        register(turret,turret2);

    }
    @Override
    public void run() {
        super.run();
        telemetryData.addData("servoPos1", turret.getPosition());
        telemetryData.addData("servoPos2", turret2.getPosition());
        telemetryData.addData("servoAngle",360* turret.getPosition()/0.76);
        telemetryData.addData("servoAngle2",360* turret2.getPosition()/0.76);

        telemetryData.update();

    }
}
