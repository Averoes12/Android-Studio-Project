package com.averoes.daff.mvvmapp.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.averoes.daff.mvvmapp.model.User;

/**
 * Created by daff on 27/02/19 at 6:50.
 */

public class UserVIewModel {

    public static final String MESSAGE_SUCCES = "Login Succes";
    public static final String MESSAGE_FAILED = "Login Failed";
    private Context context;
    private User user;

    public UserVIewModel(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    public void updateModel(String email, String pass) {

    }

    public void validLogin(){

        boolean isValid = true;

        if (TextUtils.isEmpty(user.getEmail())){
            isValid = false;
        }

        if (TextUtils.isEmpty(user.getPassword())){
            isValid = false;
        }

        if (isValid){
            showToast(MESSAGE_SUCCES);
        }else {
            showToast(MESSAGE_FAILED);
        }
    }

    private void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}



