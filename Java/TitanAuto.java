package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by mars on 10/30/16.
 */
@Autonomous(name="TitanAuto", group="Auto")
@Disabled
public class TitanAuto extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    private Servo capballServo = null;
    private Servo capballServo1 = null;

    private DcMotor harvestMotor = null;

    private DcMotor launcherMotor = null;

    private int tracker = 0;

    public void moveForward() {
        leftMotor1.setPower(.5);
        leftMotor2.setPower(.5);
        rightMotor1.setPower(.5);
        rightMotor2.setPower(.5);
    }

    public void dontMove() {
        leftMotor1.setPower(0);
        leftMotor2.setPower(0);
        rightMotor1.setPower(0);
        rightMotor2.setPower(0);
    }

    public void shoot() {
        launcherMotor.setPower(1);
    }

    public void dontShoot() {
        launcherMotor.setPower(0);
    }

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftMotor1 = hardwareMap.dcMotor.get("left motor 1");
        leftMotor2 = hardwareMap.dcMotor.get("left motor 2");
        rightMotor1 = hardwareMap.dcMotor.get("right motor 1");
        rightMotor2 = hardwareMap.dcMotor.get("right motor 2");
        rightMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);
        leftMotor1.setTargetPosition(0);
        leftMotor2.setTargetPosition(0);
        rightMotor1.setTargetPosition(0);
        rightMotor2.setTargetPosition(0);

        launcherMotor = hardwareMap.dcMotor.get("launcher");
        launcherMotor.setTargetPosition(0);
        //launcherMotor.setDirection(DcMotor.Direction.REVERSE); NEVER DO THIS
        telemetry.addData("Shooter", launcherMotor.getCurrentPosition());

        harvestMotor = hardwareMap.dcMotor.get("harvester");
        harvestMotor.setTargetPosition(0);
        telemetry.addData("harvester", harvestMotor.getCurrentPosition());

        capballServo = hardwareMap.servo.get("capball servo");
        capballServo1 = hardwareMap.servo.get("capball servo 1");
        capballServo.setPosition(.2);
        capballServo1.setPosition(.6);
        telemetry.addData("New Servo", capballServo1.getPosition());
        telemetry.addData("Old Servo", capballServo.getPosition());
    }

    @Override
    public void start() {
        runtime.reset();
        tracker = 0;
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());

        telemetry.addData("Left 1: ", leftMotor1.getCurrentPosition());
        telemetry.addData("Left 2: ", leftMotor2.getCurrentPosition());
        telemetry.addData("Right 1: ", rightMotor1.getCurrentPosition());
        telemetry.addData("Right 2: ", rightMotor2.getCurrentPosition());

        telemetry.addData("Launcher: ", launcherMotor.getCurrentPosition());

        telemetry.addData("Harvester: ", harvestMotor.getCurrentPosition());

        telemetry.addData("Tracker: ", tracker);

        if (tracker == 0) {
            if (leftMotor2.getCurrentPosition() >= 400) {
                harvestMotor.setPower(0);
                dontMove();
                dontShoot();
                tracker = 1;
            } else {
                moveForward();
            }
        } else if (tracker == 1) {
            if ((runtime.time() <= 3) && (launcherMotor.getCurrentPosition() <= 1500)) {
                shoot();
            } else {
                dontShoot();
                if (harvestMotor.getCurrentPosition() <= 100) {
                    harvestMotor.setPower(.5);
                } else {
                    harvestMotor.setPower(0);
                    tracker = 2;
                }
            }
        } else if (tracker == 2) {
            if ((runtime.time() <= 6) && (launcherMotor.getCurrentPosition() <= 3600)) {
                harvestMotor.setPower(0);
                shoot();
            } else {
                dontShoot();
                tracker = 3;
            }
        } else if (tracker == 3) {
            if (leftMotor1.getCurrentPosition() >= 3000) {
                dontMove();
                tracker = 4;
            } else {
                moveForward();
            }
        } else {
            dontMove();
            dontShoot();
        }

    }

    @Override
    public void stop() {

    }
}

        /*
        * This is Ellie's Program


        if (runtime.time() <= 2) {
            if (leftMotor1.getCurrentPosition() <=400) {
                moveForward();
            } else if (leftMotor1.getCurrentPosition() >= 400) {
                dontMove();
            }
        }

        if (runtime.time() >= 2 && runtime.time() <= 5) {
            shoot();
            dontMove();
        }

        if (runtime.time() >= 5 && runtime.time() <= 7) {
            dontShoot();
            dontMove();
            harvestMotor.setPower(.5);
        }

        if (runtime.time() >= 7 && runtime.time() <= 11) {
            shoot();
            dontMove();
            harvestMotor.setPower(0);
        }

        if (runtime.time() >= 11 && leftMotor1.getCurrentPosition() >= 75 && runtime.time() <= 13) {
            moveForward();
            dontShoot();
        }

        if (runtime.time() >= 13) {
            dontMove();
            dontShoot();
            harvestMotor.setPower(0);
        }
*/
        //VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV
        /*if (runtime.time() <= 2) {
            if (leftMotor1.getCurrentPosition() <= 400) {
                moveForward();
            } else if (leftMotor1.getCurrentPosition() >= 400) {
                //harvestMotor.setPower(.5);
                dontMove();
                shoot();
            } else {
                harvestMotor.setPower(0);
                dontMove();
                dontShoot();
            }
        } else if (tracker == 0) {
            if (launcherMotor.getCurrentPosition() <= 400) {
                shoot();
            } else {
                dontShoot();
                if (launcherMotor.getCurrentPosition() <= 800 && runtime.time() >= 6) {
                    harvestMotor.setPower(.5);
                } else {
                    harvestMotor.setPower(0);
                    /*if (holder.getPosition() != .4) {
                        holder.setPosition(.4);
                    }*/
                    /*<<<<<<<<<<<<<<<<<<<<<<<<<,,tracker = 1;
                }
            }
        } else if (tracker == 1) {
            if ((runtime.time() > 0) && (runtime.time() <= 10)) {
                /*if (holder.getPosition() != .4) {
                    holder.setPosition(.4);
                }*/
                /*<<<<<<<<<<<<<<<<<<<<launcherMotor.setPower(1);
            } else if (launcherMotor.getCurrentPosition() >= 200 && runtime.time() <= 13) {
                harvestMotor.setPower(0);
            } else {
                if ((runtime.time() >= 17) && (launcherMotor.getCurrentPosition() <= 3500)) {
                    harvestMotor.setPower(0);
                    shoot();
                } else {
                    dontShoot();
                    telemetry.addLine("AM I WORKING???");
                    if ((runtime.time() >= 19) && (launcherMotor.getCurrentPosition() >= 75)) {
                        //harvestMotor.setPower(.5);
                        tracker = 2;
                    } else {
                        harvestMotor.setPower(0);
                        /*if (holder.getPosition() != .1) {
                            holder.setPosition(.1);
                        }*/
                    /*<<<<<<<<<<<<<<<<<<<<,}
                }
            }
        } else if (tracker == 2) {
            if (launcherMotor.getCurrentPosition() >= 100) {
                //harvestMotor.setPower(.5);
            } else {
                harvestMotor.setPower(0);
                //This is a placeholder so the program won't throw a fit about an empty else statement
                //This is for driving, and for testing I do not want to destroy something so im going to comment this out
                if (leftMotor1.getCurrentPosition() >= 3000) {
                    dontMove();
                } else {
                    moveForward();
                }
            }
            //lift harvester
            //drive forward to hit capball
        } else {
            dontMove();
            dontShoot();
        }
    }
*/
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    /* This is a fully functioning 2 shooting system, I am going to simplify it,
     so commenting out the entire loop
    @Override
    public void loop() {

        telemetry.addData("Status", "Running: " + runtime.toString());

        telemetry.addData("Left 1: ", leftMotor1.getCurrentPosition());
        telemetry.addData("Left 2: ", leftMotor2.getCurrentPosition());
        telemetry.addData("Right 1: ", rightMotor1.getCurrentPosition());
        telemetry.addData("Right 2: ", rightMotor2.getCurrentPosition());

        telemetry.addData("Launcher: ", launcherMotor.getCurrentPosition());

        telemetry.addData("Harvester: ", harvestMotor.getCurrentPosition());

        telemetry.addData("Holder: ", holder.getPosition());

        telemetry.addData("Tracker: ", tracker);

        //move forward is open
        //move backward is closed

        //This is to shoot 2 balls
        if (runtime.time() <= 3) {
            if (leftMotor1.getCurrentPosition() <= 300) {
                moveForward();
            } else if (harvestMotor.getCurrentPosition() <= 200) {
                harvestMotor.setPower(.5);
            } else {
                harvestMotor.setPower(0);
                dontMove();
                dontShoot();
            }
        } else if (tracker == 0) {
            if (launcherMotor.getCurrentPosition() <= 1500) {
                shoot();
            } else {
                dontShoot();
                if (harvestMotor.getCurrentPosition() >= 50) {
                    harvestMotor.setPower(-.5);
                } else {
                    harvestMotor.setPower(0);
                    if (holder.getPosition() != .4) {
                        holder.setPosition(.4);
                    }
                    tracker = 1;
                }
            }
        } else if (tracker == 1) {
            if ((runtime.time() > 0) && (runtime.time() <= 8)) {
                if (holder.getPosition() != .4) {
                    holder.setPosition(.4);
                }
            } else if (harvestMotor.getCurrentPosition() <= 200) {
                harvestMotor.setPower(.5);
            } else {
                if ((runtime.time() >= 12) && (launcherMotor.getCurrentPosition() <= 3600)) {
                    harvestMotor.setPower(0);
                    shoot();
                } else {
                    dontShoot();
                    telemetry.addLine("AM I WORKING???");
                    if ((runtime.time() >= 17) && (harvestMotor.getCurrentPosition() >= 75)) {
                        harvestMotor.setPower(-.5);
                        tracker = 2;
                    } else {
                        harvestMotor.setPower(0);
                        if (holder.getPosition() != .1) {
                            holder.setPosition(.1);
                        }
                    }
                }
            }
        } else if (tracker == 2) {
            if (harvestMotor.getCurrentPosition() >= 100) {
                harvestMotor.setPower(-.5);
            } else {
                harvestMotor.setPower(0);
                //This is a placeholder so the program won't throw a fit about an empty else statement
                //This is for driving, and for testing I do not want to destroy something so im going to comment this out
                if (leftMotor1.getCurrentPosition() >= 3000) {
                    dontMove();
                } else {
                    moveForward();
                }
            }
            //lift harvester
            //drive forward to hit capball
        } else {
            dontMove();
            dontShoot();
        }
    }*/

        /*This is for hitting capball
        if (leftMotor1.getCurrentPosition() >= 3000) {
            dontMove();
        } else {
            moveForward();
        }*/

        /*This is going to be for the beacons once I am finished...
        if (leftMotor1.getCurrentPosition() <= 2000) {
            moveForward();
        } else {
            dontMove();
        }
        */

        /* This shoots a ball and then knocks off the capball

        if (runtime.time() <= 10) {
            dontMove();
        } else {
            if (launcherMotor.getCurrentPosition() <= 1500) {
                shoot();
            } else {
                dontShoot();
                if (leftMotor1.getCurrentPosition() >= 3000) {
                    dontMove();
                } else {
                    moveForward();
                }
            }
        }

        */
        //int motorPos = leftMotor1.getCurrentPosition();
        //int newMotorPos = motorPos + 3500;

        /*while (motorPos <= newMotorPos) {
            leftMotor1.setPower(.3);
            leftMotor2.setPower(.3);
            rightMotor1.setPower(.3);
            rightMotor2.setPower(.3);
        }

        if (leftMotor1.getCurrentPosition() >= 3500) {
            leftMotor1.setPower(0);
            leftMotor2.setPower(0);
            rightMotor1.setPower(0);
            rightMotor2.setPower(0);
        } else {
            leftMotor1.setPower(.3);
            leftMotor2.setPower(.3);
            rightMotor1.setPower(.3);
            rightMotor2.setPower(.3);
        }*/
