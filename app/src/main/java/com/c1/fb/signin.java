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
import com.google.firebase.auth.FirebaseUser;

public class signin extends AppCompatActivity {

    EditText email1;
    EditText password1;
    Button login;
    Button signup;
    boolean goback=true;
    FirebaseAuth mAuth;
    public static final String TAG = "false";
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(!goback){
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();
        signup= (Button) findViewById(R.id.signup);
        email1= (EditText) findViewById(R.id.et_email);
        login= (Button) findViewById(R.id.login);
        password1= (EditText) findViewById(R.id.et_pass);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    goback=false;
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signin.this, com.c1.fb.signup.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(email1.getText().toString(), password1.getText().toString())
                        .addOnCompleteListener(signin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                                startActivity(new Intent(signin.this,feed_main.class));

                                if (!task.isSuccessful()) {
                                    Log.d(TAG, "signInWithEmail:failed", task.getException());
                                    Toast.makeText(signin.this,R.string.auth_failed,
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

}
/*  if(auth.getCurrentUser()!=null)
        {
        startActivity(new Intent(signin.this,feed_main.class));
        finish();
        }
        setContentView(R.layout.activity_signin);
        email= (EditText) findViewById(R.id.et_email);
        pass= (EditText) findViewById(R.id.et_pass);

        signup= (Button) findViewById(R.id.signup);
        Forgetpass= (Button) findViewById(R.id.forget_pass);

        auth=FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        startActivity(new Intent(signin.this,signup.class));
        }
        });

        login.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        String email1=email.getText().toString();

final String password=pass.getText().toString();

        if(email.getText().toString().trim().equalsIgnoreCase(""))
        {
        Toast.makeText(signin.this, "enter the email!!", Toast.LENGTH_SHORT).show();
        email.setError("Enter the email");

        }
        if(TextUtils.isEmpty(password))
        {
        Toast.makeText(signin.this, "enter password!!", Toast.LENGTH_SHORT).show();
        }
        auth.signInWithEmailAndPassword(email1,password).addOnCompleteListener(signin.this, new OnCompleteListener<AuthResult>() {
@Override
public void onComplete(@NonNull Task<AuthResult> task) {
        if(!task.isSuccessful())
        {
        if(password.length()<6)
        {
        pass.setError("minimum length should be 6");
        }
        else
        {
        Toast.makeText(signin.this, "authentication failed", Toast.LENGTH_SHORT).show();
        }
        }
        else
        {
        startActivity(new Intent(signin.this,feed_main.class));
        finish();
        }
        }
        });
        }
        });

        }}*/