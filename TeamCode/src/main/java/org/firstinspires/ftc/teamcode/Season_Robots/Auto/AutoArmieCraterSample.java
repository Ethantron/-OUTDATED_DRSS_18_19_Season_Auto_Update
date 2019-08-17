package org.firstinspires.ftc.teamcode.Season_Robots.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous(name= "AutoArmieCraterSample", group = "Auto")
public class AutoArmieCraterSample extends LinearOpMode {

    //Motors and Servos
    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor LiftArm;
    DcMotor spinner;
    DcMotor shoulder;
    DcMotor elbow;
    Servo Camera;

    //Timer
    ElapsedTime ResetTime = new ElapsedTime();

    //Vuforia
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY = " ASHBoLr/////AAABmbIUXlLiiEgrjVbqu8Iavlg6iPFigYso/+BCZ9uMzyAZFoo9CIzpV818SAqrjzuygz3hCeLW/ImK3xMH7DalGMwavqetwXS9Jw4I+rff2naxgV7n+EtYFvdCkUJDHfHVq1A4mhxDHgrjWZEqnLmZk25ppnIizQ0Ozcq4h6UmrWndEVEz8eKcCgn+IuglCEoEswvNBRAaKm/TAlpxLRNC6jQkZdJUh/TGYT05g9YCZo4+1ugmx01jrPCyHQVPVoeXm6VebLIuP7sNPw7njYzmVi2ffV5bYc4vf5kc5l5JwhBdPqnxuMfDLnHWaCkAO1UlVWqy2eY7/4b6iUYI2yN16ZKswSzLMmMNtPBu7e9HhKxA ";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    //Variables
    double step = 1;

    //Mineral Identification
    int goldMineralX = -1;
    int silverMineral1X = -1;
    int silverMineral2X = -1;
    boolean gold = false;
    boolean silver = false;

    //Mineral Position
    double CP = .8;
    int pos = 0;
    int goldpos = 0;

    @Override
    public void runOpMode() {
        //Initialization

        //Motors and Servos
        motorFrontRight = hardwareMap.dcMotor.get("FR");
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);

        motorFrontLeft = hardwareMap.dcMotor.get("FL");
        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);

        motorBackLeft = hardwareMap.dcMotor.get("BL");
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);

        motorBackRight = hardwareMap.dcMotor.get("BR");
        motorBackRight.setDirection(DcMotor.Direction.REVERSE);

        LiftArm = hardwareMap.dcMotor.get("LA");
        LiftArm.setDirection(DcMotor.Direction.FORWARD);

        spinner = hardwareMap.dcMotor.get("SP");
        spinner.setDirection(DcMotorSimple.Direction.FORWARD);

        shoulder = hardwareMap.dcMotor.get("SH");
        shoulder.setDirection(DcMotorSimple.Direction.FORWARD);

        elbow = hardwareMap.dcMotor.get("EL");
        elbow.setDirection(DcMotorSimple.Direction.REVERSE);

        Camera = hardwareMap.servo.get("CA");
        Camera.setDirection(Servo.Direction.FORWARD);
        Camera.setPosition(CP);
        //End of Motor and Servo Initialization

        //Vuforia Initialization
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();
        //End of Vuforia Initialization

        // set power to all wheels to zero so they do not move
        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);

        //End of Initialization

        waitForStart(); //Waits for start button to be pressed

        if (step == 0) {
            step = 3;
        }

        if (step == 1) { //Descend from lander

            //Start Lowering Robot
            LiftArm.setPower(-1); //Sets Lift Power to 1 and starts robot descent
            sleep(10500); //Waits 5.25 Seconds


            //Stop lowering robot
            LiftArm.setPower(0); // Sets lift power to 0 and stops robot descent

            step++; //Move on to next step
        }

        if (step == 2) { //Strafe Left to get better view of minerals

            //Start Strafe Left
            motorFrontRight.setPower(-.5); //sets motor power to strafe left
            motorBackLeft.setPower(.5); //sets motor power to strafe left
            motorFrontLeft.setPower(-.5); //sets motor power to strafe left
            motorBackRight.setPower(.5); //sets motor power to strafe left
            sleep(650);
            //End of Strafe Left

            /*//Move Forward
            motorFrontRight.setPower(-.25); //sets motor power to forward
            motorBackLeft.setPower(.25); //sets motor power to forward
            motorFrontLeft.setPower(.25); //sets motor power to forward
            motorBackRight.setPower(-.25); //sets motor power to forward
            sleep(275);*/

            motorFrontRight.setPower(0); //Set motors to stop
            motorFrontLeft.setPower(0); //Set motors to stop
            motorBackLeft.setPower(0); //Set motors to stop
            motorBackRight.setPower(0); //Set motors to stop
            //End of move forward

            scan(); //Move on to scanning
        }

    }

    //Private Voids

        //Scanning Private Void
            private void scan () {
                pos = pos + 1; //define position
                telemetry.addData("step", "4");

                if (pos > 3) {
                    center(); //If no gold is found, will default to center
                }

                if (pos <= 3) { //Scanning and Identification

                    // Start of scanning
                    ResetTime.reset();
                    while (opModeIsActive() && (ResetTime.seconds() < 2.5)) {
                        //telemetry.addData("Where", "Step 7: %2.5f S Elapsed", ResetTime.seconds());
                        if (tfod != null) {
                            tfod.activate();
                        }
                        if (tfod != null) {
                            // getUpdatedRecognitions() will return null if no new information is available since
                            // the last time that call was made.
                            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                            if (updatedRecognitions != null) {
                                //telemetry.addData("# Object Detected", updatedRecognitions.size());
                                if (updatedRecognitions.size() == 1) {
                                    int goldMineralX = -1;
                                    int silverMineral1X = -1;
                                    int silverMineral2X = -1;
                                    for (Recognition recognition : updatedRecognitions) {
                                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                            goldMineralX = (int) recognition.getLeft();
                                        }
                                                /*else if (silverMineral1X == -1) {
                                                    silverMineral1X = (int) recognition.getLeft();
                                                }*/
                                        if (recognition.getLabel().equals(LABEL_SILVER_MINERAL)) {
                                            silverMineral1X = (int) recognition.getLeft();
                                            silverMineral2X = (int) recognition.getLeft();
                                        }
                                    }
                                            /*if (goldMineralX != -1 || silverMineral1X != -1) {
                                                telemetry.addData("goldMineral1 = ", goldMineralX);
                                                telemetry.addData("silverMineral1X = ", silverMineral1X);
                                            }*/
                                    if (goldMineralX != -1) {
                                        gold = true;
                                        telemetry.addData("gold", "detected");
                                        telemetry.update();
                                    }

                                    if (silverMineral1X != -1 || silverMineral2X != -1) {
                                        silver = true;
                                        telemetry.addData("silver", "detected");
                                        telemetry.addData("Silver", silver);
                                        telemetry.update();
                                    }
                                }
                            }
                        }

                        //End of Scanning

                        //Identify Gold Position

                        if (gold == true) {
                            goldpos = pos; //Set gold position to the position just scanned
                        }

                                /*if (silver == true && step == 4) {
                                    CP = CP - .2; //Set camera to proper position. pos 1 = .7, pos 2 = .5, pos 3 = .3
                                    //silverMineral1X = -1;
                                    //silverMineral2X = -1;
                                    telemetry.addData("Rescanning", "now");
                                    telemetry.addData("CP", CP);
                                    sleep(100);
                                    step--; //Moves back one step, hence restarting the scan
                                }*/

                        if (goldpos == 1) {
                            right(); //if gold is in the left position, start the right program
                        }

                        if (goldpos == 2) {
                            center(); //if gold is in the center position, start the center program
                        }

                        if (goldpos == 3) {
                            left(); //if gold is in the right position, start the left program

                        } else {
                            silverMineral1X = -1;//Reset scan outcome
                            silverMineral2X = -1;//Reset scan outcome
                        }

                    }

                }


                CP = CP - .3; //Set camera to proper position. pos 1 = .8, pos 2 = .5, pos 3 = .2
                Camera.setPosition(CP);
                rescan(); //Run the rescan void
            }
        //End of the Scan Private Void

        //Rescan Private Void
            private void rescan () {
                scan(); //Run the scan void
            }
        //End of Rescan Private Void

        //initVuforia Private Void

            /**
             * Initialize the Vuforia localization engine.
             */
            private void initVuforia () {
                /*
                 * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
                 */
                VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

                parameters.vuforiaLicenseKey = VUFORIA_KEY;
                parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

                //  Instantiate the Vuforia engine
                vuforia = ClassFactory.getInstance().createVuforia(parameters);

                // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
            }

            /**
             * Initialize the Tensor Flow Object Detection engine.
             */
            private void initTfod () {
                int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                        "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
                TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
                tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
                tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
            }

        //End of initVuforia Private Void

        //Left Private Void

            private void left(){

                //Move Forward
                motorFrontRight.setPower(-.25); //sets motor power to forward
                motorBackLeft.setPower(.25); //sets motor power to forward
                motorFrontLeft.setPower(.25); //sets motor power to forward
                motorBackRight.setPower(-.25); //sets motor power to forward
                sleep(275);

                //Start Strafe Right to center on lander
                    motorFrontRight.setPower(.5); //sets motor power to strafe right
                    motorBackLeft.setPower(-.5); //sets motor power to strafe right
                    motorFrontLeft.setPower(.5); //sets motor power to strafe right
                    motorBackRight.setPower(-.5); //sets motor power to strafe right
                    ResetTime.reset();
                    while (opModeIsActive() && (ResetTime.seconds() < .575)) {
                        telemetry.addData("Step","1");
                        telemetry.update();
                    }
                //End of Strafe Right

                //Strafe left
                motorFrontRight.setPower(-.7);
                motorFrontLeft.setPower(-.4);
                motorBackLeft.setPower(.7);
                motorBackRight.setPower(.4);
                ResetTime.reset();
                while (opModeIsActive() && (ResetTime.seconds() < 1.5)) {
                    telemetry.addData("Step","2");
                    telemetry.update();
                }

                //Move Forward
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(.6);
                motorBackLeft.setPower(.6);
                motorBackRight.setPower(-.6);
                ResetTime.reset();
                while (opModeIsActive() && (ResetTime.seconds() < .75)) {
                    telemetry.addData("Step","3");
                    telemetry.update();
                }

                //Move Back
                motorFrontRight.setPower(.6);
                motorFrontLeft.setPower(-.6);
                motorBackLeft.setPower(-.6);
                motorBackRight.setPower(.6);
                ResetTime.reset();
                while (opModeIsActive() && (ResetTime.seconds() < .75)) {
                    telemetry.addData("Step","4");
                    telemetry.update();
                }

                //Strafe left
                motorFrontRight.setPower(.5);
                motorFrontLeft.setPower(.5);
                motorBackLeft.setPower(-.5);
                motorBackRight.setPower(-.5);
                ResetTime.reset();
                while (opModeIsActive() && (ResetTime.seconds() < 1)) {
                    telemetry.addData("Step","2");
                    telemetry.update();
                }

/*
                //Strafe Right to center with lander
                    motorFrontRight.setPower(-.5); //sets motor power to strafe right
                    motorBackLeft.setPower(-.5); //sets motor power to strafe right
                    motorFrontLeft.setPower(.5); //sets motor power to strafe right
                    motorBackRight.setPower(.5); //sets motor power to strafe right
                    sleep(650);

                //Turn Left Toward Gold
                motorFrontRight.setPower(.75); //sets motor power to turn left
                motorBackRight.setPower(.75); //sets motor power to turn left
                motorBackLeft.setPower(-.75); //sets motor power to turn left
                motorFrontLeft.setPower(-.75); //sets motor power to turn left
                sleep(325); //Turn left for .65 Seconds

                //Move Forward and knock left gold
                motorFrontRight.setPower(1); //sets motor power to move forward
                motorBackRight.setPower(1); //sets motor power to move forward
                motorBackLeft.setPower(1); //sets motor power to move forward
                motorFrontLeft.setPower(1); //sets motor power to move forward
                sleep(550); //move forward for .55 Seconds

                //Turn left Toward Depot
                motorFrontRight.setPower(1); //sets motor power to turn left
                motorBackRight.setPower(1); //sets motor power to turn left
                motorBackLeft.setPower(-1); //sets motor power to turn left
                motorFrontLeft.setPower(-1); //sets motor power to turn left
                sleep(650); //Turn left for .65 Seconds

                //Move Forward and go to depot
                motorFrontRight.setPower(1); //sets motor power to move forward
                motorBackRight.setPower(1); //sets motor power to move forward
                motorBackLeft.setPower(1); //sets motor power to move forward
                motorFrontLeft.setPower(1); //sets motor power to move forward
                sleep(1500); //move forward for 1.5 Seconds

                //Move Forward and continue to depot and activate spinner
                motorFrontRight.setPower(1); //sets motor power to move forward
                motorBackRight.setPower(1); //sets motor power to move forward
                motorBackLeft.setPower(1); //sets motor power to move forward
                motorFrontLeft.setPower(1); //sets motor power to move forward
                spinner.setPower(1); //Eject team marker
                sleep(250); //move forward for .25 Seconds

                //Turn Right Toward the Crater
                motorFrontRight.setPower(-1); //sets motor power to turn right
                motorBackRight.setPower(-1); //sets motor power to turn right
                motorBackLeft.setPower(1); //sets motor power to turn right
                motorFrontLeft.setPower(1); //sets motor power to turn right
                spinner.setPower(0); //Stop Ejecting team marker
                sleep(650); //Turn right for .65 Seconds

                //Move Forward and go to Crater
                motorFrontRight.setPower(1); //sets motor power to move forward
                motorBackRight.setPower(1); //sets motor power to move forward
                motorBackLeft.setPower(.1); //sets motor power to move forward
                motorFrontLeft.setPower(1); //sets motor power to move forward
                sleep(2000); //move forward for 2 Seconds

                //Stop Motors, extend arm, and retract lift
                motorFrontRight.setPower(0); //Set motor power to stop
                motorFrontLeft.setPower(0); //Set motor power to stop
                motorBackLeft.setPower(0); //Set motor power to stop
                motorBackRight.setPower(0); //Set motor power to stop
                shoulder.setPower(-1); //Extend Shoulder
                elbow.setPower(-.35); //Extend Elbow
                LiftArm.setPower(1); //Retract lift
                sleep(6000); //extend arm for 6 seconds

                //Stop Extending the arm and retracting the lift
                shoulder.setPower(0); //Stop Extend Shoulder
                elbow.setPower(0); //Stop Extend Elbow
                LiftArm.setPower(0); //Stop Retract lift
*/
            }

        //End of Left Private Void

        //Center Private Void

            private void center(){

                //Move Forward
                motorFrontRight.setPower(-.25); //sets motor power to forward
                motorBackLeft.setPower(.25); //sets motor power to forward
                motorFrontLeft.setPower(.25); //sets motor power to forward
                motorBackRight.setPower(-.25); //sets motor power to forward
                sleep(275);

                //Start Strafe Right to center on lander
                motorFrontRight.setPower(.5); //sets motor power to strafe right
                motorBackLeft.setPower(-.5); //sets motor power to strafe right
                motorFrontLeft.setPower(.5); //sets motor power to strafe right
                motorBackRight.setPower(-.5); //sets motor power to strafe right
                ResetTime.reset();
                while (opModeIsActive() && (ResetTime.seconds() < .575)) {
                    telemetry.addData("Step","1");
                    telemetry.update();
                }
                //End of Strafe Right

                //Move Forward
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(.6);
                motorBackLeft.setPower(.6);
                motorBackRight.setPower(-.6);
                ResetTime.reset();
                while (opModeIsActive() && (ResetTime.seconds() < .5)) {
                    telemetry.addData("Step","2");
                    telemetry.update();
                }

                //Move Back
                motorFrontRight.setPower(.6);
                motorFrontLeft.setPower(-.6);
                motorBackLeft.setPower(-.6);
                motorBackRight.setPower(.6);
                ResetTime.reset();
                while (opModeIsActive() && (ResetTime.seconds() < .5)) {
                    telemetry.addData("Step", "3");
                    telemetry.update();
                }

                /*
                //Strafe Right to center with lander
                    motorFrontRight.setPower(-.5); //sets motor power to strafe right
                    motorBackLeft.setPower(-.5); //sets motor power to strafe right
                    motorFrontLeft.setPower(.5); //sets motor power to strafe right
                    motorBackRight.setPower(.5); //sets motor power to strafe right
                    sleep(650);

                //Move Forward and knock center gold
                    motorFrontRight.setPower(1); //sets motor power to move forward
                    motorBackRight.setPower(1); //sets motor power to move forward
                    motorBackLeft.setPower(1); //sets motor power to move forward
                    motorFrontLeft.setPower(1); //sets motor power to move forward
                    sleep(350); //move forward for .35 Seconds

                //Turn left
                    motorFrontRight.setPower(-1); //sets motor power to turn left
                    motorBackRight.setPower(-1); //sets motor power to turn left
                    motorBackLeft.setPower(1); //sets motor power to turn left
                    motorFrontLeft.setPower(1); //sets motor power to turn left
                    sleep(650); //Turn left for .65 Seconds

                //Move Forward
                    motorFrontRight.setPower(1); //sets motor power to move forward
                    motorBackRight.setPower(1); //sets motor power to move forward
                    motorBackLeft.setPower(1); //sets motor power to move forward
                    motorFrontLeft.setPower(1); //sets motor power to move forward
                    sleep(350); //move forward for .35 Seconds

                //Turn right
                    motorFrontRight.setPower(1); //sets motor power to turn right
                    motorBackRight.setPower(1); //sets motor power to turn right
                    motorBackLeft.setPower(-1); //sets motor power to turn right
                    motorFrontLeft.setPower(-1); //sets motor power to turn right
                    sleep(650); //Turn right for .65 Seconds

                //Move Forward
                    motorFrontRight.setPower(1); //sets motor power to move forward
                    motorBackRight.setPower(1); //sets motor power to move forward
                    motorBackLeft.setPower(1); //sets motor power to move forward
                    motorFrontLeft.setPower(1); //sets motor power to move forward
                    sleep(750); //move forward for .75 Seconds

                //Turn left toward depot
                    motorFrontRight.setPower(-1); //sets motor power to turn left
                    motorBackRight.setPower(-1); //sets motor power to turn left
                    motorBackLeft.setPower(1); //sets motor power to turn left
                    motorFrontLeft.setPower(1); //sets motor power to turn left
                    sleep(650); //Turn left for .65 Seconds

                //Move Forward toward depot
                    motorFrontRight.setPower(1); //sets motor power to move forward
                    motorBackRight.setPower(1); //sets motor power to move forward
                    motorBackLeft.setPower(1); //sets motor power to move forward
                    motorFrontLeft.setPower(1); //sets motor power to move forward
                    sleep(1500); //move forward for 1.5 Seconds

                //Move Forward and continue to depot and activate spinner
                    motorFrontRight.setPower(1); //sets motor power to move forward
                    motorBackRight.setPower(1); //sets motor power to move forward
                    motorBackLeft.setPower(1); //sets motor power to move forward
                    motorFrontLeft.setPower(1); //sets motor power to move forward
                    spinner.setPower(1); //Eject team marker
                    sleep(250); //move forward for .25 Seconds

                //Turn Right Toward the Crater
                    motorFrontRight.setPower(-1); //sets motor power to turn right
                    motorBackRight.setPower(-1); //sets motor power to turn right
                    motorBackLeft.setPower(1); //sets motor power to turn right
                    motorFrontLeft.setPower(1); //sets motor power to turn right
                    spinner.setPower(0); //Stop Ejecting team marker
                    sleep(650); //Turn right for .65 Seconds

                //Move Forward and go to Crater
                    motorFrontRight.setPower(1); //sets motor power to move forward
                    motorBackRight.setPower(1); //sets motor power to move forward
                    motorBackLeft.setPower(.1); //sets motor power to move forward
                    motorFrontLeft.setPower(1); //sets motor power to move forward
                    sleep(2000); //move forward for 2 Seconds

                //Stop Motors, extend arm, and retract lift
                    motorFrontRight.setPower(0); //Set motor power to stop
                    motorFrontLeft.setPower(0); //Set motor power to stop
                    motorBackLeft.setPower(0); //Set motor power to stop
                    motorBackRight.setPower(0); //Set motor power to stop
                    shoulder.setPower(-1); //Extend Shoulder
                    elbow.setPower(-.35); //Extend Elbow
                    LiftArm.setPower(1); //Retract lift
                    sleep(6000); //extend arm for 6 seconds

                //Stop Extending the arm and retracting the lift
                    shoulder.setPower(0); //Stop Extend Shoulder
                    elbow.setPower(0); //Stop Extend Elbow
                    LiftArm.setPower(0); //Stop Retract lift
                */
            }

        //End of Center Private Void

        //Right Private Void

            private void right() {

                //Move Forward
                motorFrontRight.setPower(-.25); //sets motor power to forward
                motorBackLeft.setPower(.25); //sets motor power to forward
                motorFrontLeft.setPower(.25); //sets motor power to forward
                motorBackRight.setPower(-.25); //sets motor power to forward
                sleep(275);

                //Start Strafe Right to center on lander
                motorFrontRight.setPower(.5); //sets motor power to strafe right
                motorBackLeft.setPower(-.5); //sets motor power to strafe right
                motorFrontLeft.setPower(.5); //sets motor power to strafe right
                motorBackRight.setPower(-.5); //sets motor power to strafe right
                ResetTime.reset();
                while (opModeIsActive() && (ResetTime.seconds() < .575)) {
                    telemetry.addData("Step","1");
                    telemetry.update();
                }
                //End of Strafe Right

                //Strafe right
                motorFrontRight.setPower(.7);
                motorFrontLeft.setPower(.4);
                motorBackLeft.setPower(-.7);
                motorBackRight.setPower(-.4);
                ResetTime.reset();
                while (opModeIsActive() && (ResetTime.seconds() < 1)) {
                    telemetry.addData("Step","2");
                    telemetry.update();
                }

                //Move Forward
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(.6);
                motorBackLeft.setPower(.6);
                motorBackRight.setPower(-.6);
                ResetTime.reset();
                while (opModeIsActive() && (ResetTime.seconds() < .5)) {
                    telemetry.addData("Step","3");
                    telemetry.update();
                }

                //Move Back
                motorFrontRight.setPower(.6);
                motorFrontLeft.setPower(-.6);
                motorBackLeft.setPower(-.6);
                motorBackRight.setPower(.6);
                ResetTime.reset();
                while (opModeIsActive() && (ResetTime.seconds() < .5)) {
                    telemetry.addData("Step","4");
                    telemetry.update();
                }

                //Strafe left
                motorFrontRight.setPower(-.5);
                motorFrontLeft.setPower(-.5);
                motorBackLeft.setPower(.5);
                motorBackRight.setPower(.5);
                ResetTime.reset();
                while (opModeIsActive() && (ResetTime.seconds() < .75)) {
                    telemetry.addData("Step","2");
                    telemetry.update();
                }

                /*
                //Strafe Right to center with lander
                    motorFrontRight.setPower(-.5); //sets motor power to strafe right
                    motorBackLeft.setPower(-.5); //sets motor power to strafe right
                    motorFrontLeft.setPower(.5); //sets motor power to strafe right
                    motorBackRight.setPower(.5); //sets motor power to strafe right
                    sleep(650);

                //Turn Right Toward Gold
                    motorFrontRight.setPower(-.75); //sets motor power to turn left
                    motorBackRight.setPower(-.75); //sets motor power to turn left
                    motorBackLeft.setPower(.75); //sets motor power to turn left
                    motorFrontLeft.setPower(.75); //sets motor power to turn left
                    sleep(325); //Turn right for .65 Seconds

                //Move Forward and knock left gold
                    motorFrontRight.setPower(1); //sets motor power to move forward
                    motorBackRight.setPower(1); //sets motor power to move forward
                    motorBackLeft.setPower(1); //sets motor power to move forward
                    motorFrontLeft.setPower(1); //sets motor power to move forward
                    sleep(350); //move forward for .35 Seconds

                //Turn Left
                    motorFrontRight.setPower(1); //sets motor power to turn left
                    motorBackRight.setPower(1); //sets motor power to turn left
                    motorBackLeft.setPower(-1); //sets motor power to turn left
                    motorFrontLeft.setPower(-1); //sets motor power to turn left
                    sleep(650); //Turn left for .65 Seconds

                //Move Backward
                    motorFrontRight.setPower(-1); //sets motor power to move backward
                    motorBackRight.setPower(-1); //sets motor power to move backward
                    motorBackLeft.setPower(-1); //sets motor power to move backward
                    motorFrontLeft.setPower(-1); //sets motor power to move backward
                    sleep(400); //move backward for .4 Seconds

                //Turn Left
                    motorFrontRight.setPower(1); //sets motor power to turn right
                    motorBackRight.setPower(1); //sets motor power to turn right
                    motorBackLeft.setPower(-1); //sets motor power to turn right
                    motorFrontLeft.setPower(-1); //sets motor power to turn right
                    spinner.setPower(0); //Stop Ejecting team marker
                    sleep(650); //Turn right for .65 Seconds

                //Move Forward
                    motorFrontRight.setPower(.75); //sets motor power to move forward
                    motorBackRight.setPower(.75); //sets motor power to move forward
                    motorBackLeft.setPower(.75); //sets motor power to move forward
                    motorFrontLeft.setPower(.75); //sets motor power to move forward
                    sleep(650); //move forward for .65 Seconds

                //Turn Left toward depot
                    motorFrontRight.setPower(1); //sets motor power to turn right
                    motorBackRight.setPower(1); //sets motor power to turn right
                    motorBackLeft.setPower(-1); //sets motor power to turn right
                    motorFrontLeft.setPower(-1); //sets motor power to turn right
                    sleep(650); //Turn right for .65 Seconds

                //Move Forward toward depot
                    motorFrontRight.setPower(1); //sets motor power to move forward
                    motorBackRight.setPower(1); //sets motor power to move forward
                    motorBackLeft.setPower(1); //sets motor power to move forward
                    motorFrontLeft.setPower(1); //sets motor power to move forward
                    sleep(2000); //move forward for 2 Seconds

                //Move Forward and continue to depot and activate spinner
                    motorFrontRight.setPower(1); //sets motor power to move forward
                    motorBackRight.setPower(1); //sets motor power to move forward
                    motorBackLeft.setPower(1); //sets motor power to move forward
                    motorFrontLeft.setPower(1); //sets motor power to move forward
                    spinner.setPower(1); //Eject team marker
                    sleep(250); //move forward for .25 Seconds

                //Turn Right Toward the Crater
                    motorFrontRight.setPower(-1); //sets motor power to turn right
                    motorBackRight.setPower(-1); //sets motor power to turn right
                    motorBackLeft.setPower(1); //sets motor power to turn right
                    motorFrontLeft.setPower(1); //sets motor power to turn right
                    spinner.setPower(0); //Stop Ejecting team marker
                    sleep(650); //Turn right for .65 Seconds

                //Move Forward and go to Crater
                    motorFrontRight.setPower(1); //sets motor power to move forward
                    motorBackRight.setPower(1); //sets motor power to move forward
                    motorBackLeft.setPower(.1); //sets motor power to move forward
                    motorFrontLeft.setPower(1); //sets motor power to move forward
                    sleep(2000); //move forward for 2 Seconds

                //Stop Motors, extend arm, and retract lift
                    motorFrontRight.setPower(0); //Set motor power to stop
                    motorFrontLeft.setPower(0); //Set motor power to stop
                    motorBackLeft.setPower(0); //Set motor power to stop
                    motorBackRight.setPower(0); //Set motor power to stop
                    shoulder.setPower(-1); //Extend Shoulder
                    elbow.setPower(-.35); //Extend Elbow
                    LiftArm.setPower(1); //Retract lift
                    sleep(6000); //extend arm for 6 seconds

                //Stop Extending the arm and retracting the lift
                    shoulder.setPower(0); //Stop Extend Shoulder
                    elbow.setPower(0); //Stop Extend Elbow
                    LiftArm.setPower(0); //Stop Retract lift
                    */
            }

        //End of Right Private Void

}