package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;


// TODO: Make sure this program works!!!! Make different height for the different junctions.
// TODO: Aditi: Add fullstops to documentation and make sure spaces are correct!!!
// TODO: Test It!

public class Lift {

    /** The left DCMotor of the lift */
    public DcMotorEx left;
    /** The right DCMotor of the lift */
    public DcMotorEx right;

    public TouchSensor touchSensor;

    public LinearOpMode runningOpMode;

    //TODO: Aditi: change names for configureration so that the first letter is a lowercase letter.

    /** The lift has a right and left DCMotor */
    public Lift(HardwareMap hardwareMap)
    {
        left = hardwareMap.get(DcMotorEx.class, "LeftLift");
        right = hardwareMap.get(DcMotorEx.class, "RightLift");
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        touchSensor = hardwareMap.touchSensor.get("TouchSensor");
    }

    /** The robot lift goes up */
    public void up()
    {
        left.setPower(1);
        right.setPower(-1);
    }

    /** The robot lift down */
    public void down()
    {
        left.setPower(-1);
        right.setPower(1);
    }

   /** The robot lift stops. */
    public void stop()
    {
        left.setPower(0.0);
        right.setPower(0.0);
    }

    public boolean lowPolePos()
    {
        return touchSensor.isPressed();
    }
}



// TODO: Aditi: Add documentation to this class
