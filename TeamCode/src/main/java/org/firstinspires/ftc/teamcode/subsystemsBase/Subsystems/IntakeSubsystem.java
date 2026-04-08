package org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final DcMotor intakeMotor;
    public IntakeSubsystem(final HardwareMap hwMap){
        intakeMotor = hwMap.get(DcMotor.class, "intakeMotor");
    }

    public void intake(){
        intakeMotor.setPower(-0.5);
    }
    public void outtake() {
        intakeMotor.setPower(1);
    }
    public void controlledIntake(double controller){
        intakeMotor.setPower(-1*controller);
    }
}
