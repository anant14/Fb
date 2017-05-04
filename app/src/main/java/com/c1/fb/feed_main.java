package com.c1.fb;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by anant bansal on 1/26/2017.
 */

public class feed_main extends AppCompatActivity {

    RecyclerView rvfeed;
    ArrayList<feed> feedlist;
    Button btnfeeds;
    Button btnFR;
    Button btnfriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnfeeds= (Button) findViewById(R.id.btnfeed);
        btnFR= (Button) findViewById(R.id.btnFR);
        btnfriends= (Button) findViewById(R.id.btnfriends);

        btnfeeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(feed_main.this,feed_main.class);
                startActivity(i);
            }
        });

        btnFR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1= new Intent(feed_main.this,FR_main.class);
                startActivity(i1);
            }
        });
        btnfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4=new Intent(feed_main.this,friendlist.class);
                startActivity(i4);
            }
        });
        Intent i=getIntent();

        feedlist=new ArrayList<>();
        feedlist.add(new feed("Dhoni-Yuvraj,\n" +
                "Federer-Nadal.\n" +
                "Guess 2016 ended and 2012 started again.","0"));
        feedlist.add(new feed("Wohoooo! Its gonna be Roger vs Rafa! \uD83D\uDE0D\n" +
                "Well played Grigor Dimitrov. Brace yourselves, super sunday coming. #DreamFinal #AustralianOpen2017 #GoFedererGo","0"));
        feedlist.add(new feed("I want to ask for prayer for my great niece and her premature baby boy, I just got word that he might not make it through the day, " +
                "I am going to the hospital to provide moral support for my family members. Thank you","0"));
        feedlist.add(new feed("Note ban: PM Modi thinks of next generation, Rahul Gandhi looks at disrupting next Parliament session","0"));
        feedlist.add(new feed("Chhattisgarh police personnel responsible for rape and assault of 16 women: NHRC sends notice","0"));
        feedlist.add(new feed("Income Tax Detects Rs. 4807 Crore Black Money; Seizes New Notes Worth Rs. 112 Crore","0"));
        feedlist.add(new feed("Watch This Energetic Dog and His Owner Break a Jump Roping World RecordAfter Akhilesh Yadav claims Samajwadi Party MLAs' support, Amar Singh says signatures forged","0"));

        rvfeed=(RecyclerView)findViewById(R.id.rvfeed2);

        newsadapter stuAdapter=new newsadapter() ;
        rvfeed.setLayoutManager(new LinearLayoutManager(this));
        rvfeed.setAdapter(stuAdapter);
    }

    class feedholder extends RecyclerView.ViewHolder
    {
        TextView nfeed;
        Button btnadd;
        TextView tvinc;
        Button btnimage;
        ImageView imgv;

        public feedholder(View itemView) {
            super(itemView);
            this.nfeed= (TextView) itemView.findViewById(R.id.nfeed);
            this.tvinc= (TextView) itemView.findViewById(R.id.tvinc);
            this.btnadd= (Button) itemView.findViewById(R.id.incbtn);
            this.btnimage= (Button) itemView.findViewById(R.id.imgbtn);
            this.imgv= (ImageView) itemView.findViewById(R.id.Limage);
        }
    }

    class newsadapter extends RecyclerView.Adapter<feedholder>
    {
        int flag=1;
        @Override
        public int getItemViewType(int position) {
            if(feedlist.get(position).getFeed().length()>95)
                return 0;
            else
                return 1;
        }

        @Override
        public feedholder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = getLayoutInflater();

            View Itemview;
            switch(viewType)
            {
                case 1:
                    Itemview=li.inflate(R.layout.layout1,parent,false);
                    break;
                default:
                    Itemview=li.inflate(R.layout.layout2,parent,false);
                    break;
            }
            return new feedholder(Itemview);
        }

        @Override
        public void onBindViewHolder(final feedholder holder, int position) {
            final feed feed=feedlist.get(position);
            holder.nfeed.setText(feed.getFeed());
            holder.btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.tvinc.setText(String.valueOf(Integer.valueOf(feed.getUp())+1));
                    feed.setUp(String.valueOf(Integer.valueOf(feed.getUp())+1));
                }
            });
            holder.btnimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(flag==0)
                    {
                        holder.imgv.setImageResource(R.drawable.ic_check_black_24dp);
                        flag=1;
                    }
                    else
                    {
                        holder.imgv.setImageResource(R.drawable.ic_check_box_black_24dp);
                        flag=0;
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return feedlist.size();
        }
    }
}
