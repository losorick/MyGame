package com.edu.bnu.loso.mygame.findfishgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout;

import com.edu.bnu.loso.mygame.R;

import org.w3c.dom.Text;

public class showResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);


        ImageView showResultImage = (ImageView)findViewById(R.id.imageShowResult);
        Animation show_result_animation= AnimationUtils.loadAnimation(this, R.anim.show_result_anim);
        showResultImage.startAnimation(show_result_animation);
        show_result_animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }   //在动画开始时使用

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }  //在动画重复时使用

            @Override
            public void onAnimationEnd(Animation arg0) {
            }
        });

        TextView showScoreView = (TextView)findViewById(R.id.text_Show_Result_View);
        Animation show_score_animation= AnimationUtils.loadAnimation(this, R.anim.show_score_anim);
        showScoreView.startAnimation(show_score_animation);
        show_score_animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                int score;
                Intent intent = getIntent();
                score = (int) intent.getSerializableExtra("score");

                TextView showResult = (TextView) findViewById(R.id.text_Show_Result_View);
                showResult.setText("你的成绩是：\n" + score);
            }   //在动画开始时使用

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }  //在动画重复时使用

            @Override
            public void onAnimationEnd(Animation arg0) {
            }
        });

    }
}
