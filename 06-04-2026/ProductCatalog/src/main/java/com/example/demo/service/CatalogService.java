package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.*;

@Service
public class CatalogService implements ICatalogService {
	
	@Autowired
	RestTemplate rt;

    @Override
    public List<Catalog> getWholeCatalog() {
        Product[] products = rt.getForObject("http://PRODUCT/product", Product[].class);
        List<Catalog> list = new ArrayList<>();
        for (Product p : products) {
            list.add(buildCatalog(p.getId()));
        }
        return list;
    }

    @Override
    public Catalog getCatalogById(int id) {
        return buildCatalog(id);
    }

    @Override
    public List<Catalog> getCatalogByCategory(String category) {
        Product[] products = rt.getForObject("http://PRODUCT/product", Product[].class);
        List<Catalog> list = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equals(category)) {
                list.add(buildCatalog(p.getId()));
            }
        }
        return list;
    }

    private Catalog buildCatalog(int id) {
        Product product = rt.getForObject("http://PRODUCT/product/"+ id, Product.class);
        Pricing pricing = rt.getForObject("http://PRICING/pricing/" + id, Pricing.class);
        Stock stock = rt.getForObject("http://STOCK/stock/" + id, Stock.class);

        Catalog c = new Catalog();

        c.setId(product.getId());
        c.setName(product.getName());
        c.setCategory(product.getCategory());
        c.setOfferPrice(pricing.getOfferPrice());
        c.setItemsLeft(stock.getItemsLeft());

        return c;
    }
}