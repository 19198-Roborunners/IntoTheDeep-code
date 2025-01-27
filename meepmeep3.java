package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;

import org.jetbrains.annotations.NotNull;
import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.core.colorscheme.scheme.*;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class meepmeep3 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity blueSpecimen = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-12, 60, Math.toRadians(-90f)))


                        .splineToConstantHeading(new Vector2d(0, 30.5), Math.toRadians(-90))
                        .setTangent(Math.toRadians(135f))
                        .splineToConstantHeading(new Vector2d(-23.5, 38), Math.toRadians(180f))

                        .splineToConstantHeading(new Vector2d(-34, 12), Math.toRadians(-90f))
                        .splineToConstantHeading(new Vector2d(-51, 12), Math.toRadians(90f))
                        .splineToConstantHeading(new Vector2d(-46, 49), Math.toRadians(90f))
                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-46, 18), Math.toRadians(-90f))
                        .splineToConstantHeading(new Vector2d(-54, 12), Math.toRadians(90f))
                        .splineToConstantHeading(new Vector2d(-54, 48), Math.toRadians(90f))
                        .turn(Math.toRadians(180f))
                        .setTangent(Math.toRadians(-45f))
                        .splineToConstantHeading(new Vector2d(-36, 58.5), Math.toRadians(90))
                        .turn(Math.toRadians(180f))
                        //-----
                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-4, 33.5), Math.toRadians(-90))
                        .setTangent(Math.toRadians(90))
                        .strafeTo(new Vector2d(-4,37))
                        .turn(Math.toRadians(180f))
                        .splineToConstantHeading(new Vector2d(-45, 56.5), Math.toRadians(90))
                        .turn(Math.toRadians(180))
                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-7, 35), Math.toRadians(-90))

                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)

                .addEntity(blueSpecimen)


                .start();
    }
}