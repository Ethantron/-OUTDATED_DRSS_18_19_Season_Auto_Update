package org.firstinspires.ftc.teamcode.Season_Robots.Tests.Armiebot_WIP_Folder;

import android.app.Activity;

import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@Autonomous(name= "AutoArmieCrater_WIP", group = "Armiebot_Tests_Food")
public class AutoArmieCrater_WIP extends LinearOpMode {

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor LiftArm;
    DcMotor spinner;

    double currentstep = 0;
    View relativeLayout;

    ElapsedTime ResetTime = new ElapsedTime();

    @Override
    public void runOpMode() {

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        float[] hsvValues = new float[3];
        final float values[] = hsvValues;

        motorFrontRight = hardwareMap.dcMotor.get("FR");
        motorFrontRight.setDirection(DcMotor.Direction.FORWARD);

        motorFrontLeft = hardwareMap.dcMotor.get("FL");
        motorFrontLeft.setDirection(DcMotor.Direction.FORWARD);

        motorBackLeft = hardwareMap.dcMotor.get("BL");
        motorBackLeft.setDirection(DcMotor.Direction.FORWARD);

        motorBackRight = hardwareMap.dcMotor.get("BR");
        motorBackRight.setDirection(DcMotor.Direction.FORWARD);

        LiftArm = hardwareMap.dcMotor.get("LA");
        LiftArm.setDirection(DcMotor.Direction.FORWARD);

        spinner = hardwareMap.dcMotor.get("SP");
        spinner.setDirection(DcMotorSimple.Direction.FORWARD);



        waitForStart();

        LiftArm.setPower(-1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 9.5)) {
            telemetry.addData("Where", "Step 1: %2.5f S Elapsed", ResetTime.seconds());




        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .4)) {
            telemetry.addData("Where", "Step 2: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 3.5)) {
            telemetry.addData("Where", "Step 3: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .75)) {
            telemetry.addData("Where", "Step 4: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////
        motorFrontRight.setPower(-1.1);
        motorFrontLeft.setPower(-1.1);
        motorBackLeft.setPower(-1.1);
        motorBackRight.setPower(-1.1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .5)) {
            telemetry.addData("Where", "Step 5: %2.5f S Elapsed", ResetTime.seconds());

        }

        sleep(2000);

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .5) && hsvValues[1] >= .8) {
            telemetry.addData("Where", "Step 6: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.addData("S", "%.3f", hsvValues[1]);
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .5) && hsvValues[1] <= .6) {
            telemetry.addData("Where", "Step 7: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.addData("S", "%.3f", hsvValues[1]);
            telemetry.update();
        }


        motorFrontRight.setPower(1.1);
        motorFrontLeft.setPower(1.1);
        motorBackLeft.setPower(1.1);
        motorBackRight.setPower(1.1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .5)) {
            telemetry.addData("Where", "Step 8: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////
        motorFrontRight.setPower(.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .75)) {
            telemetry.addData("Where", "Step 4: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1.25)) {
            telemetry.addData("Where", "Step 5: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .5)) {
            telemetry.addData("Where", "Step 6: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1)) {
            telemetry.addData("Where", "Step 6: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 2.2)) {
            telemetry.addData("Where", "Step 7: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        spinner.setPower(.8);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .3)) {
            telemetry.addData("Where", "Step 8: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
            if (opModeIsActive() && ResetTime.seconds() >= .3) {
                spinner.setPower(0.0);
            }
        }

        motorFrontRight.setPower(.9);
        motorFrontLeft.setPower(-.8);
        motorBackLeft.setPower(-.8);
        motorBackRight.setPower(.8);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 2.7)) {
            telemetry.addData("Where", "Step 9: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }


    }
}