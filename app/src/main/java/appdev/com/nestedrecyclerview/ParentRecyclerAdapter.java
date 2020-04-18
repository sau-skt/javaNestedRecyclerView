package appdev.com.nestedrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ParentRecyclerAdapter extends RecyclerView.Adapter<ParentRecyclerAdapter.MyViewHolder> {

    ArrayList<String> parentArrayList;
    Context context;
    ArrayList<String> daysArrayList = new ArrayList<>();

    public ParentRecyclerAdapter(ArrayList<String> parentArrayList, Context context) {
        this.parentArrayList = parentArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_rowlayout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.ItemName.setText(parentArrayList.get(position));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.ChildRV.setLayoutManager(layoutManager);
        holder.ChildRV.setHasFixedSize(true);
        daysArrayList.clear();
        if (parentArrayList.get(position).equals("Item One")){
            String[] days = {"Sat","Sun","Mon"};
            for (int i =0;i<days.length;i++){
                daysArrayList.add(days[i]);
            }
        }
        else {
            String[] days = {"Sat","Sun","Mon","Tue","Wed","Thu","Fri"};
            for (int i =0;i<days.length;i++){
                daysArrayList.add(days[i]);
            }
        }
        ChildRecyclerAdapter childRecyclerAdapter = new ChildRecyclerAdapter(daysArrayList);
        holder.ChildRV.setAdapter(childRecyclerAdapter);
        childRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return parentArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ItemName;
        RecyclerView ChildRV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemName = itemView.findViewById(R.id.itemname);
            ChildRV = itemView.findViewById(R.id.childRV);
        }
    }
}
