package com.autumn.service.grpc;

import com.autumn.proto.HelloRequest;
import com.autumn.proto.HelloServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class HelloGrpcService {
    @GrpcClient("helloService")
    private HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub;

    public String helloMessage(String name) {
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        return helloServiceBlockingStub.sayHello(request).getMessage();
    }

}
