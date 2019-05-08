package com.afaque.rsgss.sungraceSupport;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sungraceenergysolutionspvtltd.R;

public class CrnListAdapter extends BaseAdapter {
    CrnListActivity crnList;

    CrnListAdapter(CrnListActivity crnList)
    {
        this.crnList = crnList;
    }
    @Override
    public int getCount() {
        return  crnList.crnLists.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolderItem {
        TextView sId,sCrn,sCustomerName,sTechnician;
        RelativeLayout technician_list_layout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final CrnListAdapter.ViewHolderItem holder;
        if (convertView == null) {
            holder = new CrnListAdapter.ViewHolderItem();
            LayoutInflater inflater = (LayoutInflater) crnList.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.crn_list_cell, null);

            holder.sId = convertView.findViewById(R.id.id);
            holder.sCrn = convertView.findViewById(R.id.crn);
            holder.sTechnician=convertView.findViewById(R.id.technician);
            holder.technician_list_layout=convertView.findViewById(R.id.technician_list_layout);
            holder.technician_list_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(crnList.getApplicationContext(), ComplainstatusCnfActivity.class);
                    intent.putExtra("CRN", holder.sCrn.getText().toString());
                    crnList.getApplicationContext().startActivity(intent);

                }
            });
            convertView.setTag(holder);

        }
        else
        {
            holder = (CrnListAdapter.ViewHolderItem) convertView.getTag();
        }

        holder.sId.setText(this.crnList.crnLists.get(position).Id);
        holder.sCrn.setText(this.crnList.crnLists.get(position).Crn);
        holder.sTechnician.setText(this.crnList.crnLists.get(position).Technician);
        holder.technician_list_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(crnList.getApplicationContext(), ComplainstatusCnfActivity.class);
                intent.putExtra("CRN", holder.sCrn.getText().toString());
                crnList.startActivity(intent);
            }
        });

        return convertView;
    }
}
