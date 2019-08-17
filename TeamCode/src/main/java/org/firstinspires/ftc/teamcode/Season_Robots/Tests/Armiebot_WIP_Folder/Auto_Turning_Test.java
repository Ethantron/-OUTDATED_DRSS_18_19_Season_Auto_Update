package org.firstinspires.ftc.teamcode.Season_Robots.Tests.Armiebot_WIP_Folder;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Auto_Turning_Test", group = "Armiebot_WIP_Folder")
public class Auto_Turning_Test extends LinearOpMode {
    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
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

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);

        waitForStart();

        motorFrontRight.setPower(.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .25)) {
            telemetry.addData("Where", "Step : 1 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 3)) {
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .25)) {
            telemetry.addData("Where", "Step : 1 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 3)) {
            telemetry.update();
        }

        motorFrontRight.setPower(.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .5)) {
            telemetry.addData("Where", "Step : 2 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 3)) {
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .5)) {
            telemetry.addData("Where", "Step : 2 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 3)) {
            telemetry.update();
        }

        motorFrontRight.setPower(.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .75)) {
            telemetry.addData("Where", "Step : 3 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 3)) {
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .75)) {
            telemetry.addData("Where", "Step : 3 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 3)) {
            telemetry.update();
        }

        motorFrontRight.setPower(.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1)) {
            telemetry.addData("Where", "Step : 4 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 3)) {
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1)) {
            telemetry.addData("Where", "Step : 4 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 3)) {
            telemetry.update();
        }

        motorFrontRight.setPower(.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1.25)) {
            telemetry.addData("Where", "Step : 5 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 3)) {
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1.25)) {
            telemetry.addData("Where", "Step : 5 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 3)) {
            telemetry.update();
        }

        motorFrontRight.setPower(.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1.5)) {
            telemetry.addData("Where", "Step : 6 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 3)) {
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1.5)) {
            telemetry.addData("Where", "Step : 6 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 3)) {
            telemetry.update();
        }

        motorFrontRight.setPower(.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1.75)) {
            telemetry.addData("Where", "Step : 7 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 3)) {
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1.75)) {
            telemetry.addData("Where", "Step : 7 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 3)) {
            telemetry.update();
        }

        motorFrontRight.setPower(.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 2)) {
            telemetry.addData("Where", "Step : 8 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 3)) {
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 2)) {
            telemetry.addData("Where", "Step : 8 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 9999999)) {
            telemetry.addData("Where", "Step : DONE %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
    }
}
