package cn.lc.model.framework.imageload;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.lc.model.R;

/**
 * Created by hh on 2017/6/13.
 */

public class GlideLoading {


    private static GlideLoading load;


    private GlideLoading() {

    }

    public static GlideLoading getInstance() {
        if (load == null) {
            load = new GlideLoading();
        }
        return load;
    }

    public void loadImgUrlNyImgLoader(Context ct, String url, ImageView iv) {
        Glide.with(ct).load(url).into(iv);
    }

    public void loadImgUrlHeader(Context ct, String url, ImageView iv) {
        Glide.with(ct).load(url).placeholder(R.drawable.visitor_discrepancy).into(iv);
    }
}
