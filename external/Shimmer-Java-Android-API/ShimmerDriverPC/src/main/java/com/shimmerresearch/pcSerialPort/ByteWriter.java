package com.shimmerresearch.pcSerialPort;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface ByteWriter {

    void write(byte[] bytes) throws IOException;

    void write(byte oneByte) throws IOException;

    void write(byte[] bytes, long timeout) throws IOException, InterruptedException, TimeoutException;

    void write(byte oneByte, long timeout) throws IOException, InterruptedException, TimeoutException;

    void cancelWrite() throws IOException;

}