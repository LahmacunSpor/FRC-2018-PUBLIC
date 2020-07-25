/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.Constants;

public class RobotContainer
{
    // The robot's subsystems and commands are defined here...
    public static subDrivetrain subDrivetrain = new subDrivetrain();
    public static autonomous autonomous = new autonomous();

    XboxController xbox = new XboxController(Constants.controller.Xbox360);

    public RobotContainer()
    {
        configureButtonBindings();
    }
    private void configureButtonBindings()
    {
        driveWithJoysticks(xbox.getRawAxis(1), xbox.getRawAxis(3));
        JoystickButton X1 = new JoystickButton(xbox, 1);
    }
    private void driveWithJoysticks(double leftAxis, double rightAxis){
        subDrivetrain.tankDrive(leftAxis, rightAxis);
    }

    public Command getAutonomousCommand()
    {
        return autonomous;
    }
}
