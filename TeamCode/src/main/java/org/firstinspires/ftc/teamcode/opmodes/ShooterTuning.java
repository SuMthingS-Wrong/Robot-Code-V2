package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;


/* I feel I don't need as many comments as the code is pretty self explanatory,
   but there are six buttons to choose which of the velocity and feedforward coefficient to tune,
   and theres a button to increase velocity and step size and the values of each coefficient */
public class ShooterTuning extends OpMode {
    GamepadEx driver1;
    DcMotor intakeMotor;
    MotorEx shooterMotor1;
    MotorEx shooterMotor2;
    MotorGroup flywheel;
    ServoEx angleServo;

    public double highVelocity = 1500;
    public double lowVelocity = 800;

    enum Tuning {
        KS, KV, KA, KP, KI, KD
    }
    Tuning tuning = Tuning.KV;
    double currTargetVelocity = highVelocity;
    // Feedforward Coefficiients
    double kS = 0;
    double kV = 0;
    double kA = 0;
    // PID Coefficients
    double kP = 0;
    double kI = 0;
    double kD = 0;
    double[] stepSizes = {10.0, 1.0, 0.1, 0.01, 0.001};
    int stepIndex = 1;
    @Override
    public void init() {
        driver1 = new GamepadEx(gamepad1);
        angleServo = new ServoEx(hardwareMap, "shooterAngleServo");
        shooterMotor1 = new MotorEx(hardwareMap, "shooterMotor1", 28, 5800);
        shooterMotor2 = new MotorEx(hardwareMap, "shooterMotor2", 28, 5800);
        shooterMotor2.setInverted(true);
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");;
        flywheel = new MotorGroup(shooterMotor1, shooterMotor2);
        flywheel.setRunMode(Motor.RunMode.VelocityControl);
        flywheel.setVeloCoefficients(kP,kI,kD);
        flywheel.setFeedforwardCoefficients(kS,kV,kA);
        telemetry.addLine("Init Complete");
    }

    @Override
    public void loop() {
        if (gamepad1.right_bumper){
            if(currTargetVelocity == highVelocity){
                currTargetVelocity = lowVelocity;
            } else {
                currTargetVelocity = highVelocity;
            }
        }
        if (gamepad1.leftBumperWasPressed()){
            stepIndex = (stepIndex+1)%stepSizes.length;
        }
        if (driver1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)>0){
            telemetry.addData("Intaking","True");
            intakeMotor.setPower(-1*driver1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER));
        }

        if (gamepad1.xWasPressed()){
            tuning = Tuning.KP;
        }
        if(gamepad1.yWasPressed()){
            tuning  = Tuning.KI;
        }
        if(gamepad1.bWasPressed()){
            tuning = Tuning.KD;
        }
        if(gamepad1.aWasPressed()){
            tuning = Tuning.KS;
        }
        if(gamepad1.dpadLeftWasPressed()){
            tuning = Tuning.KV;
        }
        if(gamepad1.dpadRightWasPressed()){
            tuning = Tuning.KA;
        }
        if(gamepad1.dpadUpWasPressed()){
            switch (tuning){
                case KP:
                    kP +=stepSizes[stepIndex];
                case KI:
                    kI +=stepSizes[stepIndex];
                case KD:
                    kD +=stepSizes[stepIndex];
                case KS:
                    kS +=stepSizes[stepIndex];
                case KV:
                    kV +=stepSizes[stepIndex];
                case KA:
                    kA +=stepSizes[stepIndex];
            }
        }
        if(gamepad1.dpadDownWasPressed()){
            kP-=stepSizes[stepIndex];
            switch (tuning){
                case KP:
                    kP -=stepSizes[stepIndex];
                case KI:
                    kI -=stepSizes[stepIndex];
                case KD:
                    kD -=stepSizes[stepIndex];
                case KS:
                    kS -=stepSizes[stepIndex];
                case KV:
                    kV -=stepSizes[stepIndex];
                case KA:
                    kA -=stepSizes[stepIndex];
            }
        }

        shooterMotor1.setVeloCoefficients(kP, kI, kD);
        shooterMotor2.setVeloCoefficients(kP, kI, kD);
        shooterMotor1.setFeedforwardCoefficients(kS,kV, kA);
        shooterMotor2.setFeedforwardCoefficients(kS,kV, kA);
        shooterMotor1.setVelocity(currTargetVelocity);
        shooterMotor2.setVelocity(-currTargetVelocity);
        double currVelocity1 = shooterMotor1.getVelocity();
        double currVelocity2 = shooterMotor2.getVelocity();
        double error1 = currTargetVelocity - currVelocity1;
        double error2 = currTargetVelocity + currVelocity2;
        telemetry.addData("Currently Tuning", tuning);
        telemetry.addData("P", kP);
        telemetry.addData("I", kI);
        telemetry.addData("D", kD);
        telemetry.addData("kV", kV);
        telemetry.addData("kS", kS);
        telemetry.addData("kA", kA);
        telemetry.addData("Error in motor 1", error1);
        telemetry.addData("Error in motor 2", error2);
        telemetry.addData("Current Velocity in motor 1", currVelocity1);
        telemetry.addData("Current Velocity in motor 2", currVelocity2);

    }
}
