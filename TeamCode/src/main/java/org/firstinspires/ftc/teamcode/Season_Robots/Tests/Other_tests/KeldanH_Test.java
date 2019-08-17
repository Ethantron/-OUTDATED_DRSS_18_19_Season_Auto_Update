package org.firstinspires.ftc.teamcode.Season_Robots.Tests.Other_tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="KeldanH_Test", group="Autonomous")
public class KeldanH_Test extends LinearOpMode{

    DcMotor FrontLeft;
    DcMotor FrontRight;
    DcMotor BackLeft;
    DcMotor BackRight;
    double currentstep = 0;
    double drivePower = .5;

    @Override
    public void runOpMode() {

        //Initialize the Robot
        FrontLeft = hardwareMap.dcMotor.get("FL");
        FrontRight = hardwareMap.dcMotor.get("FR");
        BackLeft = hardwareMap.dcMotor.get("BL");
        BackRight = hardwareMap.dcMotor.get("BR");

        FrontLeft.setDirection(DcMotor.Direction.REVERSE);
        BackLeft.setDirection(DcMotor.Direction.REVERSE);
        //apparently supposed to reverse one side. worked for antoine

        waitForStart();

    if (currentstep == 0){
       FrontLeft.setPower(drivePower);
       BackLeft.setPower(drivePower);
       FrontRight.setPower(drivePower);
       BackRight.setPower(drivePower);
      currentstep++;
  }
//move forward
    if (currentstep == 1) {
        sleep(2000);
        currentstep++;
   }
//previous step lasts 2 seconds
    if (currentstep == 2){
       FrontRight.setPower(drivePower);
       FrontLeft.setPower(-drivePower);
       BackRight.setPower(drivePower);
       BackLeft.setPower(-drivePower);
       currentstep++;
   }
//turn left
    if (currentstep == 3){
       sleep(900);
       currentstep++;
    }
//previous step lasts 1.2 seconds
    if (currentstep == 4){
       FrontLeft.setPower(drivePower);
       BackLeft.setPower(drivePower);
       FrontRight.setPower(drivePower);
       BackRight.setPower(drivePower);
      currentstep++;
  }
//move forward
    if (currentstep == 5) {
        sleep(2000);
        currentstep++;
   }
//previous step lasts 2 seconds


    if (currentstep == 6) {
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
    }
//Stops robot at end of program











    }
}
