package org.firstinspires.ftc.teamcode.Season_Robots.Tests.AGagne_Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous(name="SpinnyCode", group="Autonomous")
public class Spinny extends LinearOpMode {

    DcMotor FrontLeft;
    DcMotor FrontRight;
    DcMotor BackLeft;
    DcMotor BackRight;

    double drivePower = .5;

    @Override
    public void runOpMode() throws InterruptedException {

        //Initialize the Robot
        FrontLeft = hardwareMap.dcMotor.get("FL");
        FrontRight = hardwareMap.dcMotor.get("FR");
        BackLeft = hardwareMap.dcMotor.get("BL");
        BackRight = hardwareMap.dcMotor.get("BR");

        FrontLeft.setDirection(DcMotor.Direction.REVERSE); //Note to Ethan: I saw in one of the tutorials that you want to reverse one side, don't know if it is true?
        BackLeft.setDirection(DcMotor.Direction.REVERSE); //Note to Ethan: I saw in one of the tutorials that you want to reverse one side, don't know if it is true?
        //End of Initialization

        waitForStart();
        //Starts when start button is pressed

        //Start of Spinning
        FrontLeft.setPower(.25);
        FrontRight.setPower(-.25);
        BackLeft.setPower(.25);
        BackRight.setPower(-.25);

        sleep(5000); //Robot will spin for 5 seconds

        //Cuts Power to Wheels
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
        //End of Spinning

        sleep(500); //Robot will wait for .5 seconds

        //Start of Wall Slam
        FrontLeft.setPower(drivePower);
        FrontRight.setPower(drivePower);
        BackLeft.setPower(drivePower);
        BackRight.setPower(drivePower);

        sleep(2500); //Will drive into wall for 2.5 Seconds

        //Cuts Power to Wheels
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
        //End of Wall Slam

        //Start of BackUp
        FrontLeft.setPower(-drivePower);
        FrontRight.setPower(-drivePower);
        BackLeft.setPower(-drivePower);
        BackRight.setPower(-drivePower);

        sleep(500); //Robot will BackUp for .5 seconds
        //End of BackUp

        //Start of Rotate
        FrontLeft.setPower(drivePower);
        FrontRight.setPower(-drivePower);
        BackLeft.setPower(drivePower);
        BackRight.setPower(-drivePower);

        sleep(1000); //This is intended to rotate toward the crater, will need tweaking. (1 sec is guess)

        //Cuts Power to Wheels
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
        //End of Rotate

        //Start of Crater Launch
        FrontLeft.setPower(1);
        FrontRight.setPower(1);
        BackLeft.setPower(1);
        BackRight.setPower(1);

        sleep(5000); //Robot will drive towards crater for 5 seconds. Will need tweaking

        //Cuts Power to Wheels
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
        //End of Crater Launch

        //Start of Victory Dance
        FrontLeft.setPower(drivePower);
        FrontRight.setPower(-drivePower);
        BackLeft.setPower(drivePower);
        BackRight.setPower(-drivePower);

        sleep(10000); //Robot will spin for 10 seconds

        //Cuts Power to Wheels
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
        //End of Victory Dance
    }
}
