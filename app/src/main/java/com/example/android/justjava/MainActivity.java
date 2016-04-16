package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view){
        quantity=quantity+1;
        display(quantity);
    }

    public void decrement(View view){
        quantity=quantity-1;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream=whippedCreamCheckBox.isChecked();
        Log.v("MainActivity","Has Whipped Cream: "+hasWhippedCream);

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        Log.v("MainActivity","Has Chocolate: "+hasChocolate);


        int price=calculatePrice();
        String priceMessage = createOrderSummary(price,hasWhippedCream,hasChocolate);
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order
     * @return total price
     * */
    private int calculatePrice(){
        return quantity*5;
    }


    /**
     * Create Summary of Order
     *
     * @param price of the order
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @return text summary
     * */
    private String createOrderSummary(int price,boolean addWhippedCream,boolean addChocolate){
        String priceMessage = "Name: Lyla the Labyrinth";
        priceMessage+="\nAdd Whipped Cream?:"+addWhippedCream;
        priceMessage+="\nAdd Chocolate?:"+addChocolate;
        priceMessage+="\nQuanitty: "+quantity;
        priceMessage+="\nTotal: $"+price;
        priceMessage+="\nThank you!";

        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }


}