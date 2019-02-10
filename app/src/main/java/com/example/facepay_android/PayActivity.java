package com.example.facepay_android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PayActivity extends AppCompatActivity {

    private Button Pay, Request, Return;
    private TextView Name;
    private EditText Amount;
    private ImageView Image;

    private Bitmap img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        this.img = FaceHandler.lastImage;

        Pay = findViewById(R.id.button);
        Request = findViewById(R.id.button2);
        Return = findViewById(R.id.button3);
        Name = findViewById(R.id.name);
        Amount = findViewById(R.id.payment);
        Image = findViewById(R.id.image);
        // Bundle extras = getIntent().getExtras();
        //byte[] imgarray = extras.getByteArray("Image");
        //Bitmap img = BitmapFactory.decodeByteArray(imgarray, 0, imgarray.length);
        if (img != null)
            Image.setImageBitmap(img);
        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPayment();
            }
        });
    }
    public void sendPayment(){

    }

}

