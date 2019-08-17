package org.firstinspires.ftc.teamcode.Season_Robots.Tests.Armiebot_WIP_Folder;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorAdafruitRGB;
@Disabled
@TeleOp (name = "Testing_Armiebot", group = "Armiebot_WIP_Folder")
public class Testing_Armiebot extends LinearOpMode {
//Introduces each programmable part of the robot.

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor shoulder;
    DcMotor elbow;
    DcMotor spinner;
    DcMotor LiftArm;
    Servo plunger;
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
    public void runOpMode() {

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

        plunger = hardwareMap.servo.get("PL");
        plunger.setDirection(Servo.Direction.FORWARD);

        waitForStart();

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

        shoulderpower = Range.clip(Math.pow(gamepad2.left_stick_y, 3), -1,1);
        elbowpower = Range.clip(Math.pow(gamepad2.right_stick_y, 3), -1, 1);

        // write the values to the motors
        motorFrontRight.setPower(Frontright);
        motorFrontLeft.setPower(Frontleft);
        motorBackLeft.setPower(Backleft);
        motorBackRight.setPower(Backright);

        Frontright = Range.clip(Math.pow(FrontRight, 3), -Speed,Speed);
        Frontleft = Range.clip(Math.pow(FrontLeft, 3), -Speed,Speed);
        Backright = Range.clip(Math.pow(BackRight, 3), -Speed,Speed);
        Backleft = Range.clip(Math.pow(BackLeft, 3), -Speed,Speed);

        if(gamepad1.left_bumper){
            Speed = 1.0;
        }

        if (gamepad1.right_bumper){
            Speed = 0.5;
        }

        // Sets the motors to the pre-defined values.

        shoulder.setPower(shoulderpower);
        elbow.setPower(elbowpower);



        if (gamepad2.a){
            elbow.getCurrentPosition();
            elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elbow.setTargetPosition(4278);
            elbow.setPower(-1);
            shoulder.setPower(-1);
        }

        if (Potent.getVoltage() == 1){
            shoulder.setPower(.2);
        }

        if (gamepad2.right_bumper){
            elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        if (gamepad2.b){
            spinner.setPower(.4);
            spinner.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        if (gamepad2.x){
            spinner.setPower(1);
            spinner.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (!gamepad2.x && !gamepad2.b){
            spinner.setPower(0);
        }

        if (gamepad2.y){
            spinner.setPower(.75);
            spinner.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        if (gamepad1.y){
            LiftArm.setPower(1.0);
        }

        if (gamepad1.a){
            LiftArm.setPower(-1.0);
        }

        if (!gamepad1.a && !gamepad1.y){
            LiftArm.setPower(0);
        }

        /*
         * Telemetry for debugging
         */
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Potentiometer", Potent.getVoltage());
        telemetry.addData("Elbow speed", elbow.getPower());
        telemetry.addData("Path", shoulder.getCurrentPosition() + " " + elbow.getCurrentPosition());
        telemetry.addData("Target", elbow.getTargetPosition());
        telemetry.addData("Speed", Speed);
        telemetry.update();



    }

    /*
     * This method scales the joystick input so for low joystick values, the
     * scaled value is less than linear.  This is to make it easier to drive
     * the robot more precisely at slower speeds.
     */
    public double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }

}

