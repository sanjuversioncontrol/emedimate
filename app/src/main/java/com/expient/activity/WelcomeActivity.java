package com.expient.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeActivity extends Activity implements Animation.AnimationListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WelcomeActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        String customFont = "Xerox Serif Wide Bold Italic.ttf";
        Typeface _typeface = Typeface.createFromAsset(getAssets(), customFont);
        TextView _textView = (TextView) findViewById(R.id.appname);
        _textView.setTypeface(_typeface);
        startAnimation();
    }

    private void startAnimation(){
        Animation _anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
        _anim.reset();
        LinearLayout _l=(LinearLayout) findViewById(R.id.lin_lay);
        _l.clearAnimation();
        _l.startAnimation(_anim);

        _anim = AnimationUtils.loadAnimation(this,R.anim.translate);
        _anim.reset();
        ImageView _img = (ImageView) findViewById(R.id.logo);
        _img.clearAnimation();
        _img.startAnimation(_anim);
        _anim.setAnimationListener(this);
    }
    @Override
    public void onAnimationStart(Animation animation) {

    }
    @Override
    public void onAnimationEnd(Animation animation) {

        finish();
        Intent i=new Intent(WelcomeActivity.this,EulaActivity.class);
        startActivity(i);
    }
    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
