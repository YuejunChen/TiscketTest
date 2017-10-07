package test.study.select_city.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import test.study.select_city.R;
import test.study.select_city.bean.Train_info;
import test.study.select_city.fragment.LoginFragment;

public class ListActivity extends AppCompatActivity {
    private TextView startStation,endStation,startTime,endTime,trainNumber,departureDate;
    private Button submit_order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        startStation=(TextView)findViewById(R.id.startStation);
        endStation=(TextView)findViewById(R.id.endStation);
        startTime=(TextView)findViewById(R.id.startTime);
        endTime=(TextView)findViewById(R.id.endTime);
        trainNumber=(TextView)findViewById(R.id.trainNumber);
        departureDate=(TextView)findViewById(R.id.departureTime);
        submit_order=(Button)findViewById(R.id.submit);
        Train_info trainOrder=(Train_info)getIntent().getSerializableExtra("trainInfo");
        startStation.setText(trainOrder.getStartStation());
        endStation.setText(trainOrder.getEndStation());
        startTime.setText(trainOrder.getDepartureTime());
        endTime.setText(trainOrder.getArriveTime());
        trainNumber.setText(trainOrder.getTrainNumber());
        departureDate.setText(trainOrder.getDepartureDate());
        submit_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref=getSharedPreferences("userdata",MODE_PRIVATE);
                boolean userState=pref.getBoolean("userstate",false);
                if(userState==false){
                    ComponentName componentName = new ComponentName(ListActivity.this, LoginFragment.class);
                    Intent intent=new Intent();
                    intent.putExtra("className", intent.getComponent().getClassName());
                    intent.setComponent(componentName);
                    startActivity(intent);
                }else{

                }
            }
        });
    }
}
