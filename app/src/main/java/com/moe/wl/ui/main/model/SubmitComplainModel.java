package com.moe.wl.ui.main.model;

import java.util.ArrayList;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：意见投诉
 * 作者：Shixhe On 2017/9/6 0006
 */

public interface SubmitComplainModel extends MvpModel {

    Observable submitComplain(int id, String complaintContent, String suggestContent, int anonymous, ArrayList<String> paths);

    Observable getLabelling();

}
