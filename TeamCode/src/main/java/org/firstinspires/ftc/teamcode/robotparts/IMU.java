package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;



public class IMU
{
    /** Gyro: The actual IMU in this object. */
    private BNO055IMU imu;
    /** The parameters of the IMU. */
    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
    /** The offset from the absolute orienation plane. */
    private double angularOffset = 0;


    public IMU(HardwareMap hardwareMap){
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.loggingTag = "IMU";  //Loggin tag and config name ARE NOT THE SAME (I think) TODO: Figure out if luc was correct

        imu = hardwareMap.get(BNO055IMU.class, "IMU");
        imu.initialize(parameters);
    }


    /** Gets the angle of the robot on the absolute orientation plane from -180 to 180 degrees. */
    public double getAngle()
    {
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double Angle = -angles.firstAngle - angularOffset;

        if(Angle > 180)
            Angle -= 360;
        else if(Angle < -180)
            Angle += 360;

        return Angle;
    }

    /** Gets the deviation from a given angle. */
    public double getDeviation(double DesiredAngle)
    {
        double DeviationAngle = getAngle() - DesiredAngle;

        if(DeviationAngle > 180)
            DeviationAngle -= 360;
        else if(DeviationAngle < -180)
            DeviationAngle += 360;

        return DeviationAngle;
    }

    /** Returns an array with correction values for the motors to turn. */
    public double[] getTurnCorrectionValues(double DesiredAngle, double P)
    {
        double CorrectionValue = 0;
        double DeviationAngle = getDeviation(DesiredAngle);

        if(Math.abs(DeviationAngle) <= P)  {
            CorrectionValue = DeviationAngle/P;

        } else {
            if(DeviationAngle > 0) {
                CorrectionValue = 1;
            } else {
                CorrectionValue = -1;
            }
        }
        CorrectionValue *= 0.3;
        return new double[] {CorrectionValue, CorrectionValue, -CorrectionValue, -CorrectionValue};
    }


    /** Resets the entire IMU. This takes up a second. */
    public void Reset()
    {
        imu.initialize(parameters);
    }

    /** Sets AngularOffset, which will result in a resetted angular orientation. */
    public void ResetAngularOrientation()
    {
        angularOffset = getAngle();
    }


}