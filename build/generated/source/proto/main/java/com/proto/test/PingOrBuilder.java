// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: test/test.proto

package com.proto.test;

public interface PingOrBuilder extends
    // @@protoc_insertion_point(interface_extends:test.Ping)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string id = 1;</code>
   * @return The id.
   */
  java.lang.String getId();
  /**
   * <code>string id = 1;</code>
   * @return The bytes for id.
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <code>string ipAddress = 2;</code>
   * @return The ipAddress.
   */
  java.lang.String getIpAddress();
  /**
   * <code>string ipAddress = 2;</code>
   * @return The bytes for ipAddress.
   */
  com.google.protobuf.ByteString
      getIpAddressBytes();

  /**
   * <code>int32 portNo = 3;</code>
   * @return The portNo.
   */
  int getPortNo();

  /**
   * <code>int32 nonce = 4;</code>
   * @return The nonce.
   */
  int getNonce();

  /**
   * <code>string publicKey = 5;</code>
   * @return The publicKey.
   */
  java.lang.String getPublicKey();
  /**
   * <code>string publicKey = 5;</code>
   * @return The bytes for publicKey.
   */
  com.google.protobuf.ByteString
      getPublicKeyBytes();
}
