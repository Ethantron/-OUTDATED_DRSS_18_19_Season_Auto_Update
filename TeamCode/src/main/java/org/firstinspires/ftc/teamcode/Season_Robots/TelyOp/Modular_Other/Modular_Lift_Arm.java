package org.firstinspires.ftc.teamcode.Season_Robots.TelyOp.Modular_Other;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@TeleOp(name = "Modular_Lift_Arm", group= "Modular_Other")
public class Modular_Lift_Arm extends LinearOpMode {

    public DcMotor LiftArm;
    int currentstep = 0;

    @Override
    public void runOpMode() {

        LiftArm = hardwareMap.dcMotor.get("LA");
        LiftArm.setDirection(DcMotor.Direction.FORWARD);
        waitForStart();

        while (!isStopRequested()){
            if (currentstep == 0){
                if (gamepad1.a){
                    LiftArm.setPower(1.0);
                    currentstep ++;
                }
            }

            if (currentstep == 1) {
                sleep(9000);
                currentstep ++;
            }

            if (currentstep == 2) {
                LiftArm.setPower(0.0);
                currentstep ++;
            }

            if (currentstep == 3){
                if(gamepad1.a) {
                    LiftArm.setPower(-1.0);
                    currentstep++;
                }
            }

            if (currentstep == 4) {
                sleep(9500);
                currentstep ++;
            }

            if (currentstep == 5) {
                LiftArm.setPower(0.0);
                currentstep = 0;
            }
        }

    }
}
