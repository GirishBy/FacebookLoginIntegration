package com.mofosys.project.facebooklogin;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


/**
 * Created by girish on 20/12/16.
 */
public class LogoutActivity extends AppCompatActivity {

    ImageView user_picture;
    private String profileUrl = "https://graph.facebook.com/1196401663774089/picture?type=large";

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout_activtiy);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        TextView name = (TextView) findViewById(R.id.textView);
        TextView email = (TextView) findViewById(R.id.textView2);
        TextView gender = (TextView) findViewById(R.id.textView3);
        TextView fbId = (TextView) findViewById(R.id.textView4);
        Button logout = (Button) findViewById(R.id.logout_button);
        user_picture = (ImageView) findViewById(R.id.imageView);

        Intent in = getIntent();
        String uname = in.getStringExtra("NAME");
        String uemail = in.getStringExtra("EMAIL");
        String ugender = in.getStringExtra("GENDER");
        String uid = in.getStringExtra("ID");
        String pic = in.getStringExtra("PIC_URL");


        // loading header background image
        Glide.with(this).load(profileUrl)
                .crossFade()
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(user_picture);

        name.setText("Name : " + uname);
        email.setText("Email : " + uemail);
        gender.setText("Gender : " + ugender);
        fbId.setText("FB ID : " + uid);

        User user = PrefUtils.getCurrentUser(LogoutActivity.this);

        Log.i("EemailL: ", user.email);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefUtils.clearCurrentUser(LogoutActivity.this);
                Intent in = new Intent(LogoutActivity.this, MainActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
                finish();
            }
        });

    }

}
