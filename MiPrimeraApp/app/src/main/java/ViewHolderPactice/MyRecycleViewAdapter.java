package ViewHolderPactice;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teaching.android.miprimeraapp.R;


public class MyRecycleViewAdapter extends RecyclerView.Adapter {
    private String[] dataSet;

    public MyRecycleViewAdapter(String[] dataSet){
        this.dataSet=dataSet;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_item, parent , false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        (( MyViewHolder)holder).bind(dataSet[position]);
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.length : 0;
    }
}
