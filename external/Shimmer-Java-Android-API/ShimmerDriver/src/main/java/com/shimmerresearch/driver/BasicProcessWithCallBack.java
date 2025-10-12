package com.shimmerresearch.driver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import com.shimmerresearch.driverUtilities.UtilShimmer;

public abstract class BasicProcessWithCallBack {

    protected Callable mThread = null;

    ;
    protected LinkedBlockingDeque<ShimmerMsg> mQueue = new LinkedBlockingDeque<ShimmerMsg>(1024);
    protected ConsumerThread mGUIConsumerThread = null;
    private WaitForData mWaitForData = null;
    private List<Callable> mListOfConsumers = Collections.synchronizedList(new ArrayList<Callable>());
    private List<BasicProcessWithCallBack> mListOfMsgProducers = new ArrayList<BasicProcessWithCallBack>();
    private List<WaitForData> mListWaitForData = Collections.synchronizedList(new ArrayList<WaitForData>());
    private String threadName = "";
    private boolean mIsDebug = false;

    public BasicProcessWithCallBack() {

    }

    public BasicProcessWithCallBack(BasicProcessWithCallBack b) {
        mWaitForData = new WaitForData(b);
    }

    protected abstract void processMsgFromCallback(ShimmerMsg shimmerMSG);

    public void queueMethod(ShimmerMsg smsg) {
        try {
            mQueue.put(smsg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void queueMethod(int i, Object ojc) {
        try {
            mQueue.put(new ShimmerMsg(i, ojc));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getQueueSize() {
        return mQueue.size();
    }

    public void startConsumerThreadIfNull() {
        if (mGUIConsumerThread == null || mGUIConsumerThread.stop) {
            mGUIConsumerThread = new ConsumerThread();
            if (!threadName.isEmpty()) {
                mGUIConsumerThread.setName(threadName);
            }
            mGUIConsumerThread.start();
        }
    }

    public void removeConsumer(BasicProcessWithCallBack b) {
        synchronized (mListOfConsumers) {
            Iterator<Callable> entries = mListOfConsumers.iterator();
            while (entries.hasNext()) {
                Callable c = entries.next();
                if (c instanceof WaitForData) {
                    for (WaitForData w : b.mListWaitForData) {
                        if (c.equals(w)) {
                            entries.remove();
                        }
                    }
                    if (c.equals(b.mWaitForData)) {
                        entries.remove();
                    }
                }
            }
        }
    }

    public void stopConsumerThread() {
        if (mGUIConsumerThread != null) {
            mGUIConsumerThread.stop = true;
            ShimmerMsg smsg = new ShimmerMsg(0, new EndThread());
            try {
                mQueue.put(smsg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setWaitForData(BasicProcessWithCallBack b) {
        startConsumerThreadIfNull();
        mListOfMsgProducers.add(b);
        if (mWaitForData != null) {
            synchronized (mListWaitForData) {
                mListWaitForData.add(new WaitForData(b));
            }
        } else {
            mWaitForData = new WaitForData(b);
        }

        if (mIsDebug) {
            printListOfThreads();
        }
    }

    public void removeSetWaitForData(BasicProcessWithCallBack b) {


        StringBuilder builder = null;
        if (mIsDebug) {
            builder = new StringBuilder();
        }

        if (mWaitForData != null) {
            BasicProcessWithCallBack bpwc = mWaitForData.returnBasicProcessWithCallBack();
            if (bpwc == b || bpwc.equals(b)) {
                if (builder != null) {
                    builder.append("\tRemoving thread\tSimpleName: " + b.threadName + "\tHashCode: " + mWaitForData.hashCode());
                }
                mWaitForData = null;
            }
        }

        synchronized (mListWaitForData) {
            Iterator<WaitForData> entries = mListWaitForData.iterator();
            while (entries.hasNext()) {
                WaitForData wFD = entries.next();
                BasicProcessWithCallBack bpwc = wFD.returnBasicProcessWithCallBack();
                if (bpwc == b || bpwc.equals(b)) {
                    b.removeConsumer(this);
                    if (builder != null) {
                        builder.append("\n\tRemoving thread\tSimpleName: " + b.threadName + "\tHashCode: " + wFD.hashCode());
                    }
                    entries.remove();
                }
            }
        }

        synchronized (mListOfMsgProducers) {
            Iterator<BasicProcessWithCallBack> entries = mListOfMsgProducers.iterator();
            while (entries.hasNext()) {
                BasicProcessWithCallBack bpwc = entries.next();
                if (bpwc == b || bpwc.equals(b)) {
                    b.removeConsumer(this);
                    if (builder != null) {
                        builder.append("\n\tRemoving thread\tSimpleName: " + b.threadName + "\tHashCode: " + bpwc.hashCode());
                    }
                    entries.remove();
                }
            }
        }

        if (builder != null && builder.length() > 0) {
            consolePrintLn("");
            consolePrintLn(this.getClass().getSimpleName() + " -> BasicProcessWithCallBack");
            consolePrintLn(builder.toString());
        }

        if (mWaitForData == null && mListWaitForData.isEmpty() && mListOfMsgProducers.isEmpty()) {
            stopConsumerThread();
        }

        if (mIsDebug) {
            printListOfThreads();
        }
    }

    public void removeSetWaitForDataAll() {

        for (BasicProcessWithCallBack bpwc : mListOfMsgProducers) {
            bpwc.removeConsumer(this);
        }

        mWaitForData = null;
        Iterator<WaitForData> entries = mListWaitForData.iterator();
        while (entries.hasNext()) {
            entries.remove();
        }
    }

    ;


    public void setWaitForDataWithSingleInstanceCheck(BasicProcessWithCallBack b) {
        startConsumerThreadIfNull();

        if (mWaitForData != null) {
            boolean found = false;
            if (mWaitForData.returnBasicProcessWithCallBack().equals(b)) {
                found = true;
            }
            if (!found) {
                synchronized (mListWaitForData) {
                    Iterator<WaitForData> entries = mListWaitForData.iterator();
                    while (entries.hasNext()) {
                        WaitForData wfd = entries.next();
                        if (wfd != null && wfd.returnBasicProcessWithCallBack() != null) {
                            if (wfd.returnBasicProcessWithCallBack().equals(b)) {
                                found = true;
                            }
                        }
                    }
                }
            }
            if (!found) {
                synchronized (mListWaitForData) {
                    mListOfMsgProducers.add(b);
                    mListWaitForData.add(new WaitForData(b));
                }
            }
        } else {
            mWaitForData = new WaitForData(b);
        }

    }

    public void passCallback(Callable c) {
        if (mThread != null) {
            synchronized (mListOfConsumers) {
                mListOfConsumers.add(c);
            }
        } else {
            mThread = c;
        }
    }

    public void sendCallBackMsg(ShimmerMsg s) {
        if (mThread != null) {
            mThread.callBackMethod(s);
        }

        synchronized (mListOfConsumers) {
            Iterator<Callable> entries = mListOfConsumers.iterator();
            while (entries.hasNext()) {
                Callable c = entries.next();
                c.callBackMethod(s);
            }
        }


    }

    ;

    public void sendCallBackMsg(int i, Object ojc) {
        if (mThread != null) {
            mThread.callBackMethod(i, ojc);
        }

        synchronized (mListOfConsumers) {
            Iterator<Callable> entries = mListOfConsumers.iterator();
            while (entries.hasNext()) {
                Callable c = entries.next();
                c.callBackMethod(i, ojc);
            }
        }

    }

    public void setThreadName(String name) {
        threadName = name;
        if (mGUIConsumerThread != null) {
            mGUIConsumerThread.setName(name);
        }
    }

    public void processMsgFromCallbackFromUpperLevel(ShimmerMsg shimmerMSG) {
        processMsgFromCallback(shimmerMSG);
    }

    public void printListOfThreads() {
        consolePrintLn("");
        consolePrintLn(this.getClass().getSimpleName() + " -> BasicProcessWithCallBack" + ": Printing List of Threads");

        StringBuilder builder = new StringBuilder();

        builder.append("\tmWaitForData:");
        if (mWaitForData != null) {
            builder.append("\n\t\tSimple Name: " + mWaitForData.getClass().getSimpleName() + "\t" + mWaitForData.returnBasicProcessWithCallBack().getClass().getSimpleName() + "\tHashCode: " + mWaitForData.hashCode());
        } else {
            builder.append("\n\t\tnull");
        }

        builder.append("\n\tmListOfConsumers:");
        synchronized (mListOfConsumers) {
            if (mListOfConsumers.size() > 0) {
                Iterator<Callable> entries = mListOfConsumers.iterator();
                while (entries.hasNext()) {
                    Callable c = entries.next();
                    builder.append("\n\t\tSimple Name: " + c.getClass().getSimpleName() + "\tHashCode: " + c.hashCode());
                }
            } else {
                builder.append("\n\t\tempty");
            }
        }

        builder.append("\n\tmListWaitForData:");
        synchronized (mListWaitForData) {
            if (mListWaitForData.size() > 0) {
                Iterator<WaitForData> entries = mListWaitForData.iterator();
                while (entries.hasNext()) {
                    WaitForData c = entries.next();
                    builder.append("\n\t\tSimple Name: " + c.getClass().getSimpleName() + "\t" + c.returnBasicProcessWithCallBack().getClass().getSimpleName() + "\tHashCode: " + c.hashCode());
                }
            } else {
                builder.append("\n\t\tempty");
            }
        }

        builder.append("\n\tmListOfMsgProducers:");
        synchronized (mListOfMsgProducers) {
            if (mListOfMsgProducers.size() > 0) {
                Iterator<BasicProcessWithCallBack> entries = mListOfMsgProducers.iterator();
                while (entries.hasNext()) {
                    BasicProcessWithCallBack c = entries.next();
                    builder.append("\n\t\tSimple Name: " + c.getClass().getSimpleName() + "\t" + "\tHashCode: " + c.hashCode());
                }
            } else {
                builder.append("\n\t\tempty");
            }
        }

        consolePrintLn(builder.toString());
        consolePrintLn("");
    }

    private void consolePrintLn(String toPrint) {
        System.out.println(toPrint);
    }

    private class EndThread {
    }

    public class ConsumerThread extends Thread {
        public boolean stop = false;

        public void run() {
            while (!stop) {
                try {
                    ShimmerMsg shimmerMSG = mQueue.take();
                    if (shimmerMSG.mB instanceof EndThread) {
                        stop = true;
                    } else {
                        processMsgFromCallback(shimmerMSG);
                    }
                } catch (InterruptedException e) {
                    System.out.print("QUE BLOCKED");
                    e.printStackTrace();
                }
            }

            removeSetWaitForDataAll();

        }

        ;
    }

    public class WaitForData implements com.shimmerresearch.driver.Callable {
        BasicProcessWithCallBack track;

        public WaitForData(BasicProcessWithCallBack bpwcb) {
            track = bpwcb;
            bpwcb.passCallback(this);
        }

        public BasicProcessWithCallBack returnBasicProcessWithCallBack() {
            return track;
        }

        @Override
        public void callBackMethod(ShimmerMsg s) {
            try {
                mQueue.put(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void callBackMethod(int i, Object ojc) {
            try {
                mQueue.put(new ShimmerMsg(i, ojc));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
