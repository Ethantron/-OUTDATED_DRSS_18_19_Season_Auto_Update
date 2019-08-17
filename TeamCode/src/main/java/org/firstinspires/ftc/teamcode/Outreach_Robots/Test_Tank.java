package org.firstinspires.ftc.teamcode.Outreach_Robots;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@Autonomous(name = "Test_Tank", group= "team")
public class Test_Tank extends LinearOpMode {


    Servo Servoleft;
    Servo Servoright;
    DcMotor Relay;


    @Override
    public void runOpMode() {
        Servoleft = hardwareMap.servo.get("Servoleft");
        Servoleft.setDirection(Servo.Direction.FORWARD);

        Servoright = hardwareMap.servo.get("Servoright");
        Servoright.setDirection(Servo.Direction.FORWARD);

        // Relay = hardwareMap.dcMotor.get("Relay");
        // Relay.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        double joy = gamepad1.left_stick_y;

        double Valadjust = joy / 2 + 0.5;

        double Joy = gamepad1.right_stick_y;

        double ValAdjust = Joy / 2 + 0.5;

        Servoright.setPosition(ValAdjust);
        Servoleft.setPosition(Valadjust);


    }

}