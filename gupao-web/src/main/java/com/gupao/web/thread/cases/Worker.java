package com.gupao.web.thread.cases;

/**
 * Created by James on 2017-12-02.
 * From 咕泡学院出品
 * 老师咨询 QQ 2904270631
 */
public class Worker implements Runnable {
    private Constants constants;

    public Worker(Constants constants) {
        this.constants = constants;
    }

    @Override
    public void run() {
        System.out.println(constants.isFlag());
        while (!constants.isFlag()){
            System.out.println(1);
        }
    }
}
