package ViewHolderPactice;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.teaching.android.miprimeraapp.R;

import java.util.Random;

public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView MyTextView;

    public MyViewHolder (View itemView){
        super(itemView);
        MyTextView = itemView.findViewById(R.id.text_view_view_holder);
    }

    public void bind (String value){
        MyTextView .setText(value);
        MyTextView.setBackgroundColor(Color.parseColor(value));
        MyTextView.setHeight(new Random().nextInt(400));
    }
}
