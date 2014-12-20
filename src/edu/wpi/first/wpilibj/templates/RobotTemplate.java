/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot {
    Joystick leftJoystick = new Joystick(1);
    Joystick rightJoystick = new Joystick(2);
    RobotDrive robotDrive = new RobotDrive(4,3,2,1);
    DoubleSolenoid climberPiston = new DoubleSolenoid(3,4);
    Relay compressorSpike = new Relay(1);
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        
        robotDrive.setSafetyEnabled(true);
        while(isOperatorControl() && isEnabled()){
            robotDrive.tankDrive(-leftJoystick.getY(), -rightJoystick.getY());
            if(rightJoystick.getRawButton(6)){
                climberPiston.set(DoubleSolenoid.Value.kForward);
            }
            if(rightJoystick.getRawButton(7)){
                climberPiston.set(DoubleSolenoid.Value.kReverse);
            }
            if(rightJoystick.getRawButton(8)){
                climberPiston.set(DoubleSolenoid.Value.kOff);
            }
            Timer.delay(0.01);
        }
    }
}
