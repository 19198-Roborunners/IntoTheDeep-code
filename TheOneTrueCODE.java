package org.firstinspires.ftc.robotcontroller.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TheOneTrueCODE extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors and servos
        DcMotor rightFront = hardwareMap.dcMotor.get("rightFront");
        DcMotor leftFront = hardwareMap.dcMotor.get("leftFront");
        DcMotor rightBack = hardwareMap.dcMotor.get("rightBack");
        DcMotor leftBack = hardwareMap.dcMotor.get("leftBack");
        DcMotor rightarm = hardwareMap.dcMotor.get("rightarm");
        DcMotor leftarm = hardwareMap.dcMotor.get("leftarm");
        Servo rightarmrotate = hardwareMap.servo.get("rightarmrotate");
        Servo rightextension = hardwareMap.servo.get("rightextension");
        Servo rightclawrotate = hardwareMap.servo.get("rightclawrotate");
        Servo leftarmrotate = hardwareMap.servo.get("leftarmrotate");
        Servo clawspin = hardwareMap.servo.get("clawspin");
        Servo armgrabber = hardwareMap.servo.get("armgrabber");
        Servo leftextension = hardwareMap.servo.get("leftextension");
        Servo leftclawrotate = hardwareMap.servo.get("leftclawrotate");
        Servo claw = hardwareMap.servo.get("claw");

        // Reverse right motors
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightarm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftarm.setDirection(DcMotor.Direction.REVERSE);
        leftarm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightarm.setDirection(DcMotor.Direction.FORWARD);
        leftarm.setPower(1);
        rightarm.setPower(1);

        // Wait for start signal
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            // Robot driving control
            double y = gamepad1.left_stick_y;
            double x = -gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            // Motor power calculations
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            leftFront.setPower(frontLeftPower * 0.75);
            leftBack.setPower(backLeftPower * 0.75);
            rightFront.setPower(frontRightPower * 0.75);
            rightBack.setPower(backRightPower * 0.75);



            // Arm control using D-Pad
            if (gamepad2.dpad_up) {
                rightarm.setTargetPosition(3100);
                leftarm.setTargetPosition(3100);
                leftarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            } else if (gamepad2.dpad_down) {
                rightarm.setTargetPosition(0);
                leftarm.setTargetPosition(0);
                rightarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                leftarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            //extension code thing
            if (gamepad2.dpad_left) {
                // extension out
                rightextension.setPosition(0);
                leftextension.setPosition(1);
            } else if (gamepad2.dpad_right) {
                //extension in
                rightextension.setPosition(0.27);
                leftextension.setPosition(0.73);
            }



            if (gamepad2.y) {
                armgrabber.setPosition(0);
                clawspin.setPosition(0.35);
                rightclawrotate.setPosition(0.97);
                leftclawrotate.setPosition(0.03);
                rightextension.setPosition(0.205);
                leftextension.setPosition(0.795);
                rightarmrotate.setPosition(0.905);
                leftarmrotate.setPosition(0.095);
                sleep(1200);
                armgrabber.setPosition(0.13);
                sleep(200);
                claw.setPosition(0);
            }

            //claw rotation code
            if (gamepad2.left_trigger > 0.5) {
                rightarmrotate.setPosition(0.98);
                leftarmrotate.setPosition(0.02);
            } else if (gamepad2.right_trigger > 0.5) {
                rightarmrotate.setPosition(0.7);
                leftarmrotate.setPosition(0.3);
            }
            //hanging block stuff
            if (gamepad1.a) {
                rightarmrotate.setPosition(0.88);
                leftarmrotate.setPosition(0.12);
            } else if (gamepad1.dpad_up) {
                rightarm.setTargetPosition(1900);
                leftarm.setTargetPosition(1900);
                leftarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            } else if (gamepad1.dpad_down) {
                rightarm.setTargetPosition(0);
                leftarm.setTargetPosition(0);
                leftarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightarm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                sleep(200);
                armgrabber.setPosition(0);
            }

            //claw
            if (gamepad2.left_bumper) {
                claw.setPosition(0);
            } else if (gamepad2.right_bumper) {
                claw.setPosition(0.28);
            }
             if (gamepad2.a) {
                 rightclawrotate.setPosition(0.05);
                 leftclawrotate.setPosition(0.95);
             }
            //arm grabber
            if (gamepad2.left_stick_button) {
                armgrabber.setPosition(0);
            } else if (gamepad2.right_stick_button){
                armgrabber.setPosition(0.13);
            }
            if (gamepad2.x) {
                clawspin.setPosition(0);
            } else if (gamepad2.b) {
                clawspin.setPosition(0.35);
            }


            //print servo data
            telemetry.addData("Right arm rotate position", rightarmrotate.getPosition());
            telemetry.addData("Left arm rotate position", leftarmrotate.getPosition());
            telemetry.addData("Claw spin position", clawspin.getPosition());
            telemetry.update();
        }

    }

}
