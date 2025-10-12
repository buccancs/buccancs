package com.shimmerresearch.algorithms;

import com.shimmerresearch.algorithms.AbstractSignalGenerator.DataThread;
import com.shimmerresearch.driver.BasicProcessWithCallBack;
import com.shimmerresearch.driver.ShimmerMsg;

public abstract class AbstractSignalGenerator extends BasicProcessWithCallBack {
    public int sleepDurationInNano = 976562;
    public int sleepDurationInMilli = 0;
    DataThread dt = null;


    public abstract Object generateSignal();

    public void stopGenerator() {
        if (dt != null) {
            dt.stopGen();
        }
    }

    public void startGenerator() {
        if (dt != null) {
            dt.stopGen();
        }
        dt = new DataThread();
        dt.start();
    }

    @Override
    protected void processMsgFromCallback(ShimmerMsg shimmerMSG) {

    }

    public class DataThread extends Thread {

        private boolean generate = true;

        public void run() {
            createSignalGen();
        }

        private void createSignalGen() {

            while (generate) {
                Object x = generateSignal();

                if (x != null) {
                    threadSleep();
                }
            }

        }

        public boolean isRunning() {
            return generate;
        }

        public void stopGen() {
            generate = false;
        }

        private void threadSleep() {
            try {
                Thread.sleep(sleepDurationInMilli, sleepDurationInNano);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
