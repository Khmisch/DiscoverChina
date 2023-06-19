package com.example.myapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

/**
 * BaseFragment is the parent for all Fragments
 */
public class BaseFragment extends Fragment {
    private AppCompatDialog progressDialog;

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissLoading();
    }


    public void showLoading(Activity activity) {
        if (activity == null) return;

        if (progressDialog != null && progressDialog.isShowing()) {
//            progressDialog.dismiss();
        } else {
            progressDialog = new AppCompatDialog(activity, R.style.CustomDialog);
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
}
