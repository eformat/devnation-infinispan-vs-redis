package com.redhat.devnation.model;

import org.infinispan.protostream.annotations.ProtoEnumValue;

public enum Gender {
   @ProtoEnumValue(number = 0)
   MALE,
   @ProtoEnumValue(number = 1)
   FEMALE
}
