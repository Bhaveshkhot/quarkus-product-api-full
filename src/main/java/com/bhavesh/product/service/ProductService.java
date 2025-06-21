package com.bhavesh.product.service;

import com.bhavesh.product.entity.Product;
import jakarta.enterprise.context.ApplicationScoped;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class ProductService {

    private static final Logger LOG = Logger.getLogger(ProductService.class);

    public Uni<List<Product>> listAll() {
        LOG.info("Fetching all products");
        return Product.listAll();
    }

    public Uni<Product> findById(Long id) {
        LOG.infof("Finding product by ID: %d", id);
        return Product.findById(id);
    }

    public Uni<Product> create(Product product) {
        LOG.infof("Creating product: %s", product.name);
        return product.persist()
                .invoke(() -> LOG.debugf("Product persisted with name: %s", product.name))
                .replaceWith(product);
    }

    public Uni<Boolean> delete(Long id) {
        LOG.infof("Deleting product with ID: %d", id);
        return Product.deleteById(id)
                .invoke(deleted -> {
                    if (deleted) LOG.infof("Product with ID %d deleted", id);
                    else LOG.warnf("No product found to delete with ID: %d", id);
                });
    }

    public Uni<Product> update(Long id, Product updated) {
        LOG.infof("Updating product with ID: %d", id);
        return Product.<Product>findById(id)
                .onItem().ifNotNull().transformToUni(existing -> {
                    existing.name = updated.name;
                    existing.description = updated.description;
                    existing.price = updated.price;
                    existing.quantity = updated.quantity;
                    LOG.debugf("Updated product: %s", existing.name);
                    return existing.persist();
                });
    }

    public Uni<Boolean> isStockAvailable(Long id, int count) {
        LOG.infof("Checking stock availability for ID: %d with requested count: %d", id, count);
        return Product.findById(id).map(p -> {
            Product product = (Product) p;
            boolean available = product != null && product.quantity >= count;
            LOG.debugf("Stock available: %s", available);
            return available;
        });
    }

    public Uni<List<Product>> getSortedByPrice() {
        LOG.info("Retrieving all products sorted by ascending price");
        return Product.list("ORDER BY price ASC");
    }
}
