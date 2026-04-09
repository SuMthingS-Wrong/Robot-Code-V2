package org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class GateSubsystem extends SubsystemBase {
    ServoEx gate;
    public GateSubsystem(HardwareMap hwMap){
        gate = new ServoEx(hwMap, "gate");
    }
    public void tempIncreasePos(){
        gate.set(gate.getRawPosition() + 0.01);
    }
    public void tempDecreasePos(){
        gate.set(gate.getRawPosition() - 0.01);
    }
    public double getPosition(){return gate.getRawPosition();}
}
