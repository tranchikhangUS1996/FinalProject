package com.example.lap60020_local.finalproject.Ui;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lap60020_local.finalproject.ModelData.Entity.AuthenticationSessionID;
import com.example.lap60020_local.finalproject.ModelData.Params.LoginParams;
import com.example.lap60020_local.finalproject.MyApplication;
import com.example.lap60020_local.finalproject.R;
import com.example.lap60020_local.finalproject.ViewModel.LoginViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

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
        loginViewModel = ((MyApplication) getApplication()).getLoginViewModel();
        disposable = new CompositeDisposable();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbind();
    }

    @Optional
    @OnClick(R.id.login_sigup)
    public void onSignUp(View view) {
        // chuyen den link dang ky
    }

    @Optional
    @OnClick(R.id.login_signin)
    public void onSignin(View v) {
        LoginParams params = new LoginParams(username.getText().toString(),password.getText().toString());
        disposable.add(loginViewModel.login(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new LoginObserver()));
    }


    public void unbind() {
        disposable.clear();
    }

    class LoginObserver extends DisposableObserver<AuthenticationSessionID> {

        @Override
        public void onNext(AuthenticationSessionID authenticationSessionID) {
            MyApplication application = (MyApplication) getApplication();
            application.setSessionId(authenticationSessionID.sessionId);
            finish();
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {
            Toast.makeText(getApplicationContext(), "Log in success!", Toast.LENGTH_SHORT).show();
        }
    }
}
