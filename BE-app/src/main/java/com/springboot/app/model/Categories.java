package com.springboot.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Categories implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String slug;
	
	public Categories(String name, String slug) {
		this.name=name;
		this.slug=slug;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	@Column(name = "slug", nullable = false)
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug=slug;
	}
	
	@Override
	public String toString() {
		return "Catefory [id=" + id + ", name=" + name + ", slug=" + slug + "]";
	}
}
