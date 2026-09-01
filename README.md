# SuMthingS Wrong — FTC Robot Code V2

Robot control software developed by **SuMthingS Wrong** for the FIRST Tech Challenge.

This repository contains the team's Java-based control system for the **2025–2026 FTC DECODE season**, built on the official FTC SDK. The codebase combines command-based robot architecture, autonomous path planning, computer vision and subsystem-level hardware control.

## Features

### Autonomous Path Following

Autonomous routines use **Pedro Pathing** to generate and follow paths across the field.

The robot supports autonomous routines for different alliance colours and starting positions:

* Blue Back
* Blue Forward
* Red Back
* Red Forward

Paths are constructed using **Bézier lines and curves**, with heading interpolation used to control the robot's orientation throughout each route.

Autonomous actions are organised into command sequences combining movement, pickup, scoring and parking stages.

### Mecanum Drive

The drivetrain uses a four-motor mecanum configuration, allowing forward/backward movement, strafing and rotation.

Motor powers are dynamically normalised to keep outputs within valid ranges while preserving the requested direction of movement.

Pedro Pathing's follower is also used to track the robot's:

* position
* heading
* velocity

These values can then be used by other systems such as autonomous navigation and shooter calculations.

### Command-Based Architecture

The robot software uses **SolversLib's command framework** to separate robot hardware from higher-level control logic.

Major mechanisms are represented as independent subsystems:

* `DriveSubsystem`
* `IntakeSubsystem`
* `ShooterSubsystem`
* `GateSubsystem`
* `VisionSubsystem`

This keeps mechanism control modular while allowing TeleOp and autonomous routines to coordinate multiple systems through commands.

### Vision

A **Limelight 3A** is integrated through the vision subsystem.

The system can:

* switch between vision pipelines
* determine whether a valid target is visible
* retrieve targeting information
* estimate distance to the goal using camera geometry

Distance is calculated using the camera height, target height and the vertical angle reported by the Limelight.

### Shooter Control

The shooter uses two motors grouped as a flywheel together with an adjustable-angle servo.

The codebase includes support for:

* velocity-controlled flywheel operation
* adjustable shooter angle
* lookup-table-based shooter configuration
* manual RPM and angle tuning

An experimental targeting command also explores calculating shooter parameters directly from the robot's position relative to the goal.

The calculation uses projectile motion to determine an appropriate launch angle and velocity and includes compensation for the robot's own movement by resolving its velocity into components relative to the target.

> The dynamic projectile-motion targeting system is currently under development and is not yet fully integrated into the shooter control loop.

### TeleOp

Driver-controlled operation combines Pedro Pathing's tele-operated drive mode with command-based mechanism controls.

Gamepad inputs control functions including:

* mecanum driving
* intake and outtake
* shooter RPM adjustment
* shooter angle adjustment

## Project Structure

```text
TeamCode/src/main/java/org/firstinspires/ftc/teamcode/
│
├── commands/
│   ├── MathShooter.java
│   └── RobotConstants.java
│
├── opmodes/
│   ├── Autonomous/
│   │   ├── BlueBackAuto.java
│   │   ├── BlueForwardAuto.java
│   │   ├── RedBackAuto.java
│   │   └── RedForwardAuto.java
│   └── PedroTeleop.java
│
├── pedroPathing/
│   ├── Constants.java
│   └── Tuning.java
│
├── subsystemsBase/Subsystems/
│   ├── DriveSubsystem.java
│   ├── GateSubsystem.java
│   ├── IntakeSubsystem.java
│   ├── ShooterSubsystem.java
│   └── VisionSubsystem.java
│
├── Test/
├── samples/
└── utils/
```

## Tech Stack

* **Java**
* **FIRST Tech Challenge SDK**
* **Android Studio**
* **Gradle**
* **Pedro Pathing**
* **SolversLib**
* **Limelight 3A**

## Key Engineering Concepts

This project explores several areas of robotics and software engineering, including:

* object-oriented robot architecture
* command-based control systems
* autonomous navigation
* Bézier path generation
* mecanum-drive kinematics
* localisation and pose tracking
* computer vision
* hardware abstraction
* projectile-motion modelling
* velocity compensation
* real-time driver control

## Development

The project is built using the official FTC Android Studio project structure.

The team's custom robot code can be found primarily under:

```text
TeamCode/src/main/java/org/firstinspires/ftc/teamcode/
```

The remainder of the repository includes the FTC Robot Controller SDK and supporting build files required to deploy the software to FTC hardware.

---

**SuMthingS Wrong — FIRST Tech Challenge**
