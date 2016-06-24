package com.expient.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class View_patients_activity extends AppCompatActivity {

    private FloatingActionButton fab;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patients_activity);
        initToolBar();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(View_patients_activity.this, Add_new_patient_activity.class);
                startActivity(intent);
            }
        });

        TableLayout  tl = (TableLayout) findViewById(R.id.patient_details);


        for (int i = 0; i <20 ; i++) {


        TableRow  tr = new TableRow(this);

        TextView _idtxt=new TextView(this);
        TextView _nametxt=new TextView(this);
        TextView _locationtxt=new TextView(this);
        TextView _detailstxt=new TextView(this);
            _detailstxt.setTypeface(null, Typeface.BOLD_ITALIC);
            _detailstxt.setMovementMethod(LinkMovementMethod.getInstance());
        TextView _medicinestxt=new TextView(this);
            _medicinestxt.setTypeface(null, Typeface.BOLD_ITALIC);
            _medicinestxt.setMovementMethod(LinkMovementMethod.getInstance());
        TextView _recenttxt=new TextView(this);
            _recenttxt.setTypeface(null, Typeface.BOLD_ITALIC);
            _recenttxt.setMovementMethod(LinkMovementMethod.getInstance());


            TableRow.LayoutParams idParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            idParams.topMargin = 2;
            idParams.rightMargin = 2;

            TableRow.LayoutParams nameParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            nameParams.topMargin = 2;
            nameParams.rightMargin = 2;

            TableRow.LayoutParams locParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            locParams.topMargin = 2;
            locParams.rightMargin = 2;

            TableRow.LayoutParams detailsParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            detailsParams.topMargin = 2;
            detailsParams.rightMargin = 2;

            TableRow.LayoutParams medParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            medParams.topMargin = 2;
            medParams.rightMargin = 2;

            TableRow.LayoutParams recentParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            recentParams.topMargin = 2;
            recentParams.rightMargin = 2;


            _idtxt.setLayoutParams(idParams);
            _nametxt.setLayoutParams(nameParams);
            _locationtxt.setLayoutParams(locParams);
            _detailstxt.setLayoutParams(detailsParams);
            _medicinestxt.setLayoutParams(medParams);
            _recenttxt.setLayoutParams(recentParams);

            _idtxt.setTextColor(getResources().getColor(R.color.black));
            _nametxt.setTextColor(getResources().getColor(R.color.black));
            _locationtxt.setTextColor(getResources().getColor(R.color.black));
            _detailstxt.setTextColor(getResources().getColor(R.color.link_color));
            _medicinestxt.setTextColor(getResources().getColor(R.color.link_color));
            _recenttxt.setTextColor(getResources().getColor(R.color.link_color));

            _idtxt.setBackgroundColor(getResources().getColor(R.color.row_back));
            _nametxt.setBackgroundColor(getResources().getColor(R.color.row_back));
            _locationtxt.setBackgroundColor(getResources().getColor(R.color.row_back));
            _detailstxt.setBackgroundColor(getResources().getColor(R.color.row_back));
            _medicinestxt.setBackgroundColor(getResources().getColor(R.color.row_back));
            _recenttxt.setBackgroundColor(getResources().getColor(R.color.row_back));

            _idtxt.setPadding(20, 20, 20, 20);
            _nametxt.setPadding(20, 20, 20, 20);
            _locationtxt.setPadding(20, 20, 20, 20);
            _detailstxt.setPadding(20, 20, 20, 20);
            _medicinestxt.setPadding(20, 20, 20, 20);
            _recenttxt.setPadding(20, 20, 20, 20);

            SpannableString _contentdetails = new SpannableString("View");
            _contentdetails.setSpan(new UnderlineSpan(), 0, _contentdetails.length(), 0);
            SpannableString _contentmedicine = new SpannableString("Order Now");
            _contentmedicine.setSpan(new UnderlineSpan(), 0, _contentmedicine.length(), 0);
            SpannableString _contentrecents = new SpannableString("MVW85");
            _contentrecents.setSpan(new UnderlineSpan(), 0, _contentrecents.length(), 0);


            _idtxt.setText("AHDBSDSDF JB");
            _nametxt.setText("B");
            _locationtxt.setText("CSDFBFBBFW");
            _detailstxt.setText(_contentdetails);
            _medicinestxt.setText(_contentmedicine);
            _recenttxt.setText(_contentrecents);

            tr.addView(_idtxt);
            tr.addView(_nametxt);
            tr.addView(_locationtxt);
            tr.addView(_detailstxt);
            tr.addView(_medicinestxt);
            tr.addView(_recenttxt);



            tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {


        int id = menuItem.getItemId();
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
        return super.onOptionsItemSelected(menuItem);



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

}
