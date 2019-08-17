package org.firstinspires.ftc.teamcode.Season_Robots.Tests.BabyBot_Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Button_side", group="Tests")
public class Button_side extends LinearOpMode {
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;
    DigitalChannel button;
    ElapsedTime ResetTime = new ElapsedTime();

    @Override
    public void runOpMode() {

        FL = hardwareMap.dcMotor.get("FL");
        FL.setDirection(DcMotor.Direction.REVERSE);

        FR = hardwareMap.dcMotor.get("FR");
        FR.setDirection(DcMotor.Direction.FORWARD);

        BL = hardwareMap.dcMotor.get("BL");
        BL.setDirection(DcMotor.Direction.REVERSE);

        BR = hardwareMap.dcMotor.get("BR");
        BR.setDirection(DcMotor.Direction.FORWARD);

        button = hardwareMap.get(DigitalChannel.class, "button");
        button.setMode(DigitalChannel.Mode.INPUT);

        waitForStart();

        while (opModeIsActive()){

        if (button.getState() == false){

            FL.setPower(.5);
            FR.setPower(.75);
            BL.setPower(.5);
            BR.setPower(.75);
            ResetTime.reset();
            while (opModeIsActive() && (ResetTime.seconds() < 100)) {
                telemetry.addData("Where", "Step 1: %2.5f S Elapsed", ResetTime.seconds());
                telemetry.update();
            }
        }else{
            FL.setPower(.75);
            FR.setPower(.5);
            BL.setPower(.75);
            BR.setPower(.5);
            ResetTime.reset();
            while (opModeIsActive() && (ResetTime.seconds() < 100)) {
                telemetry.addData("Where", "Step 2: %2.5f S Elapsed", ResetTime.seconds());
                telemetry.update();
            }

        }

        }
    }
}
