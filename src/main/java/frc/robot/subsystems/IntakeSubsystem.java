// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.Intake.*;
import static com.ctre.phoenix.motorcontrol.TalonSRXControlMode.PercentOutput;
import static edu.wpi.first.wpilibj.PneumaticsModuleType.CTREPCM;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.CANCoder;
//import com.playingwithfusion.TimeOfFlight;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  private final TalonSRX intakeMotor = new TalonSRX(intake_TALON_CAN);
  //private final CANSparkMax wristMotor = new CANSparkMax(wrist_SPARK_CAN, MotorType.kBrushless);
  //private final CANCoder wristCANCoder = new CANCoder(wrist_CANCODER);
  private final Solenoid intakeSolenoid = new Solenoid(CTREPCM, intake_SOLENOID_CHANNEL);
  //public final TimeOfFlight TOFSensor = new TimeOfFlight(TOF_sensor_CAN);

  private double wristCurrentPosition;
  private String currentIntakeCommand;
  private final double intakeSpeed = 0.9;
  private double TOFRange;
  //private boolean FullIntakeCheck;
  //private boolean wristOk;

  //private final SimpleMotorFeedforward wristFeedForward = new SimpleMotorFeedforward(0,0,0);
  //private final ProfiledPIDController wristPID = new ProfiledPIDController(0.1, 0.0, 0.0, new Constraints(15, 100));
  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    ShuffleboardTab subsystemTab = Shuffleboard.getTab("Subsystems");
    ShuffleboardLayout intakeLayout = subsystemTab.getLayout("Intake", BuiltInLayouts.kList)
                    .withSize(1, 4)
                    .withPosition(0, 0);
    intakeLayout.addNumber("TOF Range", () -> TOFRange);
    intakeLayout.addNumber("Wrist Position", () -> wristCurrentPosition);

    subsystemTab.add("Current Intake Command", currentIntakeCommand, "none");
  }

  public void WristPosition() {
  }

  public void IntakeInCube() {
    intakeSolenoid.set(true);
    intakeMotor.set(PercentOutput, intakeSpeed);
    currentIntakeCommand = "Intake In Cube";
  }

  public void IntakeOutCube() {
    intakeSolenoid.set(true);
    intakeMotor.set(PercentOutput, -intakeSpeed);
    currentIntakeCommand = "Intake Out Cube";
  }

  public void IntakeIdle() {
    intakeSolenoid.set(false);
    intakeMotor.set(PercentOutput, 0);
    currentIntakeCommand = "Intake Idle";
  }

  public void IntakeInCone(){
    intakeSolenoid.set(false);
    intakeMotor.set(PercentOutput, intakeSpeed);
    currentIntakeCommand = "Intake In Cone";
  }

  public void IntakeOutCone(){
    intakeSolenoid.set(false);
    intakeMotor.set(PercentOutput, -intakeSpeed);
    currentIntakeCommand = "Intake Out Cone";
  }

  /*public void FullIntakeCheck() {
    if (TOFRange <= 2) {
      FullIntakeCheck = true;
    }
    else {
      FullIntakeCheck = false;
    }
  }*/

  /*public void TOFIntake() {
    if (FullIntakeCheck = true) {
      wristMotor.set(intakeSpeed);
    }
    else {
      wristMotor.set(0);
    }
  }*/

  @Override
  public void periodic() {
    //TOFRange = TOFSensor.getRange();
    SmartDashboard.putString("Current intake command", currentIntakeCommand);
    //FullIntakeCheck();
    // This method will be called once per scheduler run
  }
}
