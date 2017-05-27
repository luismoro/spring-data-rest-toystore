package br.com.toystore.repository;

import br.com.toystore.model.Product;
import br.com.toystore.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by luismoro on 23/05/17.
 */
@RepositoryRestResource
public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {
}
