
package org.usfirst.frc.team6113.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends IterativeRobot {
	
	Joystick driverStick = new Joystick(0);
	
	Spark LeftDrive = new Spark(0);             //working
	Spark RightDrive = new Spark(1);            //working
	Spark MainArm1 = new Spark(3);              //NOT tested probably Hesitating?
	Spark ScrewArm2 = new Spark(7);             //working
	Spark RedlineArm4 = new Spark(6);           //working
	Spark RightGrabberArm5 = new Spark(2);      //hesitating
	Spark LeftGrabberArm7 = new Spark(4);       //hesitating
	//Spark Arm8 = new Spark(7);
	//Spark Arm9 = new Spark(8);
	
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
		MainArm1.set(LeftTrigger);
		MainArm1.set(-RightTrigger);
		
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
			RightGrabberArm5.set(0);
			LeftGrabberArm7.set(0);
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
