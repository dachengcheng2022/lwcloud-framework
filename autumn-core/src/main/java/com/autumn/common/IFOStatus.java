package com.autumn.common;

public class IFOStatus {
//    -1：未发布
//0:即将分叉
//1：已分叉
//11:分叉成功
//12：分叉失败
    public final static int NO_PUB = -1;
    public final static int FORKING = 0;
    public final static int FORKED = 1;
    public final static int FORKED_SUCCESS = 11;
    public final static int FORKED_FAILD = 12;
}
