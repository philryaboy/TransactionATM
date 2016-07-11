package com.example.philip.transactionatm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import java.util.Scanner;
import java.text.DecimalFormat;

public class MainActivity extends Activity {

    //private EditText urlText;

    private static double currentBal = 54321;

    static int notes_200 = 3, notes_100 = 2, notes_50 = 4, notes_20 = 3, notes_10 = 49, notes_5 = 23, notes_1 = 10;

    static int given_notes_200 = 0, given_notes_100 = 0, given_notes_50 = 0, given_notes_20 = 0, given_notes_10 = 0,
            given_notes_5 = 0, given_notes_1 = 0;

    static final int max_amount_for_client = 1000;

    static int amount_in_dispenser = notes_200 * 200 + notes_100 * 100 + notes_50 * 50 + notes_20 * 20 +
            notes_10 * 10 + notes_5 * 5 + notes_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //urlText = (EditText) findViewById(R.id.url_field);

        Button balanceButton = (Button) findViewById(R.id.balance_button);
        Button depositButton = (Button) findViewById(R.id.deposit_button);
        Button withdrawButton = (Button) findViewById(R.id.withdraw_button);
        Button exitButton =  (Button) findViewById(R.id.exit_button);

        // Setup event handlers
        balanceButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                viewBalance();
            }
        });

        depositButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                depositFounds();
            }
        });

        withdrawButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                withdrawFunds();
            }
        });

        exitButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                exitProgram();
            }
        });
    }
    public static void viewBalance() {
        DecimalFormat money = new DecimalFormat ("$#,##0.00");
        System.out.println("\t-- The current balance is: $" + currentBal);
    }

    public static double withdrawFunds() {
        Scanner scanner = new Scanner(System.in);
        int withdrawMoney = 0;

        //while(!scanner.hasNextInt()){}
        //withdrawMoney = scanner.nextInt();

        withdrawMoney = 756;  //TEST

        System.out.println("Amount to withdraw: " + withdrawMoney);

        if(currentBal >= withdrawMoney){
            currentBal = currentBal - withdrawMoney;
            dispenser(withdrawMoney);
        }
        else{
            System.out.println("Not enough money on your account");
        }
        return currentBal;
    }

    public static double depositFounds() {
        Scanner scanner = new Scanner(System.in);
        int addMoney = 0;

        //while(!scanner.hasNextInt()){}
        //addMoney = scanner.nextInt();

        addMoney = 1500; // TEST

        System.out.println("Amount to deposit: " + addMoney);
        currentBal = currentBal + addMoney;
        return currentBal;
    }

    public static void exitProgram(){
        DecimalFormat money = new DecimalFormat ("$#,##0.00");
        System.out.println("The balance is: $" + currentBal);
        System.out.println("Bye! \n");
        System.exit(0);
    }


    public static void dispenser(int amount){
        int result = 0, remain = 0;
        if(amount <= max_amount_for_client && amount <= amount_in_dispenser){
            if(amount == 200){
                given_notes_200 = 1;
                notes_200 -= 1;
                giveCash();
                return;
            }

            if(amount > 200){
                result  = amount/200;
                amount  = amount%200;
                given_notes_200 = result;
                notes_200 -= result;
            }

            if(amount == 100){
                given_notes_100 = 1;
                notes_100 -= 1;
                giveCash();
                return;
            }

            if(amount > 100){
                result  = amount/100;
                amount  = amount%100;
                given_notes_100 = result;
                notes_100 -= result;
            }

            if(amount == 50){
                given_notes_50 = 1;
                notes_50 -= 1;
                giveCash();
                return;
            }

            if(amount > 50){
                result  = amount/50;
                amount  = amount%50;
                given_notes_50 = result;
                notes_50 -= result;
            }

            if(amount == 20){
                given_notes_20 = 1;
                notes_20 -= 1;
                giveCash();
                return;
            }

            if(amount > 20){
                result  = amount/20;
                amount  = amount%20;
                given_notes_20 = result;
                notes_20 -= result;
            }

            if(amount == 10){
                given_notes_10 = 1;
                notes_10 -= 1;
                giveCash();
                return;
            }

            if(amount > 10){
                result  = amount/10;
                amount  = amount%10;
                given_notes_10 = result;
                notes_10 -= result;
            }

            if(amount == 5){
                given_notes_5 = 1;
                notes_5 -= 1;
                giveCash();
                return;
            }

            if(amount > 5){
                result  = amount/5;
                amount  = amount%5;
                given_notes_5 = result;
                notes_5 -= result;
            }

            if(amount == 1){
                given_notes_1 = 1;
                notes_1 -= 1;
                giveCash();
                return;
            }

            if(amount > 1){
                result  = amount/1;
                amount  = amount%1;
                given_notes_1 = result;
                notes_1 -= result;
                giveCash();
                return;
            }
        }

        if(amount > amount_in_dispenser){
            System.out.println("Not enough cash in dispenser");
            return;
        }

        if(amount > max_amount_for_client){
            System.out.println("The amount is too big");
            return;
        }
    }

    public static void giveCash(){
        // startDispenser();
        System.out.println("Please take your cash.");
    }
}
