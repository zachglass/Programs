package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Muses", group="Testing")
@Disabled
public class MusesTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private DcMotor scoringMotor = null;

    private DcMotor harvesterMotor = null;

    private Servo stopBarServo = null;

    private TouchSensor button = null;

    private double motorThreshold = 0.065;

    //private Servo capServo = null;

    private DcMotor capMotor = null;

    public void driveInit() {
        telemetry.addData("Status", "Initialized");

        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        // leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors

        telemetry.addData("Status", "Initialized");
    }

    public void scoringInit() {
        scoringMotor = hardwareMap.dcMotor.get("scoring motor");
    }

    public void harvesterInit() {
        harvesterMotor = hardwareMap.dcMotor.get("harvester motor");
        //harvesterMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void stopbarInit() {
        stopBarServo = hardwareMap.servo.get("stopbar");
        stopBarServo.setPosition(.7);
    }

    public void capMotorInit() {
        capMotor = hardwareMap.dcMotor.get("cap motor");
    }

    /*public void touchInit() {
        button = hardwareMap.touchSensor.get("button");
    }*/

    /*public void capInit() {
        capServo = hardwareMap.servo.get("cap");
        capServo.setPosition(1);
    } */

    @Override
    public void init() {
        driveInit();
        //scoringInit();
        //harvesterInit();
        //stopbarInit();
        //touchInit();
        //capInit();
        //capMotorInit();
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

        telemetry.addData("left1 encoder", leftMotor1.getCurrentPosition());
        telemetry.addData("left2 encoder", leftMotor2.getCurrentPosition());
        telemetry.addData("right1 encoder", rightMotor1.getCurrentPosition());
        telemetry.addData("right2 encoder", rightMotor2.getCurrentPosition());

        telemetry.addData("Status", "Running: " + runtime.toString());
        telemetry.addData("RightX", gamepad1.right_stick_x);
        telemetry.addData("RightY", gamepad1.right_stick_y);
        telemetry.addData("LeftX", gamepad1.left_stick_x);
        telemetry.addData("LeftY", gamepad1.left_stick_y);

        float leftX = -gamepad1.left_stick_x;// assigning controller values to a variable
        float rightX = -gamepad1.right_stick_x;
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;

        float coord = leftY;
        float coord1 = rightY;
        DcMotor.Direction dir1 = DcMotor.Direction.REVERSE;
        DcMotor.Direction dir2 = DcMotor.Direction.REVERSE;
        DcMotor.Direction dir3 = DcMotor.Direction.FORWARD;
        DcMotor.Direction dir4 = DcMotor.Direction.FORWARD;

        if (((leftX >= motorThreshold) || (leftX <= -motorThreshold)) && ((rightX >= motorThreshold) || (rightX <= -motorThreshold))) {
            dir1 = DcMotor.Direction.FORWARD;
            dir4 = DcMotor.Direction.REVERSE;
            coord = leftX;
            coord1 = rightX;
        }
        leftMotor1.setDirection(dir1);// Set to FORWARD if using AndyMark motors
        leftMotor2.setDirection(dir2);
        rightMotor1.setDirection(dir3);
        rightMotor2.setDirection(dir4);
        leftMotor1.setPower(coord1);
        leftMotor2.setPower(coord1);
        rightMotor1.setPower(coord);
        rightMotor2.setPower(coord);
        telemetry.addData("RightX", gamepad1.right_stick_x);
        telemetry.addData("RightY", gamepad1.right_stick_y);
        telemetry.addData("LeftX", gamepad1.left_stick_x);
        telemetry.addData("LeftY", gamepad1.left_stick_y);

        //float leftX = -gamepad1.left_stick_x;// assigning controller values to a variable
        //switch the directions on the auto
    }

    public void scoringLoop() {

        scoringMotor.setPower(gamepad2.left_stick_y);
    }

    public void harvesterLoop() {

        harvesterMotor.setPower(gamepad2.right_stick_y);
    }

    public void capMotorLoop() {

        telemetry.addData("right trigger", gamepad1.right_trigger);
        telemetry.addData("left trigger", gamepad1.left_trigger);

        if(gamepad1.right_trigger > 0) {
            capMotor.setPower(gamepad1.right_trigger);

        } else if (gamepad1.left_trigger > 0) {
            capMotor.setPower(-gamepad1.left_trigger);

        } else {
            capMotor.setPower(0);
        }
    }

    public void stopbarLoop() {
        telemetry.addData("x",gamepad2.right_bumper);
        telemetry.addData("position Stopbar",stopBarServo.getPosition());
        if (gamepad2.right_bumper) {
            if (stopBarServo.getPosition() != 0) {
                stopBarServo.setPosition(0);
            }
        } else {
            if (stopBarServo.getPosition() != .7) {
                stopBarServo.setPosition(.7);
            }
        }
    }

    /*public void touchLoop() {
        telemetry.addData("Button Pressed: ", button.isPressed());
        telemetry.addData("Button Value: ", button.getValue());
    }*/

    /*public void capLoop() {
        telemetry.addData("y", gamepad1.left_bumper);
        telemetry.addData("position Capbar", capServo.getPosition());
        if (gamepad1.left_bumper) {
            if (capServo.getPosition() != 0) {
                capServo.setPosition(0);
            }
        } else {
            if (capServo.getPosition() != .7) {
                capServo.setPosition(.7);
            }
        }
    } */

    @Override
    public void loop() {
        driveLoop();
        //scoringLoop();
        //harvesterLoop();
        //stopbarLoop();
        //touchLoop();
        //capMotorLoop();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
