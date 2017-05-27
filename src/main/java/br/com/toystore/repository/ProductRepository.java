package br.com.toystore.repository;

import br.com.toystore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by luismoro on 23/05/17.
 */
@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Long> {

    // /products/search/findByDescription?description=description1
    List<Product> findByDescription(@Param("description") String description);

    // /products/search/advancedSearch?description=description1&productType=1
    @Query("select e from Product e "
            +"where (description=:description) "
            +"and (productType.id=:productType) ")
    Page advancedSearch(@Param("description") String description,
                               @Param("productType") Long productType,
                               Pageable page);

}
