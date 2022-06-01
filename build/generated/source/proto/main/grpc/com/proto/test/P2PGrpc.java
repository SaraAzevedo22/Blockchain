package com.proto.test;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.1)",
    comments = "Source: test/test.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class P2PGrpc {

  private P2PGrpc() {}

  public static final String SERVICE_NAME = "test.P2P";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.proto.test.PingID,
      com.proto.test.Ping> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ping",
      requestType = com.proto.test.PingID.class,
      responseType = com.proto.test.Ping.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.proto.test.PingID,
      com.proto.test.Ping> getPingMethod() {
    io.grpc.MethodDescriptor<com.proto.test.PingID, com.proto.test.Ping> getPingMethod;
    if ((getPingMethod = P2PGrpc.getPingMethod) == null) {
      synchronized (P2PGrpc.class) {
        if ((getPingMethod = P2PGrpc.getPingMethod) == null) {
          P2PGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<com.proto.test.PingID, com.proto.test.Ping>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.test.PingID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.test.Ping.getDefaultInstance()))
              .setSchemaDescriptor(new P2PMethodDescriptorSupplier("ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static P2PStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<P2PStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<P2PStub>() {
        @java.lang.Override
        public P2PStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new P2PStub(channel, callOptions);
        }
      };
    return P2PStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static P2PBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<P2PBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<P2PBlockingStub>() {
        @java.lang.Override
        public P2PBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new P2PBlockingStub(channel, callOptions);
        }
      };
    return P2PBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static P2PFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<P2PFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<P2PFutureStub>() {
        @java.lang.Override
        public P2PFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new P2PFutureStub(channel, callOptions);
        }
      };
    return P2PFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class P2PImplBase implements io.grpc.BindableService {

    /**
     */
    public void ping(com.proto.test.PingID request,
        io.grpc.stub.StreamObserver<com.proto.test.Ping> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPingMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.proto.test.PingID,
                com.proto.test.Ping>(
                  this, METHODID_PING)))
          .build();
    }
  }

  /**
   */
  public static final class P2PStub extends io.grpc.stub.AbstractAsyncStub<P2PStub> {
    private P2PStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected P2PStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new P2PStub(channel, callOptions);
    }

    /**
     */
    public void ping(com.proto.test.PingID request,
        io.grpc.stub.StreamObserver<com.proto.test.Ping> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class P2PBlockingStub extends io.grpc.stub.AbstractBlockingStub<P2PBlockingStub> {
    private P2PBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected P2PBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new P2PBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.proto.test.Ping ping(com.proto.test.PingID request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class P2PFutureStub extends io.grpc.stub.AbstractFutureStub<P2PFutureStub> {
    private P2PFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected P2PFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new P2PFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.proto.test.Ping> ping(
        com.proto.test.PingID request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PING = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final P2PImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(P2PImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PING:
          serviceImpl.ping((com.proto.test.PingID) request,
              (io.grpc.stub.StreamObserver<com.proto.test.Ping>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class P2PBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    P2PBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.proto.test.Test.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("P2P");
    }
  }

  private static final class P2PFileDescriptorSupplier
      extends P2PBaseDescriptorSupplier {
    P2PFileDescriptorSupplier() {}
  }

  private static final class P2PMethodDescriptorSupplier
      extends P2PBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    P2PMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (P2PGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new P2PFileDescriptorSupplier())
              .addMethod(getPingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
