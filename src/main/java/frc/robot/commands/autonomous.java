package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subDrivetrain;


public class autonomous extends CommandBase {
    private final subDrivetrain drivetrain = subDrivetrain.getInstance();

    private final double kDriveTick2Feet = 1.0 / 128 * 6 * Math.PI / 12;
    final double kP = 0.5;
    final double kI = 0.5;
    final double kD = 0.5;
    final double iLimit = 1;

    int sectionNum = 0; /*
     0 = go to forward (3.4 feet)
     1 = go to reverse (3.4 feet)
     2 = turn left with right motors (30) degree, Math.PI * 2.25 / 36 * 6)
     3 = go to forward (7.5 feet)
     4 = turn right with left motors (60 degree, Math.PI * 2.25 / 36 * 3)
     5 = go to forward (5.0 feet)
     */
    int driveStyle = 0; //0 = tankDrive, 1 = leftMotors, 2 = rightMotors
    boolean driveRotation = true; //true = forward, false = reverse

    double setpoint = 0;
    double errorSum = 0;
    double lastTimestamp = 0;
    double lastError = 0;

    public void clearValues(){
        drivetrain.encoder.reset();
        errorSum = 0;
        lastError = 0;
        lastTimestamp = Timer.getFPGATimestamp();
        setpoint = 0;
    }

    public double degree2Feet(double degree){
        return Math.PI * 2.25 / 36 * (degree / 10);
    }

    public autonomous() {
        addRequirements(this.drivetrain);
    }

    @Override
    public void initialize() {
        clearValues();
    }

    @Override
    public void execute() {
        if(sectionNum == 0){
            driveRotation = true;
            driveStyle = 0;
            setpoint = 3.4;
        }
        if(sectionNum == 1){
            driveRotation = false;
            driveStyle = 0;
            setpoint = 3.4;
        }
        if(sectionNum == 2){
            driveRotation = true;
            driveStyle = 2;
            setpoint = degree2Feet(30);
        }
        if(sectionNum == 3){
            driveRotation = true;
            driveStyle = 0;
            setpoint = (7.5);
        }
        if(sectionNum == 4){
            driveRotation = true;
            driveStyle = 1;
            setpoint = degree2Feet(60);
        }
        if(sectionNum == 5){
            driveRotation = true;
            driveStyle = 0;
            setpoint = 5;
        }
        double sensorPosition = drivetrain.encoder.get() * kDriveTick2Feet;
        double error = setpoint - sensorPosition;
        double dt = Timer.getFPGATimestamp() - lastTimestamp;
        if (Math.abs(error) < iLimit) {
            errorSum += error * dt;
        }
        double errorRate = (error - lastError) / dt;
        double outputSpeed = kP * error + kI * errorSum + kD * errorRate;
        if(driveStyle == 0){
            if(!driveRotation) outputSpeed *= -1;
            drivetrain.tankDrive(outputSpeed, outputSpeed);
        }
        if(driveStyle == 1){
            if(!driveRotation) outputSpeed *= -1;
            drivetrain.leftMotor.set(outputSpeed);
        }
        if(driveStyle == 2){
            if(!driveRotation) outputSpeed *= -1;
            drivetrain.rightMotor.set(outputSpeed);
        }
        lastTimestamp = Timer.getFPGATimestamp();
        lastError = error;
        if(outputSpeed == 0){
            sectionNum++;
            clearValues();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
