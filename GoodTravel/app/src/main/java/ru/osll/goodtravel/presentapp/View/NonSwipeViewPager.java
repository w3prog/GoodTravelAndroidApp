package ru.osll.goodtravel.presentapp.View;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by mycrfotkai on 25.11.16.
 */

public class NonSwipeViewPager extends ViewPager
{
    public NonSwipeViewPager(Context context)
    {
        super(context);
    }

    public NonSwipeViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        return false;
    }
}
