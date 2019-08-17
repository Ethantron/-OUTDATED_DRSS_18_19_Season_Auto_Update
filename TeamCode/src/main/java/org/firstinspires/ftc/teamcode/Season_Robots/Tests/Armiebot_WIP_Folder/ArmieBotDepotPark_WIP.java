package org.firstinspires.ftc.teamcode.Season_Robots.Tests.Armiebot_WIP_Folder;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;
@Disabled
@Autonomous(name = "AutoArmieDepotPark_WIP", group = "Armiebot_Tests_Food")
public class ArmieBotDepotPark_WIP extends LinearOpMode {


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
    int currentstep = 0;
    int goldMineralX = -1;
    int silverMineral1X = -1;
    int silverMineral2X = -1;

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

        waitForStart();

        LiftArm.setPower(-1*2);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 9.5)) {
            telemetry.addData("Where", "Step 1: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower((-.6)*2);
        motorFrontLeft.setPower((-.6*.5)*2);
        motorBackLeft.setPower((.6)*2);
        motorBackRight.setPower((.6*.5)*2);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .6)) {
            telemetry.addData("Where", "Step 2: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.7);
        motorFrontLeft.setPower(.7);
        motorBackLeft.setPower(.7);
        motorBackRight.setPower(-.7);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .45)) {
            telemetry.addData("Where", "Step 3: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(1);
        motorFrontLeft.setPower(1);
        motorBackLeft.setPower(1);
        motorBackRight.setPower(1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .65)) {
            telemetry.addData("Where", "Step 4: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(.7);
        motorFrontLeft.setPower(-.7);
        motorBackLeft.setPower(-.7);
        motorBackRight.setPower(.7);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .7)) {
            telemetry.addData("Where", "Step 9: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();

        }

        motorFrontRight.setPower(-.7);
        motorFrontLeft.setPower(-.7);
        motorBackLeft.setPower(-.7);
        motorBackRight.setPower(-.7);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .2)) {
            telemetry.addData("Where", "Step 9: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();

        }

        motorFrontRight.setPower(.7);
        motorFrontLeft.setPower(.7);
        motorBackLeft.setPower(-.7);
        motorBackRight.setPower(-.7);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .45)) {
            telemetry.addData("Where", "Step 10: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        motorFrontRight.setPower(-.7);
        motorFrontLeft.setPower(.7);
        motorBackLeft.setPower(.7);
        motorBackRight.setPower(-.7);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .6)) {
            telemetry.addData("Where", "Step 11: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }

        spinner.setPower(.8);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .5)) {
            telemetry.addData("Where", "Step 12: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
            if (opModeIsActive() && ResetTime.seconds() >= .5) {
                spinner.setPower(0.0);
            }
        }

        motorFrontRight.setPower(-.8);
        motorFrontLeft.setPower(.8);
        motorBackLeft.setPower(.8);
        motorBackRight.setPower(-.8);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 2.25)) {
            telemetry.addData("Where", "Step 15: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
    }
}

