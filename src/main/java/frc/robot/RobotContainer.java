// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;



/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
    // The robot's subsystems and commands are defined here...
    private final RomiDrivetrain romiDrivetrain = new RomiDrivetrain();
    
    private final ExampleCommand autoCommand = new ExampleCommand(romiDrivetrain);
    private final Joystick joystick = new Joystick(0);
    JoystickButton joystickButton1 = new JoystickButton(joystick, 1);
    JoystickButton joystickButton2 = new JoystickButton(joystick, 2);
    JoystickButton joystickButton3 = new JoystickButton(joystick, 3);
    JoystickButton joystickButton4 = new JoystickButton(joystick, 4);
    private final XboxController controller = new XboxController(0); // Assuming Xbox controller on port 0

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer()
    {
        // Configure the button bindings
        configureButtonBindings();
    }
    
    
    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        romiDrivetrain.setDefaultCommand(new RunCommand(() -> romiDrivetrain.arcadeDrive(-2 * joystick.getRawAxis(1), -2 * joystick.getRawAxis(0)), romiDrivetrain));
        joystickButton1.onTrue(romiDrivetrain.driveStraight(1, 3));
        joystickButton3.onTrue(driveSquare());
        joystickButton2.onTrue(romiDrivetrain.circle(1, 15));
        joystickButton4.onTrue(w());
    }
    
    
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand()
    {
        // An ExampleCommand will run in autonomous
        return autoCommand;
    }

    public Command driveSquare() {
        return Commands.sequence(
                romiDrivetrain.driveStraight(1, 1),
                romiDrivetrain.turn(1, 0.25),
                romiDrivetrain.driveStraight(1, 1),
                romiDrivetrain.turn(1, 0.25),
                romiDrivetrain.driveStraight(1, 1),
                romiDrivetrain.turn(1, 0.25),
                romiDrivetrain.driveStraight(1, 1),
                romiDrivetrain.turn(1, 0.25)
        );
    }
    public Command w() {
        return Commands.sequence(
            romiDrivetrain.turn(0.5, 0.25),
            romiDrivetrain.driveStraight(1, 1),
            romiDrivetrain.turn(-1, 0.45),
            romiDrivetrain.driveStraight(1, 1),
            romiDrivetrain.turn(1, 0.45),
            romiDrivetrain.driveStraight(1, 1),
            romiDrivetrain.turn(-1, 0.45),
            romiDrivetrain.driveStraight(1, 1)
        );
    }
}
