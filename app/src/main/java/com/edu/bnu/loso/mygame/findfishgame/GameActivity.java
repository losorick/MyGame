package com.edu.bnu.loso.mygame.findfishgame;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.edu.bnu.loso.mygame.R;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    final int gameTop = 140;
    final int gameLeft = 10;
    final int gameHeight = 400;
    final int gameWidth = 300;
    final int fish_file_count = 4;
    int fish_time_count = 5;
    TextView fish_time;
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            // 需要做的事:发送消息
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (fish_time_count<10) {
                    fish_time.setText("时间：00:0" + Integer.toString(--fish_time_count));
                    if(fish_time_count<=0) {
                        timer.cancel();
                        Intent intent = new Intent();                         //这是里计数结束
                        intent.setClass(GameActivity.this, showResultActivity.class);
                        intent.putExtra("score",score);
                        startActivity(intent);
                    }
                }
                else
                    fish_time.setText("时间：00:" + Integer.toString(--fish_time_count));
            }
            super.handleMessage(msg);
        };
    };

    int left,top;
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    int t = 0;
    int direction;      //鱼群方向
    int score;          //分数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        fish_time = (TextView) findViewById(R.id.text_fish_time);
        timer.schedule(task, 0, 1000);
        final Button button = (Button) findViewById(R.id.buttonfishpause);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent();                         //这是里计数结束
                intent.setClass(GameActivity.this, pauseActivity.class);
                startActivity(intent);
            }
        });
    }

    private void newGame(){

        MoveImageView fish = (MoveImageView)findViewById(R.id.image_fish);
        left = (int)(Math.random()*gameWidth);
        top = (int)(Math.random()* gameHeight);
        direction = (int)(Math.random()*4);

        int fish_file_num = (int)(Math.random() * fish_file_count);
        int fish_file = getResources().getIdentifier("fish_" + fish_file_num,"drawable","com.edu.bnu.loso.mygame");
        fish.setImageDrawable(getResources().getDrawable(fish_file));
        //FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(left + gameLeft, top + gameTop);
        //fish.setLayoutParams(params);

        //fish.setLocation(left + gameLeft, top + gameTop);
        RelativeLayout.LayoutParams params;
        params = (RelativeLayout.LayoutParams)fish.getLayoutParams();
        params.leftMargin = left + gameLeft;
        params.topMargin = top + gameTop;
        fish.setLayoutParams(params);


        fish.setRotation(direction * 90);

        Animation fish_animation= AnimationUtils.loadAnimation(this, R.anim.fish_start_anim);
        fish.startAnimation(fish_animation);
        fish_animation.setAnimationListener(new Animation.AnimationListener() {
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

    }

    private void showResult(int d){
        MoveImageView result = (MoveImageView)findViewById(R.id.image_result);


        if (d == direction){
            result.setImageDrawable(getResources().getDrawable(R.drawable.yes));
            score += 100;
        }
        else{
            result.setImageDrawable(getResources().getDrawable(R.drawable.no));
            score += 0;
        }


        RelativeLayout.LayoutParams params;
        params = (RelativeLayout.LayoutParams)result.getLayoutParams();
        params.leftMargin = left + gameLeft;
        params.topMargin = top + gameTop;
        result.setLayoutParams(params);

        TextView ShowScore = (TextView)findViewById(R.id.text_fish_score);
        ShowScore.setText("分：" + score);

        result.setVisibility(View.VISIBLE);
        //播放判断结果的动画
        Animation result_animation= AnimationUtils.loadAnimation(this, R.anim.result_anim);
        result.startAnimation(result_animation);
        result_animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }   //在动画开始时使用

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }  //在动画重复时使用

            @Override
            public void onAnimationEnd(Animation arg0) {
                MoveImageView result = (MoveImageView)findViewById(R.id.image_result);
                result.setVisibility(View.INVISIBLE);

            }
        });

        //播放鱼群等待的画面
        Animation fish_animation= AnimationUtils.loadAnimation(this, R.anim.fish_anim);
        MoveImageView fish = (MoveImageView)findViewById(R.id.image_fish);
        fish.startAnimation(fish_animation);
        fish_animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }   //在动画开始时使用

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }  //在动画重复时使用

            @Override
            public void onAnimationEnd(Animation arg0) {
                newGame();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //继承了Activity的onTouchEvent方法，直接监听点击事件

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            x2 = event.getX();
            y2 = event.getY();
            if( Math.abs(y1 - y2) > 50 || Math.abs(x1 - x2) > 50) {
                if( Math.abs(y1 - y2) > Math.abs(x1 - x2)){
                    if(y1 > y2){
                        //Toast.makeText(GameActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
                        showResult(3);
                    }
                    else{
                        //Toast.makeText(GameActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
                        showResult(1);
                    }
                }
                else{
                    if(x1 > x2){
                        //Toast.makeText(GameActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
                        showResult(2);
                    }
                    else{
                        //Toast.makeText(GameActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
                        showResult(0);
                    }
                }

            }

        }
        return super.onTouchEvent(event);
    }
}
