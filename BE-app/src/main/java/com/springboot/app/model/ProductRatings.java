package com.springboot.app.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productRatings")
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRatings implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "star", nullable = false)
	private int star;
	
	@Column(name = "comment", nullable = false)
	private String comment;
	
	@Column(name = "createDate")
	private Date createDate;
	
	@JoinColumn(name = "accid", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Accounts account;
	
	@JoinColumn(name = "prodid", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Products product;
}
