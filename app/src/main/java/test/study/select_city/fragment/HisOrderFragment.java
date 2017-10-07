package test.study.select_city.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import test.study.select_city.R;
import test.study.select_city.adapter.TiscketAdapter;
import test.study.select_city.bean.Ticket;

/**
 * Created by Mr.Chen on 2017/8/27.
 */
public class HisOrderFragment extends Fragment {
    private List<Ticket> ticketList = new ArrayList<Ticket>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_futorder, container,false);
        TiscketAdapter adapter = new TiscketAdapter(getActivity(), R.layout.ticket_order, ticketList);
        ListView listView = (ListView)view. findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

}
