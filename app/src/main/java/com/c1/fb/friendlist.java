package com.c1.fb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class friendlist extends AppCompatActivity {

    RecyclerView rvfeed;
    String filename="myfile";
    ArrayList<friends> friendL;
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
                Intent i=new Intent(friendlist.this,feed_main.class);
                startActivity(i);
            }
        });

        btnFR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1= new Intent(friendlist.this,FR_main.class);
                startActivity(i1);
            }
        });
        btnfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4=new Intent(friendlist.this,friendlist.class);
                startActivity(i4);
            }
        });

        friendL=new ArrayList<>();
        friendL.add(new friends("Harsh","M","Agra"));
        friendL.add(new friends("Shubhankar","M","Panipat"));

        Intent i2=getIntent();
        readFromFile();
        /*String name=i2.getStringExtra("name");
        String gender=i2.getStringExtra("gender");
        String place=i2.getStringExtra("place");
        friendL.add(new friends(name,gender,place));*/
        
        rvfeed= (RecyclerView) findViewById(R.id.rvfeed2);
        friendadapter friendad=new friendadapter();
        rvfeed.setLayoutManager(new LinearLayoutManager(this));
        rvfeed.setAdapter(friendad);

        friendad.notifyDataSetChanged();


    }
    public void readFromFile()
    {
        File filesDir = getFilesDir();
        FileInputStream fInStr = null;
        File fileToRead = new File(filesDir, filename);
        try
        {
            fInStr = new FileInputStream(fileToRead);
            InputStreamReader iStrRd = new InputStreamReader(fInStr);
            BufferedReader bf=new BufferedReader(iStrRd);
            String str="";
            while ((str=bf.readLine())!=null)
            {
                SharedPreferences spref=getSharedPreferences(filename,MODE_PRIVATE);
                String name=spref.getString(str+"name","");
                String gender=spref.getString(str+"gender","");
                String place=spref.getString(str+"place","");
                friendL.add(new friends(name,gender,place));
            }
        } catch (IOException e) {
            e.printStackTrace();}
    }

    class Fholder extends RecyclerView.ViewHolder
    {
        TextView tvname;
        TextView tvgender;
        TextView tvplace;

        public Fholder(View itemView) {
            super(itemView);
            this.tvname= (TextView) itemView.findViewById(R.id.tvname);
            this.tvgender= (TextView) itemView.findViewById(R.id.tvgender);
            this.tvplace= (TextView) itemView.findViewById(R.id.tvplace);
        }
    }

    class friendadapter extends RecyclerView.Adapter<Fholder>
    {

        @Override
        public Fholder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = getLayoutInflater();
            View Itemview;
            Itemview = li.inflate(R.layout.friendlist2,parent,false);
            return new Fholder(Itemview);
        }

        @Override
        public void onBindViewHolder(Fholder holder, int position) {
            final friends student=friendL.get(position);

            holder.tvname.setText(student.getName().toString());
            holder.tvgender.setText(student.getGender().toString());
            holder.tvplace.setText(student.getPlace().toString());
        }

        @Override
        public int getItemCount() {
            return friendL.size();
        }
    }
}
