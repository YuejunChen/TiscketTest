package test.study.select_city.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import org.litepal.crud.DataSupport;

import java.util.List;

import test.study.select_city.R;
import test.study.select_city.bean.Passenger;

/**
 * Created by Mr.Chen on 2017/8/27.
 */
public class PassengerAdapter extends RecyclerView.Adapter<PassengerAdapter.ViewHolder> {
    private List<Passenger> mUserList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View userView;
        EditText passengerName;
        EditText passengerNum;
        ImageButton edit;
        ImageButton save;
        ImageButton delete;

        public ViewHolder(View view){
            super(view);
            userView=view;
            passengerName=(EditText) view.findViewById(R.id.userName);
            passengerNum=(EditText) view.findViewById(R.id.userNumber);
            edit=(ImageButton)view.findViewById(R.id.edit);
            save=(ImageButton)view.findViewById(R.id.save);
            delete=(ImageButton)view.findViewById(R.id.delete);
        }
    }
    public PassengerAdapter(List<Passenger> orderList){
        mUserList=orderList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                holder.passengerName.setFocusableInTouchMode(true);
                holder.passengerName.setFocusable(true);
                holder.passengerName.requestFocus();
                holder.passengerNum.setFocusableInTouchMode(true);
                holder.passengerNum.setFocusable(true);
                holder.passengerNum.requestFocus();
                holder.save.setVisibility(View.VISIBLE);
                holder.delete.setVisibility(View.VISIBLE);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position=holder.getAdapterPosition();
                Passenger passenger=mUserList.get(position);
                mUserList.remove(position);
                notifyItemChanged(position);
                notifyItemRangeChanged(0,mUserList.size());
                DataSupport.deleteAll(Passenger.class,"id=",""+passenger.getId());
            }
        });
        holder.save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position=holder.getAdapterPosition();
                Passenger passenger=mUserList.get(position);
                passenger.updateAll("id=?",""+passenger.getId());
                passenger.save();
                holder.save.setVisibility(View.INVISIBLE);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Passenger passenger=mUserList.get(position);
        viewHolder.passengerName.setText(passenger.getPassenger_name());
        viewHolder.passengerNum.setText(passenger.getPassenger_num());
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return mUserList.size();
    }
}
