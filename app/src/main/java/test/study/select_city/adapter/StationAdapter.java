package test.study.select_city.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import test.study.select_city.R;
import test.study.select_city.bean.Location;

/**
 * Created by Mr.Chen on 2017/9/5.
 */
public class StationAdapter extends ArrayAdapter<Location> {

    private int resourceId;

    public StationAdapter(Context context, int textViewResourceId,
                        List<Location> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Location location = getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.startStation = (TextView) view.findViewById (R.id.start);
            viewHolder.endStation = (TextView) view.findViewById (R.id.end);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.startStation.setText(location.getStart());
        viewHolder.endStation.setText(location.getEnd());
        return view;
    }

    class ViewHolder {
        TextView startStation;
        TextView endStation;
    }

}
