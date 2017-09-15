package com.app.davidwang.androidyishutansuo.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.davidwang.androidyishutansuo.R;
import com.app.davidwang.androidyishutansuo.widget.MyScrollView;
import com.nineoldandroids.view.ViewHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewActivity extends AppCompatActivity implements MyScrollView.OnScrollListener {

    @Bind(R.id.tv_show)
    TextView tvShow;
    @Bind(R.id.btn_scroll)
    Button btnScroll;
    @Bind(R.id.scroll)
    MyScrollView scroll;

    private WindowManager mWindowManager;
    private Button mFloatingButton;

    private int mLastX;
    private int mLastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);

        ViewGroup content = (ViewGroup) findViewById(android.R.id.content);

        try {
            TextView childAt0 = (TextView) (((ViewGroup) content.getChildAt(0)).getChildAt(0));

            Log.d("getChild", childAt0.getText().toString());
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        mFloatingButton = new Button(this);
        mFloatingButton.setText(" button");
        final WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT
                , WindowManager.LayoutParams.WRAP_CONTENT
                , 0, 0, PixelFormat.TRANSPARENT);
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 100;
        mLayoutParams.y = 300;

        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.addView(mFloatingButton, mLayoutParams);

        mFloatingButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int rawX = (int) event.getRawX();
                int rawY = (int) event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        int x = (int) event.getX();
                        int y = (int) event.getY();
                        mLayoutParams.x = rawX;
                        mLayoutParams.y = rawY;
                        mWindowManager.updateViewLayout(mFloatingButton, mLayoutParams);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        break;
                    }
                    default:
                        break;
                }

                return false;
            }
        });

        tvShow.post(new Runnable() {
            @Override
            public void run() {
                Log.d("zuobiao", "坐标：x " + tvShow.getX() + " y " + tvShow.getY()
                        + " translationX : " + tvShow.getTranslationX() + " translationY : " + tvShow.getTranslationY());
                Log.d("zuobiao", "位置：l " + tvShow.getLeft() + " t " + tvShow.getTop()
                        + " r " + tvShow.getRight() + " b " + tvShow.getBottom());
            }
        });

        btnScroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getRawX();
                int y = (int) event.getRawY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE: {
                        int deltaX = x - mLastX;
                        int deltaY = y - mLastY;

                        int translationX = (int) (ViewHelper.getTranslationX(v) + deltaX);
                        int translationY = (int) (ViewHelper.getTranslationY(v) + deltaY);

                        ViewHelper.setTranslationX(v, translationX);
                        ViewHelper.setTranslationY(v, translationY);

                        break;
                    }
                }

                mLastX = x;
                mLastY = y;

                return false;
            }
        });

        scroll.setOnScrollListener(this);
    }

    @Override
    public void onScroll(int scrollY) {
        Log.d("zuobiao", "坐标：x " + tvShow.getX() + " y " + tvShow.getY()
                + " translationX : " + tvShow.getTranslationX() + " translationY : " + tvShow.getTranslationY());
        Log.d("zuobiao", "位置：l " + tvShow.getLeft() + " t " + tvShow.getTop()
                + " r " + tvShow.getRight() + " b " + tvShow.getBottom());
    }

    @OnClick({R.id.tv_show, R.id.btn_scroll})
    void onClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_show: {
                ObjectAnimator oa = ObjectAnimator.ofFloat(tvShow, "translationX", 200);
                oa.setDuration(2000);
                oa.start();

                Toast.makeText(this, "show", Toast.LENGTH_SHORT).show();

                oa.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        Log.d("zuobiao", "坐标：x " + tvShow.getX() + " y " + tvShow.getY()
                                + " translationX : " + tvShow.getTranslationX() + " translationY : " + tvShow.getTranslationY());
                        Log.d("zuobiao", "位置：l " + tvShow.getLeft() + " t " + tvShow.getTop()
                                + " r " + tvShow.getRight() + " b " + tvShow.getBottom());
                    }
                });

                break;
            }
            case R.id.btn_scroll: {
                Toast.makeText(this, "scroll", Toast.LENGTH_SHORT).show();

                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        try {
            mWindowManager.removeView(mFloatingButton);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        super.onDestroy();
    }
}
