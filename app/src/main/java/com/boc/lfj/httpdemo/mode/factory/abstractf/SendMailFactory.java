package com.boc.lfj.httpdemo.mode.factory.abstractf;

import com.boc.lfj.httpdemo.mode.factory.MailSender;
import com.boc.lfj.httpdemo.mode.factory.Sender;

/**
 * Created by Administrator on 2017/3/15.
 */

public class SendMailFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
