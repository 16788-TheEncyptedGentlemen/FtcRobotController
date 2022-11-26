package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class BlueStart1Park3 extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot robot = new CompetitionRobot(this);

        BezierCurveRoute BlueStart1Park3 = new BezierCurveRoute(
                /** The x-coefficients. */
                new double[] {152.96, -139.815, 43.8166666666667},
                /** The Y-coefficients. */
                new double[] {8.36500000000046, -65.7250000000004, 121.093333333333},
                robot,
                0.6,
                /** STRAFE or FOLLOW. */
                DRIVE_METHOD.STRAFE,
                this
        );

        /** Initialisation. */
        waitForStart();

        BlueStart1Park3.executeWithPointSkip();
    }
}