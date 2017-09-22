package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by mars on 5/3/17.
 */

//I have created this program as a template for next year so that we can simplify our programs

public class singleProgramTest extends OpMode {

    //I have declared the variables so that the logic will work below...

    public ElapsedTime runtime = new ElapsedTime();

    public DcMotor leftMotor1 = null;
    public DcMotor leftMotor2 = null;
    public DcMotor rightMotor1 = null;
    public DcMotor rightMotor2 = null;

    public DcMotor leftLinearSlide = null;
    public DcMotor rightLinearSlide = null;

    public DcMotor launchMotor = null;

    public DcMotor harvestMotor = null;

    public Servo capballServo = null;
    public Servo capballServo1 = null;

    //private Servo holder = null;

    public void inits() {

        telemetry.addLine("MOTORS");
        //Drive
        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");

        leftMotor1.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);
        rightMotor1.setDirection(DcMotor.Direction.FORWARD);
        rightMotor2.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addLine("LINEAR SLIDES");
        //Linear Slides
        leftLinearSlide = hardwareMap.dcMotor.get("left linear slide");
        rightLinearSlide = hardwareMap.dcMotor.get("right linear slide");

        leftLinearSlide.setDirection(DcMotor.Direction.REVERSE);
        rightLinearSlide.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addLine("CAPBALL SERVOS");
        //Capball Servoes
        capballServo = hardwareMap.servo.get("capball servo");
        capballServo1 = hardwareMap.servo.get("capball servo 1");
        capballServo.setPosition(.2);
        capballServo1.setPosition(.8);

        telemetry.addLine("LAUNCHER");
        //Launcher
        launchMotor = hardwareMap.dcMotor.get("launcher");
        launchMotor.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addLine("HARVESTER");
        //Harvester
        harvestMotor = hardwareMap.dcMotor.get("harvester");
        harvestMotor.setDirection(DcMotor.Direction.FORWARD);

        //Holder Servo
        //holder = hardwareMap.servo.get("holder");
        //holder.setDirection(Servo.Direction.REVERSE);

        //holder.setPosition(.1);
        telemetry.addLine("DONE!");
    }

    @Override
    public void init() {
        inits();
    }

    @Override
    public void loop() {
        inits();
    }
}
