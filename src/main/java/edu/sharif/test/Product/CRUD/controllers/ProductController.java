package edu.sharif.test.Product.CRUD.controllers;

import edu.sharif.test.Product.CRUD.models.Product;
import edu.sharif.test.Product.CRUD.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public List<Product> all(){
        return productService.getProducts();
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id){
        Product product = productService.findById(id);
        if(product!=null){
            return new ResponseEntity<>(product,new HttpHeaders() , HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404
        }
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Product product){
        boolean contained = productService.getProducts().contains(product);
        if(contained){
            return new ResponseEntity<>("Repeated Product!",HttpStatus.FORBIDDEN);//403
        }
        boolean isAdded= productService.addProduct(product);
        if(isAdded){
            return new ResponseEntity(product,HttpStatus.CREATED); //201
        }else{
            return new ResponseEntity("Invalid Information",HttpStatus.BAD_REQUEST); //400
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteById(@RequestHeader("id") Integer id){
        boolean isDeleted = productService.deleteById(id);
        //boolean isDeleted = false; - For Crating False Result
        if(isDeleted){
            return new ResponseEntity<>(isDeleted,HttpStatus.OK); //200
        }else{
            return new ResponseEntity<>(isDeleted,HttpStatus.NOT_FOUND); //404
        }
    }


}
