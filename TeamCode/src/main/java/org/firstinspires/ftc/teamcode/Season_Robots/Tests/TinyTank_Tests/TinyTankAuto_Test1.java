package org.firstinspires.ftc.teamcode.Season_Robots.Tests.TinyTank_Tests;

import com.qualcomm.robotcore.eventloop.EventLoop;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Math.abs;
@Disabled
@Autonomous(name = "TinyTankAuto_Test1", group= "TinyTank_Tests")
public class TinyTankAuto_Test1 extends LinearOpMode {
    Servo Servoleft;
    Servo Servoright;
    DcMotor Light;
    double step = 1;
    Servo Pan;
    Servo Tilt;
//    double Rightup = abs(Servoright.getPosition() + 0.0075);

    public void runOpMode() {
        Servoleft = hardwareMap.servo.get("Servoleft");
        Servoleft.setDirection(Servo.Direction.FORWARD);

        Servoright = hardwareMap.servo.get("Servoright");
        Servoright.setDirection(Servo.Direction.FORWARD);

        Light = hardwareMap.dcMotor.get("RedLight");
        Light.setDirection(DcMotorSimple.Direction.FORWARD);

        Pan = hardwareMap.servo.get("Pan"); //Continuous Rotation Servo
        Tilt = hardwareMap.servo.get("Tilt"); //180 Degree Servo

        Tilt.setPosition(0.5); //Sets servo into "Zero" position
        Pan.setPosition(0.5);
        Servoright.setPosition(0.5);
        Servoleft.setPosition(0.5);
        Light.setPower(-1);

        waitForStart();

        go();
        go();
        go();
        go();
        go();
        go();
        go();
        go();
        go();
        go();
        go();
        go();
        go();
        go();
        go();
        go();
        go();
        go();
        go();
        go();
        go();
        go();


  /*      if (step == 0) {
            Servoleft.setPosition(-.7);
            Servoright.setPosition(-.7);
            step++;
        }

        if (step == 1) {
            sleep(2000);
            step++;
        }

        if (step == 2) {
            Servoleft.setPosition(0);
            Servoright.setPosition(0);
            step++;
        }

        if (step == 3) {
            Servoleft.setPosition(.7);
            Servoright.setPosition(-.7);
            step++;
        }

        if (step == 4) {
            sleep(1250);
            step++;
        }

        if (step == 5) {
            Servoleft.setPosition(0);
            Servoright.setPosition(0);
            step++;
        }

        if (step == 6) {
            Servoleft.setPosition(-.7);
            Servoright.setPosition(-.7);
            step++;
        }

        if (step == 7) {
            sleep(2000);
            step++;
        }

        if (step == 8) {
            Servoleft.setPosition(0);
            Servoright.setPosition(0);
            step++;
        }

        if (step == 9) {
            Servoleft.setPosition(.7);
            Servoright.setPosition(-.7);
            step++;
        }

        if (step == 10) {
            sleep(1250);
            step++;
        }

        if (step == 11) {
            Servoleft.setPosition(0);
            Servoright.setPosition(0);
            step = ++;
        }

        if (step == 12) {
            Servoleft.setPosition(-.7);
            Servoright.setPosition(-.7);
            step++;
        }

        if (step == 13) {
            sleep(5000);
            step++;
        }

        if (step == 14) {
            Servoright.setPosition(0);
            Servoleft.setPosition(0);
        }*/
    }
    private void go(){ //Go loop that runs program
        if (step == -1){

        }

        if (step == 0) {
            Servoleft.setPosition(-.7);
            Servoright.setPosition(-.7);
            step++;
        }

        if (step == 1) {
            sleep(2000);
            step++;
        }

        if (step == 3) {
            Servoleft.setPosition(0.5);
            Servoright.setPosition(-.7);
            step++;
        }

        if (step == 4) {
            sleep(2120);
            step++;
        }

        if (step == 5) {
            Servoleft.setPosition(0);
            Servoright.setPosition(0);
            step++;
        }

        if (step == 6) {
            Servoleft.setPosition(-.7);
            Servoright.setPosition(-.7);
            step++;
        }

        if (step == 7) {
            sleep(2000);
            step++;
        }

        if (step == 8) {
            Servoleft.setPosition(0);
            Servoright.setPosition(0);
            step++;
        }

        if (step == 9) {
            Servoleft.setPosition(.5);
            Servoright.setPosition(-.7);
            step++;
        }

        if (step == 10) {
            sleep(2120);
            step++;
        }

        if (step == 11) {
            Servoleft.setPosition(0);
            Servoright.setPosition(0);
            step = 0;
        }



    }

}
