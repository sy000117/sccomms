package com.cometchat.pro.androiduikit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.uikit.ui_components.cometchat_ui.CometChatUI;
import com.cometchat.pro.uikit.ui_resources.utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;


public class SelectActivity extends AppCompatActivity {


    private MaterialButton logout;

    private MaterialButton unifiedLaunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        logout = findViewById(R.id.logout);
        unifiedLaunch = findViewById(R.id.directLaunch);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser(v);
            }
        });
        if (Utils.isDarkMode(this)) {
            logout.setBackgroundColor(getResources().getColor(R.color.darkModeBackground));
        } else {
            logout.setBackgroundColor(getResources().getColor(R.color.textColorWhite));
        }
        unifiedLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectActivity.this, CometChatUI.class));
                overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
            }
        });

    }

    private void logoutUser(View view) {
        CometChat.logout(new CometChat.CallbackListener<String>() {
            @Override
            public void onSuccess(String s) {
                startActivity(new Intent(SelectActivity.this,MainActivity.class));
            }

            @Override
            public void onError(CometChatException e) {
                Snackbar.make(view,"Login Error:"+e.getCode(),Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (CometChat.getLoggedInUser()==null)
        {
            startActivity(new Intent(SelectActivity.this,MainActivity.class));
        }
    }
}
