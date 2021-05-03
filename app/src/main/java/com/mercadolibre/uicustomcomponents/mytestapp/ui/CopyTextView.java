package com.mercadolibre.uicustomcomponents.mytestapp.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.mercadolibre.uicustomcomponents.mytestapp.R;

public class CopyTextView extends androidx.appcompat.widget.AppCompatTextView {

    private static String GREEN_COLOR = "#37B670";
    private static final int IC_CONTENT_COPY = R.drawable.ic_content_copy;
    private static final int IC_CHECK = R.drawable.ic_check;

    public CopyTextView(final Context context) {
        super(context);
        init();
    }

    public CopyTextView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CopyTextView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setView();
        setEvents();
    }

    private void setView() {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, IC_CONTENT_COPY, 0);
    }

    private void setEvents() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {

                copyToClipboard();

                int currentColor = getCurrentTextColor();

                // Change icon
                setCompoundDrawablesWithIntrinsicBounds(0, 0, IC_CHECK, 0);

                // Change color
                final Drawable[] compoundDrawables = getCompoundDrawables();
                compoundDrawables[2].setTint(Color.parseColor(GREEN_COLOR));
                setTextColor(Color.parseColor(GREEN_COLOR));

                // Set timeout and return initial state
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setCompoundDrawablesWithIntrinsicBounds(0, 0, IC_CONTENT_COPY, 0);
                        compoundDrawables[2].setTint(currentColor);
                        setTextColor(currentColor);
                    }
                }, 2000);
            }
        });
    }

    private void copyToClipboard() {
        ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("copy", getText());
        clipboard.setPrimaryClip(clip);
    }
}
