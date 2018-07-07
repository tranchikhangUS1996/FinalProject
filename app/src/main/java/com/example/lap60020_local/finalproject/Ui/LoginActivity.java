package com.example.lap60020_local.finalproject.Ui;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lap60020_local.finalproject.R;
import com.example.lap60020_local.finalproject.ViewModel.LoginViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import io.reactivex.disposables.CompositeDisposable;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_username)
    @Nullable
    EditText username;
    @BindView(R.id.login_password)
    @Nullable
    EditText password;
    private LoginViewModel loginViewModel;
    private CompositeDisposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Optional
    @OnClick(R.id.login_sigup)
    public void onSignUp(View view) {
        // chuyen den link dang ky
    }

    @Optional
    @OnClick(R.id.login_signin)
    public void onSignin(View v) {
        //disposable.add(loginViewModel.login())
    }
}
