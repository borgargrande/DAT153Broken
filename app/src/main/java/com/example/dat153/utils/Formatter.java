package com.example.dat153.utils;

import android.graphics.Bitmap;

public class Formatter {
    public Formatter() {
    }

    //Method for formatting all images to correct format.
    public Bitmap scaledBM(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int maxWidth = 500;

        int maxHeight = 500;
        if (width > height) {
            // landscape
            float ratio = (float) width / maxWidth;
            width = maxWidth;
            height = (int) (height / ratio);
        } else if (height > width) {
            // portrait
            float ratio = (float) height / maxHeight;
            height = maxHeight;
            width = (int) (width / ratio);
        } else {
            // square
            height = maxHeight;
            width = maxWidth;
        }
        bm = Bitmap.createScaledBitmap(bm, width, height, true);
        return bm;
    }
}
