


package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.*;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "mecanumOp")
public class MecanumTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException{

        DcMotor motorFL = hardwareMap.dcMotor.get("motorFrontLeft");
        DcMotor motorFR = hardwareMap.dcMotor.get("motorFrontRight");
        DcMotor motorBL = hardwareMap.dcMotor.get("motorBackLeft");
        DcMotor motorBR = hardwareMap.dcMotor.get("motorBackRight");
        DcMotor motorArm = hardwareMap.dcMotor.get("motorArm");
        Servo servoRotation = hardwareMap.servo.get("servoRotation");
        Servo servoClaw = hardwareMap.servo.get("servoClaw");

        //zeroPowerBehavior makes the motors seize up when not moving so they dont slide. Like me at a party.
        motorFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //motors are on oppositedirections so u gotta reverse one side
        motorFR.setDirection((DcMotorSimple.Direction.REVERSE));
        motorBR.setDirection((DcMotorSimple.Direction.REVERSE));
        
        waitForStart();
        
        if(isStopRequested()) return;
        
        while(opModeIsActive()) {

            //getting some input :O
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;
            double yArm = gamepad2.left_stick_y;
            double xArm = gamepad2.right_stick_x;
            boolean servoClose = gamepad2.a;
            boolean servo;
            //declaring our powers ;)
            double powerFL = (y + x + rx)/1.2;
            double powerBL = (y - x + rx)/1.2;
            double powerFR = (y - x - rx);
            double powerBR = (y + x - rx);


            if(servoClose){ //servoclose == true can be simplified to just this
                servoClaw.setPosition(0.15);
                //servo = true;

            }
            else  {
                servoClaw.setPosition(0);
            }

            //makes the roboboi go vrooom
            motorBL.setPower(powerBL);
            motorBR.setPower(powerBR);
            motorFL.setPower(powerFL);
            motorFR.setPower(powerFR);
            motorArm.setPower(yArm);
            servoRotation.setPosition(xArm);

            
            
        }
    }
}
