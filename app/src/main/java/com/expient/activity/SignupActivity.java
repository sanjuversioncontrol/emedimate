package com.expient.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.expient.parser.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.Bind;

public class SignupActivity extends AppCompatActivity {

    @Bind(R.id.input_name) EditText _nameText;
    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_mobile) EditText _inputmobile;
    @Bind(R.id.btn_signup) Button _signupButton;

    private static final String TAG = "SignupActivity";

    JSONParser _jparse=new JSONParser();
    JSONObject _json;
    private static String url_login = "http://10.0.2.2:8080/AndroidLogin/login_servlet";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        setHeader();
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
                hideKeyboard();
            }
        });


    }
    public  void setHeader(){

        ViewStub stub = (ViewStub) findViewById(R.id.vsHeader);
        View inflated = stub.inflate();

        TextView txtTitle = (TextView) inflated.findViewById(R.id.txtHeading);
        txtTitle.setText("eMedimate");

        String customFont = "Xerox Serif Wide Bold Italic.ttf";
        Typeface typeface = Typeface.createFromAsset(getAssets(), customFont);
        txtTitle.setTypeface(typeface);
    }

    public void signup() {

        Log.d(TAG, "Signup");
        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        String _namestring = _nameText.getText().toString();
        String _emailstring = _emailText.getText().toString();
        String _numberstring = _inputmobile.getText().toString();

        new LoginProcess().execute(_namestring,_emailstring,_numberstring);

    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Intent i=new Intent(SignupActivity.this,OtpInputActivity.class);
        startActivity(i);
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _inputmobile.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _inputmobile.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _inputmobile.setError(null);
        }

        return valid;
    }
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public  class LoginProcess extends AsyncTask<String,String,String>{
//        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this, R.style.AppTheme_Dark_Dialog);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog.setIndeterminate(true);
//            progressDialog.setMessage("Creating Account...");
//            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {


            _json = _jparse.makeHttpRequest(url_login, "GET", params);
            String s = null;
            try {
                s = _json.getString("info");


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            progressDialog.dismiss();
            if (s.equals("success")) {
                Intent login = new Intent(getApplicationContext(), SignupActivity.class);
                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login);
                finish();
            }
        }
    }
}