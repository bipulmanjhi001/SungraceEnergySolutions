package com.afaque.rsgss.sungraceSupport;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afaque.rsgss.sungraceSupport.apihandler.AppConstant;
import com.afaque.rsgss.sungraceSupport.apihandler.JsonObjectHandler;
import com.sungraceenergysolutionspvtltd.R;

import org.json.JSONObject;

import java.util.HashMap;

public class ComplainstatusCnfActivity extends AppCompatActivity {

    TextView nameAssign,contactNo,mStatus,Msg_first,Status_text,newtext_status,m_crn;
    String CRNno;
    Button homebtn,exitbtn,backbtn;
    JsonObjectHandler handler;
    LinearLayout linlaHeaderProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complainstatus_cnf);
        linlaHeaderProgress =  findViewById(R.id.linlaHeaderProgress);

        Intent intent = getIntent();
        CRNno = intent.getStringExtra("CRN");

        new GetTechnicianDetailsByCRN(CRNno).execute();

        nameAssign=findViewById(R.id.name_assign);
        contactNo=findViewById(R.id.contact_no);
        mStatus=findViewById(R.id.status);
        Msg_first=findViewById(R.id.msg_first);
        Status_text=findViewById(R.id.status_text);
        homebtn=findViewById(R.id.homebtn);
        exitbtn=findViewById(R.id.exitbtn);
        newtext_status=findViewById(R.id.newtext_status);
        backbtn=findViewById(R.id.back_btn);
        m_crn=findViewById(R.id._crn);

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
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ComplainstatusActivity.class);
                startActivity(intent);
            }
        });


    }


        @Override
        public void onBackPressed() {
            super.onBackPressed();

        }


    public class GetTechnicianDetailsByCRN extends AsyncTask<Void,Void,JSONObject> {
        JSONObject jsonObject = null;
        String crnNo;
        GetTechnicianDetailsByCRN(String CRNno){
            this.crnNo=CRNno;
        }
        @Override
        protected JSONObject doInBackground(Void...voids){
            handler = new JsonObjectHandler();
            try {
                HashMap<String,String> data=new HashMap<>();
                data.put("CRN_NO",""+crnNo);
                jsonObject=handler.makeHttpRequest(AppConstant.complaintDetailsByCRNNo,"POST",data,null);

            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return jsonObject;
        }
        @Override
        protected void onPostExecute(final JSONObject jsonObject){
            linlaHeaderProgress.setVisibility(View.GONE);
            try {
                if (jsonObject!=null){
                    JSONObject statusObject=jsonObject.optJSONObject("reqStatus");
                    String Status=statusObject.getString("status");
                    String Message=statusObject.getString("message");
                    if (statusObject.getBoolean("status")){
                        JSONObject resultObject=jsonObject.optJSONObject("Result");
                        String TechnicianName = resultObject.getString("technician");
                        String MobileNo = resultObject.getString("mobile");
                        String CRNNo = resultObject.getString("CRN_NO");
                        String ComplaintStatus = resultObject.getString("status");
                        String ActiveStatus = resultObject.getString("isactive");

                        if(ComplaintStatus.equals("")||ComplaintStatus.equals(null)){
                            nameAssign.setVisibility(View.GONE);
                            contactNo.setVisibility(View.GONE);
                            mStatus.setVisibility(View.GONE);
                            newtext_status.setText("Complaint not found");
                            newtext_status.setVisibility(View.VISIBLE);
                            Status_text.setVisibility(View.GONE);
                        }else {
                            if (ComplaintStatus.equals("New")){
                                nameAssign.setVisibility(View.GONE);
                                contactNo.setVisibility(View.GONE);
                                mStatus.setVisibility(View.GONE);
                                newtext_status.setText("Your complaint is under Process");
                                newtext_status.setVisibility(View.VISIBLE);
                                Status_text.setVisibility(View.GONE);
                            }else if(ComplaintStatus.equals("Completed")) {
                                nameAssign.setVisibility(View.GONE);
                                contactNo.setVisibility(View.GONE);
                                mStatus.setVisibility(View.GONE);
                                newtext_status.setText("Your complaint is Completed further any Enquiry please call to Customer Support");
                                newtext_status.setVisibility(View.VISIBLE);
                                Status_text.setVisibility(View.GONE);
                            }else {
                                mStatus.setText(ComplaintStatus);
                                mStatus.setVisibility(View.VISIBLE);
                                nameAssign.setText(TechnicianName);
                                nameAssign.setVisibility(View.VISIBLE);
                                contactNo.setText(MobileNo);
                                contactNo.setVisibility(View.VISIBLE);
                                Msg_first.setVisibility(View.VISIBLE);
                                Status_text.setVisibility(View.VISIBLE);
                            }
                        }

                        if(ActiveStatus.equals("DeActive")){
                            nameAssign.setVisibility(View.GONE);
                            contactNo.setVisibility(View.GONE);
                            mStatus.setVisibility(View.GONE);
                            m_crn.setText(CRNNo);
                            m_crn.setVisibility(View.VISIBLE);
                            newtext_status.setText("DeActivated  Further any Assistance please call Customer support");
                            newtext_status.setVisibility(View.VISIBLE);
                            Status_text.setVisibility(View.GONE);
                        }


                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ComplainstatusCnfActivity.this);
                        builder.setMessage(Message)
                                .setTitle("Warning")
                                .setIcon(R.drawable.ic_warning)
                                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ComplainstatusCnfActivity.this);
                    builder.setMessage("CRN NO. not found please try after some time")
                            .setTitle("Warning")
                            .setIcon(R.drawable.ic_warning)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
