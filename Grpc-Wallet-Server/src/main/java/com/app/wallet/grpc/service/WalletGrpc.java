package com.app.wallet.grpc.service;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.14.0)",
    comments = "Source: wallet.proto")
public final class WalletGrpc {

  private WalletGrpc() {}

  public static final String SERVICE_NAME = "Wallet";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.app.wallet.grpc.service.WalletOuterClass.CreateRequest,
      com.app.wallet.grpc.service.WalletOuterClass.CreateResponse> getCreateWalletMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createWallet",
      requestType = com.app.wallet.grpc.service.WalletOuterClass.CreateRequest.class,
      responseType = com.app.wallet.grpc.service.WalletOuterClass.CreateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.app.wallet.grpc.service.WalletOuterClass.CreateRequest,
      com.app.wallet.grpc.service.WalletOuterClass.CreateResponse> getCreateWalletMethod() {
    io.grpc.MethodDescriptor<com.app.wallet.grpc.service.WalletOuterClass.CreateRequest, com.app.wallet.grpc.service.WalletOuterClass.CreateResponse> getCreateWalletMethod;
    if ((getCreateWalletMethod = WalletGrpc.getCreateWalletMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getCreateWalletMethod = WalletGrpc.getCreateWalletMethod) == null) {
          WalletGrpc.getCreateWalletMethod = getCreateWalletMethod = 
              io.grpc.MethodDescriptor.<com.app.wallet.grpc.service.WalletOuterClass.CreateRequest, com.app.wallet.grpc.service.WalletOuterClass.CreateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Wallet", "createWallet"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.app.wallet.grpc.service.WalletOuterClass.CreateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.app.wallet.grpc.service.WalletOuterClass.CreateResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("createWallet"))
                  .build();
          }
        }
     }
     return getCreateWalletMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest,
      com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse> getDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deposit",
      requestType = com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest.class,
      responseType = com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest,
      com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse> getDepositMethod() {
    io.grpc.MethodDescriptor<com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest, com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse> getDepositMethod;
    if ((getDepositMethod = WalletGrpc.getDepositMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getDepositMethod = WalletGrpc.getDepositMethod) == null) {
          WalletGrpc.getDepositMethod = getDepositMethod = 
              io.grpc.MethodDescriptor.<com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest, com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Wallet", "deposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("deposit"))
                  .build();
          }
        }
     }
     return getDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest,
      com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse> getWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "withdraw",
      requestType = com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest.class,
      responseType = com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest,
      com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse> getWithdrawMethod() {
    io.grpc.MethodDescriptor<com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest, com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse> getWithdrawMethod;
    if ((getWithdrawMethod = WalletGrpc.getWithdrawMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getWithdrawMethod = WalletGrpc.getWithdrawMethod) == null) {
          WalletGrpc.getWithdrawMethod = getWithdrawMethod = 
              io.grpc.MethodDescriptor.<com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest, com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Wallet", "withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("withdraw"))
                  .build();
          }
        }
     }
     return getWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.app.wallet.grpc.service.WalletOuterClass.BalanceRequest,
      com.app.wallet.grpc.service.WalletOuterClass.BalanceResponse> getBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "balance",
      requestType = com.app.wallet.grpc.service.WalletOuterClass.BalanceRequest.class,
      responseType = com.app.wallet.grpc.service.WalletOuterClass.BalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.app.wallet.grpc.service.WalletOuterClass.BalanceRequest,
      com.app.wallet.grpc.service.WalletOuterClass.BalanceResponse> getBalanceMethod() {
    io.grpc.MethodDescriptor<com.app.wallet.grpc.service.WalletOuterClass.BalanceRequest, com.app.wallet.grpc.service.WalletOuterClass.BalanceResponse> getBalanceMethod;
    if ((getBalanceMethod = WalletGrpc.getBalanceMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getBalanceMethod = WalletGrpc.getBalanceMethod) == null) {
          WalletGrpc.getBalanceMethod = getBalanceMethod = 
              io.grpc.MethodDescriptor.<com.app.wallet.grpc.service.WalletOuterClass.BalanceRequest, com.app.wallet.grpc.service.WalletOuterClass.BalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Wallet", "balance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.app.wallet.grpc.service.WalletOuterClass.BalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.app.wallet.grpc.service.WalletOuterClass.BalanceResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("balance"))
                  .build();
          }
        }
     }
     return getBalanceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WalletStub newStub(io.grpc.Channel channel) {
    return new WalletStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WalletBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new WalletBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static WalletFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new WalletFutureStub(channel);
  }

  /**
   */
  public static abstract class WalletImplBase implements io.grpc.BindableService {

    /**
     */
    public void createWallet(com.app.wallet.grpc.service.WalletOuterClass.CreateRequest request,
        io.grpc.stub.StreamObserver<com.app.wallet.grpc.service.WalletOuterClass.CreateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateWalletMethod(), responseObserver);
    }

    /**
     */
    public void deposit(com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest request,
        io.grpc.stub.StreamObserver<com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositMethod(), responseObserver);
    }

    /**
     */
    public void withdraw(com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest request,
        io.grpc.stub.StreamObserver<com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    /**
     */
    public void balance(com.app.wallet.grpc.service.WalletOuterClass.BalanceRequest request,
        io.grpc.stub.StreamObserver<com.app.wallet.grpc.service.WalletOuterClass.BalanceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBalanceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateWalletMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.app.wallet.grpc.service.WalletOuterClass.CreateRequest,
                com.app.wallet.grpc.service.WalletOuterClass.CreateResponse>(
                  this, METHODID_CREATE_WALLET)))
          .addMethod(
            getDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest,
                com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse>(
                  this, METHODID_DEPOSIT)))
          .addMethod(
            getWithdrawMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest,
                com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse>(
                  this, METHODID_WITHDRAW)))
          .addMethod(
            getBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.app.wallet.grpc.service.WalletOuterClass.BalanceRequest,
                com.app.wallet.grpc.service.WalletOuterClass.BalanceResponse>(
                  this, METHODID_BALANCE)))
          .build();
    }
  }

  /**
   */
  public static final class WalletStub extends io.grpc.stub.AbstractStub<WalletStub> {
    private WalletStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WalletStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WalletStub(channel, callOptions);
    }

    /**
     */
    public void createWallet(com.app.wallet.grpc.service.WalletOuterClass.CreateRequest request,
        io.grpc.stub.StreamObserver<com.app.wallet.grpc.service.WalletOuterClass.CreateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateWalletMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deposit(com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest request,
        io.grpc.stub.StreamObserver<com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void withdraw(com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest request,
        io.grpc.stub.StreamObserver<com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void balance(com.app.wallet.grpc.service.WalletOuterClass.BalanceRequest request,
        io.grpc.stub.StreamObserver<com.app.wallet.grpc.service.WalletOuterClass.BalanceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class WalletBlockingStub extends io.grpc.stub.AbstractStub<WalletBlockingStub> {
    private WalletBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WalletBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WalletBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.app.wallet.grpc.service.WalletOuterClass.CreateResponse createWallet(com.app.wallet.grpc.service.WalletOuterClass.CreateRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateWalletMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse deposit(com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest request) {
      return blockingUnaryCall(
          getChannel(), getDepositMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse withdraw(com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.app.wallet.grpc.service.WalletOuterClass.BalanceResponse balance(com.app.wallet.grpc.service.WalletOuterClass.BalanceRequest request) {
      return blockingUnaryCall(
          getChannel(), getBalanceMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class WalletFutureStub extends io.grpc.stub.AbstractStub<WalletFutureStub> {
    private WalletFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WalletFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WalletFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.app.wallet.grpc.service.WalletOuterClass.CreateResponse> createWallet(
        com.app.wallet.grpc.service.WalletOuterClass.CreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateWalletMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse> deposit(
        com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse> withdraw(
        com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.app.wallet.grpc.service.WalletOuterClass.BalanceResponse> balance(
        com.app.wallet.grpc.service.WalletOuterClass.BalanceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_WALLET = 0;
  private static final int METHODID_DEPOSIT = 1;
  private static final int METHODID_WITHDRAW = 2;
  private static final int METHODID_BALANCE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final WalletImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(WalletImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_WALLET:
          serviceImpl.createWallet((com.app.wallet.grpc.service.WalletOuterClass.CreateRequest) request,
              (io.grpc.stub.StreamObserver<com.app.wallet.grpc.service.WalletOuterClass.CreateResponse>) responseObserver);
          break;
        case METHODID_DEPOSIT:
          serviceImpl.deposit((com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest) request,
              (io.grpc.stub.StreamObserver<com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest) request,
              (io.grpc.stub.StreamObserver<com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse>) responseObserver);
          break;
        case METHODID_BALANCE:
          serviceImpl.balance((com.app.wallet.grpc.service.WalletOuterClass.BalanceRequest) request,
              (io.grpc.stub.StreamObserver<com.app.wallet.grpc.service.WalletOuterClass.BalanceResponse>) responseObserver);
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

  private static abstract class WalletBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    WalletBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.app.wallet.grpc.service.WalletOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Wallet");
    }
  }

  private static final class WalletFileDescriptorSupplier
      extends WalletBaseDescriptorSupplier {
    WalletFileDescriptorSupplier() {}
  }

  private static final class WalletMethodDescriptorSupplier
      extends WalletBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    WalletMethodDescriptorSupplier(String methodName) {
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
      synchronized (WalletGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WalletFileDescriptorSupplier())
              .addMethod(getCreateWalletMethod())
              .addMethod(getDepositMethod())
              .addMethod(getWithdrawMethod())
              .addMethod(getBalanceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
