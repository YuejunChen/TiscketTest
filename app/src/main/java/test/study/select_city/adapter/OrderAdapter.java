package test.study.select_city.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.study.select_city.R;
import test.study.select_city.bean.Order;

/**
 * Created by Mr.Chen on 2017/8/27.
 */
public class OrderAdapter extends ArrayAdapter<Order> {

private int resourceId;

public OrderAdapter(Context context, int textViewResourceId,
                    List<Order> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {
        Order order = getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
        view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        viewHolder = new ViewHolder();
        viewHolder.orderImage = (ImageView) view.findViewById (R.id.order_image);
        viewHolder.orderName = (TextView) view.findViewById (R.id.order_name);
        view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
        view = convertView;
        viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.orderImage.setImageResource(order.getImageId());
        viewHolder.orderName.setText(order.getName());
        return view;
        }

class ViewHolder {
    ImageView orderImage;
    TextView orderName;
}

}

