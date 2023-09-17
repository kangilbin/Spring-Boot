package com.example.orderservice.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name="orders")
public class OrderEntity implements Serializable { // Serializable : 직렬화, 해당 객체를 전송하거나 데이터를 보관하기 위해서

}
