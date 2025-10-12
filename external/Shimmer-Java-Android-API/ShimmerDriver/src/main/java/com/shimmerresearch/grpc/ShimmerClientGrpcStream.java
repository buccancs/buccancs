package com.shimmerresearch.grpc;

import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.driver.BasicProcessWithCallBack;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerMsg;
import com.shimmerresearch.grpc.ShimmerGRPC;
import com.shimmerresearch.grpc.ShimmerGRPC.FileByteTransfer;
import com.shimmerresearch.grpc.ShimmerServerGrpc;
import com.shimmerresearch.grpc.ShimmerGRPC.HelloReply;
import com.shimmerresearch.grpc.ShimmerGRPC.HelloRequest;
import com.shimmerresearch.grpc.ShimmerGRPC.ObjectCluster2;
import com.shimmerresearch.grpc.ShimmerGRPC.StreamRequest;
import com.shimmerresearch.grpc.ShimmerServerGrpc.ShimmerServerStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class ShimmerClientGrpcStream extends BasicProcessWithCallBack {
    private final ManagedChannel channel;
    private final ShimmerServerGrpc.ShimmerServerStub stub;
    ObjectCluster mLastRXOJC;
    StreamObserver<ObjectCluster2> requestObserver;
    StreamObserver<FileByteTransfer> requestObserverFile;

    public ShimmerClientGrpcStream(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        stub = ShimmerServerGrpc.newStub(channel);
        StreamRequest sreq = StreamRequest.newBuilder().setMessage("All").build();


        StreamObserver<HelloReply> responseObserver = new StreamObserver<HelloReply>() {


            @Override
            public void onNext(HelloReply value) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        };

        StreamObserver<HelloReply> responseObserverFile = new StreamObserver<HelloReply>() {


            @Override
            public void onNext(HelloReply value) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        };

        requestObserver = stub.sendDataStream(responseObserver);
        requestObserverFile = stub.sendFileStream(responseObserverFile);


        stub.getDataStream(sreq, new StreamObserver<ObjectCluster2>() {

            @Override
            public void onNext(ObjectCluster2 value) {
                ObjectCluster ojc = new ObjectCluster(value);
                mLastRXOJC = ojc;
                sendCallBackMsg(ShimmerBluetooth.MSG_IDENTIFIER_DATA_PACKET, ojc);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }

        });
		  
		  /*
		  stub.sayHello(req, new StreamObserver<HelloReply>(){

				@Override
				public void onNext(HelloReply value) {
					 System.out.println(value.getMessage());
				}

				@Override
				public void onError(Throwable t) {
					System.out.println("ERROR");
				}

				@Override
				public void onCompleted() {
					System.out.println("COMPLETED");
				}});
				*/


    }

    public void sendFile(FileByteTransfer file) {
        requestObserverFile.onNext(file);
    }

    public void sendOJCToServer(ObjectCluster2 ojc) {
        requestObserver.onNext(ojc);
    }

    public ObjectCluster getLastReceivedOJC() {
        return mLastRXOJC;
    }

    @Override
    protected void processMsgFromCallback(ShimmerMsg shimmerMSG) {

    }
}
