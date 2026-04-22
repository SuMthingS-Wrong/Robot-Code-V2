package org.firstinspires.ftc.teamcode.Test;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.util.TelemetryData;

public class ksTuner extends CommandOpMode {
    private Button incShooter, decShooter, incAngle, decAngle, coarse, fine, extrafine, extraextrafine, incKS, decKS;
    GamepadEx driverOp;
    TelemetryData telemetryData;

    TestSubsystem m_subsystem;

    public void initialize(){
        super.reset();
        telemetryData = new TelemetryData(telemetry);
        driverOp = new GamepadEx(gamepad1);
        m_subsystem = new TestSubsystem(hardwareMap);
        incShooter = (new GamepadButton(driverOp, GamepadKeys.Button.DPAD_LEFT))
                .whenHeld(new InstantCommand(m_subsystem::startShooter, m_subsystem));
        decShooter = (new GamepadButton(driverOp, GamepadKeys.Button.DPAD_RIGHT))
                .whenHeld(new InstantCommand(m_subsystem::stopShooter, m_subsystem));
        decKS = (new GamepadButton(driverOp, GamepadKeys.Button.DPAD_DOWN))
                .whenHeld(new InstantCommand(m_subsystem::stopShooter, m_subsystem));
        incKS = (new GamepadButton(driverOp, GamepadKeys.Button.DPAD_UP))
                .whenHeld(new InstantCommand(m_subsystem::stopShooter, m_subsystem));
        coarse = (new GamepadButton(driverOp, GamepadKeys.Button.CIRCLE))
                .whenHeld(new InstantCommand(m_subsystem::setnotpoint1, m_subsystem));
        fine = (new GamepadButton(driverOp, GamepadKeys.Button.CROSS))
                .whenHeld(new InstantCommand(m_subsystem::setnotpointnot1, m_subsystem));
        extrafine = (new GamepadButton(driverOp, GamepadKeys.Button.A))
                .whenHeld(new InstantCommand(m_subsystem::setnotpointnotnot1, m_subsystem));
        extraextrafine= (new GamepadButton(driverOp, GamepadKeys.Button.B))
                .whenHeld(new InstantCommand(m_subsystem::setnotpointnotnotnot1, m_subsystem));
//        incAngle = (new GamepadButton(driverOp, GamepadKeys.Button.DPAD_UP))
//                .whileHeld(new InstantCommand(m_subsystem::increaseAngle, m_subsystem));
//        decAngle = (new GamepadButton(driverOp, GamepadKeys.Button.DPAD_DOWN))
//                .whileHeld(new InstantCommand(m_subsystem::decreaseAngle, m_subsystem));
    }

    @Override
    public void run() {
        super.run();
        telemetryData.addData("kS", m_subsystem.getKS());
        telemetryData.addData("currStep", m_subsystem.getCurrStep());

        telemetryData.addData("speed", m_subsystem.getSpeed());

        telemetryData.update();
    }
}
