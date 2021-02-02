package com.example.dat153.TypeConverters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import com.example.dat153.Entity.Campus;

import java.io.ByteArrayOutputStream;

public class QuestionsTypeConverter {

    @TypeConverter
    public String fromCampus(Campus campus) {
        return campus.toString();
    }

    @TypeConverter
    public Campus toCampus(String campus) {
        return Campus.valueOf(campus);
    }

    @TypeConverter
    public byte[] fromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }

    @TypeConverter
    public Bitmap toBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }


}
