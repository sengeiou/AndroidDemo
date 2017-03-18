package com.boc.lfj.httpdemo.mode.factory.abstractf;

import com.boc.lfj.httpdemo.mode.factory.Sender;
import com.boc.lfj.httpdemo.mode.factory.SmsSender;

/**
 * Created by Administrator on 2017/3/15.
 */

public class SendSmsFactory implements Provider {
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
