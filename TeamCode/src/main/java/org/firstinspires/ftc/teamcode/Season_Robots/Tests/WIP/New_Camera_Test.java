package org.firstinspires.ftc.teamcode.Season_Robots.Tests.WIP;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "New Camera Test", group= "team")
public class New_Camera_Test extends OpMode {
    Servo Pan;

    public void init(){
        Pan = hardwareMap.servo.get("Pan");
        Pan.setPosition(0.25);
    }

    public void loop(){
        if (gamepad1.dpad_up){
            Pan.setPosition(.25);
        }

        if (gamepad1.dpad_down){
            Pan.setPosition(.45);
        }
        if (gamepad1.dpad_left){
            if (Pan.getPosition() > 0.00) {
                Pan.setPosition(Pan.getPosition() - 0.003);
            }
        }
        if (gamepad1.dpad_right){
            if (Pan.getPosition() <0.45) {
                Pan.setPosition(Pan.getPosition() + 0.003);
            }
        }
        telemetry.addData("position",Pan.getPosition());
    }
}