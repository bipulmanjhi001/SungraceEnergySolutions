package com.afaque.rsgss.sungraceSupport;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.afaque.rsgss.sungraceSupport.apihandler.JsonObjectHandler;
import com.sungraceenergysolutionspvtltd.R;

public class ThankyouActivity extends AppCompatActivity {

    JsonObjectHandler handler;
    TextView crnNo;
    String CUID,CRN;
    private SharedPreferences sharedPreferencesCustomerDetails;
    Button homebtn,exitbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);
        crnNo=findViewById(R.id.complaint_no);
        homebtn=findViewById(R.id.homebtn);
        exitbtn=findViewById(R.id.exitbtn);

        sharedPreferencesCustomerDetails= getApplicationContext().getSharedPreferences("customerDetails", MODE_PRIVATE);
         CUID=sharedPreferencesCustomerDetails.getString("CUID","");
         CRN=sharedPreferencesCustomerDetails.getString("CRN","");

         crnNo.setText(CRN);
        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HomeComplaintActivity.class);
                startActivity(intent);
            }
        });

    }
}
