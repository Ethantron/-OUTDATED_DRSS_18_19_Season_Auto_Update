package org.firstinspires.ftc.teamcode.Season_Robots.Tests.AGagne_Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp (name = "RobertTheRobot", group = "TeleOp")
public class Robert extends LinearOpMode {

    //Set up the motors
    DcMotor Left;
    DcMotor Right;

    double drivepower = .5;
    double RightMotor = .8;

    @Override
    public void runOpMode() {

        //Initialize the motors
        Left = hardwareMap.dcMotor.get("Lwheel");
        Left.setDirection(DcMotor.Direction.FORWARD);

        Right = hardwareMap.dcMotor.get("Rwheel");
        Right.setDirection(DcMotor.Direction.REVERSE);
        //End of initialization

        waitForStart();
        //Waits for start button to be pressed

        while (opModeIsActive()) {
            //Speed Control
            if (gamepad1.a) {
                drivepower = .25;
                RightMotor = .8/2;

            }

            if (gamepad1.b) {
                drivepower = .5;
                RightMotor = .8;
            }

            if (gamepad1.x) {
                drivepower = .75;
                RightMotor = .8;
            }

            if (gamepad1.y) {
                drivepower = 1; // Sets motors to full speed when X is pressed
                RightMotor = .8*2;
            }
            //End of Speed Control

            //Driving motors
            double leftMotorPower = Range.clip(Math.pow(gamepad1.left_stick_y, 3), -drivepower, drivepower); //Sets Left Motor Power to Joystick
            double rightMotorPower = Range.clip(Math.pow(gamepad1.right_stick_y, 3), -RightMotor, RightMotor); //Sets Left Motor Power to Joystick

            Left.setPower(leftMotorPower); //Controls Left Motor with Left Joystick Y
            Right.setPower(rightMotorPower); // Controls Right Motor with Right Joystick y
            //End of Driving Motors

            //Telemetry
            telemetry.addData("Speed", drivepower);
            telemetry.update();
        }

    }
    }

