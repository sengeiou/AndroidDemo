package com.boc.lfj.httpdemo.mode.factory.staticf;

import com.boc.lfj.httpdemo.mode.factory.MailSender;
import com.boc.lfj.httpdemo.mode.factory.Sender;
import com.boc.lfj.httpdemo.mode.factory.SmsSender;

/**
 * Created by Administrator on 2017/3/15.
 */

public class FactoryMethod {
    public static Sender produceMail() {
        return new MailSender();
    }

    public static Sender produceSms() {
        return new SmsSender();
    }
}
