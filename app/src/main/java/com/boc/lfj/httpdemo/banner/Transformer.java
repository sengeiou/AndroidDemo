package com.boc.lfj.httpdemo.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.boc.lfj.httpdemo.banner.transformer.AccordionTransformer;
import com.boc.lfj.httpdemo.banner.transformer.BackgroundToForegroundTransformer;
import com.boc.lfj.httpdemo.banner.transformer.CubeInTransformer;
import com.boc.lfj.httpdemo.banner.transformer.CubeOutTransformer;
import com.boc.lfj.httpdemo.banner.transformer.DefaultTransformer;
import com.boc.lfj.httpdemo.banner.transformer.DepthPageTransformer;
import com.boc.lfj.httpdemo.banner.transformer.FlipHorizontalTransformer;
import com.boc.lfj.httpdemo.banner.transformer.FlipVerticalTransformer;
import com.boc.lfj.httpdemo.banner.transformer.ForegroundToBackgroundTransformer;
import com.boc.lfj.httpdemo.banner.transformer.RotateDownTransformer;
import com.boc.lfj.httpdemo.banner.transformer.RotateUpTransformer;
import com.boc.lfj.httpdemo.banner.transformer.ScaleInOutTransformer;
import com.boc.lfj.httpdemo.banner.transformer.StackTransformer;
import com.boc.lfj.httpdemo.banner.transformer.TabletTransformer;
import com.boc.lfj.httpdemo.banner.transformer.ZoomInTransformer;
import com.boc.lfj.httpdemo.banner.transformer.ZoomOutSlideTransformer;
import com.boc.lfj.httpdemo.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
