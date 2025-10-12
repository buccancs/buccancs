package com.shimmerresearch.algorithms;

import com.shimmerresearch.algorithms.AbstractSignalGenerator.DataThread;
import com.shimmerresearch.driver.BasicProcessWithCallBack;
import com.shimmerresearch.driver.ShimmerMsg;

public abstract class AbstractSignalGenerator extends BasicProcessWithCallBack {
    public int sleepDurationInNano = 976562; //nano seconds
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
        // TODO Auto-generated method stub

    }

    public class DataThread extends Thread {

        private boolean generate = true;

        public void run() {
            createSignalGen();
        }

        private void createSignalGen() {
            // TODO Auto-generated method stub

            while (generate) {
                Object x = generateSignal();

                if (x != null) {
                    //System.out.println(x);
                    //PLOT HERE
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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
