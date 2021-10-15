package com.example.eatwell;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogProgress {
    private static ProgressDialog progressDialog;

    public static void getDialogProgress(Boolean shouldShow, Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("Connecting to remote server");
        }
        if (shouldShow) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }
}
