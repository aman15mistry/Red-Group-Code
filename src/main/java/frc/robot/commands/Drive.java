// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Drive extends CommandBase {
  /** Creates a new Drive. */
  private final DriveTrain driveSubsystem;
  private final DoubleSupplier left, right; 

  public Drive(DriveTrain d, DoubleSupplier left, DoubleSupplier right) {
    // Use addRequirements() here to declare subsystem dependencies.
    driveSubsystem = d;
    this.left = left;
    this.right = right; 
    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSubsystem.tankDrive(left.getAsDouble(), right.getAsDouble()); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
