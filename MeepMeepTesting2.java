package com.example.meepmeeptesting;



import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import org.rowlandhall.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;

public class MeepMeepTesting2 {

    public static void main(String[] args) {
        // Create a new MeepMeep instance with the desired screen size
        MeepMeep meepMeep = new MeepMeep(700);

        // Create and set up the bot with Road Runner configuration
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(10.31, 63.74, Math.toRadians(-90.00)))
                        // Define the spline path for the trajectory
//                        .splineTo(new Vector2d(-0.09, 32.39), Math.toRadians(270.00))
//                       // .addTemporalMarker(() -> servo.setPosition(0)) // Lower servo
//                        //.addTemporalMarker(() -> servo.setPosition(0)) // raise servo
//                        .waitSeconds(0.5)
//                        .back(10)
//                        .strafeRight(29)
//                        .splineTo(new Vector2d(-36.47, 18.51), Math.toRadians(248.20))
//                        .splineTo(new Vector2d(-45, 9), Math.toRadians(270.00))
//                        .strafeLeft(45)
//                        .strafeRight(8)
//                        .turn(Math.toRadians(90))
//                        //.addTemporalMarker(() -> servo.setPosition(0)) // raise arm
//                        .strafeRight(5)
//                        .forward(18)
//                        .setReversed(true)
//                        .splineTo(new Vector2d(-3.46, 37), Math.toRadians(270.00))
//                        .setReversed(false)
//                        .turn(Math.toRadians(180))
//                        //.addTemporalMarker(() -> servo.setPosition(0)) // lower arm
//                        .waitSeconds(0.5)
                       // TrajectorySequence trajectory0 = drive.trajectorySequenceBuilder(new Pose2d(10.31, 62.06, Math.toRadians(90.00)))
                        .setReversed(false)
                        .splineTo(new Vector2d(52, 53.81), Math.toRadians(45.00))
                        .setReversed(false)
                        .turn(Math.toRadians(180))
                        .build());

        // Set up the background, dark mode, and add the bot to the simulation
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();  // Start the simulation
    }
}

