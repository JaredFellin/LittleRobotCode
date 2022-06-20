// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  
  
  private CANSparkMax leftMotor;
  private CANSparkMax rightMotor;
  private DifferentialDrive robotDrive;
  private double forward;
  private double rotate; 
  private Joystick ps4;
  private double leftTrigger;
  private double rightTrigger;
  private double leftStickX;
  //private double rightStickY;
  //private boolean leftBumper;
 // private boolean rightBumper;

  private static final int leftMotor_ID = 1;
  private static final int rightMotor_ID = 2;

  private static final MotorType motor_Type = MotorType.kBrushless;
  private static final int ps4_Port = 0;
  private static final int leftTrigger_Axis = 3;
  private static final int rightTrigger_Axis = 4;
 // private static final int rightStickY_ID = 5;
 private static final Hand leftStickX_Hand = Hand.kLeft;

 // private static final int leftBumper_ID = 4;
 // private static final int rightBumper_ID = 2;
  
  private static final double cruiseSpeed = 0.35;
  private double forward_Sensitivity = cruiseSpeed;
  private static final double slowTurn = 0.5;
  private double rotate_Sensitivity = slowTurn;
  
  private final Timer timer = new Timer();
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
  
  leftMotor = new CANSparkMax(leftMotor_ID, motor_Type);
  rightMotor = new CANSparkMax(rightMotor_ID, motor_Type);

  robotDrive = new DifferentialDrive(leftMotor, rightMotor);

  ps4 = new Joystick(ps4_Port);
 
}
  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    timer.reset();
    timer.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (timer.get() < 2.0) {
      robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
    } else {
      robotDrive.stopMotor(); // stop robot
    }
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    //robotDrive.arcadeDrive(stick.getY(), m_stick.getX());
    leftTrigger = ps4.getRawAxis(leftTrigger_Axis);
   rightTrigger = ps4.getRawAxis(rightTrigger_Axis);
    leftStickX = ps4.getX(leftStickX_Hand);
   // rightStickY = ps4.getRawAxis(rightStickY_ID);
    
   // leftBumper = ps4.getRawButton(leftBumper_ID);
   // rightBumper = ps4.getRawButton(rightBumper_ID);


    robotDrive.arcadeDrive(forward,rotate);
    forward = (rightTrigger - leftTrigger) * forward_Sensitivity;
    rotate = leftStickX * rotate_Sensitivity;
    
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
