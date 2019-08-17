package org.firstinspires.ftc.teamcode.Season_Robots.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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

@Disabled
@Autonomous (name = "AutoArmieDepotFinal", group = "Armiebot_Auto")
public class AutoArmieDepotFinalCopy extends LinearOpMode {


    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor LiftArm;
    DcMotor spinner;
    DcMotor shoulder;
    DcMotor elbow;
    Servo Camera;
    ElapsedTime ResetTime = new ElapsedTime();
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY = " ASHBoLr/////AAABmbIUXlLiiEgrjVbqu8Iavlg6iPFigYso/+BCZ9uMzyAZFoo9CIzpV818SAqrjzuygz3hCeLW/ImK3xMH7DalGMwavqetwXS9Jw4I+rff2naxgV7n+EtYFvdCkUJDHfHVq1A4mhxDHgrjWZEqnLmZk25ppnIizQ0Ozcq4h6UmrWndEVEz8eKcCgn+IuglCEoEswvNBRAaKm/TAlpxLRNC6jQkZdJUh/TGYT05g9YCZo4+1ugmx01jrPCyHQVPVoeXm6VebLIuP7sNPw7njYzmVi2ffV5bYc4vf5kc5l5JwhBdPqnxuMfDLnHWaCkAO1UlVWqy2eY7/4b6iUYI2yN16ZKswSzLMmMNtPBu7e9HhKxA ";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    int currentstep = 0;
    int goldMineralX = -1;
    int silverMineral1X = -1;
    int silverMineral2X = -1;
    double CP = .7;
    boolean gold = false;

    @Override
    public void runOpMode() {
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

        // set power to all wheels to zero so they do not move
        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);

        // Lets wait for the play button to be hit
        waitForStart();

        // Lower the robot
        LiftArm.setPower(1); //Drops down robot
        ResetTime.reset(); //sets timer
        while (opModeIsActive() && (ResetTime.seconds() < 5.25)) {
            telemetry.addData("Where", "Step 1: %2.5f S Elapsed", ResetTime.seconds()); //waits for time to finish
            telemetry.update();
        }

        // Stop lowering the robot
        LiftArm.setPower(0); // stop lowering the robot
        ResetTime.reset(); //set timer
        while (opModeIsActive() && (ResetTime.seconds() < .5)) {
            telemetry.addData("Where", "Step 2: %2.5f S Elapsed", ResetTime.seconds()); //waits for time to finish
            telemetry.update();
        }

        // Move left to unhook from lander
        motorFrontRight.setPower(-.7); //sets motor power to strafe left
        motorBackLeft.setPower(.7); //sets motor power to strafe left
        motorFrontLeft.setPower(-.4); //sets motor power to strafe left
        motorBackRight.setPower(.4); //sets motor power to strafe left
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .8)) {
            telemetry.addData("Where", "Step 3: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        // Stop moving left
        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .5)) {
            telemetry.addData("Where", "Step 4: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        // move forward to clear the lander
        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .075)) {
            telemetry.addData("Where", "Step 5: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        // move right to center in front of the lander
        motorFrontRight.setPower(.7);
        motorFrontLeft.setPower(.4);
        motorBackLeft.setPower(-.7);
        motorBackRight.setPower(-.4);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .8)) {
            telemetry.addData("Where", "Step 6: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        // stop moving
        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);

        for (double CP = 1.0; CP >= 0.0; CP = CP - 0.5) {

            Camera.setPosition(CP);
            ResetTime.reset();

            // scan the image area
            while (opModeIsActive() && (ResetTime.seconds() < 4)) {
                telemetry.addData("Where", "Step 7: %2.5f S Elapsed", ResetTime.seconds());
                if (tfod != null) {
                    tfod.activate();
                }
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        if (updatedRecognitions.size() == 1) {
                            int goldMineralX = -1;
                            int silverMineral1X = -1;
                            for (Recognition recognition : updatedRecognitions) {
                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    goldMineralX = (int) recognition.getLeft();
                                } else if (silverMineral1X == -1) {
                                    silverMineral1X = (int) recognition.getLeft();
                                }
                            }
                            if (goldMineralX != -1 || silverMineral1X != -1) {
                                telemetry.addData("goldMineral1 = ", goldMineralX);
                                telemetry.addData("silverMineral1X = ", silverMineral1X);
                            }
                            if (goldMineralX != -1) {
                                gold = true;
                            }
                        }
                        telemetry.update();
                    }
                }
            }
            if (gold == true) {
                if (CP == 1.0) {
                    right();
                } else if (CP == 0.0){
                    left();
                }
                break;
            }
        }
        if (!gold) {
            center();
       }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
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
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    private void right() {

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .3)) {
            telemetry.addData("Where", "Right Step : 1 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .45)) {
            telemetry.addData("Where", "Right Step : 2 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
        motorFrontRight.setPower(.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .35)) {
            telemetry.addData("Where", "Right Step : 3 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .475)) {
            telemetry.addData("Where", "Right Step : 4 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        spinner.setPower(1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1)) {
            telemetry.addData("Where", "Right Step : 5 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        spinner.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1)) {
            telemetry.addData("Where", "Right Step : 6 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1.5)) {
            telemetry.addData("Where", "Right Step : 7 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        shoulder.setPower(-.75);
        elbow.setPower(-.45);
        LiftArm.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 7)) {
            telemetry.addData("Where", "Right Step : 8 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        shoulder.setPower(0);
        elbow.setPower(0);
        LiftArm.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 99999)) {
            telemetry.addData("Where", "Right Step : 9 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
    }

    private void center() {

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .6)) {
            telemetry.addData("Where", "Center Step : 1 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        spinner.setPower(1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1)) {
            telemetry.addData("Where", "Center Step : 2 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        spinner.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .9)) {
            telemetry.addData("Where", "Center Step : 3 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1.5)) {
            telemetry.addData("Where", "Center Step : 4 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        shoulder.setPower(-.75);
        elbow.setPower(-.45);
        LiftArm.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 6)) {
            telemetry.addData("Where", "Center Step : 5 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        shoulder.setPower(0);
        elbow.setPower(0);
        LiftArm.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 99999)) {
            telemetry.addData("Where", "Center Step : 6 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
    }

    private void left(){

        motorFrontRight.setPower(.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .2)) {
            telemetry.addData("Where", "Left Step : 1 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .8)) {
            telemetry.addData("Where", "Left Step : 2 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .6)) {
            telemetry.addData("Where", "Left Step : 3 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .49)) {
            telemetry.addData("Where", "Left Step : 4 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        spinner.setPower(1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1)) {
            telemetry.addData("Where", "Left Step : 5 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(-.6);
        motorBackLeft.setPower(-.6);
        motorBackRight.setPower(-.6);
        spinner.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .55)) {
            telemetry.addData("Where", "Left Step : 6 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.6);
        motorFrontLeft.setPower(.6);
        motorBackLeft.setPower(.6);
        motorBackRight.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 2)) {
            telemetry.addData("Where", "Left Step : 7 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        shoulder.setPower(-1);
        elbow.setPower(-.35);
        LiftArm.setPower(-.6);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 6)) {
            telemetry.addData("Where", "Left Step : 8 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(0);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorBackRight.setPower(0);
        shoulder.setPower(0);
        elbow.setPower(0);
        LiftArm.setPower(0);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 99999)) {
            telemetry.addData("Where", "Left Step : 9 %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
    }
}