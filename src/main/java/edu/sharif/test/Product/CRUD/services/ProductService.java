package edu.sharif.test.Product.CRUD.services;

import edu.sharif.test.Product.CRUD.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    public static List<Product> products = new ArrayList<>();
    static {
        products.add(new Product(1,"Slipper",100000));
        products.add(new Product(2,"Introduction to Testing Book",300000));
        products.add(new Product(3,"ASUS Lap Top",42000000));
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean addProduct(Product product){
        return products.add(product);
    }

    public Product findById(int id){
        for (Product p : products){
            if(p.getId()==id){
                return p;
            }
        }
        return null;
    }

    public boolean deleteById(int id){
        Product product = null;
        for (Product p:products) {
            if(p.getId()==id){
                product=p;
            }
        }

        if(product==null){
            return false;
        }else{
            return products.remove(product);
        }
    }
}
