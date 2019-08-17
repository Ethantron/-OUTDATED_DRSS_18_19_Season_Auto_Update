package org.firstinspires.ftc.teamcode.Season_Robots.Tests.Failed_Tests;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name = "Difficult_Mech_Test", group= "Failed_Tests")
public class Difficult_Mech_Test extends OpMode{

    DcMotor FrontleftMotor;
    DcMotor FrontrightMotor;
    DcMotor BackleftMotor;
    DcMotor BackrightMotor;
    Servo TestServo;
    double setspeed = 1.00;

    public void init() {

        FrontleftMotor = hardwareMap.dcMotor.get("FL");
        FrontleftMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        FrontrightMotor = hardwareMap.dcMotor.get("FR");
        FrontrightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        BackleftMotor = hardwareMap.dcMotor.get("BL");
        BackleftMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        BackrightMotor = hardwareMap.dcMotor.get("BR");
        BackrightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        TestServo = hardwareMap.servo.get("Servo");
        TestServo.setDirection(Servo.Direction.FORWARD);
        TestServo.setPosition(1.0);

    }

    public void loop() {

        if (gamepad1.a){
            setspeed = 1.00;
        }
        if (gamepad1.b){
            setspeed = 0.5;
        }
        if (gamepad1.x){
            setspeed = 0.75;
        }
        if (gamepad1.y){
            setspeed = 0.25;
        }

        if (gamepad2.a){
            TestServo.setPosition(1.0);
        }
        if (gamepad2.b){
            TestServo.setPosition(0.0);
        }

        //Forward
        if (gamepad1.left_stick_y > 0.1){
            FrontleftMotor.setPower(setspeed);
            FrontrightMotor.setPower(setspeed);
            BackleftMotor.setPower(setspeed);
            BackrightMotor.setPower(setspeed);
        }

        //Reverse
        if (gamepad1.left_stick_y < -0.1){
            FrontleftMotor.setPower(-setspeed);
            FrontrightMotor.setPower(-setspeed);
            BackleftMotor.setPower(-setspeed);
            BackrightMotor.setPower(-setspeed);
        }

        //Turn Right
        if (gamepad1.left_stick_x < -0.1){
            FrontleftMotor.setPower(setspeed);
            FrontrightMotor.setPower(-setspeed);
            BackleftMotor.setPower(setspeed);
            BackrightMotor.setPower(-setspeed);
        }

        //Turn Left
        if (gamepad1.left_stick_x > 0.1){
            FrontleftMotor.setPower(-setspeed);
            FrontrightMotor.setPower(setspeed);
            BackleftMotor.setPower(-setspeed);
            BackrightMotor.setPower(setspeed);
        }

        //Strafe Left
        if (gamepad1.right_stick_x < 0) {
            FrontleftMotor.setPower(-setspeed-0.35);
            FrontrightMotor.setPower(setspeed+0.35);
            BackleftMotor.setPower(setspeed);
            BackrightMotor.setPower(-setspeed);
        }

        //Strafe Right
        if (gamepad1.right_stick_x > 0){
            FrontleftMotor.setPower(setspeed+0.35);
            FrontrightMotor.setPower(-setspeed-0.35);
            BackleftMotor.setPower(-setspeed);
            BackrightMotor.setPower(setspeed);
        }

        //Stop
        if ((gamepad1.left_stick_y <0.1)&(gamepad1.left_stick_y > -0.1)&
            (gamepad1.left_stick_x <0.1)&(gamepad1.left_stick_x > -0.1)&
            (gamepad1.right_stick_x <0.1)&(gamepad1.right_stick_x > -0.1)){
            FrontleftMotor.setPower(0);
            FrontrightMotor.setPower(0);
            BackleftMotor.setPower(0);
            BackrightMotor.setPower(0);
        }

        telemetry.addData("Speed", setspeed);

    }
}
