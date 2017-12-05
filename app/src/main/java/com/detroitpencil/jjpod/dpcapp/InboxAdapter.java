package com.detroitpencil.jjpod.dpcapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jjpod on 12/5/2017.
 */

public class InboxAdapter extends BaseAdapter {

    ArrayList<InboxItem> inbox = new ArrayList<>();

    void addInboxItem(String companyName, String date){
        InboxItem inboxItem = new InboxItem(companyName, date);
        inbox.add(inboxItem);
    }

    @Override
    public int getCount() {
        return inbox.size();
    }

    @Override
    public Object getItem(int i) {
        return inbox.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int currentPosition = i;

        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            final RelativeLayout layout = (RelativeLayout)inflater.inflate(R.layout.inbox_item, null, false);
            TextView company = layout.findViewById(R.id.cName);
            TextView date = layout.findViewById(R.id.date);

            company.setText(inbox.get(i).getCompanyName());
            date.setText(inbox.get(i).getDate());

            return layout;
        }

        return view;
    }
}
