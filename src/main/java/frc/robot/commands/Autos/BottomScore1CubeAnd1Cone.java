// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autos;

import com.pathplanner.lib.commands.PPSwerveControllerCommand;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ArmToHome;
import frc.robot.commands.IntakeCube;
import frc.robot.commands.OutakeCone;
import frc.robot.commands.OutakeCube;
import frc.robot.commands.ScoreHigh;
import frc.robot.commands.ScoreLow;
import frc.robot.commands.ShoulderToHome;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.WristSubsystem;
//walker was here
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BottomScore1CubeAnd1Cone extends SequentialCommandGroup {
  /** Creates a new Auto1. */
  public BottomScore1CubeAnd1Cone(Swerve swerve, IntakeSubsystem intakeSubsystem, ArmSubsystem armSubsystem, WristSubsystem wristSubsystem) {
    addCommands(
      new ScoreHigh(wristSubsystem, armSubsystem),
      new OutakeCone(intakeSubsystem),
      new ArmToHome(wristSubsystem, armSubsystem),
      new ShoulderToHome(armSubsystem),
      new PathPlannerCommand(swerve, 1, "Straight 1 Bottom"),
      new PathPlannerCommand(swerve, 1, "Go to first game piece 1 bottom"),
      new PathPlannerCommand(swerve, 1, "Turn 180 Right 1 Bottom"),
      new PathPlannerCommand(swerve, 1, "Go to first game piece 2 bottom"),
      new ScoreLow(wristSubsystem, armSubsystem),
      new IntakeCube(intakeSubsystem),
      new ArmToHome(wristSubsystem, armSubsystem),
      new ShoulderToHome(armSubsystem),
      new PathPlannerCommand(swerve, 1, "Turn 180 left 1 Bottom"), 
      new PathPlannerCommand(swerve, 1, "Go back Bottom"),
      new ScoreHigh(wristSubsystem, armSubsystem),
      new OutakeCube(intakeSubsystem),
      new ArmToHome(wristSubsystem, armSubsystem),
      new ShoulderToHome(armSubsystem)
    );
  }
}

