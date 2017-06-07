package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(name="Sensor", group="Testing")
@Disabled
public class SensorTest extends OpMode {

    private DeviceInterfaceModule dim = null;

    private OpticalDistanceSensor optical = null;
    private ColorSensor topColor = null;
    private ColorSensor bottomColor1 = null;
    private ColorSensor bottomColor2 = null;
    private ModernRoboticsI2cRangeSensor range = null;
    //private IrSeekerSensor ir1 = null;
    //private IrSeekerSensor ir2 = null;

    public void telemetrys() {
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

        /*
        telemetry.addData("OpLight: ", optical.getLightDetected());
        telemetry.addData("OpRawLight: ", optical.getRawLightDetected());
        telemetry.addData("OpRawLightMax: ", optical.getRawLightDetectedMax());
        */

        /* Commented these out since I don't believe we will be using them
        telemetry.addData("Angle ir1", ir1.getAngle());
        telemetry.addData("Strength ir1", ir1.getStrength());

        telemetry.addData("Angle ir2", ir2.getAngle());
        telemetry.addData("Strength ir2", ir2.getStrength());
        */

        telemetry.addLine("RANGE");
        telemetry.addData("cm optical", "%.2f cm", range.cmOptical());

        telemetry.addData("raw ultrasonic", range.rawUltrasonic());
        telemetry.addData("raw optical", range.rawOptical());

        telemetry.addData("cm", "%.2f cm", range.getDistance(DistanceUnit.CM));
        telemetry.addData("mm", "%.2f mm", range.getDistance(DistanceUnit.MM));
        telemetry.addData("m", "%.2f m", range.getDistance(DistanceUnit.METER));
        telemetry.addData("in", "%.2f in", range.getDistance(DistanceUnit.INCH));
        telemetry.addData("ft", "%.2f ft", (range.getDistance(DistanceUnit.INCH)/12)); //This is the math to get feet cause why not
        telemetry.addLine(" ");

        //DIM
        telemetry.addData("Dim Digital Input State Byte", dim.getDigitalInputStateByte());
        telemetry.addData("Dim Digital Output State Byte", dim.getDigitalOutputStateByte());
        telemetry.addData("Dim Digital IO Control Byte", dim.getDigitalIOControlByte());
        telemetry.addLine(" ");

        telemetry.addLine("TESTING STUFF");
        telemetry.addData("topColor I2c address", topColor.getI2cAddress());
        telemetry.addData("bottomColor1 I2c address", bottomColor1.getI2cAddress());
        telemetry.addData("bottomColor2 I2c address", bottomColor2.getI2cAddress());
        telemetry.addLine(" ");

        telemetry.update();

    }

    @Override
    public void init() {

        dim = hardwareMap.deviceInterfaceModule.get("dim");
        dim.setLED(0, true);
        //dim.setLED(1, true);
        //dim.resetDeviceConfigurationForOpMode();

        range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");

        topColor = hardwareMap.colorSensor.get("topColor");
        bottomColor1 = hardwareMap.colorSensor.get("bottomColor1");
        bottomColor2 = hardwareMap.colorSensor.get("bottomColor2");

        topColor.enableLed(false);
        bottomColor1.enableLed(false);
        bottomColor2.enableLed(false);

        //topColor.enableLed(true);
        bottomColor1.enableLed(true);
        bottomColor2.enableLed(true);

        //This is to set the color sensors to different I2c Address's so that we can have
        topColor.setI2cAddress(I2cAddr.create8bit(0x10));
        bottomColor1.setI2cAddress(I2cAddr.create8bit(0x12));
        bottomColor2.setI2cAddress(I2cAddr.create8bit(0x14));

        /*
        I2cAddr topColorAddress = topColor.getI2cAddress();
        I2cAddr bottomColor1Address = bottomColor1.getI2cAddress();
        I2cAddr bottomColor2Address = bottomColor2.getI2cAddress();

        telemetry.addData("topColor I2c address", topColorAddress);
        telemetry.addData("bottomColor1 I2c address", bottomColor1Address);
        telemetry.addData("bottomColor2 I2c address", bottomColor2Address);

        //This is to hopefully set a new I2c Address so that I can have 3 independent color sensors
        topColor.setI2cAddress(I2cAddr.create8bit(0x70));
        bottomColor1.setI2cAddress(I2cAddr.create8bit(0x70));
        bottomColor2.setI2cAddress(I2cAddr.create8bit(0x70));
*/

        //optical = hardwareMap.opticalDistanceSensor.get("distance");
        //ir1   = hardwareMap.irSeekerSensor.get("ir1");
        //ir2   = hardwareMap.irSeekerSensor.get("ir2");

    }


    @Override
    public void loop() {

        telemetrys();

    }

    @Override
    public void stop() {

    }
}
