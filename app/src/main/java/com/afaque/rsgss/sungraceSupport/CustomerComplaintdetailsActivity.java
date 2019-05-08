package com.afaque.rsgss.sungraceSupport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.sungraceenergysolutionspvtltd.R;

public class CustomerComplaintdetailsActivity extends AppCompatActivity {

    Button registerbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_complaintdetails);

        registerbtn=findViewById(R.id.manually_reg_btn);

        Spinner dropdown = findViewById(R.id.complaint_type_spinner);
        String[] items = new String[]{"Select Complaint Type", "Sample text 1", "Sample Text 2","Sample Text 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ThankyouActivity.class);
                startActivity(intent);
            }
        });
    }

}
