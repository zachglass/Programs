package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by mars on 10/9/16.
 */
@TeleOp(name="Servo", group="Testing")
@Disabled
public class ServoTest extends OpMode {

    private Servo leftArmServo = null;
    private Servo rightArmServo = null;

    @Override
    public void init() {

        leftArmServo = hardwareMap.servo.get("servo left");
        rightArmServo = hardwareMap.servo.get("servo right");

        leftArmServo.setPosition(0);
        rightArmServo.setPosition(0);

        rightArmServo.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            if ((leftArmServo.getPosition() != 1) && (rightArmServo.getPosition() != 0)) {
                leftArmServo.setPosition(1);
                rightArmServo.setPosition(0);
            }
        } else {
            if ((leftArmServo.getPosition() != 0) && (rightArmServo.getPosition() != 1)) {
                leftArmServo.setPosition(0);
                rightArmServo.setPosition(1);
            }
        }
    }
}
