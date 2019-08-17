package org.firstinspires.ftc.teamcode.Season_Robots.Tests.AGagne_Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous (name = "CameraControlAuto", group = "Autonomous")
public class Autonomous_WithCameraControl extends LinearOpMode {

    DcMotor FrontLeft;
    DcMotor FrontRight;
    DcMotor BackLeft;
    DcMotor BackRight;
    Servo Pan;
    Servo Tilt;

    double DrivePower = .5;

    @Override
    public void runOpMode() throws InterruptedException {

        //Initializes The Robot
        FrontLeft = hardwareMap.dcMotor.get("FL");
        FrontLeft.setDirection(DcMotor.Direction.REVERSE);

        FrontRight = hardwareMap.dcMotor.get("FR");
        FrontRight.setDirection(DcMotor.Direction.FORWARD);

        BackLeft = hardwareMap.dcMotor.get("BL");
        BackLeft.setDirection(DcMotor.Direction.REVERSE);

        BackRight = hardwareMap.dcMotor.get("BR");
        BackRight.setDirection(DcMotor.Direction.FORWARD);

        Pan = hardwareMap.servo.get("Pan");
        Tilt = hardwareMap.servo.get("Tilt");

        Tilt.setPosition(0.5);
        sleep(250); //Sleep Used to give Time For Servo to Initialize
        //End of Initialization

        waitForStart();
            //Starts when play button is pressed

            //Beginning Of Camera Move
            /*Tilt.setPosition(.75);
            Pan.setPosition(1);

            sleep(25); //Camera Will Turn Clockwise for .025 Seconds
            sleep(1000); //Camera Will "Scan" for 1 Second

            Pan.setPosition(0);

            sleep(35); //Camera will Turn CounterClockwise for .035 Seconds
            sleep(1000); //Camera Will "Scan" for 1 Second

            Pan.setPosition(0);

            sleep(25); //Camera will Turn Clockwise for .025 Seconds
            sleep(1000); //Camera Will "Scan" for 1 Second

            Tilt.setPosition(.5);
            Pan.setPosition(1);

            sleep(35); // Camera will Turn Clockwise for .035 Seconds
            sleep(1000); // Camera will Wait for 1 Second*/
            //End Of Camera Move

            //Beginning Of Move Forward
            FrontLeft.setPower(DrivePower);
            FrontRight.setPower(DrivePower);
            BackLeft.setPower(DrivePower);
            BackRight.setPower(DrivePower);

            sleep(1500); //Robot Will Move Forward For 1.5 Seconds

            FrontLeft.setPower(0);
            FrontRight.setPower(0);
            BackLeft.setPower(0);
            BackRight.setPower(0);
            //End Or Move Forward. Robot should be partially in the Depot

            //Beginning of Backup
            /*FrontLeft.setPower(-DrivePower);
            FrontRight.setPower(-DrivePower);
            BackLeft.setPower(-DrivePower);
            BackRight.setPower(-DrivePower);

            sleep(500); //Robot Will Backup For .5 Second

            FrontLeft.setPower(0);
            FrontRight.setPower(0);
            BackLeft.setPower(0);
            BackRight.setPower(0);*/
            //End of Backup

            //Beginning of Clockwise Turn
            FrontLeft.setPower(DrivePower);
            FrontRight.setPower(-DrivePower);
            BackLeft.setPower(DrivePower);
            BackRight.setPower(-DrivePower);

            sleep(575); //Robot Will Turn For .575 Second

            FrontLeft.setPower(0);
            FrontRight.setPower(0);
            BackLeft.setPower(0);
            BackRight.setPower(0);
            //End of Clockwise Turn

            //Beginning Of Move Forward
            FrontLeft.setPower(DrivePower);
            FrontRight.setPower(DrivePower);
            BackLeft.setPower(DrivePower);
            BackRight.setPower(DrivePower);

            sleep(2750); //Robot Will Move Forward For 2.75 Seconds

            FrontLeft.setPower(0);
            FrontRight.setPower(0);
            BackLeft.setPower(0);
            BackRight.setPower(0);
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
