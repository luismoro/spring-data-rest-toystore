package br.com.toystore.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import java.io.Serializable;

/**
 * Created by luismoro on 23/05/17.
 */
@Entity
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq")
    private Long id;
    @NotNull
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull
    private ProductType productType;
    private static final Logger LOGGER = LoggerFactory.getLogger(Product.class);

    public Product(){}

    public Product(String description) {
        this.description = description;
    }

    @ConstructorProperties({"id", "description", "productType"})
    public Product(Long id, String description, ProductType productType) {
        this.id = id;
        this.description = description;
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
