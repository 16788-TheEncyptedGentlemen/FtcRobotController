package org.firstinspires.ftc.Robots;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.RobotParts.Odometry.OdometryOmniWheels; 
import org.firstinspires.ftc.RobotParts.IMU;
import org.firstinspires.ftc.RobotParts.Drivetrains.MecanumDrivetrain;
import org.firstinspires.ftc.RobotParts.Intakes.CompliantIntake;
import org.firstinspires.ftc.RobotParts.Grabbers.*;
import org.firstinspires.ftc.RobotParts.Webcam.Webcam;
import org.firstinspires.ftc.RobotParts.Shooters.Ringshooter.RingShooterOld;
import org.firstinspires.ftc.RobotParts.Other.ShootPositionColorSensors;


public class WedstrijdRobot extends LoaderDisplay
{
    //---------------------------------------------------------------------
    //Used variabels:
    // * Odometry: The odometry of the robot to calculate the position of the robot
    // * IMU: The integrated measurement unit inside of the Expansion hub that we use as gyroscope
    // * Drivetrain: The drivetrain of the robot to move the robot
    // * Intake: The intake of the robot to take in rings
    // * WobbleGoaldropper: The grabber in front of the robot for autonomous to drop the wobblegoal
    // * WobbleGoalgrabber: The grabber that grabs the wobble goal and places it outside the field
    // * webcam: The webcam of the robot to measure the amount of rings at the start of autonomous
    // * Shooter: The shooter of the robot, including the processing system and the actual shooter
    // * ColorSensors: The color sensors to help align the robot on the shooting line
    // * runningOpModeAut: A LinearOpMode that is associative to the main program, if the main program is a LinearOpMode
    // * runningOpModeDC: An OpMode that is associative to the main program, if the main program is an OpMode
    //---------------------------------------------------------------------
        public OdometryOmniWheels Odometry;
        public IMU imu;
        public MecanumDrivetrain Drivetrain; 
        public CompliantIntake Intake;
        public WobbleGoalDropper WobbleGoaldropper;
        public WobbleGoalGrabber WobbleGoalgrabber;
        public Webcam webcam;
        public RingShooterOld Shooter;
        public ShootPositionColorSensors ColorSensors;
        public TestThreadGrabber TestGrabber;
    //---------------------------------------------------------------------
    //Used variabels:
    //---------------------------------------------------------------------
    
    
    
    
    
    //---------------------------------------------------------------------
    //Constructors:
    // * LinearOpMode: The constructor for a LinearOpMode program
    // * OpMode: The constructor for an OpMode program
    //---------------------------------------------------------------------
        public WedstrijdRobot(LinearOpMode _runningLinearOpMode)
        { 
            runningLinearOpMode = _runningLinearOpMode;
            HardwareMap hardwaremap = runningLinearOpMode.hardwareMap;
            
            ShowLoading("Odometry");
            Odometry = new OdometryOmniWheels(hardwaremap);
            ShowLoading("imu");    
            imu = new IMU(hardwaremap);
            ShowLoading("Drivetrain"); 
            Drivetrain = new MecanumDrivetrain(runningLinearOpMode, Odometry, imu);
            ShowLoading("Intake");   
            Intake = new CompliantIntake(hardwaremap);   
            ShowLoading("Shooter");
            Shooter = new RingShooterOld(hardwaremap, Drivetrain);
            ShowLoading("ColorSensors");
            ColorSensors = new ShootPositionColorSensors(runningLinearOpMode, Drivetrain);
            ShowLoading("WobbleGoaldropper");
            WobbleGoaldropper = new WobbleGoalDropper(runningLinearOpMode);
            ShowLoading("webcam");
            webcam = new Webcam(runningLinearOpMode);
            ShowLoading("TestThreadGrabber");
            TestGrabber = new TestThreadGrabber(runningLinearOpMode);
            
            ShowLoading("Done initializing! Press start to play");
        }
        
        
        
        public WedstrijdRobot(OpMode _runningOpmode)
        {
            runningOpMode = _runningOpmode;
            HardwareMap hardwaremap = runningOpMode.hardwareMap;
            
            ShowLoading("Odometry");
            Odometry = new OdometryOmniWheels(hardwaremap);       
            ShowLoading("imu");
            imu = new IMU(hardwaremap);  
            ShowLoading("Drivetrain"); 
            Drivetrain = new MecanumDrivetrain(hardwaremap, Odometry, imu); 
            ShowLoading("Intake");
            Intake = new CompliantIntake(hardwaremap);         
            ShowLoading("Shooter");
            Shooter = new RingShooterOld(hardwaremap, Drivetrain);
            ShowLoading("ColorSensors");
            ColorSensors = new ShootPositionColorSensors(hardwaremap, Drivetrain);
            ShowLoading("WobbleGoalgrabber");
            WobbleGoalgrabber = new WobbleGoalGrabber(hardwaremap);  
            ShowLoading("TestThreadGrabber");
            TestGrabber = new TestThreadGrabber(runningOpMode);            
            
            ShowLoading("Done initializing! Press start to play");
        }
    //---------------------------------------------------------------------
    //Constructors
    //---------------------------------------------------------------------
    
    
    
    
    
    //---------------------------------------------------------------------
    //Other methods
    //---------------------------------------------------------------------
    
        public void KillAllThreads()
        {
            TestGrabber.Kill();
        }
    
    //---------------------------------------------------------------------
    //Other methods
    //---------------------------------------------------------------------
}
