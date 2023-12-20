package com.RogersCenter.readwritespell;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Animations {

    public static void keyboard_imgbutton_anim(Context context, ImageButton btn) {
        btn.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click));
    }

    public static void keyboard_button_anim(Context context,Button btn) {
        btn.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click));
    }

    public static void image_anim_last(Context context,ImageView img) {
        img.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click));
    }
    public static void image_anim(Context context,ImageView img) {
        img.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click));
    }
    public static void Textview_anim(Context context,TextView tv) {
        tv.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click));
    }

    public static void View_anim(Context context, View vv) {
        vv.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click));
    }

}
