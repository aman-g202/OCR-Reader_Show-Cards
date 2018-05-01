package corporation.darkshadow.ocr.Adapters;

/**
 * Created by darkshadow on 28/4/18.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import corporation.darkshadow.ocr.Pojo.CardDetails;
import corporation.darkshadow.ocr.R;


/**
 * Created by darkshadow on 8/3/18.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private Context context;
    private List<CardDetails> cardsList;

    public CardAdapter(Context context, List<CardDetails> cardsList) {
        this.context = context;
        this.cardsList = cardsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namecard, emailcard, mobilecard;

        public MyViewHolder(View itemView) {
            super(itemView);
            namecard = (TextView)itemView.findViewById(R.id.namecard);
            emailcard = (TextView)itemView.findViewById(R.id.emailcard);
            mobilecard = (TextView)itemView.findViewById(R.id.mobilecard);
        }
    }

    @Override
    public CardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(CardAdapter.MyViewHolder holder, int position) {
        final CardDetails cardDetails = cardsList.get(position);
        holder.namecard.setText(cardDetails.getName());
        holder.emailcard.setText(cardDetails.getEmail());
        holder.mobilecard.setText(cardDetails.getPhone());

    }

    @Override
    public int getItemCount() {
        return cardsList.size();
    }
}

