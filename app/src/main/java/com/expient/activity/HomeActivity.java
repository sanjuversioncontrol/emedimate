package com.expient.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class HomeActivity extends DashBoardActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

    }

            public void onButtonClicker(View v)
       {
           Intent intent;

          switch (v.getId()) {
           case R.id.main_btn_view_patients:
                 intent = new Intent(this, View_patients_activity.class);
                  startActivity(intent);
                  break;

            case R.id.main_btn_add_patient:
                intent = new Intent(this, Add_new_patient_activity.class);
                startActivity(intent);
                    break;

              case R.id.main_btn_order:
                   intent = new Intent(this, Order_Medicine_First.class);
                 startActivity(intent);
                   break;

              case R.id.main_btn_purchase_history:
                   intent = new Intent(this, Medicine_Purchase_History.class);
                   startActivity(intent);
                  break;

        case R.id.main_btn_order_status:
             intent = new Intent(this, Medicine_Order_Status.class);
                 startActivity(intent);
                  break;

            case R.id.main_btn_track_order:
                   intent = new Intent(this, Track_Order.class);
                 startActivity(intent);
                  break;
            default:
                  break;
            }
           }


}
