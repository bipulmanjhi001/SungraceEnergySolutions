package com.afaque.rsgss.sungraceSupport;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.afaque.rsgss.sungraceSupport.Modals.CrnLists;
import com.afaque.rsgss.sungraceSupport.apihandler.AppConstant;
import com.afaque.rsgss.sungraceSupport.apihandler.JsonObjectHandler;
import com.sungraceenergysolutionspvtltd.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class CrnListActivity extends AppCompatActivity {

    JsonObjectHandler handler;
    LinearLayout linlaHeaderProgress;
    public ArrayList<CrnLists> crnLists = new ArrayList<CrnLists>();
    public CrnListAdapter adapter;
    public ListView crnListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crn_list);

        crnListView = findViewById(R.id.crn_listView);
        linlaHeaderProgress = findViewById(R.id.linlaHeaderProgress);

        Intent intent = getIntent();
        String MobileNo = intent.getStringExtra("Mobile");

        adapter = new CrnListAdapter(CrnListActivity.this);
        crnListView.setAdapter(adapter);
        handler=new JsonObjectHandler();
        linlaHeaderProgress.setVisibility(View.VISIBLE);
        new GetNewJobList(MobileNo).execute();
    }

    public void get_data(String data)
    {
        try {
            JSONArray data_array=new JSONArray(data);

            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());

                CrnLists add=new CrnLists();
                add.Id = obj.getString("id");
                add.Crn = obj.getString("CRN_NO");
                add.Date = obj.getString("date");
                add.Technician = obj.getString("technician");
                crnLists.add(add);
            }
            adapter.notifyDataSetChanged();
            //adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public class GetNewJobList extends AsyncTask<Void, Void, JSONObject> {

        JSONObject jsonObject=null;

        String mobile;
        GetNewJobList(String MobileNo) {
            this.mobile=MobileNo;
        }

        @Override
        protected JSONObject doInBackground(Void... params) {

            try {
                HashMap<String,String> data=new HashMap<>();
                data.put("mobile",""+mobile);
                jsonObject=handler.makeHttpRequest(AppConstant.crnListByMobile, "POST", data,null);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return jsonObject;
        }

        @Override
        protected void onPostExecute(final JSONObject jsonobject) {
            linlaHeaderProgress.setVisibility(View.GONE);
            try
            {
                if(jsonObject!=null) {
                    JSONObject statusObject = jsonObject.optJSONObject("reqStatus");
                    if(statusObject.getBoolean("status")){
                        JSONArray resultObjectList=jsonObject.optJSONArray("Result");
                        if (!resultObjectList.equals(null)) {
                            get_data(resultObjectList.toString());
                        }
                    }
                }
            }catch(Exception e){

            }


        }


    }
}
