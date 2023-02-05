```protobuf
syntax = "proto2";

option java_multiple_files = true;
option java_package = "com.pdd.pml.msg";

package pml.msg;

message Int64Array {
  repeated int64 value = 1;
}

message BytesArray {
  repeated bytes value = 1;// [ctype = STRING_PIECE];
}

message FloatArray {
  repeated float value = 1;
}

message Feature {
  // NOTE: Anyone who modifies this message MUST make a consistent
  // modification on feature/types.h.
  optional int32 tag = 1;
  repeated int64 int64_value = 2;
  repeated bytes bytes_value = 3;// [ctype = STRING_PIECE];
  repeated float float_value = 4;
  repeated Int64Array int64_array_value = 5;
  repeated BytesArray bytes_array_value = 6;
  repeated FloatArray float_array_value = 7;
}

message Features {
  repeated Feature feature = 1;
}

// To all polaris users(aka PRS, PQP, PUP, etc):
// Don't fill this message directly without polaris Session,
// otherwise the behaviou is undefined.
message FeatureColumn {
  optional Feature feature = 1;

  // Type of the column, which is set at the first time a ColumnFiller
  // modifies this column. Clearing a column clears its dtype as well,
  // default value is FEATURE_TYPE_UNKNOWN, which indicats that an empty
  // column is type-compatible for all types.
  optional int32 dtype = 2 [default = 0];

  // ********* Begin of shape related fields *********

  optional int32 row_count = 3 [default = 0];

  // A valid FeatureColumn fills either `dense_shape' for dense fields which all
  // rows have the same size of elements or `offset' for sparse/ragged fields.
  // Defaultly all features are treated as a single-element field, which is the
  // most common usage scenario in a prediction system.

  // If all rows have the same size(including zero for empty column),
  // `dense_shape' is set to the size of each row.
  // Prediction `row_count' * `dense_shape' == feature.`dtype'_value().size()
  // must hold in this case.
  // -1 otherwise and `offset' must be set appropriatly.
  optional int32 dense_shape = 4 [default = 1];

  // The offset of the first element of each row, size of which should euqal to
  // `row_count'.
  repeated int32 offset = 5 [packed = true];

  // ********* End of shape related fields *********
};

message FeatureColumns {
  // NOTE: Anyone who modifies this message MUST make a consistent
  // modification Session::SerializeColumnAsInColumns on feature/session.cc
  repeated FeatureColumn column = 1;
};

message Stat {
  optional int64 positive = 1 [default = 0];
  optional int64 negative = 2 [default = 1];
  optional int64 count = 3;
  optional int64 click = 4 [default = 0];
  optional int64 pay = 5 [default = 0];
  optional int64 pv = 6 [default = 0];
}

message Example {
  optional Features context = 1;
  optional Features document = 2;
  optional Stat target = 3;
  optional string id = 4;
  optional string sequence_id = 5;
  optional string sequence_stamp = 6;
  repeated Sequence sequence = 7;
}

message ExampleJoin {
  optional bytes example = 1;
  message StickFeature {
    optional int32 tag = 1;
    optional sfixed64 key = 2;
    optional Feature feature = 3;
  }
  repeated StickFeature stick_feature = 3;
}

message Sequence {
  repeated Example example = 1;
  optional string id = 2;
}

message ExamplesWithContext {
  optional Features context = 1;
  repeated Features documents = 2;
}

message TensorExamplesWithContext {
  optional bytes context = 1;
  repeated bytes documents = 2;
}

```

