package org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class TurretSecond extends SubsystemBase {
//    public class TurretServo extends ServoEx {
//        public TurretServo(HardwareMap hwMap, String id){
//            super(hwMap,id);
//        }
//
//        @Override public void set(double output) {
//            super.set(output * 0.76);
//        }
//    }

    private final ServoEx servo2;

    public TurretSecond(final HardwareMap hwMap){
        servo2 = new ServoEx(hwMap, "servo2");
    }

    public void set360() {
        servo2.set(0.76);

    }

    public void set90() {
        servo2.set(0.76/4);

    }
    public void set180() {
        servo2.set(0.76/2);

    }
    public void set0() {
        servo2.set(0);

    }
    public double getPosition(){
            return servo2.getRawPosition();


}
}
