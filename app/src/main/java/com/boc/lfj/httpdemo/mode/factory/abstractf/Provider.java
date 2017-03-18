package com.boc.lfj.httpdemo.mode.factory.abstractf;

import com.boc.lfj.httpdemo.mode.factory.Sender;

/**
 * Created by Administrator on 2017/3/15.
 */

public interface Provider {
    Sender produce();
}
