package test.study.select_city.fragment;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.study.select_city.Activity.CheckActivity;
import test.study.select_city.Activity.TrainInfoActivity;
import test.study.select_city.R;
import test.study.select_city.adapter.StationAdapter;
import test.study.select_city.bean.Location;
import test.study.select_city.utils.ListDataSave;
import test.study.select_city.utils.Okhttp;

/**
 * 首页碎片界面
 *
 *
 */
public class HomeFragment extends Fragment {
	//火车出发日期
	private EditText departureTime;
	//当前日期
	private long time;
	//火车查询按钮
	private Button checkButton;
	//始发站和终点站的输入框
	private TextView startStation,endStation;
	//始发站和终点站调转按钮
	private ImageView turnButton;
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private List<Location> locationList = new ArrayList<Location>();
	private Context mContext;
	private ListDataSave dataSave;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_home, container,
				false);
		departureTime=(EditText) view.findViewById(R.id.startData_label);
		startStation=(TextView) view.findViewById(R.id.startStation_label);
		endStation=(TextView) view.findViewById(R.id.arriveStation_label);
		mContext = getActivity().getApplicationContext();
		dataSave = new ListDataSave(mContext, "locateData");
		initLocate();
		final StationAdapter adapter = new StationAdapter(getActivity(), R.layout.station, locationList);
		ListView listView = (ListView)view. findViewById(R.id.list_view);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Location location=locationList.get(position);
				startStation.setText(location.getStart());
				endStation.setText(location.getEnd());
				editor.clear();
				editor.putString("startStation",location.getStart());
				editor.putString("endStation",location.getEnd());
				editor.commit();
			}
		});
		//持久化存储数据
		pref = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
		//获取SharedPreferences.Editor对象
		editor = pref.edit();
		startStation.setText(pref.getString("startStation","北京"));
		endStation.setText(pref.getString("endStation","上海"));
		turnButton=(ImageView)view.findViewById(R.id.turn);
		time = System.currentTimeMillis();
		Date date = new Date(time);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		departureTime.setText(format.format(date));
		departureTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {showDatePickDlg(departureTime);
			}
		});
		startStation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(),CheckActivity.class);
				startActivityForResult(intent,1);
			}
		});
		endStation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(),CheckActivity.class);
				startActivityForResult(intent,2);
			}
		});
		checkButton=(Button)view.findViewById(R.id.ticketSearch_button);
		checkButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String start=startStation.getText().toString();
				String end=endStation.getText().toString();
				String time=departureTime.getText().toString();
                setLocate(start,end);
				Okhttp okhttp=new Okhttp();
				okhttp.DemandWithPost(start, end, time, new Callback() {
					@Override
					public void onFailure(Call call, IOException e) {
						Toast.makeText(getActivity(),"url不存在",Toast.LENGTH_LONG);

					}
					@Override
					public void onResponse(Call call, Response response) throws IOException {
						Intent intent=new Intent(getActivity(),TrainInfoActivity.class);
						intent.putExtra("trainInfo",response.body().string());
						intent.putExtra("trainDate",departureTime.getText().toString());
						startActivity(intent);
					}
				});


			}
		});
		turnButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String start=startStation.getText().toString();
				String end=endStation.getText().toString();
				startStation.setText(end);
				endStation.setText(start);
			}
		});
		return view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch (requestCode)
		{
			case 1:
				if (resultCode == getActivity().RESULT_OK)
				{
					String returnedData = data.getStringExtra("cityName");
					startStation.setText(returnedData);
					editor.clear();
					editor.putString("startStation",returnedData);
					editor.commit();
				}
				break;
			case 2:
				if (resultCode == getActivity().RESULT_OK)
				{
					String returnedData = data.getStringExtra("cityName");
					endStation.setText(returnedData);
					editor.clear();
					editor.putString("endStation",returnedData);
					editor.commit();
				}
				break;
			default:
		}
	}
	private void setLocate(final String start,final String end){
		new Thread(new Runnable() {
			@Override
			public void run() {
				LinkedList<Location> locationList =dataSave.getDataList("locate");
				Location location=new Location();
				location.setStart(start);
				location.setEnd(end);
				if (!locationList.contains(location)){
					if (locationList.size()<3){
						locationList.addFirst(location);
					}
					else{
						locationList.removeLast();
						locationList.addFirst(location);
					}
				}
				else{
					locationList.remove(locationList.indexOf(location));
					locationList.addFirst(location);
				}
				dataSave.setDataList("locate", locationList);
			}
		}).start();
	}
	private void initLocate(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				    LinkedList<Location> list=dataSave.getDataList("locate");
					for(Location location:list){
							locationList.add(location);
						}
				}
		}).start();
	}
	public void showDatePickDlg(final EditText mEditText) {
		Calendar calendar=Calendar.getInstance();
		DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				int month=monthOfYear+1;
				mEditText.setText(year + "-" + month + "-" + dayOfMonth);
			}
		}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		DatePicker dp = datePickerDialog.getDatePicker();
		dp.setMinDate(calendar.getTime().getTime());
		datePickerDialog.show();
	}

}
