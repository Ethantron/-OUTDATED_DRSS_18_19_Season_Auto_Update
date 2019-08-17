package org.firstinspires.ftc.teamcode.Season_Robots.Tests.AGagne_Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name="Robert_Autonomous", group="Autonomous")
public class Robert_Autonomous extends LinearOpMode{

    //Set up the motors
    DcMotor Left;
    DcMotor Right;
    double currentstep = 0;

    @Override
    public void runOpMode() {

            //Initialize the motors
            Left = hardwareMap.dcMotor.get("Lwheel");
            Left.setDirection(DcMotor.Direction.REVERSE);
            Right = hardwareMap.dcMotor.get("Rwheel");
            Right.setDirection(DcMotor.Direction.REVERSE);
            //End of initialization



            waitForStart();
            //Waits for start button to be pressed

        if (currentstep == 0) {
            Left.setPower(1);
            Right.setPower(1);
            currentstep++;
        }

        if (currentstep == 1) {
            sleep(2000);
            currentstep++;
        }

        if (currentstep == 2){
            Left.setPower(0);
            Right.setPower(0);
            currentstep++;
        }
    }
}
