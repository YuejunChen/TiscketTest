package test.study.select_city.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.study.select_city.Activity.ListActivity;
import test.study.select_city.R;
import test.study.select_city.bean.Train_info;

/**
 * Created by Mr.Chen on 2017/8/27.
 */
public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder>{
    private List<Train_info> mTrainList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View trainView;
        TextView trainNumber,startStation,departureDate,startTime,endTime,endStation;
        public ViewHolder(View view){
            super(view);
            trainView=view;
            trainNumber=(TextView)view.findViewById(R.id.trainNumber);
            startStation=(TextView)view.findViewById(R.id.startStation);
            departureDate=(TextView)view.findViewById(R.id.departureTime);
            startTime=(TextView)view.findViewById(R.id.startTime);
            endTime=(TextView)view.findViewById(R.id.endTime);
            endStation=(TextView)view.findViewById(R.id.endStation);

        }
    }

    public TrainAdapter(List<Train_info> trainList){
        mTrainList =trainList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.info_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.trainView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Train_info train = mTrainList.get(position);
                Intent intent=new Intent(v.getContext(),ListActivity.class);
                intent.putExtra("train_data",train);
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Train_info train= mTrainList.get(position);
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return mTrainList.size();
    }
}
