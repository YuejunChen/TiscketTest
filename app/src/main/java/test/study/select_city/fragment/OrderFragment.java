package test.study.select_city.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import test.study.select_city.Activity.OrderActivity;
import test.study.select_city.R;
import test.study.select_city.adapter.OrderAdapter;
import test.study.select_city.bean.Order;

/**
 * 订单查询的碎片页面
 * @author wwj_748
 *
 */
public class OrderFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	private List<Order> orderList = new ArrayList<Order>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_order, container,
				false);
		initOrders();
		OrderAdapter adapter = new OrderAdapter(getActivity(), R.layout.order_item, orderList);
		ListView listView = (ListView)view. findViewById(R.id.list_view);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Order order=orderList.get(position);
				if(order.getImageId()==R.drawable.train_pic){
					ComponentName comp=new ComponentName(getActivity(),OrderActivity.class);
					Intent intent=new Intent();
					intent.setComponent(comp);
					startActivity(intent);
				}
			}
		});
		return view;
	}
	private void initOrders() {
			Order train = new Order("火车票订单", R.drawable.train_pic);
			orderList.add(train);
			Order plane = new Order("飞机票订单", R.drawable.plane_pic);
			orderList.add(plane);
			Order insurance = new Order("免费领取交通意外险", R.drawable.insurance_pic);
			orderList.add(insurance);

	}
}
