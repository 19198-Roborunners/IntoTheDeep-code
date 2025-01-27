package org.firstinspires.ftc.teamcode;
import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous(name = "auto1122", group = "Autonomous")
public class auto1122 extends LinearOpMode {

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(-11.73, 63.74, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        // vision here that outputs position
        int visionOutputPosition = 1;

        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .splineTo(new Vector2d(-0.09, 32.39), Math.toRadians(270.00))  // First path segment
                .waitSeconds(0.5)  // Add a pause
                .strafeTo(new Vector2d(-33.6, 41.3))  // Strafe to another position
                .splineTo(new Vector2d(-36.47, 18.51), Math.toRadians(248.20))  // Spline to a specific position
                .splineTo(new Vector2d(-45, 9), Math.toRadians(270.00))  // Another spline
                .strafeTo(new Vector2d(-44.5, 53.6))  // Strafe again
                .strafeTo(new Vector2d(-45.3, 45.9))  // Another strafe
                .turn(Math.toRadians(90));  // Turn 90 degrees
        TrajectoryActionBuilder tab2 = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(-39.9, 47.7))  // Continue strafing
                .strafeTo(new Vector2d(-40.1, 63.8))  // Final position in this action
                .setReversed(true)  // Reverse trajectory direction
                .splineTo(new Vector2d(-3.46, 37), Math.toRadians(270.00))  // Spline to a new position
                .setReversed(false);  // Stop reversing
        TrajectoryActionBuilder tab3 = drive.actionBuilder(initialPose)
                .turn(Math.toRadians(180))  // Rotate 180 degrees
                .waitSeconds(0.5);  // Pause for half a second;
        Action trajectoryActionCloseOut = tab1.endTrajectory().fresh()
                .strafeTo(new Vector2d(48, 12))
                .build();



        while (!isStopRequested() && !opModeIsActive()) {
            int position = visionOutputPosition;
            telemetry.addData("Position during Init", position);
            telemetry.update();
        }

        int startPosition = visionOutputPosition;
        telemetry.addData("Starting Position", startPosition);
        telemetry.update();
        waitForStart();

        if (isStopRequested()) return;

        Action trajectoryActionChosen;
        if (startPosition == 1) {
            trajectoryActionChosen = tab1.build();
        } else if (startPosition == 2) {
            trajectoryActionChosen = tab2.build();
        } else {
            trajectoryActionChosen = tab3.build();
        }

        Actions.runBlocking(
                new SequentialAction(
                        trajectoryActionChosen,

                        trajectoryActionCloseOut
                )
        );
    }
}