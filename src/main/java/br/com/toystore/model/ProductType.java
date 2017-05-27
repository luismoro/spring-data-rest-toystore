package br.com.toystore.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import java.io.Serializable;

/**
 * Created by luismoro on 23/05/17.
 */

@Entity
public class ProductType implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_type_id_seq")
    @SequenceGenerator(name = "product_type_id_seq", sequenceName = "product_type_id_seq")
    private Long id;
    @NotNull
    private String name;

    public ProductType(){};

    @ConstructorProperties({"id", "name"})
    public ProductType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
