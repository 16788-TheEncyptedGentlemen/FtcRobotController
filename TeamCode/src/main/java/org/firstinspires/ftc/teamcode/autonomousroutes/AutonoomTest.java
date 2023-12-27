package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class AutonoomTest extends LinearOpMode {
    private final boolean BLUE_SIDE = false;
    private final boolean SKIP_VISION = false;
    private BezierCurveRoute case0;
    private BezierCurveRoute case2;
    private CompetitionRobot robot;
    private BezierCurveRoute redStart1Case2Board;

    private void initAutonomous() {
        robot = new CompetitionRobot(this);

        case0 = new BezierCurveRoute(
                new double[]{-111.135, 136.23, -53.3766666666667}, //The x-coefficients
                new double[]{-3.58499999999987, 59.7499999999999, 5.97500000000001}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2 = new BezierCurveRoute(
                new double[] {-14.9375000000001, 53.7750000000004, -83.6500000000008, 122.487500000001, -61.5425000000004}, //The x-coefficients
                new double[] {182.2375, -274.85, 101.575, 167.3, -115.3175}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.FOLLOW, //STRAFE or FOLLOW
                this
        );

        redStart1Case2Board = new BezierCurveRoute(
                new double[] {-544.919999999999, 946.439999999998, -1051.59999999999, 29872.0125, -201118.5, 643184.85, -1225166.58, 1502769.2625, -1186862.05, 560016.435, -128844.9, 6803.7325}, //The x-coefficients
                new double[] {93.2100000000003, -2168.925, 14196.6, -33125.4, 118305, -455474.25, 937448.82, -957974.7375, 374501.05, 117319.125, -148963.92, 36051.3575}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

    }

    @Override
    public void runOpMode() {
        int markerPosition = 1;

        initAutonomous();

        while (!isStarted() && !isStopRequested()) {
            markerPosition = robot.webcam.getMarkerPosition(BLUE_SIDE);
            // result
            telemetry.addData("Position", markerPosition);
            telemetry.update();
        }

        // Initialisation.
        waitForStart();

        // Choose default option if skip.
        if (SKIP_VISION) {
            markerPosition = 1;
        }

        switch (markerPosition) {
            case 0: // LEFT
                leftPixelPlacement();
                return;
            case 2: // RIGHT
                rightPixelPlacement();
                return;
            default: // Default MIDDLE
                middlePixelPlacement();
                return;
        }
    }

    private void middlePixelPlacement() {
        robot.drivetrain.driveStraight(80, 0.4);
        robot.drivetrain.driveStraight(-25, 0.4);
    }

    private void rightPixelPlacement() {
        case2.executeWithPointSkip();
        robot.drivetrain.driveStraight(-10, 0.4);
        robot.drivetrain.turnRobotAO(90);
        robot.imu.reset();
        redStart1Case2Board.executeWithPointSkip();
    }

    private void leftPixelPlacement() {
        case0.executeWithPointSkip();
        robot.drivetrain.driveStraight(-10, 0.4);
    }
}