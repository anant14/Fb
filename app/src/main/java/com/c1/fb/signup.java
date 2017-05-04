package com.c1.fb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    EditText et_mail;
    EditText etpass;
    Button signup;
    FirebaseAuth MAuth;
    private FirebaseAuth.AuthStateListener mAuthlistner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        MAuth=FirebaseAuth.getInstance();
        et_mail= (EditText) findViewById(R.id.check_email);
        etpass= (EditText) findViewById(R.id.check_pass);
        signup= (Button) findViewById(R.id.check_signup);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task<AuthResult> authResultTask = MAuth.createUserWithEmailAndPassword(et_mail.getText().toString(), etpass.getText().toString())
                        .addOnCompleteListener(com.c1.fb.signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Log.d("check1", "createUserWithEmail:onComplete:" + task.isSuccessful());
                                    startActivity(new Intent(com.c1.fb.signup.this,signin.class));
                                }

                                if (!task.isSuccessful()) {
                                    Log.w("check1", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(signup.this, "Auth failed", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
}
  /*  String conpass=etconpass.getText().toString();
    String name=et_name.getText().toString();
    String age=et_age.getText().toString();
    String gender=et_gender.getText().toString();

Log.d(TAG,"email: "+email1);
        Log.d(TAG,"pass: "+pass);

        if(et_mail.getText().toString().trim().equals(""))
        {
        Toast.makeText(signup.this, "please enter email id", Toast.LENGTH_SHORT).show();
        et_mail.setError("please enter email id");
        Log.d(TAG,"email validation: ");
        }
        et_mail.addTextChangedListener(new TextWatcher() {
@Override
public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        et_mail.setError(null);
        }

@Override
public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

@Override
public void afterTextChanged(Editable s) {
        et_mail.setError(null);
        }
        });
        if(TextUtils.isEmpty(pass)||TextUtils.isEmpty(conpass))
        {
        Toast.makeText(signup.this, "please enter password", Toast.LENGTH_SHORT).show();
        }
        if(pass!=conpass)
        {
        Toast.makeText(signup.this, "enter the confirm password same as above", Toast.LENGTH_SHORT).show();
        }
        if(pass.length()<6)
        {
        Toast.makeText(signup.this, "enter password more than 6 characters", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(name))
        {
        Toast.makeText(signup.this, "please enter name", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(age))
        {
        Toast.makeText(signup.this, "please enter age", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(gender))
        {
        Toast.makeText(signup.this, "please enter gender", Toast.LENGTH_SHORT).show();
        }
        if(gender!="MALE"||gender!="FEMALE"||gender!="male"||gender!="Male"||gender!="female"||gender!="Female")
        {
        Toast.makeText(signup.this, "enter either male or female", Toast.LENGTH_SHORT).show();
        }*/