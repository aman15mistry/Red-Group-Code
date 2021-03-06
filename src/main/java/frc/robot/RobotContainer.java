// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// Code
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ConveyerOn;
import frc.robot.commands.Drive;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IntakeOn;
import frc.robot.subsystems.Conveyer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.ServoEngame;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final JoystickButton c1, intake, endgame;
  private final Conveyer c;
  private final ServoEngame servo;
  private final Intake in;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    DriveTrain driveTrain = new DriveTrain();
    // Configure the button bindings
    Joystick leftGamepad = new Joystick(Constants.LEFT);
    Joystick rightGamepad = new Joystick(Constants.RIGHT);
    c1 = new JoystickButton(leftGamepad, 1);
    c = new Conveyer();
    intake = new JoystickButton(leftGamepad, 2);
    in = new Intake();
    servo = new ServoEngame(); 
    endgame = new JoystickButton(rightGamepad, 1);
    configureButtonBindings();

    driveTrain.setDefaultCommand(new Drive(driveTrain, 
                                            () -> leftGamepad.getRawAxis(1),
                                            () -> rightGamepad.getRawAxis(1)));
     
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    c1.whileHeld(new ConveyerOn(c, .5));
    intake.whenPressed(new IntakeOn(in));
    endgame.whenPressed(new InstantCommand(() -> servo.setPos(90)));
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
