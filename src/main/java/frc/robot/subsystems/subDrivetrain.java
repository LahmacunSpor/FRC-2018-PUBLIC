package frc.robot.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class subDrivetrain extends SubsystemBase {
    private static subDrivetrain INSTANCE;

    public PWMTalonSRX leftBack = new PWMTalonSRX(Constants.drivebase.leftBack);
    public PWMTalonSRX leftFront = new PWMTalonSRX(Constants.drivebase.leftFront);
    public PWMTalonSRX rightBack = new PWMTalonSRX(Constants.drivebase.rightBack);
    public PWMTalonSRX rightFront = new PWMTalonSRX(Constants.drivebase.rightFront);

    public Encoder encoder = new Encoder(Constants.drivebase.encoder.channelA, Constants.drivebase.encoder.channelB, Constants.drivebase.encoder.reverseDirection, EncodingType.k4X);

    public SpeedControllerGroup leftMotor = new SpeedControllerGroup(leftBack, leftFront);
    public SpeedControllerGroup rightMotor = new SpeedControllerGroup(rightBack, rightFront);

    public DifferentialDrive drive = new DifferentialDrive(leftMotor, rightMotor);

    public void tankDrive(double left, double right){
        drive.tankDrive(left * 0.6, right * 0.6);
    }

    public static subDrivetrain getInstance() {
        if (INSTANCE == null) {
            synchronized (subDrivetrain.class) {
                if (INSTANCE == null) {
                    INSTANCE = new subDrivetrain();
                }
            }
        }
        return INSTANCE;
    }
    public subDrivetrain() {
    }
}

