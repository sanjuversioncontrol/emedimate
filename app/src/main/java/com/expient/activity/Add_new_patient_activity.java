package com.expient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.expient.adapter.PlacesAutoCompleteAdapter;
import com.expient.fragments.DatePickerFragment;

import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Add_new_patient_activity extends AppCompatActivity {

    @Bind(R.id.input_layout_loc)     TextInputLayout _locTIP;
    @Bind(R.id.input_layout_name)    TextInputLayout  _nameTIP;
    @Bind(R.id.input_layout_dob)     TextInputLayout  _dobTIP;
    @Bind(R.id.input_layout_mobile)  TextInputLayout  _mobileTIP;
    @Bind(R.id.input_layout_email)   TextInputLayout  _emailTIP;
    @Bind(R.id.input_layout_address) TextInputLayout  _addressTIP;

    @Bind(R.id.loc_edt)       EditText _locText;
    @Bind(R.id.input_name)    EditText _nameText;
    @Bind(R.id.input_dob)     EditText _dobText;
    @Bind(R.id.input_mobile)  EditText _mobileText;
    @Bind(R.id.input_email)   EditText _emailText;
    @Bind(R.id.input_address) EditText _addressText;

    @Bind(R.id.btn_signup) Button _submit;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dd_new_patient_activity);
        ButterKnife.bind(this);
        initToolBar();

        _locText.addTextChangedListener(new MyTextWatcher(_locText));
        _nameText.addTextChangedListener(new MyTextWatcher(_nameText));
        _dobText.addTextChangedListener(new MyTextWatcher(_dobText));
        _mobileText.addTextChangedListener(new MyTextWatcher(_mobileText));
        _emailText.addTextChangedListener(new MyTextWatcher(_emailText));
        _addressText.addTextChangedListener(new MyTextWatcher(_addressText));
        _submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        _dobText.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
});
        AutoCompleteTextView autocompleteView = (AutoCompleteTextView)findViewById(R.id.loc_edt);
        autocompleteView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.autocomplete_list_item));
        autocompleteView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get data associated with the specified position
                // in the list (AdapterView)
                String description = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.toolbarTitle);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_home) {
            Intent homeIntent = new Intent(this, HomeActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        return super.onOptionsItemSelected(item);

    }

    private void submitForm() {
        if (!validateLocation()) {
            return;
        }

        if (!validateName()) {
            return;
        }

        if (!validateDOB()) {
            return;
        }
        if (!validateMobile()) {
            return;
        }
        if (!validateEmail()) {
            return;
        }
        if (!validateAddress()) {
            return;
        }

        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
}


    private boolean validateLocation() {
        if (_locText.getText().toString().trim().isEmpty()) {
            _locTIP.setError(getString(R.string.err_msg_loc));
            requestFocus(_locText);
            return false;
        } else {
            _locTIP.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateName() {
        if (_nameText.getText().toString().trim().isEmpty()) {
            _nameTIP.setError(getString(R.string.err_msg_name));
            requestFocus(_nameText);
            return false;
        } else {
            _nameTIP.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateDOB() {
        if (_dobText.getText().toString().trim().isEmpty()) {
            _dobTIP.setError(getString(R.string.err_msg_dob));
            requestFocus(_dobText);
            return false;
        } else {
            _dobTIP.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validateEmail() {
        String email = _emailText.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            _emailTIP.setError(getString(R.string.err_msg_email));
            requestFocus(_emailText);
            return false;
        } else {
            _emailTIP.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateMobile() {

        String mobile = _mobileText.getText().toString().trim();
        if (mobile.isEmpty() || !isValidMobile(mobile)) {
            _mobileTIP.setError(getString(R.string.err_msg_mob));
            requestFocus(_mobileText);
            return false;
        } else {
            _mobileTIP.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateAddress() {
        if (_addressText.getText().toString().trim().isEmpty()) {
            _addressTIP.setError(getString(R.string.err_msg_address));
            requestFocus(_addressText);
            return false;
        } else {
            _addressTIP.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidMobile(String phone) {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone))
        {
            check = !(phone.length() < 10 || phone.length() > 10);
        }
        else
        {
            check=false;
        }
        return check;
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_dob:
                    validateDOB();
                    break;
                case R.id.input_mobile:
                 validateMobile();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.loc_edt:
                    validateLocation();
                    break;
                case R.id.input_address:
                    validateAddress();
                    break;
            }
        }
    }
}
