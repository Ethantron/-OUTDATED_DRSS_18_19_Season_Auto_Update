package org.firstinspires.ftc.teamcode.Season_Robots.Tests.WIP;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@TeleOp(name= "Potentiometer_Tests", group = "WIP")
public class Potentiometer_Tests extends OpMode {
    DcMotor TM;
    AnalogInput PM;

    @Override
    public void init(){

        TM= hardwareMap.dcMotor.get("TM");
        TM.setDirection(DcMotor.Direction.FORWARD);

        PM = hardwareMap.analogInput.get("PM");
    }

    @Override
    public void loop(){

        telemetry.addData("Potentiometer", PM.getVoltage());
        telemetry.update();

        if (gamepad1.a && PM.getVoltage() >= 0.75){
            TM.setPower(-1);
        }

        if (gamepad1.b && PM.getVoltage() >= 0.50){
            TM.setPower(-1);
        }

        if(gamepad1.x && PM.getVoltage() >= 1.00){
            TM.setPower(-1);
        }

        if (gamepad1.y){
            TM.setPower(-1);
        }

        if (gamepad1.left_stick_y >0.1){
            TM.setPower(1);
        }

        if (gamepad1.left_stick_y <-0.1){
            TM.setPower(-1);
        }


        else {
            TM.setPower(0.0);
        }
    }
}
