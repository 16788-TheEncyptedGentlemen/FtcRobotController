package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Arm {
    /** De positie */
    public int position = 0;

    /** Ben ik al aan het stoppen? */
    boolean ikBenAanHetStoppen = false;
    public DcMotorEx motor;


    public Arm(HardwareMap hardwareMap) {
        // Create arm motor.
        motor = hardwareMap.get(DcMotorEx.class, "Arm");

        // Set accuracy of position.
        // todo: 1 is een mooi getal volgens Jeroen. Controleer of dit klopt! was 11
        motor.setTargetPositionTolerance(5);

        // onzin van bram
        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);


        /** Run all motors with encoders. */
        motor.setTargetPosition(0);
        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    public void MoveArmUp() {
        motor.setPower(1.0);
        motor.setTargetPosition(position);
        ikBenAanHetStoppen = false;
        position++;
    }

    public void MoveArmDown() {
        motor.setPower(1.0);
        motor.setTargetPosition(position);
        ikBenAanHetStoppen = false;
        position--;
    }

    public void StopArm() {
        motor.setPower(1.0);
        if (ikBenAanHetStoppen) {
            motor.setTargetPosition(position);
        } else {
            position = motor.getCurrentPosition();
            ikBenAanHetStoppen = true;
        }
    }

    public void AutoArmToBoardPosition(){
        motor.setPower(1.0);
        motor.setTargetPosition(69);
    }


}