package com.example.gym_polyakov;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class SignUp extends AppCompatActivity {


    public Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText et_user = findViewById(R.id.login_signup);
        EditText et_mail = findViewById(R.id.mail_signup);
        EditText et_pass_first = findViewById(R.id.password_signup_first);
        EditText et_pass_second = findViewById(R.id.password_signup_second);
        Button back = findViewById(R.id.b_back_signup);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }



    @Override
    public void onBackPressed() {
        View v_user = findViewById(R.id.signup_view_username);
        View v_mail = findViewById(R.id.signup_view_email);
        View v_pass_first = findViewById(R.id.signup_view_password_first);
        View v_pass_second = findViewById(R.id.signup_view_password_second);
        Button b_signup = findViewById(R.id.b_signup_signup);
        ImageView imageView =findViewById(R.id.im_homegym_signup);
        TextView textView = findViewById(R.id.tv_homegym_signup);
        v_user.animate().alpha(0).translationYBy(100).setDuration(1000);
        v_pass_first.animate().alpha(0).translationYBy(40).setDuration(1000);
        v_mail.animate().alpha(0).setDuration(800);
        v_pass_second.animate().alpha(0).setDuration(800);
        imageView.animate().translationYBy(82).setDuration(1000);
        textView.animate().translationYBy(82).setDuration(1000);
        b_signup.animate().alpha(0).translationYBy(-100).setDuration(1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(context, SignIn.class));
                Animatoo.animateFade(context);
                finish();
            }
        },800);

    }
}
