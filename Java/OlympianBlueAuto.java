package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by mars on 4/8/17.
 */

@Autonomous(name="Olympian Blue Beacon", group="Auto")
public class OlympianBlueAuto extends OpMode {

    //Wheels
    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;

    //Launcher
    private DcMotor launcherMotor = null;

    //Harvester
    private DcMotor harvesterMotor = null;

    //Sensors
    private ColorSensor topColor = null;
    private ColorSensor bottomColor1 = null;
    private ColorSensor bottomColor2 = null;
    private ModernRoboticsI2cRangeSensor range = null;
    //private IrSeekerSensor ir1 = null;
    //private IrSeekerSensor ir2 = null;

    //Servos
    private Servo armRelease = null;
    private Servo holder = null;
    //private Servo leftServo = null;
    //private Servo rightServo = null;

    //A tracker is going to be used to find when certain steps have be reached
    private int tracker = 0;
    private int temp = 0;

    private int motorPos = 0;
    private int oldMotorPos = 0;
    private int newForwardMotorPos = oldMotorPos + 500;
    private int newBackwardMotorPos = oldMotorPos - 500;
    //private int oldForwardMotorPos = newForwardMotorPos - 500;
    //private int oldBackwardMotorPos = newBackwardMotorPos + 500;

    public void telemetrys() {

        //Tracker
        telemetry.addData("Tracker: ", tracker);

        //Wheels
        telemetry.addData("Front Left: ", frontLeft.getCurrentPosition());
        telemetry.addData("Back Left: ", backLeft.getCurrentPosition());
        telemetry.addData("Front Right: ", frontRight.getCurrentPosition());
        telemetry.addData("Back Right: ", backRight.getCurrentPosition());

        //Top Color
        telemetry.addData("ColorAlpha: ", topColor.alpha());
        telemetry.addData("ColorRed: ", topColor.red());
        telemetry.addData("ColorBlue: ", topColor.blue());
        telemetry.addData("ColorGreen: ", topColor.green());

        //Bottom Colors
        telemetry.addData("ColorAlpha: ", bottomColor1.alpha());
        telemetry.addData("ColorRed: ", bottomColor1.red());
        telemetry.addData("ColorBlue: ", bottomColor1.blue());
        telemetry.addData("ColorGreen: ", bottomColor1.green());

        telemetry.addData("ColorAlpha: ", bottomColor2.alpha());
        telemetry.addData("ColorRed: ", bottomColor2.red());
        telemetry.addData("ColorBlue: ", bottomColor2.blue());
        telemetry.addData("ColorGreen: ", bottomColor2.green());

        //Range
        telemetry.addData("raw ultrasonic", range.rawUltrasonic());
        telemetry.addData("cm", "%.2f cm", range.getDistance(DistanceUnit.CM));
        telemetry.addData("mm", "%.2f mm", range.getDistance(DistanceUnit.MM));
        telemetry.addData("in", "%.2f in", range.getDistance(DistanceUnit.INCH));
        telemetry.addData("m", "%.2f m", range.getDistance(DistanceUnit.METER));

        //ir
        /*telemetry.addData("Angle ir1", ir1.getAngle());
        telemetry.addData("Strength ir1", ir1.getStrength());
        telemetry.addData("Angle ir2", ir2.getAngle());
        telemetry.addData("Strength ir2", ir2.getStrength());
        */

        //Servos
        //telemetry.addData("Left Servo: ", leftServo.getPosition());
        //telemetry.addData("Right Servo: ", rightServo.getPosition());
        telemetry.update();
    }

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        //Wheels
        frontLeft  = hardwareMap.dcMotor.get("frontLeft");//frontleft
        backLeft   = hardwareMap.dcMotor.get("backLeft");//backleft
        frontRight = hardwareMap.dcMotor.get("frontRight");//frontright
        backRight  = hardwareMap.dcMotor.get("backRight");//backright
        frontLeft.setTargetPosition(0);
        backLeft.setTargetPosition(0);
        frontRight.setTargetPosition(0);
        backRight.setTargetPosition(0);

        //Launcher
        launcherMotor = hardwareMap.dcMotor.get("launcher");

        //Harvester
        harvesterMotor = hardwareMap.dcMotor.get("harvester");

        //Range
        range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");

        //Colors
        topColor = hardwareMap.colorSensor.get("topColor");
        bottomColor1 = hardwareMap.colorSensor.get("bottomColor1");
        bottomColor2 = hardwareMap.colorSensor.get("bottomColor2");
        topColor.setI2cAddress(I2cAddr.create8bit(0x10));
        bottomColor1.setI2cAddress(I2cAddr.create8bit(0x12));
        bottomColor2.setI2cAddress(I2cAddr.create8bit(0x14));

        topColor.enableLed(false);
        bottomColor1.enableLed(false);
        bottomColor2.enableLed(false);

        //topColor.enableLed(true);
        bottomColor1.enableLed(true);
        bottomColor2.enableLed(true);

        //Servos
        armRelease = hardwareMap.servo.get("arm release");
        armRelease.setPosition(1);

        holder = hardwareMap.servo.get("holder");
        holder.setPosition(0);

        //ir1   = hardwareMap.irSeekerSensor.get("ir1");
        //ir2   = hardwareMap.irSeekerSensor.get("ir2");

        //Servos
        /*leftServo  = hardwareMap.servo.get("leftServo");
        rightServo = hardwareMap.servo.get("rightServo");
        leftServo.setDirection(Servo.Direction.REVERSE);
        leftServo.setPosition(0);
        rightServo.setPosition(0);
       */
    }

    @Override
    public void init_loop() {
        telemetrys();
    }

    public void frontTwoForward() {
        frontLeft.setPower(.5);
        frontRight.setPower(.5);
    }

    public void backTwoForward() {
        backLeft.setPower(-.5);
        backRight.setPower(-.5);
    }

    public void frontTwoBackward() {
        frontLeft.setPower(-.5);
        frontRight.setPower(-.5);
    }

    public void backTwoBackward() {
        backLeft.setPower(.5);
        backRight.setPower(.5);
    }

    public void leftTwoForward() {
        frontLeft.setPower(.5);
        backLeft.setPower(.5);
    }

    public void leftTwoBackward() {
        frontLeft.setPower(-.5);
        backLeft.setPower(-.5);
    }

    public void rightTwoForward() {
        frontRight.setPower(-.5);
        backRight.setPower(-.5);
    }

    public void rightTwoBackward() {
        frontRight.setPower(.5);
        backRight.setPower(.5);
    }

    public void leftTwoStop() {
        frontLeft.setPower(0);
        backLeft.setPower(0);
    }

    public void rightTwoStop() {
        frontRight.setPower(0);
        backRight.setPower(0);
    }

    public void frontTwoStop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }

    public void backTwoStop() {
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void moveStop() {
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }

    public void moveForward() {
        frontLeft.setPower(.5);
        backLeft.setPower(.5);
        frontRight.setPower(-.5);
        backRight.setPower(-.5);
    }

    public void moveBackward() {
        frontLeft.setPower(-.5);
        backLeft.setPower(-.5);
        frontRight.setPower(.5);
        backRight.setPower(.5);
    }

    public void moveRight() {
        frontLeft.setPower(.5);
        backLeft.setPower(-.5);
        frontRight.setPower(.5);
        backRight.setPower(-.5);
    }

    public void moveLeft() {
        frontLeft.setPower(-.5);
        backLeft.setPower(.5);
        frontRight.setPower(-.5);
        backRight.setPower(.5);
    }

    public void clockwiseRotate() {
        frontLeft.setPower(.5);
        backLeft.setPower(.5);
        frontRight.setPower(.5);
        backRight.setPower(.5);
    }

    public void counterclockwiseRotate() {
        frontLeft.setPower(-.5);
        backLeft.setPower(-.5);
        frontRight.setPower(-.5);
        backRight.setPower(-.5);
    }

    @Override
    public void loop() {

        if (tracker == 0) {
            if (launcherMotor.getCurrentPosition() >= -1500) {
                launcherMotor.setPower(-1);
            } else {
                holder.setPosition(.3);
                launcherMotor.setPower(0);
                tracker = 1;
            }
        } else if (tracker == 1) {
            if (harvesterMotor.getCurrentPosition() <= 1500) {
                harvesterMotor.setPower(1);
            } else if (launcherMotor.getCurrentPosition() <= -1500 && launcherMotor.getCurrentPosition() >= -3500) {
                harvesterMotor.setPower(0);
                launcherMotor.setPower(-1);
            } else {
                launcherMotor.setPower(0);
                tracker = 2;
            }
        } else if (tracker == 2 && frontLeft.getCurrentPosition() <= 3500) { // Move away from corner
            moveForward();
        } else if (tracker == 2 && frontLeft.getCurrentPosition() >= 3500) { // stop
            tracker = 3;
            moveStop();
        } else if (tracker == 3 && frontLeft.getCurrentPosition() <= 3800) { // Rotate to drive toward beacon
            clockwiseRotate();
        } else if (range.getDistance(DistanceUnit.INCH) >= 5) { // Move to wall
            tracker = 4;
            moveLeft();
        } else if (tracker == 4 && range.getDistance(DistanceUnit.INCH) <= 5) { // Stop robot if range sensor is less than 5 inches
            moveStop();
            tracker = 5;
        } else if (tracker == 5 && range.getDistance(DistanceUnit.INCH) <= 5) {

            if (bottomColor1.green() < 1 || bottomColor1.red() < 1 || bottomColor1.blue() < 1 || bottomColor1.alpha() < 1) {
                rightTwoBackward();
            } else {
                rightTwoStop();
                temp = 1;
            }
            if (bottomColor2.green() < 1 || bottomColor2.red() < 1 || bottomColor2.blue() < 1 || bottomColor2.alpha() < 1) {
                leftTwoBackward();
            } else {
                leftTwoStop();
                if (temp == 1) {
                    tracker = 6;
                }
            }
        } else if (tracker == 6 && range.getDistance(DistanceUnit.INCH) >= 2) { // coming up on the beacon
            moveLeft();
        } else if (tracker == 6 && topColor.blue() >= 2) {
            motorPos = backLeft.getCurrentPosition();
            if (oldMotorPos == 0) {
                oldMotorPos = backLeft.getCurrentPosition();
            } else if (motorPos <= newForwardMotorPos) {
                backTwoBackward();
            } else {
                moveStop();
                tracker = 7;
            }
        } else if (tracker == 6 && topColor.red() >= 2) {
            motorPos = frontLeft.getCurrentPosition();
            if (oldMotorPos == 0) {
                oldMotorPos = frontLeft.getCurrentPosition();
            } else if (motorPos >= newBackwardMotorPos) {
                frontTwoBackward();
            } else {
                moveStop();
                tracker = 7;
            }
        } else if (tracker == 7) {
            if (motorPos >= oldMotorPos) {
                backTwoForward();
            } else if (motorPos <= oldMotorPos) {
                frontTwoForward();
            } else {
                motorPos = 0;
                oldMotorPos = 0;
                moveStop();
                tracker = 8;
            }
        } else if (tracker == 8 && topColor.red() >= 2) {
            motorPos = backLeft.getCurrentPosition();
            if (oldMotorPos == 0) {
                oldMotorPos = backLeft.getCurrentPosition();
            } else if (motorPos <= newForwardMotorPos) {
                backTwoBackward();
            } else {
                tracker = 9;
                moveStop();
            }
        } else if (tracker == 9) {
            if (motorPos >= oldMotorPos) {
                backTwoForward();
            } else {
                moveStop();
                tracker = 10;
                oldMotorPos = 0;
                motorPos = frontLeft.getCurrentPosition();
            }
        } else if (tracker == 10 && range.getDistance(DistanceUnit.INCH) <= 5) {
            moveRight();
        } else if (tracker == 10 && motorPos <= newForwardMotorPos) {
            moveBackward();
        } else if (tracker == 10 && (motorPos >= newForwardMotorPos)) {
            moveStop();
            tracker = 11;
        }  else if (tracker == 11 && range.getDistance(DistanceUnit.INCH) >= 5) {
            moveLeft();
        } else if (tracker == 11 && range.getDistance(DistanceUnit.INCH) <= 5) {

            if (bottomColor1.green() < 1 || bottomColor1.red() < 1 || bottomColor1.blue() < 1 || bottomColor1.alpha() < 1) {
                rightTwoBackward();
            } else {
                rightTwoStop();
                temp = 2;
            }
            if (bottomColor2.green() < 1 || bottomColor2.red() < 1 || bottomColor2.blue() < 1 || bottomColor2.alpha() < 1) {
                leftTwoBackward();
            } else {
                leftTwoStop();
                if (temp == 2) {
                    tracker = 12;
                    motorPos = 0;
                }
            }
        } else if (tracker == 12 && range.getDistance(DistanceUnit.INCH) >= 2) { // coming up on the beacon
            moveLeft();
        } else if (tracker == 12 && topColor.red() >= 2) {
            motorPos = backLeft.getCurrentPosition();
            if (oldMotorPos == 0) {
                oldMotorPos = backLeft.getCurrentPosition();
            } else if (motorPos <= newForwardMotorPos) {
                backTwoBackward();
            } else {
                moveStop();
                tracker = 13;
            }
        } else if (tracker == 12 && topColor.blue() >= 2) {
            motorPos = frontLeft.getCurrentPosition();
            if (oldMotorPos == 0) {
                oldMotorPos = frontLeft.getCurrentPosition();
            } else if (motorPos >= newBackwardMotorPos) {
                frontTwoBackward();
            } else {
                moveStop();
                tracker = 13;
            }
        } else if (tracker == 13) {
            if (motorPos >= oldMotorPos) {
                backTwoForward();
            } else if (motorPos <= oldMotorPos) {
                frontTwoForward();
            } else {
                moveStop();
                tracker = 14;
                motorPos = 0;
                oldMotorPos = 0;
            }
        } else if (tracker == 14 && topColor.red() >= 2) {
            motorPos = backLeft.getCurrentPosition();
            if (oldMotorPos == 0) {
                oldMotorPos = backLeft.getCurrentPosition();
            } else if (motorPos <= newForwardMotorPos) {
                backTwoBackward();
            } else {
                tracker = 15;
                moveStop();
            }
        } else if (tracker == 15) {
            if (motorPos >= oldMotorPos) {
                backTwoForward();
            } else {
                moveStop();
                tracker = 16;
            }
        } else if (tracker == 16) {
            stop();
        }

        if (tracker == 7 && (motorPos != frontLeft.getCurrentPosition() || motorPos != backLeft.getCurrentPosition())) {
            tracker = 9;
        }

        if (tracker == 13 && (motorPos != frontLeft.getCurrentPosition() || motorPos != backLeft.getCurrentPosition())) {
            tracker = 15;
        }

        telemetrys();
    }

    @Override
    public void stop() {

    }
}
