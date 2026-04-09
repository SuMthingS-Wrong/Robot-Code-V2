package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelDeadlineGroup;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems.GateSubsystem;

@TeleOp
public class GateTest extends CommandOpMode {
    private GateSubsystem gate;
    TelemetryData telemetryData = new TelemetryData(telemetry);

    private GamepadEx driver1;
    private Button left, right;
    @Override
    public void initialize(){
        super.reset();
        driver1 = new GamepadEx(gamepad1);
        gate = new GateSubsystem(hardwareMap);
        left = (new GamepadButton(driver1, GamepadKeys.Button.X)).whenHeld(new InstantCommand((()-> gate.tempIncreasePos()), gate));
        right = (new GamepadButton(driver1, GamepadKeys.Button.CROSS)).whenHeld(new InstantCommand((()->gate.tempDecreasePos()), gate));
        register(gate);

    }
    @Override
    public void run() {
        super.run();
        telemetryData.addData("gatePose", gate.getPosition());
        telemetryData.update();

    }
}
