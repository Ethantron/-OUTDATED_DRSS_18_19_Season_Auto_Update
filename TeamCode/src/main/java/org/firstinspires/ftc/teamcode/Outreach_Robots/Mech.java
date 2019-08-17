//Allows the code to access the FTC code and imports codes.
package org.firstinspires.ftc.teamcode.Outreach_Robots;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import static android.os.SystemClock.sleep;

@Disabled
@TeleOp(name = "Mech", group= "WIP")
public class Mech extends OpMode {
//Introduces each programmable part of the robot.

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor shoulder;
    DcMotor elbow;
    DcMotor spinner;
    DcMotor LiftArm;
    AnalogInput Potent;
    double shoulderpower;
    double elbowpower;
    double Speed = 1;
    int currentstep = 0;
    double Frontleft;
    double Frontright;
    double Backleft;
    double Backright;

    ElapsedTime ResetTime = new ElapsedTime();


    @Override
    public void init() {

        //Initilizes the hardware

        motorFrontRight = hardwareMap.dcMotor.get("FR");
        motorFrontRight.setDirection(DcMotor.Direction.FORWARD);

        motorFrontLeft = hardwareMap.dcMotor.get("FL");
        motorFrontLeft.setDirection(DcMotor.Direction.FORWARD);

        motorBackLeft = hardwareMap.dcMotor.get("BL");
        motorBackLeft.setDirection(DcMotor.Direction.FORWARD);

        motorBackRight = hardwareMap.dcMotor.get("BR");
        motorBackRight.setDirection(DcMotor.Direction.FORWARD);

        shoulder = hardwareMap.dcMotor.get("SH");
        shoulder.setDirection(DcMotorSimple.Direction.FORWARD);

        elbow = hardwareMap.dcMotor.get("EL");
        elbow.setDirection(DcMotorSimple.Direction.REVERSE);

        spinner = hardwareMap.dcMotor.get("SP");
        spinner.setDirection(DcMotorSimple.Direction.FORWARD);

        LiftArm = hardwareMap.dcMotor.get("LA");
        LiftArm.setDirection(DcMotorSimple.Direction.FORWARD);

        Potent = hardwareMap.analogInput.get("PM");

    }

    @Override
    public void loop() {

        // left stick controls direction
        // right stick X controls rotation

        float gamepad1LeftY = gamepad1.left_stick_y;
        float gamepad1LeftX = -gamepad1.left_stick_x;
        float gamepad1RightX = gamepad1.right_stick_x;

        // Mechanum formulas

        double FrontLeft = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        double FrontRight = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        double BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
        double BackLeft = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;

        // clip the right/left values so that the values never exceed +/- 1

        shoulderpower = Range.clip(Math.pow(gamepad2.left_stick_y, 3), -1, 1);
        elbowpower = Range.clip(Math.pow(gamepad2.right_stick_y, 3), -1, 1);

        // write the values to the motors
        motorFrontRight.setPower(Frontright);
        motorFrontLeft.setPower(Frontleft);
        motorBackLeft.setPower(Backleft);
        motorBackRight.setPower(Backright);

        Frontright = Range.clip(Math.pow(FrontRight, 3), -Speed, Speed);
        Frontleft = Range.clip(Math.pow(FrontLeft, 3), -Speed, Speed);
        Backright = Range.clip(Math.pow(BackRight, 3), -Speed, Speed);
        Backleft = Range.clip(Math.pow(BackLeft, 3), -Speed, Speed);

        if (gamepad1.left_bumper) {
            Speed = 1.0;
        }

        if (gamepad1.right_bumper) {
            Speed = 0.5;
        }

        // Sets the motors to the pre-defined values.

        shoulder.setPower(shoulderpower);
        elbow.setPower(elbowpower);


        if (gamepad2.a) {
            elbow.getCurrentPosition();
            elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elbow.setTargetPosition(4278);
            elbow.setPower(-1);
            shoulder.setPower(-1);
            if (Potent.getVoltage() == 1) {
                shoulder.setPower(.2);
            }
        }

        if (!gamepad2.a) {
            shoulderpower = Range.clip(Math.pow(gamepad2.left_stick_y, 3), -1, 1);
            elbowpower = Range.clip(Math.pow(gamepad2.right_stick_y, 3), -1, 1);
            shoulder.setPower(shoulderpower);
            elbow.setPower(elbowpower);
        }

        if (gamepad2.right_bumper) {
            elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        if (gamepad2.b) {
            spinner.setPower(.4);
            spinner.setDirection(DcMotorSimple.Direction.FORWARD);

        }

        if (gamepad2.x) {
            spinner.setPower(.4);
        }

    }

}