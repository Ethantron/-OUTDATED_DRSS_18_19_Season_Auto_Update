package org.firstinspires.ftc.teamcode.Season_Robots.Tests.BabyBot_Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelImpl;

@TeleOp(name="Button_Test_1", group="Tests")
public class Button_Test extends LinearOpMode {



    DigitalChannel button;


    @Override
    public void runOpMode() {

// set digital channel to input mode.

        button = hardwareMap.get(DigitalChannel.class, "button");
        button.setMode(DigitalChannel.Mode.INPUT);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
// Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {
// is button pressed?
            if (button.getState() == false) {
                // button is pressed.
                telemetry.addData("Button", "PRESSED");
            } else {
                // button is not pressed.
                telemetry.addData("Button", "NOT PRESSED");
            }

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
