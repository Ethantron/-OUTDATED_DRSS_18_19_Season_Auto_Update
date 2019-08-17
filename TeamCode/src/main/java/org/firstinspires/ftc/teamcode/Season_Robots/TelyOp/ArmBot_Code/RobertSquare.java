package org.firstinspires.ftc.teamcode.Season_Robots.TelyOp.ArmBot_Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="Robert_Square", group="Autonomous")
public class RobertSquare extends LinearOpMode {

    //Set up the motors
    DcMotor Left;
    DcMotor Right;
    double currentstep = 0;
    ElapsedTime ResetTime = new ElapsedTime();

    @Override
    public void runOpMode() {

        //Initialize the motors
        Left = hardwareMap.dcMotor.get("Lwheel");
        Left.setDirection(DcMotor.Direction.REVERSE);
        Right = hardwareMap.dcMotor.get("Rwheel");
        Right.setDirection(DcMotor.Direction.REVERSE);
        //End of initialization

        // set power to all wheels to zero so they do not move
        Left.setPower(0);
        Right.setPower(0);

        waitForStart();
        //Waits for start button to be pressed

        Left.setPower(.95);
        Right.setPower(1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() <1.68)) {
            telemetry.addData("Where", "Step 1: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
    }
        Left.setPower(1);
        Right.setPower(-1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .25)) {
            telemetry.addData("Where", "Step 2: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
        Left.setPower(.95);
        Right.setPower(1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1.68)) {
            telemetry.addData("Where", "Step 3: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
        Left.setPower(1);
        Right.setPower(-1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .25)) {
            telemetry.addData("Where", "Step 4: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
        Left.setPower(.95);
        Right.setPower(1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1.68)) {
            telemetry.addData("Where", "Step 5: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
        Left.setPower(1);
        Right.setPower(-1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .25)) {
            telemetry.addData("Where", "Step 6: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
        Left.setPower(.95);
        Right.setPower(1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < 1.68)) {
            telemetry.addData("Where", "Step 7: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
        Left.setPower(1);
        Right.setPower(-1);
        ResetTime.reset();
        while (opModeIsActive() && (ResetTime.seconds() < .25)) {
            telemetry.addData("Where", "Step 8: %2.5f S Elapsed", ResetTime.seconds());
            telemetry.update();
        }
}
}
