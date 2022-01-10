package org.springframework.samples.petclinic.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends NamedEntity {
	@Min(value = 0)
	@NotNull
	@Column(name = "price")
    double price;

	@ManyToOne
	@JoinColumn(name = "product_type")
	@NotNull
    ProductType productType;
}
