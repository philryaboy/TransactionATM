package com.example.philip.transactionatm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends Activity {

    static final int max_amount_for_client = 1000;
    static int notes_200 = 3, notes_100 = 2, notes_50 = 4, notes_20 = 3, notes_10 = 49, notes_5 = 23, notes_1 = 10;

    static int given_notes_200 = 0, given_notes_100 = 0, given_notes_50 = 0, given_notes_20 = 0, given_notes_10 = 0,
            given_notes_5 = 0, given_notes_1 = 0;
    static int amount_in_dispenser = notes_200 * 200 + notes_100 * 100 + notes_50 * 50 + notes_20 * 20 +
            notes_10 * 10 + notes_5 * 5 + notes_1;
    static int money = 0;
    private static double currentBal = 5000;
    String moneyS = "";
    private EditText result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button balanceButton = (Button) findViewById(R.id.balance_button);
        Button depositButton = (Button) findViewById(R.id.deposit_button);
        Button withdrawButton = (Button) findViewById(R.id.withdraw_button);
        Button exitButton =  (Button) findViewById(R.id.exit_button);

        result = (EditText) findViewById(R.id.editTextResult);

        // Setup event handlers
        balanceButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                viewBalance();
            }
        });

        depositButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                //depositFounds();
                showInputDialogDeposit();
            }
        });

        withdrawButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                //withdrawFunds();
                showInputDialogWithdraw();
            }
        });

        exitButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                exitProgram();
            }
        });
    }

    protected void showInputDialogWithdraw() {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.amount);

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        moneyS = editText.getText().toString();
                        if (!moneyS.equals("")) {
                            money = Integer.parseInt(moneyS);
                            withdrawFunds();
                        }
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    protected void showInputDialogDeposit() {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.amount);

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        moneyS = editText.getText().toString();
                        if (!moneyS.equals("")) {
                            money = Integer.parseInt(moneyS);
                            depositFounds();
                        }
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void viewBalance() {
        DecimalFormat money = new DecimalFormat ("$#,##0.00");
        result.setText("The current balance is: $" + currentBal);
    }

    public void withdrawFunds() {
        int withdrawMoney = 0;
        withdrawMoney = money;

        if (withdrawMoney > max_amount_for_client) {
            result.setText("The amount to withdraw is more than allowed - " + max_amount_for_client);
            return;
        }

        if(currentBal >= withdrawMoney){
            currentBal -= withdrawMoney;
            result.setText("Amount to withdraw: $" + withdrawMoney + "\nThe balance is: $" + currentBal +
                    "\nPlease, take your cash");
            dispenser(withdrawMoney);
        }
        else{
            result.setText("Not enough money on your account");
        }
    }

    public void depositFounds() {
        int addMoney = 0;
        addMoney = money;
        currentBal += addMoney;
        result.setText("Amount to deposit: $" + addMoney + "\nThe balance is: $" + currentBal);
    }

    public void exitProgram() {
        System.exit(0);
    }


    public void dispenser(int amount) {

        int calcResult = 0;

        if(amount <= max_amount_for_client && amount <= amount_in_dispenser){
            if(amount == 200){
                given_notes_200 = 1;
                notes_200 -= 1;
                // startDispenser();
                return;
            }

            if(amount > 200){
                calcResult = amount / 200;
                amount  = amount%200;
                given_notes_200 = calcResult;
                notes_200 -= calcResult;
            }

            if(amount == 100){
                given_notes_100 = 1;
                notes_100 -= 1;
                // startDispenser();
                return;
            }

            if(amount > 100){
                calcResult = amount / 100;
                amount  = amount%100;
                given_notes_100 = calcResult;
                notes_100 -= calcResult;
            }

            if(amount == 50){
                given_notes_50 = 1;
                notes_50 -= 1;
                // startDispenser();
                return;
            }

            if(amount > 50){
                calcResult = amount / 50;
                amount  = amount%50;
                given_notes_50 = calcResult;
                notes_50 -= calcResult;
            }

            if(amount == 20){
                given_notes_20 = 1;
                notes_20 -= 1;
                // startDispenser();
                return;
            }

            if(amount > 20){
                calcResult = amount / 20;
                amount  = amount%20;
                given_notes_20 = calcResult;
                notes_20 -= calcResult;
            }

            if(amount == 10){
                given_notes_10 = 1;
                notes_10 -= 1;
                // startDispenser();
                return;
            }

            if(amount > 10){
                calcResult = amount / 10;
                amount  = amount%10;
                given_notes_10 = calcResult;
                notes_10 -= calcResult;
            }

            if(amount == 5){
                given_notes_5 = 1;
                notes_5 -= 1;
                // startDispenser();
                return;
            }

            if(amount > 5){
                calcResult = amount / 5;
                amount  = amount%5;
                given_notes_5 = calcResult;
                notes_5 -= calcResult;
            }

            if(amount == 1){
                given_notes_1 = 1;
                notes_1 -= 1;
                // startDispenser();
                return;
            }

            if(amount > 1){
                calcResult = amount / 1;
                amount  = amount%1;
                given_notes_1 = calcResult;
                notes_1 -= calcResult;
                // startDispenser();
                return;
            }
        } else if (amount > amount_in_dispenser)
            result.setText("Not enough cash in dispenser");
    }
}

