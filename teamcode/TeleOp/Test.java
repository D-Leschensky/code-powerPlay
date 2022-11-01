//DONT YOU DARE TOUCH - David to Siran ;)


package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp
public class Test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // declare le motor

        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");//0
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");//2
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");//1
        DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackRight");//3

        //DcMotor motorZoomPunchArm = hardwareMap.dcMotor.get("motorZoomPunchArm"); //1 on expansion
        //Servo servoZaHando = hardwareMap.servo.get("servoZaHando");
        //motorZoomPunchArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Reverse the right side motors cuz i think it work

        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x; // Counteract imperfect strafing multiply by 1.1
            double rx = gamepad1.right_stick_x;
            double punch = gamepad2.right_stick_y;
            double ducky = gamepad2.left_stick_x;
            double gamepad2YArm = (gamepad2.left_stick_y/2);



            // Denominator is just 1.
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            boolean pressedLastIteration = false;
            //next lines are controlling spinning the duck carousel




//All of your other control loop code here
            boolean pressed = gamepad2.a;
            boolean slowMode = gamepad1.b;
            boolean armPressed = gamepad2.b;
            boolean scoreArm = gamepad2.y;
            float leftTrigger = gamepad2.left_trigger;
            float rightTrigger = gamepad2.right_trigger;

//boolean redDuck = gamepad2.z;
//boolean armpress = gamepad2.y;
//boolean armpressdown = gamepad2.x;
//boolean isDown = true;



          /*  if (scoreArm == true) {
                 servoZaHando.setPosition(0.15);
            }

            if(armPressed == true){
                servoZaHando.setPosition(0.4);//0.4
            }
            if(armPressed == false && scoreArm == false){
                servoZaHando.setPosition(0);//0
            }*/





            if(slowMode == false){
                motorFrontLeft.setPower(-frontLeftPower);//the minus in front of these is to see if it will go forward cuz rn it reverse
                motorBackLeft.setPower(-backLeftPower);// yes the minus just makes it go backwards
                motorFrontRight.setPower(-frontRightPower);
                motorBackRight.setPower(-backRightPower);
                //motorZoomPunchArm.setPower(-gamepad2YArm);

            }
            if(slowMode == true){
                motorFrontLeft.setPower(-frontLeftPower/2);//the minus in front of these is to see if it will go forward cuz rn it reverse
                motorBackLeft.setPower(-backLeftPower/2);// yes the minus just makes it go backwards
                motorFrontRight.setPower(-frontRightPower/2);
                motorBackRight.setPower(-backRightPower/2);
                //motorZoomPunchArm.setPower(-gamepad2YArm);

            }
        }

    }


}
