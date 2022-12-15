/*
Code made by DAVID :)
 */

package org.firstinspires.ftc.teamcode.Autonomous;

//import com.qualcomm.robotcore.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="redAuton2")

public class redAuton2 extends LinearOpMode {


    ColorSensor color_sensor;
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor motorBR = null;
    private DcMotor motorArm = null;
    private Servo servoRotation = null;
    private Servo servoClaw = null;
    int leftRotate = 0;
    int rightRotate = 1;

    // just... declare them why are yo setting them to null ;-;

    private ElapsedTime     runtime = new ElapsedTime();


    static final double     COUNTS_PER_INCH         = 42.3;//42.18//53.76;//find on servocity 42
    static final double     DRIVE_SPEED             = 0.5;
    static final double     TURN_SPEED              = 0.5;



    @Override
    public void runOpMode() throws InterruptedException {

        //setting up the super duper ultra cool openCV pipe



        //    @Override

        //camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()

        // Initialize the drive system variables.

        /*
        DcMotor[] dList = {leftDrive, rightDrive, backLeftDrive, backRightDrive}
        String[] sdList = {"motorFrontLeft", "motorFrontRight", "motorBackLeft", "motorBackRight"}

        for(int i = 0; i < dList.length; i ++)
            dList[i] = hardwareMap.get(DcMotor.class, sdList[i])
         */

        leftDrive  = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        rightDrive = hardwareMap.get(DcMotor.class, "motorFrontRight");
        backLeftDrive  = hardwareMap.get(DcMotor.class, "motorBackLeft");
        backRightDrive = hardwareMap.get(DcMotor.class, "motorBackRight");
        motorArm = hardwareMap.dcMotor.get("slid");
        servoRotation = hardwareMap.servo.get("servoRotation");
        servoClaw = hardwareMap.servo.get("servoClaw");

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // the front motors are facing the other way so reverse
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        /*
        for(DcMotor a : list) {
            a.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            a.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         }
         */
//positive is inside down
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Updates for debug purposes :))
        telemetry.addData("Starting at(frontLeft, frontRight, backLeft, Backright, arm",  "%7d :%7d :%7d :%7d :7%d",
                leftDrive.getCurrentPosition(),
                rightDrive.getCurrentPosition(),
                backLeftDrive.getCurrentPosition(),
                backRightDrive.getCurrentPosition(),
                motorArm.getCurrentPosition()
        );
        telemetry.update();

        color_sensor = hardwareMap.colorSensor.get("color_sensor");

        // Wait for the game to start (driver presses PLAY button its kinda a triangle)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed). Negative speed is a NOO >:(((
        //encoderDrive(DRIVE_SPEED,  -650,  650, 650, -650);

        servoClaw.setPosition(0.7);
        sleep(100);
        //davidDrive(0,0,0,0,0,true);
        armDrive(0.5,20);
        sleep(100);
        //davidDrive(DRIVE_SPEED,0,0,0,);
        //zaHandoMovement(0.5,2);

        davidDrive(DRIVE_SPEED,-24,24,24,-24,true);
        sleep(100);
        int Zone;
        if((color_sensor.red() > color_sensor.blue()) && (color_sensor.red() > color_sensor.green())){
            telemetry.addData("red", "");
            telemetry.update();
            Zone = 2;
        }


        else if((color_sensor.blue() > color_sensor.red()) && (color_sensor.blue() > color_sensor.green())){
            telemetry.addData("blue", "");
            telemetry.update();
            Zone = 3;
        }

        else{
            telemetry.addData("green","");
            telemetry.update();
            Zone = 1;
        };
//TODO eat apple pie
        davidDrive(DRIVE_SPEED,  -48,  48, 48, -48, true);

        //davidDrive(0,0.5,0.5,0.5,0.5,true);
        //zaHandoMovement(0.5,100);
        /*        motorArm.setPower(1);
        sleep(2250);
        motorArm.setPower(0);*/
        armDrive(0.5,80);
        sleep(100);
        servoRotation.setPosition(rightRotate);
        sleep(250);
        davidDrive(DRIVE_SPEED,  -7,  -7, -7, -7, true);
        sleep(100);
        //davidDrive(DRIVE_SPEED,0,0,0,0,true);//-0.5 for 1000 ms
        sleep(100);
        armDrive(0.5,-50);
//        sleep(100);
        servoClaw.setPosition(1);
//        sleep(100);
        davidDrive(DRIVE_SPEED,  6,  6, 6, 6,true);
//        sleep(100);
//        davidDrive(DRIVE_SPEED,  10,  -10, -10, 10,true);
//        //- to pos
//        sleep(100);
//        servoRotation.setPosition(rightRotate);
//        armDrive(0.5,-35);
//        sleep(100);
//
//        //zaHandoMovement(0.5,-200);
////h
//        //sleep(500);
//        //davidDrive(DRIVE_SPEED,  -8,  -8, -8, -8,0, true);
//        //sleep(250);
//        //sleep(200);
//
//        davidDrive(DRIVE_SPEED,-38,-38,-38,-38,true);
//        sleep(100);
//        servoClaw.setPosition(0.7);
//        sleep(300);
//        armDrive(0.5,20);
//        davidDrive(DRIVE_SPEED,30,30,30,30,true);
//        sleep(100);
//        davidDrive(DRIVE_SPEED,-15,15,15,-15,true);//60
//        sleep(100);
//        //davidDrive(DRIVE_SPEED,0,0,0,0,15,true);
//        armDrive(0.5,75);
//        sleep(200);
//        servoRotation.setPosition(leftRotate);
//        //armDrive(0.5,-25);
//        sleep(400);
//
//        davidDrive(DRIVE_SPEED,6,6,6,6,true);
//        sleep(100);
//        //davidDrive(DRIVE_SPEED,0,0,0,0,true);
//        //zaHandoMovement(0.5,-2);
//        sleep(200);
//        armDrive(0.5,-50);
//        sleep(250);
//        servoClaw.setPosition(1);
//        sleep(100);
//        davidDrive(DRIVE_SPEED,-6,-6,-6,-6,true);
//        sleep(100);
        davidDrive(DRIVE_SPEED,12,-12,-12,12,true);
        davidDrive(TURN_SPEED,-2,2,-2,2,true);
        if (Zone == 1) {
            davidDrive(1, 20, 20, 20, 20, true);
        }
        if (Zone == 2) {
            davidDrive(1, 0, 0, 0, 0, true);
        }
        if (Zone == 3) {
            davidDrive(1, -20, -20, -20, -20, true);
        }


        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);  // pause to display final telemetry message.
    }
    public void zaHandoMovement(double speed, double inches){
        int newTarget;

        if(opModeIsActive()){
            newTarget = motorArm.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);
            motorArm.setTargetPosition(newTarget);
            motorArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            motorArm.setPower(1);

            while (opModeIsActive() && motorArm.isBusy()){

            }
            motorArm.setPower(0);
            motorArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        }
    }
    public void armDrive(double speed,double inches){
        int newArmTarget;

        if(opModeIsActive()) {
            newArmTarget = motorArm.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            motorArm.setTargetPosition(newArmTarget);
            motorArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            runtime.reset();
            motorArm.setPower(0.6);
            while(opModeIsActive() && motorArm.isBusy()){
                telemetry.addData("Running to",  " %7d :", newArmTarget);
                telemetry.addData("Currently at",  " at %7d :", motorArm.getCurrentPosition());
                telemetry.update();
            }
            motorArm.setPower(0);

            motorArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            sleep(100);
            motorArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

    public void davidDrive(double speed,
                           double leftInches, double rightInches, double backLeftInches, double backRightInches, boolean strafe) {
        int newLeftTarget;
        int newRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;
        //int newArmTarget;

        /*
        int[] tArr = {newLeftTarget, newRightTarget, newBackLeftTarget, newBackRightTarget};


         */


        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftDrive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = rightDrive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newBackLeftTarget = backLeftDrive.getCurrentPosition() + (int)(backLeftInches * COUNTS_PER_INCH);
            newBackRightTarget = backRightDrive.getCurrentPosition() + (int)(backRightInches * COUNTS_PER_INCH);
            //newArmTarget = motorArm.getCurrentPosition() +(int)(armInches * COUNTS_PER_INCH);
            leftDrive.setTargetPosition(newLeftTarget);
            rightDrive.setTargetPosition(newRightTarget);
            backLeftDrive.setTargetPosition(newBackLeftTarget);
            backRightDrive.setTargetPosition(newBackRightTarget);
            //motorArm.setTargetPosition(newArmTarget);
            // Turn On RUN_TO_POSITION
            leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //motorArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            // reset the timeout time and start motion.
            runtime.reset();
            if(strafe) {
                leftDrive.setPower(speed);//Math.abs() might be needed
                rightDrive.setPower(speed);
                backLeftDrive.setPower(speed*1.05);//1.05
                backRightDrive.setPower(speed*1.05);//1.05
                //motorArm.setPower(-0.5);
            }
            else{
                leftDrive.setPower(speed);//Math.abs() might be needed
                rightDrive.setPower(speed);
                backLeftDrive.setPower(speed);
                backRightDrive.setPower(speed);
                //motorArm.setPower(-0.5);
            }
            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (leftDrive.isBusy() && rightDrive.isBusy() && backRightDrive.isBusy() && backLeftDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Running to",  " %7d :%7d :%7d :%7d", newLeftTarget,  newRightTarget, newBackLeftTarget, newBackRightTarget);
                telemetry.addData("Currently at",  " at %7d :%7d :%7d :%7d", leftDrive.getCurrentPosition(), rightDrive.getCurrentPosition(), backLeftDrive.getCurrentPosition(), backRightDrive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            backLeftDrive.setPower(0);
            backRightDrive.setPower(0);
            // Turn off RUN_TO_POSITION
            leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //motorArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            sleep(100);
            leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //motorArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        }
    }
}
