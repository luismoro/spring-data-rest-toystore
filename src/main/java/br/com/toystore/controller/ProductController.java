package br.com.toystore.controller;

import br.com.toystore.model.Product;
import br.com.toystore.repository.ProductRepository;
import br.com.toystore.service.facede.ProductFacade;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.service.exception.InternalServerErrorException;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by luismoro on 25/05/17.
 */

@RestController
@RequestMapping("/v2/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductFacade productFacade;

    //Heroku
    // https://spring-data-rest-toystore.herokuapp.com
    
    //Local
    // GET localhost:8080/productTypes
    // POST localhost:8080/productTypes - {"name":"name2"}
    // GET localhost:8080/products
    // POST localhost:8080/products { "description":"description1", "productType" : {"id" : 1} }
    // GET localhost:8080/products/search/findByDescription?description=description1
    // GET localhost:8080/products/search/advancedSearch?description=description1&productType=1

    // GET localhost:8080/v2/products
    @RequestMapping()
    public Iterable<Product> products() {
        return productRepository.findAll();
    }

    // GET localhost:8080/v2/products/chat?mesage=hello&workspace=Car Dashboard - Sample
    @RequestMapping("/chat")
    public MessageResponse chat(@Param("mesage") String mesage, @Param("workspace") String workspace) throws UnirestException, IOException, InternalServerErrorException {
       return productFacade.getMessageResponse(mesage, workspace);
    }
}
