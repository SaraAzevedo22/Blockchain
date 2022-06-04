// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: test/test.proto

package com.proto.test;

/**
 * Protobuf type {@code test.KNodes}
 */
public final class KNodes extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:test.KNodes)
    KNodesOrBuilder {
private static final long serialVersionUID = 0L;
  // Use KNodes.newBuilder() to construct.
  private KNodes(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private KNodes() {
    kbucket_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new KNodes();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private KNodes(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              kbucket_ = new java.util.ArrayList<com.proto.test.NodeforKNodes>();
              mutable_bitField0_ |= 0x00000001;
            }
            kbucket_.add(
                input.readMessage(com.proto.test.NodeforKNodes.parser(), extensionRegistry));
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        kbucket_ = java.util.Collections.unmodifiableList(kbucket_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.proto.test.Test.internal_static_test_KNodes_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.proto.test.Test.internal_static_test_KNodes_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.proto.test.KNodes.class, com.proto.test.KNodes.Builder.class);
  }

  public static final int KBUCKET_FIELD_NUMBER = 1;
  private java.util.List<com.proto.test.NodeforKNodes> kbucket_;
  /**
   * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
   */
  @java.lang.Override
  public java.util.List<com.proto.test.NodeforKNodes> getKbucketList() {
    return kbucket_;
  }
  /**
   * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.proto.test.NodeforKNodesOrBuilder> 
      getKbucketOrBuilderList() {
    return kbucket_;
  }
  /**
   * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
   */
  @java.lang.Override
  public int getKbucketCount() {
    return kbucket_.size();
  }
  /**
   * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
   */
  @java.lang.Override
  public com.proto.test.NodeforKNodes getKbucket(int index) {
    return kbucket_.get(index);
  }
  /**
   * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
   */
  @java.lang.Override
  public com.proto.test.NodeforKNodesOrBuilder getKbucketOrBuilder(
      int index) {
    return kbucket_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < kbucket_.size(); i++) {
      output.writeMessage(1, kbucket_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < kbucket_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, kbucket_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.proto.test.KNodes)) {
      return super.equals(obj);
    }
    com.proto.test.KNodes other = (com.proto.test.KNodes) obj;

    if (!getKbucketList()
        .equals(other.getKbucketList())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getKbucketCount() > 0) {
      hash = (37 * hash) + KBUCKET_FIELD_NUMBER;
      hash = (53 * hash) + getKbucketList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.proto.test.KNodes parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.proto.test.KNodes parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.proto.test.KNodes parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.proto.test.KNodes parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.proto.test.KNodes parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.proto.test.KNodes parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.proto.test.KNodes parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.proto.test.KNodes parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.proto.test.KNodes parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.proto.test.KNodes parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.proto.test.KNodes parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.proto.test.KNodes parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.proto.test.KNodes prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code test.KNodes}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:test.KNodes)
      com.proto.test.KNodesOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.proto.test.Test.internal_static_test_KNodes_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.proto.test.Test.internal_static_test_KNodes_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.proto.test.KNodes.class, com.proto.test.KNodes.Builder.class);
    }

    // Construct using com.proto.test.KNodes.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getKbucketFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (kbucketBuilder_ == null) {
        kbucket_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        kbucketBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.proto.test.Test.internal_static_test_KNodes_descriptor;
    }

    @java.lang.Override
    public com.proto.test.KNodes getDefaultInstanceForType() {
      return com.proto.test.KNodes.getDefaultInstance();
    }

    @java.lang.Override
    public com.proto.test.KNodes build() {
      com.proto.test.KNodes result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.proto.test.KNodes buildPartial() {
      com.proto.test.KNodes result = new com.proto.test.KNodes(this);
      int from_bitField0_ = bitField0_;
      if (kbucketBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          kbucket_ = java.util.Collections.unmodifiableList(kbucket_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.kbucket_ = kbucket_;
      } else {
        result.kbucket_ = kbucketBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.proto.test.KNodes) {
        return mergeFrom((com.proto.test.KNodes)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.proto.test.KNodes other) {
      if (other == com.proto.test.KNodes.getDefaultInstance()) return this;
      if (kbucketBuilder_ == null) {
        if (!other.kbucket_.isEmpty()) {
          if (kbucket_.isEmpty()) {
            kbucket_ = other.kbucket_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureKbucketIsMutable();
            kbucket_.addAll(other.kbucket_);
          }
          onChanged();
        }
      } else {
        if (!other.kbucket_.isEmpty()) {
          if (kbucketBuilder_.isEmpty()) {
            kbucketBuilder_.dispose();
            kbucketBuilder_ = null;
            kbucket_ = other.kbucket_;
            bitField0_ = (bitField0_ & ~0x00000001);
            kbucketBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getKbucketFieldBuilder() : null;
          } else {
            kbucketBuilder_.addAllMessages(other.kbucket_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.proto.test.KNodes parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.proto.test.KNodes) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.proto.test.NodeforKNodes> kbucket_ =
      java.util.Collections.emptyList();
    private void ensureKbucketIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        kbucket_ = new java.util.ArrayList<com.proto.test.NodeforKNodes>(kbucket_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.proto.test.NodeforKNodes, com.proto.test.NodeforKNodes.Builder, com.proto.test.NodeforKNodesOrBuilder> kbucketBuilder_;

    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public java.util.List<com.proto.test.NodeforKNodes> getKbucketList() {
      if (kbucketBuilder_ == null) {
        return java.util.Collections.unmodifiableList(kbucket_);
      } else {
        return kbucketBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public int getKbucketCount() {
      if (kbucketBuilder_ == null) {
        return kbucket_.size();
      } else {
        return kbucketBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public com.proto.test.NodeforKNodes getKbucket(int index) {
      if (kbucketBuilder_ == null) {
        return kbucket_.get(index);
      } else {
        return kbucketBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public Builder setKbucket(
        int index, com.proto.test.NodeforKNodes value) {
      if (kbucketBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureKbucketIsMutable();
        kbucket_.set(index, value);
        onChanged();
      } else {
        kbucketBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public Builder setKbucket(
        int index, com.proto.test.NodeforKNodes.Builder builderForValue) {
      if (kbucketBuilder_ == null) {
        ensureKbucketIsMutable();
        kbucket_.set(index, builderForValue.build());
        onChanged();
      } else {
        kbucketBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public Builder addKbucket(com.proto.test.NodeforKNodes value) {
      if (kbucketBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureKbucketIsMutable();
        kbucket_.add(value);
        onChanged();
      } else {
        kbucketBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public Builder addKbucket(
        int index, com.proto.test.NodeforKNodes value) {
      if (kbucketBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureKbucketIsMutable();
        kbucket_.add(index, value);
        onChanged();
      } else {
        kbucketBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public Builder addKbucket(
        com.proto.test.NodeforKNodes.Builder builderForValue) {
      if (kbucketBuilder_ == null) {
        ensureKbucketIsMutable();
        kbucket_.add(builderForValue.build());
        onChanged();
      } else {
        kbucketBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public Builder addKbucket(
        int index, com.proto.test.NodeforKNodes.Builder builderForValue) {
      if (kbucketBuilder_ == null) {
        ensureKbucketIsMutable();
        kbucket_.add(index, builderForValue.build());
        onChanged();
      } else {
        kbucketBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public Builder addAllKbucket(
        java.lang.Iterable<? extends com.proto.test.NodeforKNodes> values) {
      if (kbucketBuilder_ == null) {
        ensureKbucketIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, kbucket_);
        onChanged();
      } else {
        kbucketBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public Builder clearKbucket() {
      if (kbucketBuilder_ == null) {
        kbucket_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        kbucketBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public Builder removeKbucket(int index) {
      if (kbucketBuilder_ == null) {
        ensureKbucketIsMutable();
        kbucket_.remove(index);
        onChanged();
      } else {
        kbucketBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public com.proto.test.NodeforKNodes.Builder getKbucketBuilder(
        int index) {
      return getKbucketFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public com.proto.test.NodeforKNodesOrBuilder getKbucketOrBuilder(
        int index) {
      if (kbucketBuilder_ == null) {
        return kbucket_.get(index);  } else {
        return kbucketBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public java.util.List<? extends com.proto.test.NodeforKNodesOrBuilder> 
         getKbucketOrBuilderList() {
      if (kbucketBuilder_ != null) {
        return kbucketBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(kbucket_);
      }
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public com.proto.test.NodeforKNodes.Builder addKbucketBuilder() {
      return getKbucketFieldBuilder().addBuilder(
          com.proto.test.NodeforKNodes.getDefaultInstance());
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public com.proto.test.NodeforKNodes.Builder addKbucketBuilder(
        int index) {
      return getKbucketFieldBuilder().addBuilder(
          index, com.proto.test.NodeforKNodes.getDefaultInstance());
    }
    /**
     * <code>repeated .test.NodeforKNodes kbucket = 1;</code>
     */
    public java.util.List<com.proto.test.NodeforKNodes.Builder> 
         getKbucketBuilderList() {
      return getKbucketFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.proto.test.NodeforKNodes, com.proto.test.NodeforKNodes.Builder, com.proto.test.NodeforKNodesOrBuilder> 
        getKbucketFieldBuilder() {
      if (kbucketBuilder_ == null) {
        kbucketBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.proto.test.NodeforKNodes, com.proto.test.NodeforKNodes.Builder, com.proto.test.NodeforKNodesOrBuilder>(
                kbucket_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        kbucket_ = null;
      }
      return kbucketBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:test.KNodes)
  }

  // @@protoc_insertion_point(class_scope:test.KNodes)
  private static final com.proto.test.KNodes DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.proto.test.KNodes();
  }

  public static com.proto.test.KNodes getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<KNodes>
      PARSER = new com.google.protobuf.AbstractParser<KNodes>() {
    @java.lang.Override
    public KNodes parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new KNodes(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<KNodes> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<KNodes> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.proto.test.KNodes getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
