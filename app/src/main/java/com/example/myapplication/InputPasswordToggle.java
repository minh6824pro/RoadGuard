package com.example.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

public class InputPasswordToggle extends AppCompatEditText {

    private boolean isPasswordVisible = false;
    private Drawable iconKey;
    private Drawable iconEye;
    private Drawable iconEyeOpen;

    public InputPasswordToggle(Context context) {
        super(context);
        init();
    }

    public InputPasswordToggle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InputPasswordToggle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Load icons from drawable resources
        iconKey = ContextCompat.getDrawable(getContext(), R.drawable.ic_key);
        iconEye = ContextCompat.getDrawable(getContext(), R.drawable.ic_eye);
        iconEyeOpen = ContextCompat.getDrawable(getContext(), R.drawable.ic_eye_open);

        // Set initial icons: key on the left, eye on the right
        setCompoundDrawablesWithIntrinsicBounds(iconKey, null, iconEye, null);

        // Set input type as hidden password by default
        setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            // Check if touch is within the bounds of the eye icon
            Drawable drawableRight = getCompoundDrawables()[2];
            if (drawableRight != null) {
                int iconWidth = drawableRight.getBounds().width();
                int iconStartX = getWidth() - getPaddingRight() - iconWidth;
                int iconEndX = getWidth() - getPaddingRight();

                // Check if the touch event's X position falls within the icon's horizontal bounds
                if (event.getX() >= iconStartX && event.getX() <= iconEndX) {
                    Log.d("InputPasswordToggle", "Eye icon touched");

                    // Toggle password visibility
                    if (isPasswordVisible) {
                        // Hide password
                        setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        setCompoundDrawablesWithIntrinsicBounds(iconKey, null, iconEye, null);
                    } else {
                        // Show password
                        setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        setCompoundDrawablesWithIntrinsicBounds(iconKey, null, iconEyeOpen, null);
                    }

                    // Update password visibility state
                    isPasswordVisible = !isPasswordVisible;

                    // Ensure cursor stays at the end of the text
                    setSelection(getText().length());

                    // Force input type reset for immediate update
                    setInputType(getInputType());
                    requestLayout();
                    invalidate();

                    return true;
                }
            }
        }
        return super.onTouchEvent(event);
    }

}
