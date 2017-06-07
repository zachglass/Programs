package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by mars on 1/29/17.
 */
@TeleOp(name="Lions", group="Lions")
@Disabled
public class Lions extends OpMode {

    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;
    private DcMotor harvestMotor = null;

    private Servo servo = null;

    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left motor");
        rightMotor = hardwareMap.dcMotor.get("right motor");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        harvestMotor = hardwareMap.dcMotor.get("harvester");

        servo = hardwareMap.servo.get("servo");
        servo.setPosition(1);

    }

    @Override
    public void loop() {

        telemetry.addData("leftMotor: ", leftMotor.getPower());
        telemetry.addData("rightMotor: ", rightMotor.getPower());
        telemetry.addData("harvestMotor: ", harvestMotor.getPower());
        telemetry.addData("servoPos", servo.getPosition());

        //tank controls
        //gamepad1.a forward motor
        //gamepad1.x backward motor
        //gamepad1.b servo hold function

        leftMotor.setPower(-gamepad1.left_stick_y);
        rightMotor.setPower(-gamepad1.right_stick_y);

        if (gamepad1.a) {
            harvestMotor.setPower(1);
        } else if (gamepad1.x) {
            harvestMotor.setPower(-1);
        } else {
            harvestMotor.setPower(0);
        }

        if (gamepad1.b) {
            if (servo.getPosition() != 0) {
                servo.setPosition(0);
            }
        } else {
            if (servo.getPosition() != 1) {
                servo.setPosition(1);
            }
        }
    }
}
