package com.redhat.devnation.infinispan;

import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

import com.redhat.devnation.model.Gender;
import com.redhat.devnation.model.Sheep;

@AutoProtoSchemaBuilder(
      includeClasses = {
            Gender.class,
            Sheep.class
      },
      schemaFileName = "sheep.proto",
      schemaFilePath = "proto/", 
      schemaPackageName = "devnation")
interface ModelInitializer extends SerializationContextInitializer {
}