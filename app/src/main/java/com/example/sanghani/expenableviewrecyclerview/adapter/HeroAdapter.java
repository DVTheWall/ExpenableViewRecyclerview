package com.example.sanghani.expenableviewrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sanghani.expenableviewrecyclerview.R;
import com.example.sanghani.expenableviewrecyclerview.models.Hero;

import java.util.List;

/**
 * Created by peacock on 6/12/17.
 */

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder> {


    private static int currentPosition = 0;
    private List<Hero> heroList;
    private Context context;

    public HeroAdapter(List<Hero> heroList, Context context) {
        this.heroList = heroList;
        this.context = context;
    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_heroes, parent, false);
        return new HeroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final HeroViewHolder holder, final int position) {
        Hero hero = heroList.get(position);
        holder.textViewName.setText(hero.getName());
        holder.textViewRealName.setText(hero.getRealName());
        holder.textViewTeam.setText(hero.getTeam());
        holder.textViewFirstAppearance.setText(hero.getFirstAppearance());
        holder.textViewCreatedBy.setText(hero.getCreatedBy());
        holder.textViewPublisher.setText(hero.getPublisher());
        holder.textViewBio.setText(hero.getBio().trim());

        Glide.with(context).load(hero.getImageUrl()).into(holder.imageView);
        holder.linearLayout.setVisibility(View.GONE);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Position : " + position, Toast.LENGTH_SHORT).show();
            }
        });


        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.linearLayout.getTag().equals("0")) {
                    //creating an animation
                    Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.anim);

                    //toggling visibility
                    holder.linearLayout.setVisibility(View.VISIBLE);
                    holder.arrow.setImageResource(R.drawable.ic_up_arrow);

                    //adding sliding effect
                    holder.linearLayout.startAnimation(slideDown);

                    //  notifyDataSetChanged();

                    holder.linearLayout.setTag("1");
                } else {
                    Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.up);

                    //toggling visibility
                    holder.linearLayout.setVisibility(View.GONE);
                    holder.arrow.setImageResource(R.drawable.ic_downarrow);

                    //adding sliding effect
                    holder.linearLayout.startAnimation(slideDown);

                    holder.linearLayout.setTag("0");

                }


            }
        });
       /* //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {

            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.anim);

            //toggling visibility
            holder.linearLayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.linearLayout.startAnimation(slideDown);

        }

        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
               notifyDataSetChanged();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewRealName, textViewTeam, textViewFirstAppearance,
                textViewCreatedBy, textViewPublisher, textViewBio;
        ImageView imageView, arrow;
        LinearLayout linearLayout;

        HeroViewHolder(View itemView) {
            super(itemView);

            arrow = (ImageView) itemView.findViewById(R.id.arrow);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewRealName = (TextView) itemView.findViewById(R.id.textViewRealName);
            textViewTeam = (TextView) itemView.findViewById(R.id.textViewTeam);
            textViewFirstAppearance = (TextView) itemView.findViewById(R.id.textViewFirstAppearance);
            textViewCreatedBy = (TextView) itemView.findViewById(R.id.textViewCreatedBy);
            textViewPublisher = (TextView) itemView.findViewById(R.id.textViewPublisher);
            textViewBio = (TextView) itemView.findViewById(R.id.textViewBio);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}