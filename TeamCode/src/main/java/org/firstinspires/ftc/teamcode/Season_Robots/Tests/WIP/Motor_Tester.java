package org.firstinspires.ftc.teamcode.Season_Robots.Tests.WIP;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
@Disabled
@TeleOp(name = "Motor_Tester", group= "WIP")
public class Motor_Tester extends OpMode {

    DcMotor Test;
    double setpower = 1.0;
    double Test_Power;

    public void init() {

        Test = hardwareMap.dcMotor.get("Test");
        Test.setDirection(DcMotor.Direction.FORWARD);

    }

    public void loop() {

            if (gamepad1.a) {
                setpower = 1.0;
            }

            if (gamepad1.b) {
                setpower = 0.75;
            }

            if (gamepad1.x) {
                setpower = 0.5;
            }

            if (gamepad1.y) {
                setpower = 0.25;
            }


            if (gamepad1.dpad_up){
                Test.setTargetPosition(0);
                Test.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Test.setPower(1.0);
            }

            if (gamepad1.dpad_left){
                Test.setTargetPosition(10000);
                Test.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Test.setPower(1.0);
            }

            if (gamepad1.dpad_right){
                Test.setTargetPosition(-10000);
                Test.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                Test.setPower(1.0);
            }

            float Test_Power = gamepad1.left_stick_y;
            if (gamepad1.left_stick_y == 1){
                Test.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }

            Test.setPower(Test_Power);


        telemetry.addData("Current Speed", setpower);
        telemetry.addData("Current Position", Test.getCurrentPosition());
        telemetry.addData("Target Position", Test.getTargetPosition());

    }

}
