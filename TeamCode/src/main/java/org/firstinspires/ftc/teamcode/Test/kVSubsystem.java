package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class kVSubsystem extends SubsystemBase {
    ServoEx angleServo;
    MotorEx shooterMotor;
    double kS = 0;
    double kV = 1/6000;

    int targetRpm = 0;
    double[] stepSizes = {0.00001, 0.000001};
    double step = 0.00001;
    public kVSubsystem(HardwareMap hwMap){
        angleServo = new ServoEx(hwMap, "shooterAngleServo");
        shooterMotor = new MotorEx(hwMap, "shooterMotor1", 28, 6000);
        shooterMotor.setFeedforwardCoefficients(kS, kV);


        shooterMotor.setRunMode(Motor.RunMode.VelocityControl);
//        flywheel = new MotorGroup(shooterMotor1, shooterMotor2);
//        flywheel.setRunMode(Motor.RunMode.VelocityControl);
//        flywheel.setVeloCoefficients(kP,kI,kD);
//        flywheel.setFeedforwardCoefficients(kS,kV,kA);
    }

    //    placeholders for now
    public void setShooter(int rpm){
        targetRpm = rpm;
        shooterMotor.set(rpm);

    }
    public void stopShooter(){
        shooterMotor.set(0);
    }


    //    public void decreaseAngle(){
//        double position = angleServo.getRawPosition();
//        angleServo.set(position-0.01);
//    }
//    public void increaseAngle(){
//        double position = angleServo.getRawPosition();
//
//        angleServo.set(position+0.01);
//    }
//    public double getServoPosition(){
//        return angleServo.getRawPosition();
//    }
    public void incKV(){
        kV+=step;
        shooterMotor.setFeedforwardCoefficients(kS, kV);
    }
    public void decKV(){
        kV-=step;
        shooterMotor.setFeedforwardCoefficients(kS, kV);
    }
    public void coarse(){
        step = stepSizes[0];

    }
    public double getCurrStep(){
        return step;
    }
    public void fine(){
        step = stepSizes[1];
        kS+=step;
    }


    public double getSpeed() {
        return shooterMotor.getVelocity();
    }

    public double getKV(){
        return kV;
    }
    public double getTargetVelocity(){
        return targetRpm;
    }

}
