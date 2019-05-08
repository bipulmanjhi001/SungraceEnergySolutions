package com.afaque.rsgss.sungraceSupport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.sungraceenergysolutionspvtltd.R;
import com.afaque.rsgss.sungraceSupport.apihandler.JsonObjectHandler;

public class ComplainstatusActivity extends AppCompatActivity {
    JsonObjectHandler handler;
    Button searchbtn,homebtn,exitbtn;
    LinearLayout linlaHeaderProgress;
    EditText crnNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complainstatus);
        linlaHeaderProgress =  findViewById(R.id.linlaHeaderProgress);
        crnNo=findViewById(R.id.crn_no);
        searchbtn=findViewById(R.id.search_btn);
        homebtn=findViewById(R.id.homebtn);
        exitbtn=findViewById(R.id.exitbtn);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SearchCRN = crnNo.getText().toString();
                linlaHeaderProgress.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), ComplainstatusCnfActivity.class);
                intent.putExtra("CRN", SearchCRN.toString());
                startActivity(intent);

            }
        });

        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
