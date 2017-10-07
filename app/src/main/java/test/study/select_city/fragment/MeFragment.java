package test.study.select_city.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import test.study.select_city.R;
import test.study.select_city.adapter.UserAdapter;
import test.study.select_city.bean.Mycase;

/**
 * “我的”碎片页面
 * @author wwj_748
 *
 */
public class MeFragment extends Fragment {
	private List<Mycase> userList=new ArrayList<>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_myinfo, container,
				false);
		initUser();
		RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_userInfo);
		LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);
		UserAdapter adapter=new UserAdapter(userList);
		recyclerView.setAdapter(adapter);
		return view;
	}
	private  void initUser(){
			Mycase account=new Mycase("我的账号", R.drawable.account);
			userList.add(account);
			Mycase passenger=new Mycase("常用乘客",R.drawable.passenger);
			userList.add(passenger);
			Mycase service=new Mycase("联系客服",R.drawable.service);
			userList.add(service);
	}

}
