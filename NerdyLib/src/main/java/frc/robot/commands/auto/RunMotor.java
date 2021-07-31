package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import com.nerdherd.lib.drivetrain.auto.DriveStraightContinuous;
import com.nerdherd.lib.drivetrain.experimental.Drivetrain;

public class RunMotor extends SequentialCommandGroup {
    public RunMotor(Drivetrain drive) {
        addCommands(
            new DriveStraightContinuous(drive, 100, 0.5) // Arbitrary hard-coded values
        );
    }
}