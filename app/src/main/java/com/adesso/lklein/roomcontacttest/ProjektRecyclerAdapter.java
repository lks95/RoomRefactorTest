package com.adesso.lklein.roomcontacttest;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adesso.lklein.roomcontacttest.models.Projekt;

import java.util.List;

public class ProjektRecyclerAdapter extends RecyclerView.Adapter<ProjektRecyclerAdapter.ViewHolder> {

    interface ActionCallback {
        void onLongClickListener(Projekt projekt);
    }

    private Context context;
    private List<Projekt> projektList;
    private int[] colors;
    private ActionCallback mActionCallback;

    ProjektRecyclerAdapter(Context context, List<Projekt> projektList, int[] colors){
        this.context = context;
        this.projektList = projektList;
        this.colors = colors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_recylcer_projekt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(position);
    }


    @Override
    public int getItemCount() {
        return projektList.size();
    }

    void updateData(List<Projekt> projekte){
        this.projektList = projekte;
        notifyDataSetChanged();
    }

    //ViewHolder Class
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        private TextView ProjektTextView;
        private TextView ArbeiterTextView;
        private GradientDrawable ArbeiterBackground;

        ViewHolder(View itemView){
            super(itemView);
            itemView.setOnLongClickListener(this);

            ArbeiterTextView = itemView.findViewById(R.id.initialsTextView);
            ProjektTextView = itemView.findViewById(R.id.nameTextView);
            ArbeiterBackground = (GradientDrawable) ArbeiterTextView.getBackground();
        }

        void bindData(int position){
            Projekt projekt = projektList.get(position);

            String ProjektArbeiter = projekt.getProjektname() + " " + projekt.getArbeitername();
            ProjektTextView.setText(ProjektArbeiter);

            String initial = projekt.getProjektname().toUpperCase().substring(0, 1);
            ArbeiterTextView.setText(initial);

            ArbeiterBackground.setColor(colors[position % colors.length]);
        }




        @Override
        public boolean onLongClick(View v) {
            if(mActionCallback != null){
                mActionCallback.onLongClickListener(projektList.get(getAdapterPosition()));
            }
            return true;
        }
    }

    void addActionCallback(ActionCallback actionCallback){
        mActionCallback = actionCallback;
    }

}
