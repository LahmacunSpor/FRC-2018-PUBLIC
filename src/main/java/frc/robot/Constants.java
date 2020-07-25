/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 * <p>
 * It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants 
{
    public static final class drivebase{
        public static final int leftFront = 0;
        public static final int leftBack = 1;
        public static final int rightFront = 2;
        public static final int rightBack = 3;

        public static final class encoder{
            public static final int channelA = 0;
            public static final int channelB = 1;
            public static final boolean reverseDirection = false;
        }
    }
    public static final class controller{
        public static final int Xbox360 = 0;
    }
}
