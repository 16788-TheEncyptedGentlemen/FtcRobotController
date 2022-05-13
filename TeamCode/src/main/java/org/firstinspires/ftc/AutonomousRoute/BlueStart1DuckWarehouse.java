package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.Robots.WedstrijdRobot;

//Uncomment @Autonomous to show up on the DC controller app
@Autonomous
public class  BlueStart1DuckWarehouse extends LinearOpMode
{
    public void runOpMode()
    {
        //Variables------------------------------------
        WedstrijdRobot Robot = new WedstrijdRobot(this);

        BezierCurveRoute BlueStart1Duck = new BezierCurveRoute(
                new double[] {23.8999999999986, 107.550000000008, -113.52500000002, -5.97499999998126, 12.5474999999944}, //The x-coefficients
                new double[] {-149.374999999998, 233.025, -149.375000000003, -83.6499999999967, 93.8074999999988}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );





        //Initialisation------------------------------------

        waitForStart();

        //Run program------------------------------------

        BlueStart1Duck.ExecuteWithPointSkip();
        Robot.DuckWheel.TurnRight();
        sleep(4000);
        Robot.DuckWheel.Stop();
        Robot.Drivetrain.DriveStraight(120.0, 0.6);
        Robot.Drivetrain.TurnRobotAO(0);
        sleep(500);
        Robot.Drivetrain.powerStrafeValues(-90.0, 0.5, 0);
        sleep(1000);
        Robot.Drivetrain.DriveStraight(135.0, 0.6);


    }

}
