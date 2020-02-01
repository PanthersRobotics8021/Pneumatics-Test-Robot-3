/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ExampleSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  public ExampleSubsystem() {

  }

  private final DoubleSolenoid doubleSolenoid = new DoubleSolenoid(5, 4, 5);

  public void setRam(Value state) {
    doubleSolenoid.set(state);
  }

  public void displayDashboard(String key, String toggle) {
    SmartDashboard.putString(key, toggle);
  }

  public void displayDashboardNumber(String key, double number) {
    SmartDashboard.putNumber(key, number);
  }
  
  private TalonSRX motorLeft = new TalonSRX(2);
  private TalonSRX motorRight = new TalonSRX(4);

  public void setLeftMotors(double speed) {
    motorLeft.set(ControlMode.PercentOutput, speed);
  }

  public void setRightMotors(double speed) {
    motorRight.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
