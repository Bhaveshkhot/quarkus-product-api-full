package com.bhavesh.product.resource;

import com.bhavesh.product.entity.Product;
import com.bhavesh.product.service.ProductService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import java.util.List;
import java.util.Map;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    @GET
    @WithSession
    public Uni<List<Product>> getAll() {
        return productService.listAll();
    }

    @GET
    @Path("/{id}")
    @WithSession
    public Uni<jakarta.ws.rs.core.Response> get(@PathParam("id") Long id) {
        return productService.findById(id)
        .onItem().ifNotNull().transform(product -> Response.ok(product).build())
        .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @WithTransaction
    public Uni<Product> create(@Valid Product product) {
        return productService.create(product);
    }

    @PUT
    @Path("/{id}")
    @WithTransaction
    public Uni<Product> update(@PathParam("id") Long id, @Valid Product product) {
        return productService.update(id, product);
    }

    @DELETE
    @Path("/{id}")
    @WithTransaction
    public Uni<Boolean> delete(@PathParam("id") Long id) {
        return productService.delete(id);
    }

    @GET
    @Path("/{id}/stock")
    @WithSession
    public Uni<Map<String, Boolean>> checkStock(@PathParam("id") Long id, @QueryParam("count") int count) {
        return productService.isStockAvailable(id, count)
                .map(available -> Map.of("available", available));
    }

    @GET
    @Path("/sorted")
    @WithSession
    public Uni<List<Product>> getSorted() {
        return productService.getSortedByPrice();
    }
}