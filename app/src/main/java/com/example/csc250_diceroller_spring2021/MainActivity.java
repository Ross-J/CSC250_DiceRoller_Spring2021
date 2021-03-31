package com.example.csc250_diceroller_spring2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView qtyTV;
    private TextView selectedDieTV;
    private TextView rollsTV;
    private TextView totalTV;

    private String currentQtyText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.qtyTV = this.findViewById(R.id.qtyTV);
        this.qtyTV.setText("");
        //telling the current activity to go and find the current Text View in the interface associated with that id name
        //and link it to this local variable so that things can be done with it
        this.selectedDieTV = this.findViewById(R.id.selectedDieTV);
        this.selectedDieTV.setText("");

        this.currentQtyText = "";

        this.rollsTV = this.findViewById(R.id.rollsTV);
        this.rollsTV.setText("");
        this.totalTV = this.findViewById(R.id.totalTV);
        this.totalTV.setText("");
    }


    private String extractNumberOfSides(String diceType)
    {
        //take something that looks like "D4" and returns "4"
        String answer = "";
        for(int i = 1; i < diceType.length(); i++)
        {
            answer += diceType.charAt(i);
        }
        return answer;
    }

    public void rollButtonPressed(View v)
    {
        //get the quantity as an int
        String qtyString = this.qtyTV.getText().toString();
        int qtyInt = Integer.parseInt(qtyString);

        //get the number of sides as an int
        String fullDiceString = this.selectedDieTV.getText().toString(); //like D4, D6. . .
        String trimmedDiceString = this.extractNumberOfSides(fullDiceString);
        //String trimmedDiceString = fullDiceString.substring(i);
        int numberOfSidesInt = Integer.parseInt(trimmedDiceString);

        Random r = new Random();

        int[] theRolls = new int[qtyInt];
        String individualRolls = "";
        int totalOfRolls = 0;

        for(int i = 0; i < qtyInt; i++)
        {
            theRolls[i] = r.nextInt(numberOfSidesInt) + 1;
            individualRolls += " + " +  theRolls[i];
            totalOfRolls += theRolls[i];
        }

        this.rollsTV.setText(individualRolls.substring(3));
        this.totalTV.setText(String.valueOf(totalOfRolls));


        //now we need to roll the die qtyInt number of times and store each roll in a different bucket of theRolls array
        //and set our text view on the interface for the individual rolls appropriately, as well as keep a running total and set that
        //text view appropriately
        //Finish Here


    }

    public void diceButtonPressed(View v)
    {
        this.selectedDieTV.setText(v.getTag().toString());
    }

    public void clearButtonPressed(View v)
    {
        this.currentQtyText = "";
        this.qtyTV.setText(this.currentQtyText);
        this.selectedDieTV.setText(this.currentQtyText);
        this.rollsTV.setText(this.currentQtyText);
        this.totalTV.setText(this.currentQtyText);
    }

    public void qtyButtonPressed(View v)
    {
        Button b = (Button)v;

        if(this.currentQtyText.length() == 0 && b.getText().equals("0"))
        {
            return;
        }

        if(this.currentQtyText.length() >= 4)
        {
            return;
        }

        this.currentQtyText += b.getText();
        this.qtyTV.setText(this.currentQtyText);
    }

}