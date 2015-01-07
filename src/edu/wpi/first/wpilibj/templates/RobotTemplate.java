/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    RobotDrive robotDrive = new RobotDrive(4, 3, 2, 1);
    DoubleSolenoid climberPiston = new DoubleSolenoid(3, 4);
    DoubleSolenoid magazine = new DoubleSolenoid(1, 2);
    Relay compressorSpike = new Relay(1);
    DigitalInput digitalCompressor = new DigitalInput(1);
    Jaguar backMotor = new Jaguar(6);
    Jaguar frontMotor = new Jaguar(5);
    Relay hammer = new Relay(6);

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
        while (isOperatorControl() && isEnabled()) {
            robotDrive.tankDrive(-leftJoystick.getY(), -rightJoystick.getY());
            if (rightJoystick.getRawButton(6)) {
                climberPiston.set(DoubleSolenoid.Value.kForward);
            }
            if (rightJoystick.getRawButton(7)) {
                climberPiston.set(DoubleSolenoid.Value.kReverse);
            }
            if (rightJoystick.getRawButton(8)) {
                climberPiston.set(DoubleSolenoid.Value.kOff);
            }
            if (!digitalCompressor.get()) {
                compressorSpike.set(Relay.Value.kForward);
            } else {
                compressorSpike.set(Relay.Value.kOff);
            }
            if (rightJoystick.getRawButton(11)) {
                magazine.set(DoubleSolenoid.Value.kForward);
            }
            if (rightJoystick.getRawButton(10)) {
                magazine.set(DoubleSolenoid.Value.kReverse);
            }
            double transformedThrottleRight = transformThrottle(rightJoystick.getZ());
            double transformedThrottleLeft = transformThrottle(leftJoystick.getZ());
            System.out.print(rightJoystick.getZ() + "--" + transformedThrottleRight);
            System.out.println("        " + leftJoystick.getZ() + "--" + transformedThrottleLeft);
            SmartDashboard.putNumber("Left throttle:", transformedThrottleLeft);
            SmartDashboard.putNumber("Right throttle:", transformedThrottleRight);
            if (transformedThrottleLeft > 0.1) {
                backMotor.set(transformedThrottleLeft);
            } else {
                backMotor.set(0);
            }
            if (transformedThrottleRight > 0.1) {
                frontMotor.set(transformedThrottleRight);
            } else {
                frontMotor.set(0);
            }
            if(rightJoystick.getRawButton(1)){
                hammer.set(Relay.Value.kReverse);
            } else if(rightJoystick.getRawButton(2)){
                hammer.set(Relay.Value.kForward);
            } else{
                hammer.set(Relay.Value.kOff);
            }
            Timer.delay(0.01);
        }
    }

    public double transformThrottle(double throttle) {
        double transformedThrottle = ((throttle * -1) + 1) / 2;
        return transformedThrottle;
    }
}
