package org.vicykie.myapp.service.async.impl;

import lombok.extern.log4j.Log4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.vicykie.myapp.service.async.AsyncService;

/**
 * Created by 李朝衡 on 2016/8/18.
 */
@Service
@Log4j
public class AsyncServiceImpl implements AsyncService {
    @Async
    @Override
    public void task1() {
        int i =1;
        task(i);
    }


    @Async
    @Override
    public void task2() {
        int i = 2;
        task(i);

    }
    @Async
    @Override
    public void task3() {
        int i = 3;
        task(i);
    }

    private void task(int i) {
        try {
            long start = System.currentTimeMillis();
            Thread.sleep(3000);
            long end = System.currentTimeMillis();
            log.info("task"+i+"耗时："+(end-start)+"ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
