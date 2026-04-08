package org.firstinspires.ftc.teamcode.subsystemsBase.Subsystems;

import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

public class DriveSubsystem extends SubsystemBase {
    private Motor frontRight;
    private Motor frontLeft;
    private Motor backRight;
//    private final Limelight3A limelight;
    private Motor backLeft;
    double axial;
    double lateral;
    double yaw;

    public DriveSubsystem(final HardwareMap hardwareMap){
//        limelight = hardwareMap.get(Limelight3A.class, "limelight");
//        limelight.pipelineSwitch(0);
//        limelight.start();
         frontLeft = new Motor(hardwareMap, "frontLeft");
        backLeft = new Motor(hardwareMap, "backLeft");
        frontRight = new Motor(hardwareMap, "frontRight");
        backRight = new Motor(hardwareMap, "backRight");
        frontRight.setInverted(true);
        backRight.setInverted(true);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public void drive(double leftY,double leftX,double rightX){
        axial = -leftY;
        lateral = -leftX;
        yaw = -rightX;
    }
    public void align(){

    }

    @Override
    public void periodic(){
        double frontLeftPower = axial + lateral + yaw;
        double frontRightPower = (axial - lateral) - yaw;
        double backLeftPower = (axial - lateral) + yaw;
        double backRightPower = (axial + lateral) - yaw;
        double max = JavaUtil.maxOfList(JavaUtil.createListWith(Math.abs(frontLeftPower), Math.abs(frontRightPower), Math.abs(backLeftPower), Math.abs(backRightPower)));

        if (max > 1) {
            frontLeftPower = frontLeftPower / max;
            frontRightPower = frontRightPower / max;
            backLeftPower = backLeftPower / max;
            backRightPower = backRightPower / max;
        }
        frontLeft.set(frontLeftPower);
        frontRight.set(frontRightPower);
        backLeft.set(backLeftPower);
        backRight.set(backRightPower);
    }
}
