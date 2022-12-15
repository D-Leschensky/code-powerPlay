


package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.*;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "mecanumOp")
public class MecanumTeleOp extends LinearOpMode {
    /*
    private DcMotor motorFL = hardwareMap.dcMotor.get("motorFrontLeft");
    private DcMotor motorFR = hardwareMap.dcMotor.get("motorFrontRight");
    private DcMotor motorBL = hardwareMap.dcMotor.get("motorBackLeft");
    private DcMotor motorBR = hardwareMap.dcMotor.get("motorBackRight");
     */

    @Override
    public void runOpMode() throws InterruptedException{
    // Why are these declared inside of a function instead of the class?
        DcMotor motorFL = hardwareMap.dcMotor.get("motorFrontLeft");
        DcMotor motorFR = hardwareMap.dcMotor.get("motorFrontRight");
        DcMotor motorBL = hardwareMap.dcMotor.get("motorBackLeft");
        DcMotor motorBR = hardwareMap.dcMotor.get("motorBackRight");

        boolean rotateChange = false;

        // DcMotor[] dirMotors = {motorFL, motorFR, motorBL, motorBR};

        DcMotor motorArm = hardwareMap.dcMotor.get("slid");
        Servo servoRotation = hardwareMap.servo.get("servoRotation");
        Servo servoClaw = hardwareMap.servo.get("servoClaw");

        //zeroPowerBehavior makes the motors seize up when not moving so they dont slide. Like me at a party.

        /*
        for(DcMotor i : dirMotors)
            i.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
         */

        motorFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //motors are on oppositedirections so u gotta reverse one side
        //motorFR.setDirection((DcMotorSimple.Direction.REVERSE));
        motorFL.setDirection((DcMotorSimple.Direction.REVERSE));
        motorBL.setDirection((DcMotorSimple.Direction.REVERSE));
        //motorBR.setDirection((DcMotorSimple.Direction.REVERSE));
        
        waitForStart();
        
        if(isStopRequested()) return;
        
        while(opModeIsActive()) {

            //getting some input :O haha

            // vvv Make into a function? vvv //


            /*
            Why declare thes values here instead of in the class?
            Also why not just use the raw values eg. gamepad1.left_stick_y?
            If variables are made for readability why are the values for gamepad1 and gamepad2 not labeled different?
             */
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;
            double yArm = gamepad2.left_stick_y;
            double xArm = gamepad2.right_stick_x;
            boolean servoClose = gamepad2.a;
            //double leftTrig = gamepad2.left_trigger;
            //double rightTrig = gamepad2.right_trigger;
            boolean leftBump = gamepad2.left_bumper;
            boolean rightBump = gamepad2.right_bumper;
            boolean servo;
            boolean slowMode = gamepad1.a;
            //declaring our powers ;)
            // no ^
            double powerFL = (y + x + rx);
            double powerBL = (y - x + rx);
            double powerFR = (y - x - rx);
            double powerBR = (y + x - rx);

            // int[] pList = {powerFL, powerFR, powerBL, powerBR}
            //turn = 0;
            //double servRotat = Math.abs(leftTrig - rightTrig);

            if (servoClose) { //servoclose == true can be simplified to just this so cool
                servoClaw.setPosition(0.85);
                //servo = true;
            } else {
                servoClaw.setPosition(0.7);
            }
            //if(leftBump){
                //servoRotation.setPosition(0.3); //0.3
            //}
            //SS
            if(rightBump){

                servoRotation.setPosition(-0.1); //0.6
            }
            else{

                servoRotation.setPosition(0.7);

            }
            //SS
//            if(rightBump && !rotateChange) {
//                if (servoRotation.getPosition() == 0.7) {
//                    servoRotation.setPosition(-0.1);
//                } else {
//                    servoRotation.setPosition(0.7);
//                }
//                rotateChange = true;
//            }
//            else if(!rightBump){
//                rotateChange = false;
//            }
//SS


            //makes the roboboi go vrooom
            if(slowMode) {
                motorBL.setPower(powerBL / 4);
                motorBR.setPower(powerBR / 4);
                motorFL.setPower(powerFL / 4);
                motorFR.setPower(powerFR / 4);
            }
            else{
                motorBL.setPower(powerBL);
                motorBR.setPower(powerBR);
                motorFL.setPower(powerFL);
                motorFR.setPower(powerFR);

            }
            /*
            for(int i = 0; i < pList.length; i++)
                dirMotors[i].setPower(pList[i]/1.5);
             */


            motorArm.setPower(yArm);
            //servoRotation.setPosition(servRotat+0.3);

            
            
        }
    }
}
