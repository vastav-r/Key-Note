package com.mad.vastav.keynote;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by VastaV on 10/29/2017.
 */

public class scribbleActivity  extends AppCompatActivity {

    private canvasActivity canvasView;
    ImageView imageview;
    Button button;
    Drawable drawable;
    Bitmap bitmap;
    String ImagePath;
    Uri URI;

    private Button Save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scribble);

        Save = (Button) findViewById(R.id.saveCanvas);

        canvasView = (canvasActivity) findViewById(R.id.canvas);

        button = (Button) findViewById(R.id.saveCanvas);
        imageview = (ImageView) findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO Auto-generated method stub

                drawable = getResources().getDrawable(R.id.imageView);

                bitmap = ((BitmapDrawable) drawable).getBitmap();

                ImagePath = MediaStore.Images.Media.insertImage(
                        getContentResolver(),
                        bitmap,
                        "demo_image",
                        "demo_image"
                );

                URI = Uri.parse(ImagePath);

                Toast.makeText(scribbleActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();

            }

        });
    }

    public void clearCanvas(View v) {
        canvasView.clearCanvas();
    }
}

