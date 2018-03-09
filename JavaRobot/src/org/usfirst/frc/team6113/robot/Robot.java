
package org.usfirst.frc.team6113.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends IterativeRobot {
	
	Joystick driverStick = new Joystick(0);
	
	Spark LeftDrive = new Spark(0);             
	Victor RightDrive = new Victor(1); 
	Victor MainArm1 = new Victor(3);              
	Victor ScrewArm2 = new Victor(7);
	Victor RedlineArm4 = new Victor(6);  
	Victor RightGrabberArm5 = new Victor(2);      
	Victor LeftGrabberArm7 = new Victor(4);      
	//Victor Arm8 = new Victor(7);
	//Victor Arm9 = new Victor(8);
	
	private Timer m_timer = new Timer();
	
	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture();
		System.out.println("Camera Started");
	}

	@Override
	public void autonomousInit() {
		m_timer.reset();
		m_timer.start();		

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		// Drive for 2 seconds
		if (m_timer.get() < 2.0) {
			LeftDrive.set(-1.0);
			RightDrive.set(1.0);
		} else {
			LeftDrive.set(0);
			RightDrive.set(0);			
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		double leftStickValue = driverStick.getRawAxis(1);
		double RightStickValue = driverStick.getRawAxis(5);
		double LeftTrigger = driverStick.getRawAxis(2);
		double RightTrigger = driverStick.getRawAxis(3);
		
		LeftDrive.set(leftStickValue);
		RightDrive.set(-RightStickValue);
		RedlineArm4.set(-RightTrigger);
		RedlineArm4.set(LeftTrigger);
		
		if (driverStick.getRawButton(2))
		{
			MainArm1.set(0.5);

			System.out.println("button 2 pressed");
			
		}
		else if (driverStick.getRawButton(3))
		{
			MainArm1.set(-0.5);

			System.out.println("Reverse button 3 pressed");
		}
		
		else {
			MainArm1.set(0);
		}
		
		if (driverStick.getRawButton(5))
		{
			RightGrabberArm5.set(-0.5);
			LeftGrabberArm7.set(0.5);
			System.out.println("button 5 pressed");
			
		}
		else if (driverStick.getRawButton(6))
		{
			RightGrabberArm5.set(0.5);
			LeftGrabberArm7.set(-0.5);
			System.out.println("Reverse button 6 pressed");
		}
		
		else {
			RightGrabberArm5.set(0);
			LeftGrabberArm7.set(0);			
		}
		
		if (driverStick.getRawButton(4))
		{
			ScrewArm2.set(1);

			System.out.println("button 4 pressed");
			
		}
		else if (driverStick.getRawButton(1))
		{
			ScrewArm2.set(-1);

			System.out.println("Reverse button 1 pressed");
		}
	
		else {
 		    ScrewArm2.set(0);
		}	
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
