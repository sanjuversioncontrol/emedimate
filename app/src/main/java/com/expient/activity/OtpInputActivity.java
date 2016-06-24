package com.expient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OtpInputActivity extends AppCompatActivity {

    @Bind(R.id.btn_verify) Button _verify;

    Toolbar toolbar;
    String _otp;
    boolean _delete = false;
    EditText _otpedt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_input);
        ButterKnife.bind(this);

        initToolBar();
        _verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OtpInputActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });

        _otpedt=(EditText)findViewById(R.id.input_otp);
        _otp=_otpedt.getText().toString().trim();
        _otpedt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                _otp=_otpedt.getText().toString().trim();

                if (count>after){ _delete=true;}
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                StringBuilder sb = new StringBuilder(charSequence.toString());
                int replacePosition = _otpedt.getSelectionEnd();

                if (charSequence.length() != 6) { //where 6 is the character underline per text
                    if (!_delete) {
                        if (replacePosition < charSequence.length())
                            sb.deleteCharAt(replacePosition);
                    } else {
                        sb.insert(replacePosition, '_');
                    }

                    if (replacePosition < charSequence.length() || _delete) {
                        _otpedt.setText(sb.toString());
                        _otpedt.setSelection(replacePosition);
                    } else {
                        _otpedt.setText(_otp);
                        _otpedt.setSelection(replacePosition - 1);
                    }
                }
                _delete=false;

            }




            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.toolbarTitle);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_arrow);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(OtpInputActivity.this, "clicking the toolbar!", Toast.LENGTH_SHORT).show();
                    }
                }

        );
    }
}
