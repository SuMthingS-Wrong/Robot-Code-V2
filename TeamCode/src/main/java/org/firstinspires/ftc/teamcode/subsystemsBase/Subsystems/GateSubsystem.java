package org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class GateSubsystem extends SubsystemBase {
    Servo gate;
    DcMotor shooter;
    public GateSubsystem(HardwareMap hwMap){
        gate = hwMap.get(Servo.class, "gate");
        gate.scaleRange(0, 0.8);
        shooter = hwMap.get(DcMotor.class, "shooterMotor1");
    }
    public void tempIncreasePos(){
        gate.setPosition(1);
    }
    public void tempDecreasePos(){
        gate.setPosition(0);
    }
    public double getPosition(){return gate.getPosition();}
    public void startShooter(){
        shooter.setPower(0.1);
    }
     public void stopShooter(){
        shooter.setPower(0);
     }
}
