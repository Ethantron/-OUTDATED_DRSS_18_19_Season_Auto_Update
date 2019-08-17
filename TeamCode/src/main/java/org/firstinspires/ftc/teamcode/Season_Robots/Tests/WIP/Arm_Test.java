package org.firstinspires.ftc.teamcode.Season_Robots.Tests.WIP;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
@Disabled
@TeleOp(name = "Arm_Test", group= "Test_WIP")
public class Arm_Test extends OpMode {

    DcMotor shoulder;
    DcMotor elbow;
    DcMotor spinner;
    AnalogInput Potent;
    double shoulderpower;
    double elbowpower;





    public void init(){

        shoulder = hardwareMap.dcMotor.get("SH");
        shoulder.setDirection(DcMotorSimple.Direction.FORWARD);

        elbow = hardwareMap.dcMotor.get("EL");
        elbow.setDirection(DcMotorSimple.Direction.REVERSE);

        spinner = hardwareMap.dcMotor.get("SP");
        spinner.setDirection(DcMotorSimple.Direction.FORWARD);

        AnalogInput Potent = hardwareMap.analogInput.get("PM");


    }

    public void loop() {




        shoulder.setPower(shoulderpower);
        elbow.setPower(elbowpower);

        shoulderpower = Range.clip(Math.pow(gamepad2.left_stick_y, 3), -1,1);
        elbowpower = Range.clip(Math.pow(gamepad2.right_stick_y, 3), -1, 1);

        if (gamepad2.a){
            shoulder.getCurrentPosition();
            elbow.getCurrentPosition();
            shoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            shoulder.setTargetPosition(15);
            elbow.setTargetPosition(-60);
            shoulder.setPower(1);
            elbow.setPower(1);
        }
        else {
            shoulder.setPower(shoulderpower);
            elbow.setPower(elbowpower);
        }

        if (gamepad2.right_bumper){
            elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            shoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        if (gamepad2.b){
            spinner.setPower(1.0);
            spinner.setDirection(DcMotorSimple.Direction.FORWARD);

        }

        if (gamepad2.x){
            spinner.setPower(1.0);
            spinner.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (!gamepad2.x && !gamepad2.b){
            spinner.setPower(0);
        }

        //if (Potent.getVoltage() <= 1 && gamepad2.y) {
          //  shoulder.setPower(0.50);
        //}

        else {
            shoulderpower = Range.clip(Math.pow(gamepad2.left_stick_y, 3), -1,1);
        }

        telemetry.addData("Potentiometer", Potent.getVoltage());
        telemetry.addData("Elbow speed", elbow.getPower());
        telemetry.addData("Path", shoulder.getCurrentPosition() + " " + elbow.getCurrentPosition());
        telemetry.addData("Target", shoulder.getTargetPosition() + " " + elbow.getTargetPosition());
        telemetry.update();
    }


}
