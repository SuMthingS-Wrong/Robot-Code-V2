package org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase {
    private final Servo servo1, servo2;

    public Double servopos1 = 0.0;
    public Double servopos2 = 0.0;
    public TurretSubsystem(final HardwareMap hwMap){
        servo1 = hwMap.get(Servo.class, "servo1");
        servo2 = hwMap.get(Servo.class, "servo2");

    }

    @Override
    public void periodic(){}

    public void setangle(double poschange) {
        servopos1 = servo1.getPosition();
        servopos2 = servo2.getPosition();

        servo1.setPosition(servopos1+poschange);
        servo2.setPosition(servopos1+poschange);
    }

}
