package com.boc.lfj.httpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.boc.lfj.httpdemo.bean.login.LoginResult;
import com.boc.lfj.httpdemo.bean.login.SecretKeyRequest;
import com.boc.lfj.httpdemo.bean.login.SecretKeyResult;
import com.boc.lfj.httpdemo.common.SM4Util;
import com.boc.lfj.httpdemo.common.SPUtils;
import com.boc.lfj.httpdemo.common.ToastUtils;
import com.boc.lfj.httpdemo.common.log.Logger;
import com.boc.lfj.httpdemo.retrofit.NetService;
import com.boc.lfj.httpdemo.retrofit.ServiceGenerator;
import com.kjhxtc.crypto.util.encoder.Hex;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class LoginActivity extends AppCompatActivity {
    private TextView usernameTv, PasswordTv;
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = (Button) findViewById(R.id.login);
        usernameTv = (TextView) findViewById(R.id.tv_username);
        PasswordTv = (TextView) findViewById(R.id.tv_password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSecretKey();
            }
        });
    }

    /**
     * 获取密钥
     */
    private void getSecretKey() {

        username = usernameTv.getText().toString();
        password = PasswordTv.getText().toString();
        SecretKeyRequest criteria = new SecretKeyRequest(username, password);
        NetService service = ServiceGenerator.createService(NetService.class);
        Call<SecretKeyResult> call = service.requestSecretKey(criteria);
        call.enqueue(new Callback<SecretKeyResult>() {
            @Override
            public void onResponse(Call<SecretKeyResult> call, Response<SecretKeyResult> response) {
                SecretKeyResult result = response.body();
                login(result);
            }

            @Override
            public void onFailure(Call<SecretKeyResult> call, Throwable throwable) {
                ToastUtils.showLongMsg(LoginActivity.this, "failure");
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
    }

    /**
     * 登录
     */
    private void login(SecretKeyResult result) {
        byte[] encodePassWord = SM4Util.encodeSMS4(password, result.getOtherInfo().getIDCardNo());
        String pwdEncode = Hex.toHexString(encodePassWord);
        SecretKeyRequest keyRequest = new SecretKeyRequest(username, pwdEncode);
        NetService service = ServiceGenerator.createService(NetService.class);
        Call<LoginResult> call = service.login(keyRequest);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                ToastUtils.showLongMsg(LoginActivity.this, "login success");
                SPUtils.saveCookie(response);
                SPUtils.saveLoginState(response.body().getOtherInfo());
                Logger.d(response.toString());
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable throwable) {
                ToastUtils.showLongMsg(LoginActivity.this, "login failure");
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
