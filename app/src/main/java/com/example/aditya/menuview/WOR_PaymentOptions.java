package com.example.aditya.menuview;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

public class WOR_PaymentOptions extends AppCompatActivity {

    private TextView textViewPaymentDetailsText,textViewPaymentDetailsNumbers;
    private int bill, items, deliveryCharges, ExpressDeliveryCharges,total;
    private String billingText,billingNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wor__payment_options);
        setTitle("Payment Options");
        textViewPaymentDetailsText = (TextView) findViewById(R.id.wor_textViewPaymentDetailsText);
        textViewPaymentDetailsNumbers = (TextView) findViewById(R.id.wor_textViewPaymentDetailsNumbers);
        bill = getIntent().getIntExtra("bill",0);
        items = getIntent().getIntExtra("items",0);
        deliveryCharges = 0;
        billingText = "Payable Amount:<br><br>Ordered Items:<br><br>Delivery Charges:<br><br>";
        billingNumber = "Rs."+bill+"<br><br>"+ ""+items+"<br><br>"+ "₹ "+deliveryCharges+"<br><br>";

        total = bill + deliveryCharges;
        billingText=billingText.concat("<b><big><font color=\"#000000\">Total Bill:</font></b>");
        billingNumber = billingNumber.concat("<b><big><font color=\"#000000\">₹ "+total+"</font></b>");
        textViewPaymentDetailsText.setText(Html.fromHtml(billingText));
        textViewPaymentDetailsNumbers.setText(Html.fromHtml(billingNumber));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
