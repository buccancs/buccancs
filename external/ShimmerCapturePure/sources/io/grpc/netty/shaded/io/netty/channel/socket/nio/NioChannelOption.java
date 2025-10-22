package io.grpc.netty.shaded.io.netty.channel.socket.nio;

import io.grpc.alts.CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0;
import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;

import java.io.IOException;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.channels.Channel;
import java.nio.channels.NetworkChannel;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes3.dex */
public final class NioChannelOption<T> extends ChannelOption<T> {
    private final SocketOption<T> option;

    private NioChannelOption(SocketOption<T> socketOption) {
        super(socketOption.name());
        this.option = socketOption;
    }

    public static <T> ChannelOption<T> of(SocketOption<T> socketOption) {
        return new NioChannelOption(socketOption);
    }

    static <T> boolean setOption(Channel channel, NioChannelOption<T> nioChannelOption, T t) throws IOException {
        NetworkChannel networkChannelM6292m = CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m6292m((Object) channel);
        if (!networkChannelM6292m.supportedOptions().contains(((NioChannelOption) nioChannelOption).option)) {
            return false;
        }
        if ((networkChannelM6292m instanceof ServerSocketChannel) && ((NioChannelOption) nioChannelOption).option == StandardSocketOptions.IP_TOS) {
            return false;
        }
        try {
            networkChannelM6292m.setOption(((NioChannelOption) nioChannelOption).option, t);
            return true;
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    static <T> T getOption(Channel channel, NioChannelOption<T> nioChannelOption) {
        NetworkChannel networkChannelM6292m = CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m6292m((Object) channel);
        if (!networkChannelM6292m.supportedOptions().contains(((NioChannelOption) nioChannelOption).option)) {
            return null;
        }
        if ((networkChannelM6292m instanceof ServerSocketChannel) && ((NioChannelOption) nioChannelOption).option == StandardSocketOptions.IP_TOS) {
            return null;
        }
        try {
            return (T) networkChannelM6292m.getOption(((NioChannelOption) nioChannelOption).option);
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    static ChannelOption[] getOptions(Channel channel) {
        NetworkChannel networkChannelM6292m = CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m6292m((Object) channel);
        Set setSupportedOptions = networkChannelM6292m.supportedOptions();
        int i = 0;
        if (networkChannelM6292m instanceof ServerSocketChannel) {
            ArrayList arrayList = new ArrayList(setSupportedOptions.size());
            Iterator it2 = setSupportedOptions.iterator();
            while (it2.hasNext()) {
                SocketOption socketOptionM = CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m(it2.next());
                if (socketOptionM != StandardSocketOptions.IP_TOS) {
                    arrayList.add(new NioChannelOption(socketOptionM));
                }
            }
            return (ChannelOption[]) arrayList.toArray(new ChannelOption[0]);
        }
        ChannelOption[] channelOptionArr = new ChannelOption[setSupportedOptions.size()];
        Iterator it3 = setSupportedOptions.iterator();
        while (it3.hasNext()) {
            channelOptionArr[i] = new NioChannelOption(CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m(it3.next()));
            i++;
        }
        return channelOptionArr;
    }
}
