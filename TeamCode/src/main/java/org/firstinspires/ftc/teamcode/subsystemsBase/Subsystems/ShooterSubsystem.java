package org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.util.InterpLUT;

public class ShooterSubsystem extends SubsystemBase {
    // Feedforward Coefficiients
    double kS = 0;
    double kV = 0;
    double kA = 0;
    // PID Coefficients
    double kP = 0;
    double kI = 0;
    double kD = 0;
    double servoVal;
    double shooterRPM;
    ServoEx angleServo;
    MotorEx shooterMotor1;
    MotorEx shooterMotor2;
    MotorGroup flywheel;
    public ShooterSubsystem(HardwareMap hwMap){
        angleServo = new ServoEx(hwMap, "shooterAngleServo");
        shooterMotor1 = new MotorEx(hwMap, "shooterMotor1", 28, 6000);
        shooterMotor2 = new MotorEx(hwMap, "shooterMotor2", 28, 6000);
        shooterMotor2.setInverted(true);
        flywheel = new MotorGroup(shooterMotor1, shooterMotor2);
        flywheel.setRunMode(Motor.RunMode.VelocityControl);
//        flywheel.setVeloCoefficients(kP,kI,kD);
//        flywheel.setFeedforwardCoefficients(kS,kV,kA);
    }
//    placeholders for now
    InterpLUT velocityLut  = new InterpLUT(){{
        add(5.0, 1.0);
        add(4.0, 0.9);
        add(3.0, 0.75);
        add(2.0, 0.5);
        add(1.0, 0.2);
    }};
    InterpLUT angleLut  = new InterpLUT(){{
        add(5.0, 1.0);
        add(4.0, 0.9);
        add(3.0, 0.75);
        add(2.0, 0.5);
        add(1.0, 0.2);
    }};

    public void shoot(double distance){
        double velocity = velocityLut.get(distance);
        double angle = angleLut.get(distance);
        flywheel.set(velocity);
        angleServo.set(angle);
    }

    public void increaseRPM(){
        shooterRPM += 500;
        shooterRPM = shooterRPM % 3000;
        flywheel.set(shooterRPM);
        
    }
    public void decreaseRPM(){
        shooterRPM -= 500;
        shooterRPM = shooterRPM % 3000;
        flywheel.set(shooterRPM);
    }
    public void decreaseAngle(){
        servoVal -= 1;
        servoVal = servoVal % 10;
        angleServo.set(servoVal/10);
    }
    public void increaseAngle(){
        servoVal += 1;
        servoVal = servoVal % 10;
        angleServo.set(servoVal/10);
    }


    public void shootTemp(){

    }
    public double getRPMFromVelocity(double velocity){
        return velocity;
    }
    public double getServoPosFromDegrees(double angle){
        return angle;
    }
    @Override
    public void periodic(){
//        shoot(distanc)
    }
}
