package org.firstinspires.ftc.teamcode.Test;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.util.TelemetryData;

public class kPTuner extends CommandOpMode {
    private Button set1000, set2000, set5000, coarse, fine, incKP, decKP, extraextrafine, set4000, set3000;
    GamepadEx driverOp;
    TelemetryData telemetryData;

    kPSubsystem m_subsystem;
    int[] rpms = {1000,2000, 3000, 4000, 5000};
    public void initialize(){
        super.reset();
        telemetryData = new TelemetryData(telemetry);
        driverOp = new GamepadEx(gamepad1);
        m_subsystem = new kPSubsystem(hardwareMap);
        set1000 = (new GamepadButton(driverOp, GamepadKeys.Button.DPAD_LEFT))
                .whenHeld(new InstantCommand(()-> m_subsystem.setShooter(rpms[0]), m_subsystem));
        set2000 = (new GamepadButton(driverOp, GamepadKeys.Button.DPAD_RIGHT))
                .whenHeld(new InstantCommand(()-> m_subsystem.setShooter(rpms[1]), m_subsystem));
        set3000 = (new GamepadButton(driverOp, GamepadKeys.Button.DPAD_DOWN))
                .whenHeld(new InstantCommand(()-> m_subsystem.setShooter(rpms[2]), m_subsystem));
        set4000 = (new GamepadButton(driverOp, GamepadKeys.Button.DPAD_UP))
                .whenHeld(new InstantCommand(()-> m_subsystem.setShooter(rpms[3]), m_subsystem));
        set5000 = (new GamepadButton(driverOp, GamepadKeys.Button.X))
                .whenHeld(new InstantCommand(()-> m_subsystem.setShooter(rpms[4]), m_subsystem));
        coarse = (new GamepadButton(driverOp, GamepadKeys.Button.CIRCLE))
                .whenHeld(new InstantCommand(m_subsystem::coarse, m_subsystem));
        fine = (new GamepadButton(driverOp, GamepadKeys.Button.CROSS))
                .whenHeld(new InstantCommand(m_subsystem::fine, m_subsystem));
        incKP = (new GamepadButton(driverOp, GamepadKeys.Button.LEFT_BUMPER))
                .whenHeld(new InstantCommand(m_subsystem::incKP, m_subsystem));
        incKP = (new GamepadButton(driverOp, GamepadKeys.Button.LEFT_BUMPER))
                .whenHeld(new InstantCommand(m_subsystem::decKP, m_subsystem));
    }

    @Override
    public void run() {
        super.run();
        telemetryData.addData("kS", m_subsystem.getKV());
        telemetryData.addData("currStep", m_subsystem.getCurrStep());

        telemetryData.addData("speed", m_subsystem.getSpeed());
        telemetryData.addData("targetRpm", m_subsystem.getTargetVelocity());
        telemetryData.addData("error", m_subsystem.getTargetVelocity()-m_subsystem.getSpeed());

        telemetryData.update();
    }
}

