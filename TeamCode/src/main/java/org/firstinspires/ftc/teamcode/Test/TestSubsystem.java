package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.util.InterpLUT;

public class TestSubsystem extends SubsystemBase {
    // Feedforward Coefficiients
    double servoVal;
    double shooterRPM;
    ServoEx angleServo;
    MotorEx shooterMotor;
    double kS = 0;
    double[] stepSizes = {0.1, 0.01, 0.001, 0.0001};
    double step = 0.1;
    public TestSubsystem(HardwareMap hwMap){
        angleServo = new ServoEx(hwMap, "shooterAngleServo");
        shooterMotor = new MotorEx(hwMap, "shooterMotor", 28, 6000);



        shooterMotor.setRunMode(Motor.RunMode.VelocityControl);
//        flywheel = new MotorGroup(shooterMotor1, shooterMotor2);
//        flywheel.setRunMode(Motor.RunMode.VelocityControl);
//        flywheel.setVeloCoefficients(kP,kI,kD);
//        flywheel.setFeedforwardCoefficients(kS,kV,kA);
    }

    //    placeholders for now
  public void startShooter(){
        shooterMotor.set(200);

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
    public void incKS(){
        kS+=step;
        shooterMotor.setFeedforwardCoefficients(kS, 0);
    }
    public void decKS(){
        kS-=step;
        shooterMotor.setFeedforwardCoefficients(kS, 0);
    }
    public void setnotpoint1(){
        step = stepSizes[0];

    }
    public double getCurrStep(){
        return step;
    }
    public void setnotpointnot1(){
        step = stepSizes[1];
        kS+=step;
    }
    public void setnotpointnotnot1(){
        step = stepSizes[2];
        kS+=step;
    }
    public void setnotpointnotnotnot1(){
        step = stepSizes[3];
        kS+=step;
    }
    public double getSpeed() {
        return shooterMotor.getVelocity();
    }

    public double getKS(){
        return kS;
    }

}
