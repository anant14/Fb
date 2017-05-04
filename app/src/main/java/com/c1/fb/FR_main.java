package com.c1.fb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FR_main extends AppCompatActivity {

    String filename="myfile";
    friendrequest FAdapter=null;
    RecyclerView rvfeed;
    private static final String TAG="false";
    ArrayList<friends> FList;
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
                Intent i=new Intent(FR_main.this,feed_main.class);
                startActivity(i);
            }
        });

        btnFR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1= new Intent(FR_main.this,FR_main.class);
                startActivity(i1);
            }
        });
        btnfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4=new Intent(FR_main.this,friendlist.class);
                startActivity(i4);
            }
        });
        Intent i1=getIntent();
        FList=new ArrayList<>();
        FList.add(new friends("Anant","M","Agra"));
        FList.add(new friends("Aakarshak","M","Delhi"));
        FList.add(new friends("Parth","M","Delhi"));
        FList.add(new friends("Rakshita","F","Agra"));
        FList.add(new friends("Samiksha","F","Roorkee"));
        FList.add(new friends("Muskan","F","Agra"));

        rvfeed= (RecyclerView) findViewById(R.id.rvfeed2);
        FAdapter=new friendrequest() ;
        rvfeed.setLayoutManager(new LinearLayoutManager(this));
        rvfeed.setAdapter(FAdapter);


    }
    class FHolder extends RecyclerView.ViewHolder
    {
        TextView tvname;
        TextView tvgender;
        TextView tvplace;
        ImageView ivimg;
        Button btnacc;
        Button btnrej;
        public FHolder(View itemView) {
            super(itemView);
            this.tvname= (TextView) itemView.findViewById(R.id.tvname);
            this.tvgender= (TextView) itemView.findViewById(R.id.tvgender);
            this.tvplace= (TextView) itemView.findViewById(R.id.tvplace);
            this.ivimg= (ImageView) itemView.findViewById(R.id.ivimg);
            this.btnrej= (Button) itemView.findViewById(R.id.btnrej);
            this.btnacc= (Button) itemView.findViewById(R.id.btnacc);
        }
    }
    class friendrequest extends RecyclerView.Adapter<FHolder>
    {

        @Override
        public FHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li=getLayoutInflater();
            View itemtype;
            itemtype=li.inflate(R.layout.layout3,parent,false);

            return new FHolder(itemtype);
        }

        @Override
        public void onBindViewHolder(final FHolder holder, final int position) {
            final friends friends=FList.get(position);

            holder.tvname.setText(friends.getName());
            holder.tvgender.setText(friends.getGender());
            holder.tvplace.setText(friends.getPlace());

            holder.btnacc.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent i2=new Intent(FR_main.this,friendlist.class);

                    String name=holder.tvname.getText().toString();
                    String gender=holder.tvgender.getText().toString();
                    String place=holder.tvplace.getText().toString();

                  /*  i2.putExtra("name",name);
                    i2.putExtra("gender",gender);
                    i2.putExtra("place",place);*/

                    writetofile(filename,name,gender,place);

                    FList.remove(position);
                    FAdapter.notifyDataSetChanged();

                    startActivity(i2);
                }
            });

            holder.btnrej.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FList.remove(position);
                    FAdapter.notifyDataSetChanged();
                }
            });
                if(FList.get(position).getGender()=="M")
                {
                    holder.ivimg.setImageResource(R.drawable.ic_mood_black_24dp);
                }
            else
                    if(FList.get(position).getGender()=="F")
                    {
                        holder.ivimg.setImageResource(R.drawable.ic_face_black_24dp);
                    }
        }

        @Override
        public int getItemCount() {
            return FList.size();
        }
        void writetofile(String filename,String name,String gender,String place)
        {
            SharedPreferences spref=getSharedPreferences(filename,MODE_PRIVATE);
            SharedPreferences.Editor e1=spref.edit();
                e1.putString(name + "name", name);
                e1.putString(name + "gender", gender);
                e1.putString(name + "place", place);
                e1.apply();
                File file = new File(getFilesDir(), filename);
                try {
                    FileOutputStream fout = new FileOutputStream(file, true);
                    fout.write(name.getBytes());
                    fout.write('\n');
                } catch (IOException e) {
                    Log.d(TAG, "write to file", e);
                    e.printStackTrace();
                }
            }
        }

}
