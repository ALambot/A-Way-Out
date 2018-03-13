package com.dolphin.awayout;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;


public class InventoryAdapt extends ArrayAdapter<GameObject>{


    public InventoryAdapt(@NonNull Context context, ArrayList<GameObject> ImageNames) {
        super(context, R.layout.inventory_slot,ImageNames);
    }

    @Nullable
    @Override
    public GameObject getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public void add(@Nullable GameObject object) {
        super.add(object);
    }

    @Override
    public void remove(@Nullable GameObject object){
        super.remove(object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.inventory_slot, parent , false);
        ImageView image = (ImageView) view.findViewById(R.id.imageSlotObject);
        GameObject obj = getItem(position);

        image.setImageResource(obj.getIdImage());
        image.setImageBitmap(decodeSampledBitmapFromResource(image.getResources(),
                obj.getIdImage(), 100, 100));
        obj.setImage(image);
       return view;
    }
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}
