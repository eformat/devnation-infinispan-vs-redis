package com.redhat.devnation.model;


import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

public class Sheep {
   @ProtoField(number = 1, required = true, defaultValue = "0")
   int id;
   @ProtoField(number = 2)
   String name;
   @ProtoField(number = 3)
   Gender gender;
   @ProtoField(number = 4)
   Integer age;

   Sheep(){}

   @ProtoFactory
   public Sheep(int id, String name, Gender gender, Integer age) {
      this.id = id;
      this.name = name;
      this.gender = gender;
      this.age = age;
   }

   public int getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public Integer getAge() {
      return age;
   }

   public Gender getGender() {
      return gender;
   }

   @Override
   public String toString() {
      return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", gender=" + gender +
            '}';
   }
}
