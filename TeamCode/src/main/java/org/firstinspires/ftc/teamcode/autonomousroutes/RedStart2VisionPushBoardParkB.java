package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class RedStart2VisionPushBoardParkB extends LinearOpMode {
    private final boolean BLUE_SIDE = false;
    private final boolean SKIP_VISION = false;
    private BezierCurveRoute case0;
    private BezierCurveRoute case2;
    private BezierCurveRoute case0Board0;
    private BezierCurveRoute case1Board1;
    private BezierCurveRoute case2Board2;
    private BezierCurveRoute case0ParkB;
    private BezierCurveRoute case1ParkB;
    private BezierCurveRoute case2ParkB;
    private CompetitionRobot robot;

    private void initAutonomous() {
        robot = new CompetitionRobot(this);

        case0 = new BezierCurveRoute(
                new double[] {-3.18666666666675, 54.97, -129.06, 49.7916666666665}, //The x-coefficients
                new double[] {337.786666666667, -619.01, 497.12, -148.18}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2 = new BezierCurveRoute(
                new double[] {121.093333333333, -167.3, 108.346666666667, -33.0616666666667}, //The x-coefficients
                new double[] {1.59333333333325, -14.3399999999995, 17.5266666666658, 59.3516666666669}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case0Board0 = new BezierCurveRoute(
                new double[] {-14.3399999999999, -83.6499999999999, 2158.96666666667, -3961.425, 2523.84, -509.866666666666}, //The x-coefficients
                new double[] {-179.25, 495.924999999999, -390.366666666664, -412.275000000002, 705.05, -202.751666666667}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case1Board1 = new BezierCurveRoute(
                new double[] {90.4216666666666}, //The x-coefficients
                new double[] {0}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2Board2 = new BezierCurveRoute(
                new double[] {121.093333333333, -167.3, 108.346666666667, -33.0616666666667}, //The x-coefficients
                new double[] {1.59333333333325, -14.3399999999995, 17.5266666666658, 59.3516666666669}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case0ParkB = new BezierCurveRoute(
                new double[] {4.78000000000014, -17.9250000000003, 48.1983333333335}, //The x-coefficients
                new double[] {-231.83, 253.34, -90.4216666666666}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case1ParkB = new BezierCurveRoute(
                new double[] {4.77999999999986, -7.16999999999985, -20.7133333333332, 58.9533333333331}, //The x-coefficients
                new double[] {-180.046666666666, 155.35, -70.1066666666665, 25.8916666666666}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2ParkB = new BezierCurveRoute(
                new double[] {-7.17000000000007, 3.58500000000015, 35.0533333333333}, //The x-coefficients
                new double[] {-90.8199999999999, 71.7, -12.7466666666667}, //The y-coefficients
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

        // TODO: Hier 1 functie van maken.
        robot.grabber.Grab();
        robot.tiltMechanism.TiltMechanismUp();

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
        //Push pixel naar de middelste streep.
        robot.drivetrain.driveStraight(80, 0.4);
        // Laat pixel in pusher los.
        robot.pusher.releasePreLoadLeft();
        //Rij een stuk naar achter zodat de pixel niet meer onder de robot ligt.
        robot.drivetrain.driveStraight(-25, 0.4);
        // Rij naar board.
        case1Board1.executeWithPointSkip();
        // Draai
        robot.drivetrain.turnRobotAO(90);
        // Beweeg arm naar juiste positie.
        robot.arm.AutoArmToBoardPosition();
        // Rij stukje naar voren.
        robot.drivetrain.driveStraight(10, 0.4);
        // laat pikel los.
        robot.grabber.Drop();
        // rijd achteruit.
        robot.drivetrain.driveStraight(-10,0.4);
        // Beweeg arm terug.
        robot.arm.MoveArmDown();
        // Draai robot.
        robot.drivetrain.turnRobotAO(-90);
        // Parkeer in backstage.
        case1ParkB.executeWithPointSkip();

    }

    private void rightPixelPlacement() {
        // Push pixel naar de rechter streep.
        case2.executeWithPointSkip();
        // Laat pixel in pusher los.
        robot.pusher.releasePreLoadLeft();
        // Rij een stuk naar achter zodat de pixel niet meer onder de robot ligt.
        robot.drivetrain.driveStraight(-10, 0.4);
        // Rij naar board.
        case2Board2.executeWithPointSkip();
        // Draai
        robot.drivetrain.turnRobotAO(90);
        // Beweeg arm naar juiste positie.
        robot.arm.AutoArmToBoardPosition();
        // Rij stukje naar voren.
        robot.drivetrain.driveStraight(10, 0.4);
        // laat pikel los.
        robot.grabber.Drop();
        // rijd achteruit.
        robot.drivetrain.driveStraight(-10,0.4);
        // Beweeg arm terug.
        robot.arm.MoveArmDown();
        // Draai robot.
        robot.drivetrain.turnRobotAO(-90);
        // Parkeer in backstage.
        case2ParkB.executeWithPointSkip();
    }

    private void leftPixelPlacement() {
        // Push pixel naar de rechter streep.
        case0.executeWithPointSkip();
        // Laat pixel in pusher los.
        robot.pusher.releasePreLoadLeft();
        // Rij een stuk naar achter zodat de pixel niet meer onder de robot ligt.
        robot.drivetrain.driveStraight(-10, 0.4);
        // Rij naar board.
        case0Board0.executeWithPointSkip();
        // Draai
        robot.drivetrain.turnRobotAO(90);
        // Beweeg arm naar juiste positie.
        robot.arm.AutoArmToBoardPosition();
        // Rij stukje naar voren.
        robot.drivetrain.driveStraight(10, 0.4);
        // laat pikel los.
        robot.grabber.Drop();
        // rijd achteruit.
        robot.drivetrain.driveStraight(-10,0.4);
        // Beweeg arm terug.
        robot.arm.MoveArmDown();
        // Draai robot.
        robot.drivetrain.turnRobotAO(-90);
        // Parkeer in backstage.
        case0ParkB.executeWithPointSkip();
    }
}