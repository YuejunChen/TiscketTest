package test.study.select_city.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import test.study.select_city.R;
import test.study.select_city.bean.Ticket;

/**
 * Created by Mr.Chen on 2017/8/28.
 */
public class TiscketAdapter extends ArrayAdapter<Ticket> {

    private int resourceId;

    public TiscketAdapter(Context context, int textViewResourceId,
                        List<Ticket> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Ticket ticket = getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.trainNum = (TextView) view.findViewById (R.id.trainNumber);
            viewHolder.trainTime= (TextView) view.findViewById (R.id.trainTime);
            viewHolder.trainStation=(TextView)view.findViewById(R.id.trainStation);
            viewHolder.passenger=(TextView)view.findViewById(R.id.passenger);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.trainTime.setText(ticket.getTrainTime());
        viewHolder.trainNum.setText(ticket.getTrainPassenger());
        viewHolder.trainStation.setText(ticket.getTrainStation());
        viewHolder.passenger.setText(ticket.getTrainPassenger());
        return view;
    }

    class ViewHolder {
        TextView trainTime;
        TextView trainNum;
        TextView trainStation;
        TextView passenger;
    }

}

