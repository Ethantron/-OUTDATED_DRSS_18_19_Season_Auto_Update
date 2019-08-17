package org.firstinspires.ftc.teamcode.Season_Robots.Tests.Armiebot_WIP_Folder;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;
@Disabled
@Autonomous(name = "AutoArmieDepotSteps2_WIP", group = "Armiebot_Tests_Food")
public class AutoArmieDepotSteps2_WIP extends LinearOpMode {
    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
    DcMotor LiftArm;
    DcMotor spinner;
    ElapsedTime ResetTime = new ElapsedTime();
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY = " ASHBoLr/////AAABmbIUXlLiiEgrjVbqu8Iavlg6iPFigYso/+BCZ9uMzyAZFoo9CIzpV818SAqrjzuygz3hCeLW/ImK3xMH7DalGMwavqetwXS9Jw4I+rff2naxgV7n+EtYFvdCkUJDHfHVq1A4mhxDHgrjWZEqnLmZk25ppnIizQ0Ozcq4h6UmrWndEVEz8eKcCgn+IuglCEoEswvNBRAaKm/TAlpxLRNC6jQkZdJUh/TGYT05g9YCZo4+1ugmx01jrPCyHQVPVoeXm6VebLIuP7sNPw7njYzmVi2ffV5bYc4vf5kc5l5JwhBdPqnxuMfDLnHWaCkAO1UlVWqy2eY7/4b6iUYI2yN16ZKswSzLMmMNtPBu7e9HhKxA ";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    int goldMineralX = -1;
    int silverMineral1X = -1;
    int silverMineral2X = -1;
    double currentstep0 = 0;
    double currentstep1 = 0;
    double currentstep2 = 0;
    double currentstep3 = 0;


    @Override
    public void runOpMode() {
        motorFrontRight = hardwareMap.dcMotor.get("FR");
        motorFrontRight.setDirection(DcMotor.Direction.FORWARD);

        motorFrontLeft = hardwareMap.dcMotor.get("FL");
        motorFrontLeft.setDirection(DcMotor.Direction.FORWARD);

        motorBackLeft = hardwareMap.dcMotor.get("BL");
        motorBackLeft.setDirection(DcMotor.Direction.FORWARD);

        motorBackRight = hardwareMap.dcMotor.get("BR");
        motorBackRight.setDirection(DcMotor.Direction.FORWARD);

        LiftArm = hardwareMap.dcMotor.get("LA");
        LiftArm.setDirection(DcMotor.Direction.FORWARD);

        spinner = hardwareMap.dcMotor.get("SP");
        spinner.setDirection(DcMotorSimple.Direction.FORWARD);


        telemetry.addData("currentstep0", currentstep0);
        telemetry.addData("currentstep1", currentstep1);
        telemetry.addData("currentstep2", currentstep2);
        telemetry.addData("currentstep3", currentstep3);


        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }


        waitForStart();

        if (currentstep0 == 0) {
            LiftArm.setPower(-1);
            sleep(9500);
            telemetry.update();
            currentstep0++;
        }

        if (currentstep0 == 1) {
            LiftArm.setPower(0);
            telemetry.update();
            currentstep0++;
        }

        if (currentstep0 == 2) {
            motorFrontRight.setPower(-.6);
            motorFrontLeft.setPower(-.6);
            motorBackLeft.setPower(.6);
            motorBackRight.setPower(.6);
            sleep(0400);
            sleep(0400);
            telemetry.update();
            currentstep0++;
        }

        if (currentstep0 == 3) {
            motorFrontRight.setPower(-.6);
            motorFrontLeft.setPower(.6);
            motorBackLeft.setPower(.6);
            motorBackRight.setPower(-.6);
            sleep(0200);
            telemetry.update();
            currentstep0++;
        }

        if (currentstep0 == 4) {
            motorFrontRight.setPower(.6);
            motorFrontLeft.setPower(.6);
            motorBackLeft.setPower(-.6);
            motorBackRight.setPower(-.6);
            sleep(0400);
            sleep(0400);
            telemetry.update();
            currentstep0++;
        }

        if (currentstep0 == 5) {
            motorFrontRight.setPower(.6);
            motorFrontLeft.setPower(.6);
            motorBackLeft.setPower(.6);
            motorBackRight.setPower(.6);
            sleep(1000);
            telemetry.update();
            currentstep0++;
        }

        if (currentstep0 == 6) {
            motorFrontRight.setPower(0);
            motorFrontLeft.setPower(0);
            motorBackLeft.setPower(0);
            motorBackRight.setPower(0);
            sleep(0500);
            telemetry.update();
            currentstep0++;
        }

        if (currentstep0 == 7) {
            if (opModeIsActive()) {
                /** Activate Tensor Flow Object Detection. */
                if (tfod != null) {
                    tfod.activate();
                }

                while (opModeIsActive()) {
                    if (tfod != null) {
                        // getUpdatedRecognitions() will return null if no new information is available since
                        // the last time that call was made.
                        List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                        if (updatedRecognitions != null) {
                            telemetry.addData("# Object Detected", updatedRecognitions.size());
                            if (updatedRecognitions.size() == 3) {
                                int goldMineralX = -1;
                                int silverMineral1X = -1;
                                int silverMineral2X = -1;
                                for (Recognition recognition : updatedRecognitions) {
                                    if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                        goldMineralX = (int) recognition.getLeft();
                                    } else if (silverMineral1X == -1) {
                                        silverMineral1X = (int) recognition.getLeft();
                                    } else {
                                        silverMineral2X = (int) recognition.getLeft();
                                    }
                                }
                                if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                                    if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                                        telemetry.addData("Gold Mineral Position", "Left");
                                        telemetry.update();

                                        if (currentstep1 == 0){
                                            motorFrontRight.setPower(-.6);
                                            motorFrontLeft.setPower(.6);
                                            motorBackLeft.setPower(.6);
                                            motorBackRight.setPower(-.6);
                                            sleep(0250);
                                            telemetry.update();
                                            currentstep1++;
                                        }

                                        if (currentstep1 == 1){
                                            motorFrontRight.setPower(-.6);
                                            motorFrontLeft.setPower(-.6);
                                            motorBackLeft.setPower(-.6);
                                            motorBackRight.setPower(-.6);
                                            sleep(0250);
                                            telemetry.update();
                                            currentstep1++;
                                        }

                                        if (currentstep1 == 2){
                                            motorFrontRight.setPower(-.6);
                                            motorFrontLeft.setPower(.6);
                                            motorBackLeft.setPower(.6);
                                            motorBackRight.setPower(-.6);
                                            sleep(1000);
                                            telemetry.update();
                                            currentstep1++;
                                        }

                                        if (currentstep1 == 3){
                                            motorFrontRight.setPower(0);
                                            motorFrontLeft.setPower(0);
                                            motorBackLeft.setPower(0);
                                            motorBackRight.setPower(0);
                                            spinner.setPower(.8);
                                            sleep(0250);
                                            telemetry.update();
                                            currentstep1++;
                                        }

                                        if (currentstep1 == 4){
                                            spinner.setPower(0);
                                            motorFrontRight.setPower(-.6);
                                            motorFrontLeft.setPower(-.6);
                                            motorBackLeft.setPower(-.6);
                                            motorBackRight.setPower(-.6);
                                            sleep(0500);
                                            telemetry.update();
                                            currentstep1++;
                                        }

                                        if (currentstep1 == 5){
                                            motorFrontRight.setPower(-.6);
                                            motorFrontLeft.setPower(.6);
                                            motorBackLeft.setPower(.6);
                                            motorBackRight.setPower(-.6);
                                            sleep(9000);
                                            telemetry.update();
                                            currentstep1++;
                                        }

                                    } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                        telemetry.addData("Gold Mineral Position", "Right");
                                        telemetry.update();

                                        if (currentstep2 == 0){
                                            motorFrontRight.setPower(.6);
                                            motorFrontLeft.setPower(-.6);
                                            motorBackLeft.setPower(-.6);
                                            motorBackRight.setPower(.6);
                                            sleep(0250);
                                            telemetry.update();
                                            currentstep2++;
                                        }

                                        if (currentstep2 == 1){
                                            motorFrontRight.setPower(-.6);
                                            motorFrontLeft.setPower(-.6);
                                            motorBackLeft.setPower(-.6);
                                            motorBackRight.setPower(-.6);
                                            sleep(0250);
                                            telemetry.update();
                                            currentstep2++;
                                        }

                                        if (currentstep2 == 2){
                                            motorFrontRight.setPower(-.6);
                                            motorFrontLeft.setPower(.6);
                                            motorBackLeft.setPower(.6);
                                            motorBackRight.setPower(-.6);
                                            sleep(0250);
                                            telemetry.update();
                                            currentstep2++;
                                        }

                                        if (currentstep2 == 3) {
                                            motorFrontRight.setPower(.6);
                                            motorFrontLeft.setPower(-.6);
                                            motorBackLeft.setPower(-.6);
                                            motorBackRight.setPower(.6);
                                            sleep(0150);
                                            telemetry.update();
                                            currentstep2++;
                                        }

                                        if (currentstep2 == 4){
                                            motorFrontRight.setPower(-.6);
                                            motorFrontLeft.setPower(.6);
                                            motorBackLeft.setPower(.6);
                                            motorBackRight.setPower(-.6);
                                            sleep(0500);
                                            telemetry.update();
                                            currentstep2++;
                                        }

                                        if (currentstep2 == 5){
                                            motorFrontRight.setPower(0);
                                            motorFrontLeft.setPower(0);
                                            motorBackLeft.setPower(0);
                                            motorBackRight.setPower(0);
                                            spinner.setPower(.8);
                                            sleep(0250);
                                            telemetry.update();
                                            currentstep2++;
                                        }

                                        if (currentstep2 == 6){
                                            spinner.setPower(0);
                                            motorFrontRight.setPower(-.6);
                                            motorFrontLeft.setPower(-.6);
                                            motorBackLeft.setPower(-.6);
                                            motorBackRight.setPower(-.6);
                                            sleep(0600);
                                            telemetry.update();
                                            currentstep2++;
                                        }

                                        if (currentstep2 == 7){
                                            motorFrontRight.setPower(-.6);
                                            motorFrontLeft.setPower(.6);
                                            motorBackLeft.setPower(.6);
                                            motorBackRight.setPower(-.6);
                                            sleep(9000);
                                            telemetry.update();
                                            currentstep2++;
                                        }

                                    } else {
                                        telemetry.addData("Gold Mineral Position", "Center");
                                        telemetry.update();

                                        if (currentstep3 == 0){
                                            motorFrontRight.setPower(-.6);
                                            motorFrontLeft.setPower(-.6);
                                            motorBackLeft.setPower(-.6);
                                            motorBackRight.setPower(-.6);
                                            sleep(0250);
                                            telemetry.update();
                                            currentstep3++;
                                        }

                                        if (currentstep3 == 1){
                                            motorFrontRight.setPower(-.6);
                                            motorFrontLeft.setPower(.6);
                                            motorBackLeft.setPower(.6);
                                            motorBackRight.setPower(-.6);
                                            sleep(1000);
                                            telemetry.update();
                                            currentstep3++;
                                        }

                                        if (currentstep3 == 2){
                                            motorFrontRight.setPower(0);
                                            motorFrontLeft.setPower(0);
                                            motorBackLeft.setPower(0);
                                            motorBackRight.setPower(0);
                                            spinner.setPower(.8);
                                            sleep(0250);
                                            telemetry.update();
                                            currentstep3++;
                                        }

                                        if (currentstep3 == 3){
                                            spinner.setPower(0);
                                            motorFrontRight.setPower(-.6);
                                            motorFrontLeft.setPower(-.6);
                                            motorBackLeft.setPower(-.6);
                                            motorBackRight.setPower(-.6);
                                            sleep(0500);
                                            telemetry.update();

                                            currentstep3++;
                                        }

                                        if (currentstep3 == 4) {
                                            motorFrontRight.setPower(-.6);
                                            motorFrontLeft.setPower(.6);
                                            motorBackLeft.setPower(.6);
                                            motorBackRight.setPower(-.6);
                                            sleep(9000);
                                            telemetry.update();
                                            currentstep3++;
                                        }

                                    }

                                }
                            }
                        }
                    }
                }
            }

            if (tfod == null) {
                tfod.shutdown();
            }
            currentstep0++;
        }
/*
        if (currentstep0 == 8 && goldMineralX < silverMineral1X && goldMineralX < silverMineral2X /* left){
            if (currentstep1 == 0){
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(.6);
                motorBackLeft.setPower(.6);
                motorBackRight.setPower(-.6);
                sleep(0250);
                telemetry.update();
                currentstep1++;
            }

            if (currentstep1 == 1){
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(-.6);
                motorBackLeft.setPower(-.6);
                motorBackRight.setPower(-.6);
                sleep(0250);
                telemetry.update();
                currentstep1++;
            }

            if (currentstep1 == 2){
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(.6);
                motorBackLeft.setPower(.6);
                motorBackRight.setPower(-.6);
                sleep(1000);
                telemetry.update();
                currentstep1++;
            }

            if (currentstep1 == 3){
                motorFrontRight.setPower(0);
                motorFrontLeft.setPower(0);
                motorBackLeft.setPower(0);
                motorBackRight.setPower(0);
                spinner.setPower(.8);
                sleep(0250);
                telemetry.update();
                currentstep1++;
            }

            if (currentstep1 == 4){
                spinner.setPower(0);
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(-.6);
                motorBackLeft.setPower(-.6);
                motorBackRight.setPower(-.6);
                sleep(0500);
                telemetry.update();
                currentstep1++;
            }

            if (currentstep1 == 5){
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(.6);
                motorBackLeft.setPower(.6);
                motorBackRight.setPower(-.6);
                sleep(9000);
                telemetry.update();
                currentstep1++;
            }
           else if (currentstep2 == 0 && goldMineralX > silverMineral1X && goldMineralX > silverMineral2X /* right ){
                motorFrontRight.setPower(.6);
                motorFrontLeft.setPower(-.6);
                motorBackLeft.setPower(-.6);
                motorBackRight.setPower(.6);
                sleep(0250);
                telemetry.update();
                currentstep2++;
            }

            if (currentstep2 == 1){
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(-.6);
                motorBackLeft.setPower(-.6);
                motorBackRight.setPower(-.6);
                sleep(0250);
                telemetry.update();
                currentstep2++;
            }

            if (currentstep2 == 2){
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(.6);
                motorBackLeft.setPower(.6);
                motorBackRight.setPower(-.6);
                sleep(0250);
                telemetry.update();
                currentstep2++;
            }

            if (currentstep2 == 3) {
                motorFrontRight.setPower(.6);
                motorFrontLeft.setPower(-.6);
                motorBackLeft.setPower(-.6);
                motorBackRight.setPower(.6);
                sleep(0150);
                telemetry.update();
                currentstep2++;
            }

            if (currentstep2 == 4){
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(.6);
                motorBackLeft.setPower(.6);
                motorBackRight.setPower(-.6);
                sleep(0500);
                telemetry.update();
                currentstep2++;
            }

            if (currentstep2 == 5){
                motorFrontRight.setPower(0);
                motorFrontLeft.setPower(0);
                motorBackLeft.setPower(0);
                motorBackRight.setPower(0);
                spinner.setPower(.8);
                sleep(0250);
                telemetry.update();
                currentstep2++;
            }

            if (currentstep2 == 6){
                spinner.setPower(0);
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(-.6);
                motorBackLeft.setPower(-.6);
                motorBackRight.setPower(-.6);
                sleep(0600);
                telemetry.update();
                currentstep2++;
            }

            if (currentstep2 == 7){
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(.6);
                motorBackLeft.setPower(.6);
                motorBackRight.setPower(-.6);
                sleep(9000);
                telemetry.update();
                currentstep2++;
            }

            else if (currentstep3 == 0  center ){
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(-.6);
                motorBackLeft.setPower(-.6);
                motorBackRight.setPower(-.6);
                sleep(0250);
                telemetry.update();
                currentstep3++;
            }

            if (currentstep3 == 1){
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(.6);
                motorBackLeft.setPower(.6);
                motorBackRight.setPower(-.6);
                sleep(1000);
                telemetry.update();
                currentstep3++;
            }

            if (currentstep3 == 2){
                motorFrontRight.setPower(0);
                motorFrontLeft.setPower(0);
                motorBackLeft.setPower(0);
                motorBackRight.setPower(0);
                spinner.setPower(.8);
                sleep(0250);
                telemetry.update();
                currentstep3++;
            }

            if (currentstep3 == 3){
                spinner.setPower(0);
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(-.6);
                motorBackLeft.setPower(-.6);
                motorBackRight.setPower(-.6);
                sleep(0500);
                telemetry.update();

                currentstep3++;
            }

            if (currentstep3 == 4) {
                motorFrontRight.setPower(-.6);
                motorFrontLeft.setPower(.6);
                motorBackLeft.setPower(.6);
                motorBackRight.setPower(-.6);
                sleep(9000);
                telemetry.update();
                currentstep3++;
            }
        } */
        telemetry.update();
    }


    private void initVuforia () {
    /*
    * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
    */
    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
    parameters.vuforiaLicenseKey = VUFORIA_KEY;
    parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
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
}
