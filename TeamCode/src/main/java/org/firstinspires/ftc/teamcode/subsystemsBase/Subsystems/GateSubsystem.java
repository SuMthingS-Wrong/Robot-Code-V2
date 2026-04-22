package org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.commands.RobotConstants;

public class GateSubsystem extends SubsystemBase {
    ServoEx gate;
    Motor shooter;
    public GateSubsystem(HardwareMap hwMap){
        gate = new ServoEx(hwMap, "gate");
        shooter = new Motor(hwMap, "shooterMotor1");
    }
    public void openGate(){
        gate.set(RobotConstants.GATE_OPEN_POSE);
    }
    public void closeGate(){
        gate.set(RobotConstants.GATE_CLOSE_POSE);
    }
    public double getPosition(){return gate.getRawPosition();}

}
