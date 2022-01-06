// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */

  private Spark frontL = new Spark(Constants.FRONTL); 
  private Spark frontR = new Spark(Constants.FRONTR);
  private Spark backR = new Spark(Constants.BACKR);
  private Spark backL = new Spark(Constants.BACKL);

  private final SpeedControllerGroup left = new SpeedControllerGroup (frontL, backL);

  private final SpeedControllerGroup right = new SpeedControllerGroup (frontR, backR); 

  private final DifferentialDrive drive = new DifferentialDrive (left, right);

  public DriveTrain() {
    drive.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void tankDrive(double left, double right) {
    drive.tankDrive(left, right); 
  }


  


}
