package com.turing.backendChallenge.Controller;

import com.google.common.base.Joiner;
import com.turing.backendChallenge.Entity.Cart;
import com.turing.backendChallenge.Entity.Customer;
import com.turing.backendChallenge.Entity.Product;
import com.turing.backendChallenge.Repo.CustomerRepo;
import com.turing.backendChallenge.Repo.ProductRepo;
import com.turing.backendChallenge.Repo.SearchOperations.ProductSpecBuilder;
import com.turing.backendChallenge.Repo.SearchOperations.SearchOperations;
import com.turing.backendChallenge.Repo.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping(value = "/")
public class ProductController {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    ShoppingCartRepo shoppingCartRepo;
    @Autowired
    CustomerRepo customerRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    @ResponseBody
    public List<Product> findAllBySpecification(@RequestParam(value = "search") String search) {
        ProductSpecBuilder builder = new ProductSpecBuilder();
        String operationSetExper = Joiner.on("|").join(SearchOperations.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile(
                "(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(
                    matcher.group(1),
                    matcher.group(2),
                    matcher.group(4),
                    matcher.group(3),
                    matcher.group(5));
        }

        Specification<Product> spec = builder.build();
        return productRepo.findAll(spec);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/shoppingcart/add")
    public List<Cart> addToCart(@RequestBody final Cart cart) {
        shoppingCartRepo.save(cart);
        return shoppingCartRepo.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/orders")
    public List<Customer> creatOrder(@RequestBody final Customer customer) {
        customerRepo.save(customer);
        return customerRepo.findAll();
    }
}
