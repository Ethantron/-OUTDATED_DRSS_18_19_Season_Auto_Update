package org.firstinspires.ftc.teamcode.Season_Robots.Tests.AGagne_Tests;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;



@TeleOp(name="Baby_Bot_Sensor_Testing", group="TeleOp")
public class Camera_Control_TeleOp extends LinearOpMode {

    DcMotor FrontLeft;
    DcMotor FrontRight;
    DcMotor BackLeft;
    DcMotor BackRight;
    Servo Pan;
    Servo Tilt;
    DigitalChannel button;
    double tilt = 0;

    double setpower = 1;
 //   double leftMotorPower = 1.0;
 //   double rightMotorPower = 1.0;
    double TiltActive = 1;

 //   ElapsedTime ResetTime = new ElapsedTime();

    ColorSensor sensorColor;
    private DistanceSensor sensorRange;

    @Override
    public void runOpMode() throws InterruptedException {

        //Initializes the hardware

        FrontLeft = hardwareMap.dcMotor.get("FL");
        FrontLeft.setDirection(DcMotor.Direction.FORWARD);

        FrontRight = hardwareMap.dcMotor.get("FR");
        FrontRight.setDirection(DcMotor.Direction.REVERSE);

        BackLeft = hardwareMap.dcMotor.get("BL");
        BackLeft.setDirection(DcMotor.Direction.FORWARD);

        BackRight = hardwareMap.dcMotor.get("BR");
        BackRight.setDirection(DcMotor.Direction.REVERSE);

        Pan = hardwareMap.servo.get("Pan"); //Continuous Rotation Servo
        Tilt = hardwareMap.servo.get("Tilt"); //180 Degree Servo

        Tilt.setPosition(0.5); //Sets servo into "Zero" position
        TiltActive = 1;

        button = hardwareMap.get(DigitalChannel.class, "button");
        button.setMode(DigitalChannel.Mode.INPUT);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // get a reference to the color sensor.
        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");

        sensorRange = hardwareMap.get(DistanceSensor.class, "sensor_range");
        Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor)sensorRange;

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);




        //End of Initialization

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Text", "*** Robot Data ***");

            if (button.getState() == false) {
                // button is pressed.
                FrontLeft.setPower(.5);
                FrontRight.setPower(.5);
                BackLeft.setPower(.5);
                BackRight.setPower(.5);

                sleep(500);

            } else {
                // button is not pressed.
                //Speed Change
                if (gamepad1.b) {
                    setpower = 1;
                }

                if (gamepad1.x) {
                    setpower = 0.5;
                }

                if (gamepad1.y) {
                    setpower = 0.25;
                }

                //Driving Forward
                //   leftMotorPower = Range.clip(Math.pow(gamepad1.left_stick_y, 3), -setpower, setpower);
                //   rightMotorPower = Range.clip(Math.pow(gamepad1.left_stick_y, 3), -setpower, setpower);

                //Turning Left
                //Not Done
                //Turning Right
                //Not Done
                //Setting motor power to respective joystick input
            /*    FrontLeft.setPower(leftMotorPower);
                FrontRight.setPower(rightMotorPower);
                BackLeft.setPower(leftMotorPower);
                BackRight.setPower(rightMotorPower); */

                // Moving Forward
                if (gamepad1.left_stick_y >= 0.1) {
                    FrontLeft.setPower(setpower);
                    FrontRight.setPower(setpower);
                    BackLeft.setPower(setpower);
                    BackRight.setPower(setpower);
                }

                // Moving back
                if (gamepad1.left_stick_y <= -0.1) {
                    FrontLeft.setPower(-setpower);
                    FrontRight.setPower(-setpower);
                    BackLeft.setPower(-setpower);
                    BackRight.setPower(-setpower);
                }

                // Turning left
                if (gamepad1.left_stick_x >= 0.1) {
                    FrontLeft.setPower(-setpower);
                    FrontRight.setPower(setpower);
                    BackLeft.setPower(-setpower);
                    BackRight.setPower(setpower);
                }

                // Turning right
                if (gamepad1.left_stick_x <= -0.1) {
                    FrontLeft.setPower(setpower);
                    FrontRight.setPower(-setpower);
                    BackLeft.setPower(setpower);
                    BackRight.setPower(-setpower);
                }

                //Stopping
                if (gamepad1.left_stick_y < 0.1 && gamepad1.left_stick_y > -0.1 && gamepad1.left_stick_x > -0.1 && gamepad1.left_stick_x < 0.1) {
                    FrontLeft.setPower(0);
                    FrontRight.setPower(0);
                    BackLeft.setPower(0);
                    BackRight.setPower(0);
                }

                //Camera Control
                //Pan
                if (gamepad1.right_stick_x > .2) {
                    Pan.setPosition(1); //Tells servo to go forwards
                }

                if (gamepad1.right_stick_x < -.2) {
                    Pan.setPosition(0); //Tells servo to go backwards
                }

                if (gamepad1.right_stick_x < .2 && gamepad1.right_stick_x > -.2) {
                    Pan.setPosition(.5); //Tells Servo to sit still
                }
                //End of Pan

                //Tilt THIS IS THE PROBLEM AREA!!!

                double joy = gamepad1.right_stick_y; //-1 to 1
                double TiltPos = joy / 2 + 0.5; //-0.5 to 0.5 then 0 to 1 for 180 degree servo

                if (TiltActive > 0.5) { //Tilt Control Loop
                        /*if (gamepad1.left_bumper) {
                            Tilt.setPosition(TiltPos); //Sets servo position to right_stick_y, left_bumper also breaks loop, hence holding tilt pos
                        }*/
                    if (gamepad1.a) {
                        TiltActive = 0; //Deactivates Tilt
                        sleep(50); //Gives time for button to not be pushed down
                    }

                    else {
                        Tilt.setPosition(TiltPos); //Sets servo position to right_stick_y
                    }
                }//End of Tilt Control Loop

                if (TiltActive < 0.5) {
                    if (gamepad1.a) {
                        TiltActive = 1; //Activates Tilt
                        sleep(50); //Gives time for button to not be pushed down
                    }
                }
                //End of Tilt
                //End of Camera Control


                if (TiltActive == 1){
                    telemetry.addData("Tilt Active?: ", "Yes");
                }
                else if (TiltActive == 0){
                    telemetry.addData("Tilt Active?: ", "No");
                }
                /** Color Sensor */

                // convert the RGB values to HSV values.
                // multiply by the SCALE_FACTOR.
                // then cast it back to int (SCALE_FACTOR is a double)
                Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                        (int) (sensorColor.green() * SCALE_FACTOR),
                        (int) (sensorColor.blue() * SCALE_FACTOR),
                        hsvValues);

                // send the info back to driver station using telemetry function.



                // change the background color to match the color detected by the RGB sensor.
                // pass a reference to the hue, saturation, and value array as an argument
                // to the HSVToColor method.
                relativeLayout.post(new Runnable() {
                    public void run() {
                        relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                    }
                });


                //Telemetry Data

                telemetry.addData("Current Speed",  setpower); //Tells Current Speed
                telemetry.addData("Current Angle", Tilt.getPosition()); //Says Current Angle of TiltServo (-0.5 Says exact position relative to the camera)
                telemetry.addData("Alpha", sensorColor.alpha());
                telemetry.addData("Red  ", sensorColor.red());
                telemetry.addData("Green", sensorColor.green());
                telemetry.addData("Blue ", sensorColor.blue());
                telemetry.addData("Hue", hsvValues[0]);
                //telemetry.addData("deviceName",sensorRange.getDeviceName() );
                telemetry.addData("range", String.format("%.01f mm", sensorRange.getDistance(DistanceUnit.MM)));
                telemetry.addData("range", String.format("%.01f cm", sensorRange.getDistance(DistanceUnit.CM)));
                telemetry.addData("range", String.format("%.01f m", sensorRange.getDistance(DistanceUnit.METER)));
                telemetry.addData("range", String.format("%.01f in", sensorRange.getDistance(DistanceUnit.INCH)));
               //telemetry.addData("ID", String.format("%x", sensorTimeOfFlight.getModelID()));
               //telemetry.addData("did time out", Boolean.toString(sensorTimeOfFlight.didTimeoutOccur()));
                telemetry.update();

            }
        }

        relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.WHITE);
            }
        });

    }
}