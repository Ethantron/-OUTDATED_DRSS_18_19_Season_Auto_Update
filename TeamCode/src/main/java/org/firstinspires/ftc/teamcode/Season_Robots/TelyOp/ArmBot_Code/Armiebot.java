//Allows the code to access the FTC code and imports codes.

package org.firstinspires.ftc.teamcode.Season_Robots.TelyOp.ArmBot_Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


//Introduces each programmable part of the robot.


@TeleOp(name = "Armiebot", group= "Armbot_Code")
public class Armiebot extends LinearOpMode {
    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor shoulder;
    DcMotor elbow;
    DcMotor spinner;
    DcMotor LiftArm;
    Servo plunger;
    DigitalChannel FullOpen;
    DigitalChannel FullClosed;
    //com.qualcomm.robotcore.hardware.ColorSensor ColorSensor;
    AnalogInput Potent;
    double shoulderpower;
    double elbowpower;
    double Speed = 1;
    double Frontleft;
    double Frontright;
    double Backleft;
    double Backright;
    double Fullopen = -1;
    double Fullclosed = 1;

    ElapsedTime ResetTime = new ElapsedTime();


    @Override
    public void runOpMode() {


        //Initilizes the hardware

        motorFrontRight = hardwareMap.dcMotor.get("FR");
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);

        motorFrontLeft = hardwareMap.dcMotor.get("FL");
        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);

        motorBackLeft = hardwareMap.dcMotor.get("BL");
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);

        motorBackRight = hardwareMap.dcMotor.get("BR");
        motorBackRight.setDirection(DcMotor.Direction.REVERSE);

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

        FullOpen = hardwareMap.get(DigitalChannel.class, "FullOpen");
        FullOpen.setMode(DigitalChannel.Mode.INPUT);

        FullClosed = hardwareMap.get(DigitalChannel.class, "FullClosed");
        FullClosed.setMode(DigitalChannel.Mode.INPUT);

        //ColorSensor = hardwareMap.colorSensor.get("CS");

        // left stick controls direction
        // right stick X controls rotation

        waitForStart();

        while (opModeIsActive()) {

            float gamepad1LeftY = gamepad1.left_stick_y;
            float gamepad1LeftX = -gamepad1.left_stick_x;
            float gamepad1RightX = gamepad1.right_stick_x;

            // Mechanum formulas

            double FrontLeft = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
            double FrontRight = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
            double BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
            double BackLeft = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;

            // clip the right/left values so that the values never exceed +/- 1


            // sets motors to equal values
            motorFrontRight.setPower(Frontright);
            motorFrontLeft.setPower(Frontleft);
            motorBackLeft.setPower(Backleft);
            motorBackRight.setPower(Backright);

            // sets speed
            Frontright = Range.clip(Math.pow(FrontRight, 3), -Speed, Speed);
            Frontleft = Range.clip(Math.pow(FrontLeft, 3), -Speed, Speed);
            Backright = Range.clip(Math.pow(BackRight, 3), -Speed, Speed);
            Backleft = Range.clip(Math.pow(BackLeft, 3), -Speed, Speed);

            //changes speed to full
            if (gamepad1.left_bumper) {
                Speed = 1.0;
            }

            //Sets speed to minimum speed for strafing
            if (gamepad1.right_bumper) {
                Speed = 0.35;
            }

            //lowers the lift arm
            if (gamepad1.a) {
                LiftArm.setPower(-1.0);
            }

            //raises the lift arm
            if (gamepad1.y) {
                LiftArm.setPower(1.0);
            }

            // sets lift arm to not move if there is no input
            if (!gamepad1.a && !gamepad1.y) {
                LiftArm.setPower(0);
            }

            //sets shoulder and elbow joint speed
            shoulderpower = Range.clip(Math.pow(gamepad2.left_stick_y, 3), -1, 1);
            elbowpower = Range.clip(Math.pow(gamepad2.right_stick_y, 3), Fullopen, Fullclosed);

            shoulder.setPower(shoulderpower);
       //     elbow.setPower(elbowpower);

            //Button that stops the elbow when it is fully open
            if (FullOpen.getState() == false){
                Fullopen = 0;
            }
            else {
                Fullopen = -1;
            }

            //Button that stops the elbow when it is fully closed
            if (FullClosed.getState() == true){
                Fullclosed = 0;
            }
            else {
                Fullclosed = 1;
            }

            //Has the shoulder move forward slowly when within a certain amount to safely lower shoulder
            if (Potent.getVoltage() > 1.5 && Potent.getVoltage() < 1.9 && gamepad2.left_stick_y > 0 ) {
                shoulder.setPower(-.005);
            }
            else{
                elbow.setPower(elbowpower);
            }

     /*       if (gamepad2.right_bumper) {
                elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            } */

            if (gamepad2.b) {
                spinner.setPower(.4);
                spinner.setDirection(DcMotorSimple.Direction.FORWARD);

            }

            if (gamepad2.x) {
                spinner.setPower(1.0);
                spinner.setDirection(DcMotorSimple.Direction.REVERSE);
            }

            if (gamepad2.y) {
                spinner.setPower(.75);
                spinner.setDirection(DcMotorSimple.Direction.FORWARD);
            }

            if (!gamepad2.x && !gamepad2.b && !gamepad2.y) {
                spinner.setPower(0);
            }

            if (gamepad2.left_trigger > .3) {
                plunger.setPosition(1);
            }

            if (gamepad2.right_trigger > .3) {
                plunger.setPosition(0);
            }

            if (gamepad2.right_trigger < .3 && gamepad2.left_trigger < .3) {
                plunger.setPosition(.5);
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
            //telemetry.addData("Clear", ColorSensor.alpha());
           // telemetry.addData("Red  ", ColorSensor.red());
           // telemetry.addData("Green", ColorSensor.green());
           // telemetry.addData("Blue ", ColorSensor.blue());
            telemetry.update();

        }
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

