package com.expient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.expient.dialog.AlertDialogExit;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EulaActivity extends AppCompatActivity {

    private static final String TAG = "EULA_ACTIVITY";

    @Bind(R.id.btn_agree) Button _btnAgree;
    @Bind(R.id.btn_disagree) Button _btnDisagree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eula);
        ButterKnife.bind(this);

        _btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AGREE();
            }
        });

        _btnDisagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialogExit _exit=new AlertDialogExit();
                _exit.show(getFragmentManager(), "AlertDemo");
            }
        });
    }

    private void AGREE(){
        Intent i=new Intent(EulaActivity.this,SignupActivity.class);
        startActivity(i);

    }
}
