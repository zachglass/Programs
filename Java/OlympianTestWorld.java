package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name="OlympianWorld", group="Teleop")
public class OlympianTestWorld extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;

    private DcMotor lifterMotor = null;

    private DcMotor harvesterMotor = null;

    private DcMotor launcherMotor = null;

    private Servo holderServo = null;

    private Servo armRelease = null;

    private Servo leftServo = null;
    private Servo rightServo = null;

    private ColorSensor topColor = null;
    private ColorSensor bottomColor1 = null;
    private ColorSensor bottomColor2 = null;
    private ModernRoboticsI2cRangeSensor range = null;

    private double motorThreshold = 0.065;

    public void driveInit() {
        telemetry.addData("Status", "Initialized");
        frontLeft  = hardwareMap.dcMotor.get("frontLeft");//frontleft
        backLeft   = hardwareMap.dcMotor.get("backLeft");//backleft
        frontRight = hardwareMap.dcMotor.get("frontRight");//frontright
        backRight  = hardwareMap.dcMotor.get("backRight");//backright
        // leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
    }

    public void holderServoInit() {
        holderServo = hardwareMap.servo.get("holder");
        holderServo.setPosition(0);
    }

    public void launcherInit() {
        launcherMotor = hardwareMap.dcMotor.get("launcher");
    }

    public void pushbarServoInit() {
        leftServo  = hardwareMap.servo.get("leftServo");
        rightServo = hardwareMap.servo.get("rightServo");
        leftServo.setDirection(Servo.Direction.REVERSE);
        leftServo.setPosition(0);
        rightServo.setPosition(0);
    }

    public void lifterMotorInit() {

        lifterMotor = hardwareMap.dcMotor.get("lifter");
    }

    public void armReleaseInit() {
        armRelease = hardwareMap.servo.get("arm release");
        armRelease.setPosition(1);
    }


    public void harvesterMotorInit() {
        harvesterMotor = hardwareMap.dcMotor.get("harvester");
    }

    public void sensorsInit() {
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
    }

    @Override
    public void init() {

        driveInit();
        holderServoInit();
//        pushbarServoInit();
        armReleaseInit();
        lifterMotorInit();
        harvesterMotorInit();
        launcherInit();
        sensorsInit();
    }

    @Override
    public void init_loop() {

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

   /*
    * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    */

    public void driveLoop() {
        // Driving control "telemetry"
        telemetry.addData("Status", "Running: " + runtime.toString());
        telemetry.addData("RightX", gamepad1.right_stick_x);
        telemetry.addData("RightY", gamepad1.right_stick_y);
        telemetry.addData("LeftX", gamepad1.left_stick_x);
        telemetry.addData("LeftY", gamepad1.left_stick_y);

        float leftX = -gamepad1.left_stick_x;// assigning controller values to a variable
        float rightX = gamepad1.right_stick_x;
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;

        float coord1 = leftY;
        float coord = rightY;
        DcMotor.Direction dir1 = DcMotor.Direction.FORWARD;
        DcMotor.Direction dir2 = DcMotor.Direction.FORWARD;
        DcMotor.Direction dir3 = DcMotor.Direction.REVERSE;
        DcMotor.Direction dir4 = DcMotor.Direction.REVERSE;
        if (((leftX >= motorThreshold) || (leftX <= -motorThreshold)) && ((rightX >= motorThreshold) || (rightX <= -motorThreshold))) {
            dir1 = DcMotor.Direction.REVERSE;
            dir2 = DcMotor.Direction.FORWARD;
            dir3 = DcMotor.Direction.FORWARD;
            dir4 = DcMotor.Direction.REVERSE;
            coord1 = leftX;
            coord = rightX;
        }
        frontLeft.setDirection(dir1);// Set to FORWARD if using AndyMark motors
        backLeft.setDirection(dir2);
        frontRight.setDirection(dir3);
        backRight.setDirection(dir4);
        frontLeft.setPower(coord1);
        backLeft.setPower(coord1);
        frontRight.setPower(coord);
        backRight.setPower(coord);
        //switch the directions on the auto
    }

    public void armReleaseLoop() {

        if (gamepad1.y || gamepad2.y) {
            if (armRelease.getPosition() != 0) {
                armRelease.setPosition(0);
            }
        } else {
            if (armRelease.getPosition() != 1) {
                armRelease.setPosition(1);
            }
        }
    }


    public void holderServoLoop() {
        if (gamepad1.x || gamepad2.x) {
            if (holderServo.getPosition() != 1) {
                holderServo.setPosition(1);
            }
        } else {
            if (holderServo.getPosition() != .3) {
                holderServo.setPosition(.3);
            }
        }
    }

    public void lifterMotorLoop() {

        telemetry.addData("right trigger", gamepad1.right_trigger);
        telemetry.addData("left trigger", gamepad1.left_trigger);

        if(gamepad1.right_trigger > 0) {
            lifterMotor.setPower(gamepad1.right_trigger);

        } else if (gamepad1.left_trigger > 0) {
            lifterMotor.setPower(-gamepad1.left_trigger);

        } else {
            lifterMotor.setPower(0);
        }

    }

    public void launcherLoop(){
        telemetry.addData("launcher: ", launcherMotor.getCurrentPosition());

        if (gamepad1.b || gamepad2.b) {
            launcherMotor.setPower(1);
        } else if (gamepad1.a || gamepad2.a) {
            launcherMotor.setPower(-1);
        } else {
            launcherMotor.setPower(0);
        }
    }

    public void pushBarServoLoop() {
        if (gamepad1.right_bumper || gamepad2.right_bumper) {
            if (rightServo.getPosition() != .3) {
                rightServo.setPosition(.3);
            }
        } else {
            if (rightServo.getPosition() != 0) {
                rightServo.setPosition(.05);
            }
        }
        if (gamepad1.left_bumper || gamepad2.left_bumper) {
            if (leftServo.getPosition() != .3) {
                leftServo.setPosition(.3);
            }
        } else {
            if (leftServo.getPosition() != 0) {
                leftServo.setPosition(.06);
            }
        }
    }

    public void harvesterMotorLoop() {
        telemetry.addData("harvest", harvesterMotor.getCurrentPosition());
        if (gamepad1.right_bumper || gamepad2.right_bumper) {
            harvesterMotor.setPower(1);
        } else if (gamepad1.left_bumper || gamepad2.left_bumper) {
            harvesterMotor.setPower(-1);
        } else {
            harvesterMotor.setPower(0);
        }
        /*if (gamepad1.dpad_down || gamepad2.dpad_down) {
            harvesterMotor.setPower(-1);
        } else {
            harvesterMotor.setPower(0);
        } */
    }

    public void telemetrys() {

        //Tracker
       /* telemetry.addData("Tracker: ", tracker);
        if (tracker == 0) {
            telemetry.addLine("Launching First Ball");
        } else if (tracker == 1) {
            telemetry.addLine("Launching Second Ball");
        } else if (tracker == 2) {
            telemetry.addLine("Driving Toward the Beacon");
        } else if (tracker == 3) {
            telemetry.addLine("Turning to have Sensor facing the Beacon");
        } else if (tracker == 4) {
            telemetry.addLine("Driving Toward te Beacon");
        } else if (tracker == 5) {
            telemetry.addLine("Finding the Line");
        } else if (tracker == 6) {
            telemetry.addLine("Finding Color and Moving To Press it");
        } else if (tracker == 7) {
            telemetry.addLine("Moving Back");
        } else if (tracker == 8) {
            telemetry.addLine("Color is wrong, pressing again");
        } else if (tracker == 9) {
            telemetry.addLine("Moving Back");
        } else if (tracker == 10) {
            telemetry.addLine("Driving away from Beacon 1 and toward Beacon 2");
        } else if (tracker == 11) {
            telemetry.addLine("Moving toward beacon");
            telemetry.addLine("Finding Line");
        } else if (tracker == 12) {
            telemetry.addLine("Finding COlor and Moving To Press it");
        } else if (tracker == 13) {
            telemetry.addLine("Moving Back");
        } else if (tracker == 14) {
            telemetry.addLine("Color is wrong, pressing again");
        } else if (tracker == 15) {
            telemetry.addLine("Moving back");
        } else if (tracker == 16) {
            telemetry.addLine("Done");
        }
        telemetry.addLine(" ");

*/
        //launcher
        telemetry.addData("launcher: ", launcherMotor.getCurrentPosition());

        //harvester
        telemetry.addData("harvester: ", harvesterMotor.getCurrentPosition());

        //Wheels
        telemetry.addData("Front Left: ", frontLeft.getCurrentPosition());
        telemetry.addData("Back Left: ", backLeft.getCurrentPosition());
        telemetry.addData("Front Right: ", frontRight.getCurrentPosition());
        telemetry.addData("Back Right: ", backRight.getCurrentPosition());
        telemetry.addLine(" ");

        //Top Color
        telemetry.addLine("TOP COLOR");
        telemetry.addData("ColorAlpha: ", topColor.alpha());
        telemetry.addData("ColorRed: ", topColor.red());
        telemetry.addData("ColorBlue: ", topColor.blue());
        telemetry.addData("ColorGreen: ", topColor.green());
        telemetry.addLine(" ");

        //Bottom Colors
        telemetry.addLine("BOTTOM COLOR 1");
        telemetry.addData("ColorAlpha: ", bottomColor1.alpha());
        telemetry.addData("ColorRed: ", bottomColor1.red());
        telemetry.addData("ColorBlue: ", bottomColor1.blue());
        telemetry.addData("ColorGreen: ", bottomColor1.green());
        telemetry.addLine(" ");

        telemetry.addLine("BOTTOM COLOR 2");
        telemetry.addData("ColorAlpha: ", bottomColor2.alpha());
        telemetry.addData("ColorRed: ", bottomColor2.red());
        telemetry.addData("ColorBlue: ", bottomColor2.blue());
        telemetry.addData("ColorGreen: ", bottomColor2.green());
        telemetry.addLine(" ");

        //Range
        telemetry.addData("raw ultrasonic", range.rawUltrasonic());
        telemetry.addData("cm", "%.2f cm", range.getDistance(DistanceUnit.CM));
        telemetry.addData("mm", "%.2f mm", range.getDistance(DistanceUnit.MM));
        telemetry.addData("in", "%.2f in", range.getDistance(DistanceUnit.INCH));
        telemetry.addData("m", "%.2f m", range.getDistance(DistanceUnit.METER));
        telemetry.addLine(" ");
        telemetry.addData("OPTICAL", range.cmOptical());

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
    public void loop() {
        driveLoop();
        holderServoLoop();
//        pushBarServoLoop();
        armReleaseLoop();
        lifterMotorLoop();
        harvesterMotorLoop();
        launcherLoop();
        telemetry.addLine(" ");
        telemetrys();
        //bottom greater than 10 on alpha
    }
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}

