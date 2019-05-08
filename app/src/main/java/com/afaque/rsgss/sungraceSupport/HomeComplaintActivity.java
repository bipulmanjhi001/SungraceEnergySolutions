package com.afaque.rsgss.sungraceSupport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.sungraceenergysolutionspvtltd.R;

public class HomeComplaintActivity extends AppCompatActivity {
    Button manuallyRegbtn,searchbtn,complaintStatus,searchCRNbtn;
    EditText mobileNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_complaint);

        manuallyRegbtn=findViewById(R.id.manually_reg_btn);
        searchbtn=findViewById(R.id.search_btn);
        complaintStatus=findViewById(R.id.complaint_status);
        searchCRNbtn=findViewById(R.id.searchCRNbtn);
        mobileNo=findViewById(R.id.mobileNo_et);

        complaintStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ComplainstatusActivity.class);
                startActivity(intent);
            }
        });

        manuallyRegbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CustomerComplaintregisterActivity.class);
                startActivity(intent);
            }
        });
        searchCRNbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeComplaintActivity.this, CrnListActivity.class);
                intent.putExtra("Mobile", mobileNo.getText().toString());
                startActivity(intent);
                mobileNo.setText("");
            }
        });

    }
    public void onBackPressed() {
//        Toast.makeText(getApplicationContext(), "Request submitted successfully", Toast.LENGTH_LONG).show();
        moveTaskToBack(true);
        System.exit(0);
    }
}
