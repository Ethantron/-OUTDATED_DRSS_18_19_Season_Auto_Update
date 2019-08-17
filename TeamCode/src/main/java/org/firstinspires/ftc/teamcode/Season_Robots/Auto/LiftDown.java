package org.firstinspires.ftc.teamcode.Season_Robots.Auto;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "LiftDown", group = "Auto")
public class LiftDown extends LinearOpMode {

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
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

        waitForStart();

        LiftArm.setPower(-1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 10)) {
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
            telemetry.addData("Where", "Step 2: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .5)) {
            telemetry.addData("Where", "Step 2: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        LiftArm.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 5)) {
            telemetry.addData("Where", "Right Step : 9 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        LiftArm.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 99999)) {
            telemetry.addData("Where", "Right Step : .60 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
    }
}
