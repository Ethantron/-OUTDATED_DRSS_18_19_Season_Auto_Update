package org.firstinspires.ftc.teamcode.Season_Robots.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous (name = "AutoArmieDepotWIP: MarkerPark", group = "Armiebot_Tests_Food")
public class AutoArmieDepotMarker extends LinearOpMode {

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor shoulder;
    DcMotor elbow;
    DcMotor LiftArm;
    DcMotor spinner;

    ElapsedTime ResetTime = new ElapsedTime();

    @Override
    public void runOpMode() {

        motorFrontRight = hardwareMap.dcMotor.get("FR");
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);

        motorFrontLeft = hardwareMap.dcMotor.get("FL");
        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);

        motorBackLeft = hardwareMap.dcMotor.get("BL");
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);

        motorBackRight = hardwareMap.dcMotor.get("BR");
        motorBackRight.setDirection(DcMotor.Direction.REVERSE);

        LiftArm = hardwareMap.dcMotor.get("LA");
        LiftArm.setDirection(DcMotor.Direction.FORWARD);

        spinner = hardwareMap.dcMotor.get("SP");
        spinner.setDirection(DcMotorSimple.Direction.FORWARD);

        shoulder = hardwareMap.dcMotor.get("SH");
        shoulder.setDirection(DcMotorSimple.Direction.FORWARD);

        elbow = hardwareMap.dcMotor.get("EL");
        elbow.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        LiftArm.setPower(1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 5.25)) {
            telemetry.addData("Where", "Step 1: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        LiftArm.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .5)) {
            telemetry.addData("Where", "Step 2: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.7);
        motorFrontLeft.setPower(-.4);
        motorBackLeft.setPower(.7);
        motorBackRight.setPower(.4);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .6)) {
            telemetry.addData("Where", "Step 3: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .5)) {
            telemetry.addData("Where", "Step 4: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .075)) {
            telemetry.addData("Where", "Step 5: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(.7);
        motorFrontLeft.setPower(.4);
        motorBackLeft.setPower(-.7);
        motorBackRight.setPower(-.4);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .8)) {
            telemetry.addData("Where", "Step 6: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        //Beginning of Deploy

        motorFrontRight.setPower(.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .4)) {
            telemetry.addData("Where", "Step 7: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1)) {
            telemetry.addData("Where", "Step 8:  %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .75)) {
            telemetry.addData("Where", "Step 9: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .7)) {
            telemetry.addData("Where", "Step 10: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        spinner.setPower(1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1)) {
            telemetry.addData("Where", "Step 11: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        //Beginning of Parking


        spinner.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .05)) {
            telemetry.addData("Where", "Step 12: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1.5)) {
            telemetry.addData("Where", "Step 13: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        LiftArm.setPower(-1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 5)) {
            telemetry.addData("Where", "Step 14: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        shoulder.setPower(0);
        elbow.setPower(0);
        LiftArm.setPower(-0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 99999)) {
            telemetry.addData("Where", "Step 16: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
    }
}
