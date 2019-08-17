package org.firstinspires.ftc.teamcode.Season_Robots.Tests.AGagne_Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous (name = "EncoderAuto", group = "Autonomous")
public class Autonomous_Encoder extends LinearOpMode {

    DcMotor FrontLeft;
    DcMotor FrontRight;
    DcMotor BackLeft;
    DcMotor BackRight;
    Servo Pan;
    Servo Tilt;

    double DrivePower = .25;
    double SingleR = 1120;

    @Override
    public void runOpMode() throws InterruptedException {

        //Initializes The Robot
        FrontLeft = hardwareMap.dcMotor.get("FL");
        FrontLeft.setDirection(DcMotor.Direction.REVERSE);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        FrontRight = hardwareMap.dcMotor.get("FR");
        FrontRight.setDirection(DcMotor.Direction.FORWARD);
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        BackLeft = hardwareMap.dcMotor.get("BL");
        BackLeft.setDirection(DcMotor.Direction.REVERSE);
        BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        BackRight = hardwareMap.dcMotor.get("BR");
        BackRight.setDirection(DcMotor.Direction.FORWARD);
        BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Pan = hardwareMap.servo.get("Pan");
        Tilt = hardwareMap.servo.get("Tilt");

        Tilt.setPosition(0.5);
        sleep(250); //Sleep Used to give Time For Servo to Initialize
        //End of Initialization

        waitForStart();
        //Starts when play button is pressed

        //Beginning Of Move Forward
            //Reset Encoders
            FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //Set Position
            FrontLeft.setTargetPosition((int) (SingleR*9.55)); //Move 5 Feet w/ 2" Wheels
            FrontRight.setTargetPosition((int) (SingleR*9.55)); //Move 5 Feet w/ 2" Wheels
            BackLeft.setTargetPosition((int) (SingleR*9.55)); //Move 5 Feet w/ 2" Wheels
            BackRight.setTargetPosition((int) (SingleR*9.55)); //Move 5 Feet w/ 2" Wheels
            //Run To Position
            FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //Set Drive Power
            FrontLeft.setPower(DrivePower);
            FrontRight.setPower(DrivePower);
            BackLeft.setPower(DrivePower);
            BackRight.setPower(DrivePower);
            //While Motors are running
            while (FrontLeft.isBusy() && FrontRight.isBusy() && BackLeft.isBusy() && BackRight.isBusy()) {
                telemetry.addData("Status", "Running Encoders");
                telemetry.update();
            }
            //Stop Motors Reset Encoders
            FrontLeft.setPower(0);
            FrontRight.setPower(0);
            BackLeft.setPower(0);
            BackRight.setPower(0);

            FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //End Or Move Forward. Robot should be partially in the Depot

        //Beginning of Clockwise Turn
        FrontLeft.setPower(DrivePower);
        FrontRight.setPower(-DrivePower);
        BackLeft.setPower(DrivePower);
        BackRight.setPower(-DrivePower);

        sleep(575); //Robot Will Turn For .575 Seconds

        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
        //End of Clockwise Turn

        //Beginning Of Move Forward
            //Reset Encoders
            FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //Set Position
            FrontLeft.setTargetPosition((int) (SingleR*(9.55*2))); //Move 10 Feet w/ 2" Wheels
            FrontRight.setTargetPosition((int) (SingleR*(9.55*2))); //Move 10 Feet w/ 2" Wheels
            BackLeft.setTargetPosition((int) (SingleR*(9.55*2))); //Move 10 Feet w/ 2" Wheels
            BackRight.setTargetPosition((int) (SingleR*(9.55*2))); //Move 10 Feet w/ 2" Wheels
            //Run To Position
            FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //Set Drive Power
            FrontLeft.setPower(DrivePower);
            FrontRight.setPower(DrivePower);
            BackLeft.setPower(DrivePower);
            BackRight.setPower(DrivePower);
            //While Motors are running
            while (FrontLeft.isBusy() && FrontRight.isBusy() && BackLeft.isBusy() && BackRight.isBusy()) {
                telemetry.addData("Status", "Running Encoders");
                telemetry.update();
            }
            //Stop Motors Reset Encoders
            FrontLeft.setPower(0);
            FrontRight.setPower(0);
            BackLeft.setPower(0);
            BackRight.setPower(0);

            FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //End Or Move Forward

        //Beginning of Victory Turn
        FrontLeft.setPower(-DrivePower);
        FrontRight.setPower(DrivePower);
        BackLeft.setPower(-DrivePower);
        BackRight.setPower(DrivePower);

        sleep(2500); //Robot Will Turn For 2.5 Seconds

        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
        //End of Victory Turn
    }
}
