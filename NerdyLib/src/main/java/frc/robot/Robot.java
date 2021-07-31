/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.nerdherd.lib.drivetrain.teleop.ArcadeDrive;
import com.nerdherd.lib.logging.NerdyBadlog;
import com.nerdherd.lib.misc.AutoChooser;
import com.nerdherd.lib.motor.commands.ResetSingleMotorEncoder;
import com.nerdherd.lib.motor.single.SingleMotorMechanism;
import com.nerdherd.lib.motor.single.SingleMotorVictorSPX;
import com.nerdherd.lib.pneumatics.Piston;
import com.nerdherd.lib.sensor.analog.PressureSensor;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.subsystems.Drive;
import frc.robot.commands.auto.RunMotor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  public static final String kDate = "2020_01_25_";

  public static AutoChooser chooser;
  public static Drive drive;
  // public static Indexer indexer;
  public static DriverStation ds;
  public static SingleMotorVictorSPX intakeRoll;
  public static PressureSensor pes;
  // public static SingleMotorMechanism motor;
  // public static PowerDistributionPanel pdp;
  public static Command m_autonomousCommand;
  public static Piston intake;
  public static Piston panelPos;
  public static SingleMotorMechanism panelRot;
  public static OI oi;
  public static ResetSingleMotorEncoder hoodReset;
  public static SendableChooser<Command> autoChooser;

  @Override
  public void robotInit() {
    drive = new Drive();

    ds = DriverStation.getInstance();
    pes = new PressureSensor("name",  2);
    CameraServer.getInstance().startAutomaticCapture();
    intakeRoll = new SingleMotorVictorSPX(RobotMap.kIntakeRoll, "intake rollers", false);
    intake = new Piston(RobotMap.kIntakePort1, RobotMap.kIntakePort2);
    oi = new OI();
    drive.setDefaultCommand(new ArcadeDrive(Robot.drive, Robot.oi));

    autoChooser = new SendableChooser<Command>();

    autoChooser.addOption("MoveMotor", new RunMotor(drive));
    SmartDashboard.putData("Autos", autoChooser);
  }

  // @Override
  // public void robotPeriodic() {
  //   CommandScheduler.getInstance().run();
  //   drive.reportToSmartDashboard();
  //   // System.out.println(shooter.kF);
  //   SmartDashboard.putNumber("Right Voltage 1", drive.getRightOutputVoltage());
  //   SmartDashboard.putNumber("Pressure: ", pes.getScaled());
    
    
  //   climber.reportToSmartDashboard();
    
    
  // }

  // @Override
  // public void disabledInit() {
  //   hood.resetEncoder();
  //   Robot.climber.followerFalcon.resetEncoder();
  //   Robot.climber.mainFalcon.resetEncoder();
    
  //   drive.setCoastMode();
  // }

  // @Override
  // public void disabledPeriodic() {
  //   if(oi.driveJoyLeft.getRawButton(5) || oi.driveJoyRight.getRawButton(5)) {
  //     hood.resetEncoder();
  //     climber.followerFalcon.resetEncoder();
  //     climber.mainFalcon.resetEncoder();
  //     drive.resetEncoders();
  //     drive.resetYaw();
  //     drive.resetXY();
  //   }
  //   // Robot.climber.followerFalcon.resetEncoder();
  //   // Robot.climber.mainFalcon.resetEncoder();
  // }

  @Override
  public void autonomousInit() {
    drive.setBrakeMode();
    m_autonomousCommand = autoChooser.getSelected();
    if (m_autonomousCommand != null) { 
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
    
  }

  @Override
  public void teleopInit() {
    drive.setCoastMode();
    // drive.setPose(new Pose2d(0, 0, new Rotation2d(Math.PI)));
  
  }

  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
    // drive.setCoastMode();
   
  
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
