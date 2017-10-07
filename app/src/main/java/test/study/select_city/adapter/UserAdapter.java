package test.study.select_city.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.study.select_city.R;
import test.study.select_city.bean.Mycase;

/**
 * Created by Mr.Chen on 2017/8/26.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<Mycase> mUserList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View userView;
        ImageView userImage;
        TextView userName;

        public ViewHolder(View view){
            super(view);
            userView=view;
            userImage=(ImageView)view.findViewById(R.id.edit);
            userName=(TextView)view.findViewById(R.id.user_name);
        }
    }
    public UserAdapter(List<Mycase> orderList){
        mUserList=orderList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Mycase user=mUserList.get(position);
        viewHolder.userImage.setImageResource(user.getImageId());
        viewHolder.userName.setText(user.getName());
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return mUserList.size();
    }
}
