package test.study.select_city.Activity;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import test.study.select_city.R;
import test.study.select_city.adapter.TrainAdapter;
import test.study.select_city.bean.Train_info;
import test.study.select_city.utils.Okhttp;

public class TrainInfoActivity extends AppCompatActivity {
    private List<Train_info> trainList = new ArrayList<>();
    private SharedPreferences pref;
    private TextView yesterday,tomorrow,today;
    private ImageView datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_info);
        pref=getSharedPreferences("data", Context.MODE_PRIVATE);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle(pref.getString("startStation","北京")+"-"+pref.getString("endStation","上海"));
        yesterday=(TextView)findViewById(R.id.yesterday);
        tomorrow=(TextView)findViewById(R.id.tomorrow);
        today=(TextView)findViewById(R.id.today);
        datePicker=(ImageView)findViewById(R.id.datePicker);
        Intent intent=getIntent();
        String train_info=intent.getStringExtra("trainInfo");
        String train_date=intent.getStringExtra("trainDate");
        today.setText(train_date);
        initDataWithJson(train_info);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_trainInfo);
        final SwipeRefreshLayout mRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.layout_swipe_refresh);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        TrainAdapter adapter=new TrainAdapter(trainList);
        recyclerView.setAdapter(adapter);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            public void onRefresh() {

                mRefreshLayout.setRefreshing(false);
            }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                DatePickerDialog datePickerDialog=new DatePickerDialog(TrainInfoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int month=monthOfYear+1;
                        today.setText(year + "-" + month + "-" + dayOfMonth);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                DatePicker dp = datePickerDialog.getDatePicker();
                dp.setMinDate(calendar.getTime().getTime());
                datePickerDialog.show();
            }
        });
        yesterday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    private void initDataWithJson(final String jsonData) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONArray jsonArray = new JSONArray(jsonData);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String trainNumber = jsonObject.getString("");
                        String departureDate=jsonObject.getString("");
                        String startStation = jsonObject.getString("");
                        String endStation = jsonObject.getString("");
                        String departureTime = jsonObject.getString("");
                        String arriveTime = jsonObject.getString("");
                        Train_info train = new Train_info(trainNumber,departureDate,
                                startStation, endStation, departureTime, arriveTime);
                        trainList.add(train);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    class DataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String...params) {
            String s = Okhttp.LoginWithPost(params[0],params[1]);
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                initDataWithJson(s);
                TrainAdapter adapter=new TrainAdapter(trainList);
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
