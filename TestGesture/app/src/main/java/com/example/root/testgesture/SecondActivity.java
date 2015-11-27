package com.example.root.testgesture;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class SecondActivity extends Activity {
        private GestureDetector gestureDetector;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            gestureDetector = new GestureDetector(
                    new SwipeGestureDetector());
        }

  /* ... */

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (gestureDetector.onTouchEvent(event)) {
                return true;
            }
            return super.onTouchEvent(event);
        }

        private void onLeftSwipe() {
            // Do something
            Log.d("swipe", "left");
        }

        private void onRightSwipe() {
            // Do something
            Log.d("swipe", "right");
        }

        // Private class for gestures
        private class SwipeGestureDetector
                extends GestureDetector.SimpleOnGestureListener {
            // Swipe properties, you can change it to make the swipe
            // longer or shorter and speed
            private static final int SWIPE_MIN_DISTANCE = 120;
            private static final int SWIPE_MAX_OFF_PATH = 200;
            private static final int SWIPE_THRESHOLD_VELOCITY = 200;

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2,
                                   float velocityX, float velocityY) {
                try {
                    float diffAbs = Math.abs(e1.getY() - e2.getY());
                    float diff = e1.getX() - e2.getX();

                    if (diffAbs > SWIPE_MAX_OFF_PATH)
                        return false;

                    // Left swipe
                    if (diff > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        SecondActivity.this.onLeftSwipe();

                        // Right swipe
                    } else if (-diff > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        SecondActivity.this.onRightSwipe();
                    }
                } catch (Exception e) {
                    Log.e("YourActivity", "Error on gestures");
                }
                return false;
            }
        }
    }