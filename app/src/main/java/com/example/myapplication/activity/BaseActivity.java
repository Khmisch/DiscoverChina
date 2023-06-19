package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import com.example.myapplication.R;


/**
 * BaseActivity is parent for all Activities
 */
public class BaseActivity extends AppCompatActivity {
    private Dialog progressDialog;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    public void showLoading(Activity activity) {
        if (activity == null) return;

        if (progressDialog != null && progressDialog.isShowing()) {
//            progressDialog.dismiss();
        } else {
            progressDialog = new Dialog(activity, R.style.CustomDialog);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.custom_progress_dialog);
            ImageView iv_progress = progressDialog.findViewById(R.id.iv_progress);
            AnimationDrawable animationDrawable = (AnimationDrawable) iv_progress.getDrawable();
            animationDrawable.start();
            if (!activity.isFinishing()) progressDialog.show();
        }
    }

    protected void dismissLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public void callMainActivity(Context context) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
