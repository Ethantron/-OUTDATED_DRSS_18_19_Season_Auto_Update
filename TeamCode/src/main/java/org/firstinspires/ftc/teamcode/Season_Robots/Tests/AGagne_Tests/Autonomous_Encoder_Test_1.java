package org.firstinspires.ftc.teamcode.Season_Robots.Tests.AGagne_Tests;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "AGagne_Encoder/Gyro_Testing", group = "Autonomous")
public class Autonomous_Encoder_Test_1 extends LinearOpMode {

    BNO055IMU Gyro; //Declares REV IMU Gyro Sensor

    DcMotor FrontLeft;
    DcMotor FrontRight;
    DcMotor BackLeft;
    DcMotor BackRight;
    Servo Pan;
    Servo Tilt;

    private ElapsedTime runtime = new ElapsedTime(); //Encoder Run Time
    static final double COUNTS_PER_MOTOR_REV = 1120; //AndyMark Encoder Tick Count
    static final double DRIVE_GEAR_REDUCTION = 1.0; //Gear Reduction
    static final double WHEEL_DIAMETER_INCHES = 4.0; //Diameter of wheels for correct distance
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415); //Counts Per Inch Equation

    double DrivePower = -0.5;

    @Override
    public void runOpMode() throws InterruptedException {

        //Initializes The Robot
        FrontLeft = hardwareMap.dcMotor.get("FL"); //Initialize the Motors
        FrontLeft.setDirection(DcMotor.Direction.REVERSE); //Sets Motor to Reverse

        FrontRight = hardwareMap.dcMotor.get("FR"); //Initialize the Motors
        FrontRight.setDirection(DcMotor.Direction.FORWARD); //Sets Motor to Forward

        BackLeft = hardwareMap.dcMotor.get("BL"); //Initialize the Motors
        BackLeft.setDirection(DcMotor.Direction.REVERSE); //Sets Motor to Reverse

        BackRight = hardwareMap.dcMotor.get("BR"); //Initialize the Motors
        BackRight.setDirection(DcMotor.Direction.FORWARD); //Sets Motor to Forward

        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //Resets Motor Encoder to 0
        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //Resets Motor Encoder to 0
        BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //Resets Motor Encoder to 0
        BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //Resets Motor Encoder to 0

        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //Sets each Motor to run with encoders
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //Sets each Motor to run with encoders
        BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //Sets each Motor to run with encoders
        BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //Sets each Motor to run with encoders

        Pan = hardwareMap.servo.get("Pan"); //Initialize The Servo
        Tilt = hardwareMap.servo.get("Tilt"); //Initialize The Servo

        Tilt.setPosition(0.5); //Set Servo Position to Straight Forward

        if (FrontLeft.getCurrentPosition() > 0 || FrontRight.getCurrentPosition() > 0 || BackLeft.getCurrentPosition() > 0 || BackRight.getCurrentPosition() > 0) { //If Any of the Encoders are Greater Than 0 Then Display Telemetry
            telemetry.addData("Resetting Encoders", "Please Wait"); //Display Message if Statement is true
            telemetry.update(); //Update Telemetry
        }

        if (FrontLeft.getCurrentPosition() <= 0 & FrontRight.getCurrentPosition() <= 0 & BackLeft.getCurrentPosition() <= 0 & BackRight.getCurrentPosition() <= 0) { //If All Encoders are Equal to 0 Then Display Telemetry
            telemetry.addData("Encoders Reset", ""); //Display Message if Statement is true
            telemetry.update(); //Update Telemetry
        }

        //Initializing the Gyro
        /*Gyro = (BNO055IMU) hardwareMap.gyroSensor.get("gyro"); //Initialize the Gyro

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters(); //Set Gyro Parameters to Parameters below

        parameters.mode = BNO055IMU.SensorMode.IMU; //Set Gyro as IMU
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES; //Sets angleUnit to Degrees
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC; //Sets accelerationUnit to Meters per Second per Second
        parameters.loggingEnabled = false; //Disables logging

        Gyro.initialize(parameters); //Initialize Gyro Parameters

        telemetry.addData("Gyro", "Calibrating..."); //Display to driver that Gyro is Calibrating
        telemetry.update(); //Update Telemetry

        // make sure the imu gyro is calibrated before continuing.
        while (!isStopRequested() && !Gyro.isGyroCalibrated()) //Waits for Gyro to Calibrate
        {
            sleep(50);
            idle();
        }

        telemetry.addData("Gyro Calibrated:", "Waiting for Start"); //Tells Driver that Gyro Calibration is done
        telemetry.update(); //Update Telemetry*/

        //End of Initialization

        waitForStart();
        //Starts when play button is pressed

        /*int FrontLeftTarget; //Create int for each motor position target
        int FrontRightTarget; //Create int for each motor position target
        int BackLeftTarget; //Create int for each motor position target
        int BackRightTarget; //Create int for each motor position target*/

        if (opModeIsActive()) { //Checks if opMode is still active

            //Setting Encoder position
            /*FrontLeftTarget = FrontLeft.getCurrentPosition() + (int) (1 * COUNTS_PER_INCH); //Set target position for encoder
            FrontRightTarget = FrontRight.getCurrentPosition() + (int) (1 * COUNTS_PER_INCH); //Set target position for encoder
            BackLeftTarget = BackLeft.getCurrentPosition() + (int) (1 * COUNTS_PER_INCH); //Set target position for encoder
            BackRightTarget = BackRight.getCurrentPosition() + (int) (1 * COUNTS_PER_INCH); //Set target position for encoder*/

            FrontLeft.setTargetPosition(100); //Set motor to target position
            FrontRight.setTargetPosition(100); //Set motor to target position
            BackLeft.setTargetPosition(100); //Set motor to target position
            BackRight.setTargetPosition(100); //Set motor to target position

            //Turn on Run to Position
            FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION); //Tells motors to activate run to position
            FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION); //Tells motors to activate run to position
            BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION); //Tells motors to activate run to position
            BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION); //Tells motors to activate run to position

            //Reset Runtime and Move to position
            runtime.reset(); //Resets Encoder Run Time

            FrontLeft.setPower(DrivePower); //Sets Motor power to DrivePower
            FrontRight.setPower(DrivePower); //Sets Motor power to DrivePower
            BackLeft.setPower(DrivePower); //Sets Motor power to DrivePower
            BackRight.setPower(DrivePower); //Sets Motor power to DrivePower

            //Check encoder position

            while (opModeIsActive() && (runtime.seconds() < 1000000000) && (FrontLeft.isBusy() && FrontRight.isBusy() && BackLeft.isBusy() && BackRight.isBusy())) { //Checks whether opMode is still active, Runtime is not greater than timeout time, and motors are busy.
                telemetry.addData("Encoders are Running", "Please Wait"); //Shows to driver that encoders are running
                telemetry.addData("FL Position", FrontLeft.getCurrentPosition());
                telemetry.addData("FR Position", FrontRight.getCurrentPosition());
                telemetry.addData("BL Position", BackLeft.getCurrentPosition());
                telemetry.addData("BR Position", BackRight.getCurrentPosition());
                telemetry.update(); //Update Telemetry
            }

            //Stop Motion
            FrontLeft.setPower(0); //Sets Motor power to 0
            FrontRight.setPower(0); //Sets Motor power to 0
            BackLeft.setPower(0); //Sets Motor power to 0
            BackRight.setPower(0); //Sets Motor power to 0

            //Turn off run to position
            FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //turns off run to position
            FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //turns off run to position
            BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //turns off run to position
            BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //turns off run to position

            telemetry.addData("Encoders", "Done"); //Tells Driver that encoders are done
            telemetry.update(); //Update Telemetry

        }
    }
}
