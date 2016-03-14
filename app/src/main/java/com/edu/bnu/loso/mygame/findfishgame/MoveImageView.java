package com.edu.bnu.loso.mygame.findfishgame;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toolbar;

/**
 * Created by loso on 2016/3/1.
 */
public class MoveImageView extends ImageView {

    public MoveImageView(Context context) {
        super(context);
    }

    public MoveImageView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MoveImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void setLocation(int x, int y) {

        this.layout(x, y - this.getHeight(), x + this.getWidth(), y);


    }

}
