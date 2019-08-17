package org.firstinspires.ftc.teamcode.Season_Robots.TelyOp.Modular_Drivetrains;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;




@TeleOp(name="Modular_Tank_Drive", group="Modular_Drivetrains")
public class Modular_Tank_Drive extends OpMode {


    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;

    double setpower = 1.0;
    double leftMotorPower = 1.0;
    double rightMotorPower = 1.0;


    public void init() {


        FL = hardwareMap.dcMotor.get("FL");
        FL.setDirection(DcMotorSimple.Direction.FORWARD);

        FR = hardwareMap.dcMotor.get("FR");
        FR.setDirection(DcMotorSimple.Direction.REVERSE);

        BL = hardwareMap.dcMotor.get("BL");
        BL.setDirection(DcMotorSimple.Direction.FORWARD);

        BR = hardwareMap.dcMotor.get("BR");
        BR.setDirection(DcMotorSimple.Direction.REVERSE);

    }


    public void loop() {



        if (gamepad1.a) {
            setpower = 1.0;
        }

        if (gamepad1.b) {
            setpower = 0.75;
        }

        if (gamepad1.x) {
            setpower = 0.5;
        }

        if (gamepad1.y) {
            setpower = 0.25;
        }



        leftMotorPower = Range.clip(Math.pow(gamepad1.left_stick_y, 3), -setpower, setpower);
        rightMotorPower = Range.clip(Math.pow(gamepad1.right_stick_y, 3), -setpower, setpower);



        FL.setPower(leftMotorPower);
        FR.setPower(rightMotorPower);
        BL.setPower(leftMotorPower);
        BR.setPower(rightMotorPower);


        telemetry.addData("Current Speed", setpower);
        telemetry.update();

    }
}