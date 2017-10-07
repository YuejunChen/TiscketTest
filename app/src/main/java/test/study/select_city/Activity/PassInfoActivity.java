package test.study.select_city.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import test.study.select_city.R;
import test.study.select_city.adapter.PassengerAdapter;
import test.study.select_city.bean.Passenger;


public class PassInfoActivity extends AppCompatActivity {
    private List<Passenger> passengerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_info);
        initPassData();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_passInfo);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        PassengerAdapter adapter=new PassengerAdapter(passengerList);
        recyclerView.setAdapter(adapter);
    }

    private void initPassData() {
        List<Passenger> passengers= DataSupport.findAll(Passenger.class);
        for(Passenger passenger:passengers){
            Passenger pass=new Passenger(passenger.getPassenger_name(),passenger.getPassenger_num());
            passengerList.add(pass);
        }
    }
}
