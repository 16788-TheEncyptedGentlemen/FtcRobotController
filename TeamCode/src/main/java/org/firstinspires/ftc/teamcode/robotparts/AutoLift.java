package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


// TODO: Make sure this program works!!!! Make different height for the different junctions.
// TODO: Aditi: Add fullstops to documentation and make sure spaces are correct!!!
// TODO: Test It!

public class AutoLift {

    /** The left DCMotor of the lift */
    public DcMotorEx left;
    /** The right DCMotor of the lift */
    public DcMotorEx right;
    /** The linear opmode for autonomous */
    public LinearOpMode runningOpMode;

    /** position values for levels */
    private int groundPos = 80;
    private int groundJunctionPos = 700;
    private int lowPolePos = 3800;
    private int midPolePos = 5600; // was 5300
    private int highPolePos = 5800;

    //TODO: Aditi: change names for configureration so that the first letter is a lowercase letter.

    /** The lift has a right and left DCMotor */
    public AutoLift(HardwareMap hardwareMap, LinearOpMode _runningOpMode)
    {
        left = hardwareMap.get(DcMotorEx.class, "LeftLift");
        right = hardwareMap.get(DcMotorEx.class, "RightLift");
        runningOpMode = _runningOpMode;

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }


    //TODO: ADITI: Make sure all the words except for first word in the function name start with capitals!!!

    /** Reusable function for moving lift to a position up or down */
    public void moveLiftToPosition(int position) {
        int currentPos = -left.getCurrentPosition();
        boolean targetAbove = currentPos < position;   // go up or go down?

        if (targetAbove){ // going up
            while (Math.abs(left.getCurrentPosition()) < position && !runningOpMode.isStopRequested()){
                left.setPower(-0.6);
                right.setPower(0.6);
            }
        } else { // going down
            while (Math.abs(left.getCurrentPosition()) > position && !runningOpMode.isStopRequested()){
                left.setPower(0.6);
                right.setPower(-0.6);
            }
        }
        left.setPower(0.0); // stop when reached
        right.setPower(0.0);
    }

    public void liftGroundLevel() {
        moveLiftToPosition(groundPos);
    }

    public void liftGroundJunction() {
        moveLiftToPosition(groundJunctionPos);
    }

    public void liftLowPole() {
        moveLiftToPosition(lowPolePos);
    }

    public void liftMidPole() {
        moveLiftToPosition(midPolePos);
    }

    public void liftHighPole() {
        moveLiftToPosition(highPolePos);
    }

    public int currentEnc() {
        return left.getCurrentPosition();
    }


}



// TODO: Aditi: Add documentation to this class