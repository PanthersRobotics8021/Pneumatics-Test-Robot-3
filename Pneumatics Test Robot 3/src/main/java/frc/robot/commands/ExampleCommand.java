/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotContainer;

/**
 * An example command that uses an example subsystem.
 */
public class ExampleCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ExampleSubsystem m_subsystem;
  //private final double sValue;
  //private final RobotContainer robotContainer;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ExampleCommand(ExampleSubsystem subsystem) {
    m_subsystem = subsystem;
    //sValue = solenoidValue;
   // robotContainer = new RobotContainer();

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  boolean reverse = false;

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    /*//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    boolean threeButton = m_oi.GetButton(3);
    boolean fourButton = m_oi.GetButton(4);
    Value sole = Value.kReverse;
    String solestate = "Reverse";

    if (threeButton) {
      sole = Value.kForward;
      solestate = "Forward";
    }
    else if (fourButton) {
      sole = Value.kReverse;
      solestate = "Reverse";
    }

    m_subsystem.setRam(sole);
    m_subsystem.displayDashboard("Pneumatic State", solestate);
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
   

    //variables
    double joyX = Robot.m_oi.GetDriverRawAxis(0);
    double joyY = Robot.m_oi.GetDriverRawAxis(1);
    double joyZ = Robot.m_oi.GetDriverRawAxis(2);
    double throttle = 1 - Robot.m_oi.GetDriverRawAxis(3);
    boolean thumbButton = Robot.m_oi.GetButton(1);


    //motor variables
    double turnValue = 0;
    double lMotors = joyY; 
    double rMotors = joyY;


    //reverse system
    if (thumbButton) {
      reverse = !reverse;
    }

    if (reverse) {
      lMotors = -lMotors;
      rMotors = -rMotors;
    }


    //turn system
    if (joyX > .25 || joyX < -.25D) {
      turnValue += joyX;
    }

    if (joyZ > .25 || joyZ < -.25) {
      turnValue += joyZ;
    }


    //turning values
    if (turnValue < 0 || turnValue > 0) {
      turnValue *= .6;
      lMotors *= 1;
      rMotors *= 1;
      lMotors -= turnValue * 1;
      rMotors += turnValue * 1;
    }
    else {
      lMotors *= 1;
      rMotors *= 1;
    }
    //final command
    m_subsystem.setLeftMotors(lMotors * throttle);
    m_subsystem.setRightMotors(rMotors * throttle);
    m_subsystem.displayDashboardNumber("Left Motors", lMotors);
    m_subsystem.displayDashboardNumber("Right Motors", rMotors);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setRam(Value.kReverse);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
