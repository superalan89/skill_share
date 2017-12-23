package com.immymemine.kevin.skillshare.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.immymemine.kevin.skillshare.R;
import com.jakewharton.rxbinding2.widget.RxTextView;

public class CreateGroupActivity extends AppCompatActivity {

    EditText editTextGroupName;
    TextView textViewCharactersLeft;
    ImageView imageSelectPicture;
    Button imageButtonGallery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_create_group);
        initView();
    }

    int limit = 40;

    private void initView() {
        editTextGroupName = findViewById(R.id.edit_text_group_name);
        textViewCharactersLeft = findViewById(R.id.text_view_characters_left);
        RxTextView.textChanges(editTextGroupName)
                .subscribe(charSequence -> {

                    int left = limit - charSequence.toString().length();
                    textViewCharactersLeft.setText(left + " characters left");

                });


        imageButtonGallery = findViewById(R.id.image_button_gallery);
        imageButtonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClick", "==========");
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, REQ_GALLERY);
            }
        });
    }

    private static final int REQ_GALLERY = 222;

    public void init() {
        imageSelectPicture = (ImageView) findViewById(R.id.image_select_picture);
    }

//    public void onGallery(View view) {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
//        startActivityForResult(intent, REQ_GALLERY);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri imageUri = null;
            switch (requestCode) {
                case REQ_GALLERY:
                    if (data != null) {
                        imageUri = data.getData();
                        imageSelectPicture.setImageURI(imageUri);
                    }
                    break;
            }
        }
    }
}
