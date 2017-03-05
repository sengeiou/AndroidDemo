package com.boc.pay;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.boc.pay.util.OrderInfoUtil2_0;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.alipay.sdk.app.statistic.c.s;
import static com.alipay.sdk.app.statistic.c.v;
import static com.alipay.sdk.app.statistic.c.w;

public class MainActivity extends AppCompatActivity implements IWXAPIEventHandler {
    //支付宝相关
    private static final int SDK_PAY_FLAG = 1;
    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2016080400166834";

    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCeTjklE9RXlx0i\n" +
            "Fd3c64cvyW+vZHwQxr/zZfJ1MOUV81QfkFS6kGstDXn/xS010lC3tQ//RVoaKX5G\n" +
            "8VRLJuqYTHkMukGV1NS8+WFYqzrwkdOsVqBkqMShtnBGidXOkreMNmvpEcIPmnZH\n" +
            "6YqGpI7cijbgJqW/uhzIqWVoKOmQMGGlNWMI8GoCJHgPmq4jSvsd/iDA5N5m9lzy\n" +
            "ELVdW/PxablLU3e6t00QOJDp82ADjIILEwdsBazjFtg/tIwJYR96yJF+g/EYTJm9\n" +
            "Rif/tYKagSmvhe+S/Wlprq0ZpDWHzSi/F9twhNAW5JLBL+GE5VrKgGtrvPZuwQHC\n" +
            "av2677vTAgMBAAECggEAbqDNsIqN/vQcqOBNHoD+BTgpM5HuXa73H3pkGN+liFR2\n" +
            "umQbwhEAxQhA9Kvvr0gdnnQLDOKDV/sdQyiNNsOod+whP/K9uJ52WOWR0OxAkTPn\n" +
            "j+0tw4ppd3VOOw1B/E1tta+mc8c0LcFM3W5onzVKnPZ96OE5PaYrTVOMCWFl75u2\n" +
            "ktra/HVJo/gMGhX09pVcjImFKE/7W1l8MZFdMt0Q5RSqYjwBnXmx5lXJpKv+ufX3\n" +
            "Bq4nI3n1LpM4ZY+Nta+zfvWRY600zV3JMFG4sbs7Bl8tBRYwt7D/TEIYagKQYFlF\n" +
            "T8LroP2XxBqqOo5iIWJjn06IWHlWxLFYLF5LPGHZKQKBgQDJ5Ng0aW81CnVh5zHn\n" +
            "HUzB2wN4GFzURQmRoSa+BHGq0JAPH3VJhkWTHzaMV9Zlk7OwXMYOiInCZJdy60Uq\n" +
            "7SRnBfXkNYv5Vx+WEywPixUDwx4oRhMnDI23emKh+M7K2M9p5ChMHy3BZ8vhO/Gx\n" +
            "S5K4ZmKjW8zto3jVD32Uh1W0VwKBgQDIuvNpBCwxiLbq0KVf3RHJZCAVS0As52FE\n" +
            "o5IIANC7LbV5jDEnapVymFdVKkx+W7hqfhJ+kSR6qMDKCLZk68z3PyaexHIxnYhy\n" +
            "Um87NZ2F7PecCtNj4mHFdTvdAH9pj37bYnMjSBlh8u9+9JGCYyn4O5/FQwXsCnBM\n" +
            "PL+iTTOm5QKBgQCKLXvj8z1Pegb4qLk4yo7cJr9rFJGEHnP8HeVcK8J3/CJ8lzU4\n" +
            "yxMXLEezKfLstnhG/lf3Xm+agio9VtSSGpfNgwDV3egdEQnnNtvzD2ekmYgsXDmD\n" +
            "gk4EA1k6LgWqQ1VBzgvefYBdXGLnlpVGmMikOD7OO2g4RKfQereiTjXUJQKBgFXM\n" +
            "u3CfDPjbVDyIlLemOg0YVDJl6PeIq2X4eNoc5xurDQ/4YaZUg67o+SN5Ihs8v1Yd\n" +
            "r3AUQECB025ITRZUSFdC0hI5LAqTKJidLdhkMYHmaWx0oCjowhvx4XW02cFYScQi\n" +
            "j8576gWZHs/oYC4Yjv8BFWjvHh/n0lpz+QG5+CMVAoGAL6ZrQVy7BzQb9zsvkjxj\n" +
            "aosf6sBUi43PETKSDAGTCX7KxB8XA+LcMsBVX/7Aypic8KQ5UtoQp75ZPUVk/075\n" +
            "7e5q5UBbzlyUwjYdgS+MpjTj29oMrqfzXJwbppjCPrGLTBArmQFGUxh0vOFzU5S7\n" +
            "DzXOcVzjkU3MFppsndRSS/U=";
    public static final String RSA_PRIVATE = "";
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };


    //微信相关
    private IWXAPI api;
    private String wxappid = "wxb4ba3c02aa476ea1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = WXAPIFactory.createWXAPI(this, wxappid);

        findViewById(R.id.pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alipay();
         }
        });
        findViewById(R.id.wxpay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wxpay();
            }
        });

    }

    private void alipay(){
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;


//        final String orderInfo = "app_id=2015052600090779&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22seller_id%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.01%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22IQJZSRC1YMQB5HU%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fdomain.merchant.com%2Fpayment_notify&sign_type=RSA2&timestamp=2016-08-25%2020%3A26%3A31&version=1.0&sign=cYmuUnKi5QdBsoZEAbMXVMmRWjsuUj%2By48A2DvWAVVBuYkiBj13CFDHu2vZQvmOfkjE0YqCUQE04kqm9Xg3tIX8tPeIGIFtsIyp%2FM45w1ZsDOiduBbduGfRo1XRsvAyVAv2hCrBLLrDI5Vi7uZZ77Lo5J0PpUUWwyQGt0M4cj8g%3D";

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(MainActivity.this);
                EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void wxpay(){
        //统一下单，后台调用微信接口


        //1、生成订单
        PayReq request = new PayReq();
        request.appId = wxappid;
        request.partnerId = "1900000119";
        request.prepayId= "1101000000140415649af9fc314aa429";//预支付交易会话ID
        request.packageValue = "Sign=WXPay";
        request.nonceStr= "1101000000140429eb40476f8896f4c5";
        request.timeStamp= "1398746578";
        request.sign= "7FFECB600D7157C5AA49810D2D8F28BC2811827A";
        //2、注册APPID
            api.registerApp(wxappid);
        //3、调起支付
        api.sendReq(request);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Toast.makeText(MainActivity.this,"onReq",Toast.LENGTH_LONG).show();
    }

   @Override
    public void onResp(BaseResp baseResp) {
        Toast.makeText(MainActivity.this,baseResp.errStr,Toast.LENGTH_LONG).show();
    }
}
