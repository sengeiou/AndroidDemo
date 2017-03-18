package com.boc.lfj.httpdemo.mode.builder;

import com.boc.lfj.httpdemo.mode.factory.MailSender;
import com.boc.lfj.httpdemo.mode.factory.Sender;
import com.boc.lfj.httpdemo.mode.factory.SmsSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */

public class Builder {
    private List<Sender> list = new ArrayList<Sender>();

    public void produceMailSender(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new MailSender());
        }
    }

    public void produceSmsSender(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new SmsSender());
        }
    }
}
