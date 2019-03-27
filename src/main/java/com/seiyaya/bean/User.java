package com.seiyaya.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8386485176480631177L;
	/**
	 * 编号
	 */
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 名称
	 */
	private String name;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 出生时间
	 */
	private String birthday;
}
