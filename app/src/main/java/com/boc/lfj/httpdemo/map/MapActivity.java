package com.boc.lfj.httpdemo.map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.boc.lfj.httpdemo.R;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity {
    MapView mMapView = null;
    BaiduMap mBaiduMap = null;
    Marker marker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);

        mBaiduMap = mMapView.getMap();


    }



    public void addOverLay() {
        //定义Maker坐标点
        LatLng point = new LatLng(39.963175, 116.400244);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_marka);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .zIndex(9)  //设置marker所在层级
                .draggable(true)  //设置手势拖拽
                .position(point)
                .icon(bitmap);

        // 通过marker的icons设置一组图片，再通过period设置多少帧刷新一次图片资源
//        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
//        giflist.add();
//        giflist.add(bdB);
//        giflist.add(bdC);
//        OverlayOptions ooD = new MarkerOptions().position(pt).icons(giflist)
//                .zIndex(0).period(10);

        //在地图上添加Marker，并显示
        marker = (Marker) mBaiduMap.addOverlay(option);

        //调用BaiduMap对象的setOnMarkerDragListener方法设置marker拖拽的监听
        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            public void onMarkerDrag(Marker marker) {
                //拖拽中
            }
            public void onMarkerDragEnd(Marker marker) {
                //拖拽结束
            }
            public void onMarkerDragStart(Marker marker) {
                //开始拖拽
            }
        });
    }



    //地图Logo
    public void setMapPosition() {
        mMapView.setLogoPosition(LogoPosition.logoPostionleftBottom);
        /**
         * paddingLeft、paddingTop、paddingRight、paddingBottom参数表示距离屏幕边框的左、上、右、下边距的距离
         */
//        mBaiduMap.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public void setTraffic() {
        //开启交通图
        mBaiduMap.setTrafficEnabled(true);
    }

    public void setBaiduHeatMap() {
        //开启交通图
        mBaiduMap.setBaiduHeatMapEnabled(true);
    }

    public void setMapType() {
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        //卫星地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);

        //空白地图, 基础地图瓦片将不会被渲染。在地图类型中设置为NONE，将不会使用流量下载基础地图瓦片图层。使用场景：与瓦片图层一起使用，节省流量，提升自定义瓦片图下载速度。
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
