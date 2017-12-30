package com.immymemine.kevin.skillshare.utility;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.immymemine.kevin.skillshare.R;
import com.jakewharton.rxbinding2.widget.RxTextView;

/**
 * Created by quf93 on 2017-11-26.
 */

public class DialogUtil {
    // TODO custom dialog
    private void showAlertDialog(Context context, String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    public static View showCreateGroupDialog(Context context, Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_create_group, null, false);
        int limit = 40;
        ImageButton toolbarCloseButton = view.findViewById(R.id.toolbar_close_button);
        EditText editTextGroupName = view.findViewById(R.id.edit_text_group_name);
        TextView textViewCharactersLeft = view.findViewById(R.id.text_view_characters_left);
        Button buttonCreateGroup = view.findViewById(R.id.button_create_group);
        Button imageButtonGallery = view.findViewById(R.id.image_button_gallery);

        RxTextView.textChanges(editTextGroupName)
                .subscribe(charSequence -> {
                    int left = limit - charSequence.toString().length();
                    textViewCharactersLeft.setText(left + " characters left");

                    if(left < 40) {
                        // button enabled
                        buttonCreateGroup.setEnabled(true);
                        buttonCreateGroup.setBackgroundColor(context.getResources().getColor(R.color.IcActive));
                    } else {
                        // disabled
                        buttonCreateGroup.setEnabled(false);
                        buttonCreateGroup.setBackgroundColor(context.getResources().getColor(R.color.IcDisabled));
                    }
                });

        builder.setView(view);
        AlertDialog dialog = builder.show();

        buttonCreateGroup.setOnClickListener(
                v -> {
                    dialog.dismiss();
                }
        );

        imageButtonGallery.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            activity.startActivityForResult(intent, 222);
        });

        toolbarCloseButton.setOnClickListener(
                v -> {
                    dialog.dismiss();
                }
        );

        return view;
    }
}
