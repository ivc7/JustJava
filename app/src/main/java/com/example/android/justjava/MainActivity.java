package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        if(quantity==100){
            Toast.makeText(this,"You cannot have more than 100 coffess",Toast.LENGTH_SHORT).show();
            return;
        }

        quantity=quantity+1;
        display(quantity);
    }

    public void decrement(View view){
        if(quantity == 1){

            Toast.makeText(this,"You cannot have less than 1 coffess",Toast.LENGTH_SHORT).show();

            return;
        }

        quantity=quantity-1;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText text = (EditText) findViewById(R.id.name_field);
        String name = text.getText().toString();
        Log.v("MainActivity","Name: "+name);

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream=whippedCreamCheckBox.isChecked();
        Log.v("MainActivity","Has Whipped Cream: "+hasWhippedCream);

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        Log.v("MainActivity","Has Chocolate: "+hasChocolate);


        int price=calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage = createOrderSummary(price,hasWhippedCream,hasChocolate,name);
        //displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "ivancondori1@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for captain kunal");
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order
     * @param addWhippedCream is whether or not the user whipped cream topping
     * @param addChocolate is whether or not the user wants chocolate topping
     * @return total price
     *
     * */
    private int calculatePrice(boolean addWhippedCream,boolean addChocolate) {
        //Price of 1 cup of coffee
        int basePrice=5;

        //Add $1 if the user wants whipped cream
        if(addWhippedCream){
            basePrice = basePrice+1;
        }

        //Add $2 if the user wants chocolate
        if(addChocolate){
            basePrice = basePrice+2;
        }

        //Calculate the total order price by multiplying
        return quantity*basePrice;
    }


    /**
     * Create Summary of Order
     *
     * @param price of the order
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @return text summary
     * */
    private String createOrderSummary(int price,boolean addWhippedCream,boolean addChocolate,String name){
        String priceMessage = "Name: "+name;
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