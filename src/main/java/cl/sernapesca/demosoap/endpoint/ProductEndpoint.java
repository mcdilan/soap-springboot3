package cl.sernapesca.demosoap.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import cl.sernapesca.demosoap.gen.GetProductRequest;
import cl.sernapesca.demosoap.gen.GetProductResponse;
import cl.sernapesca.demosoap.gen.GetProductsRequest;
import cl.sernapesca.demosoap.gen.GetProductsResponse;
import cl.sernapesca.demosoap.gen.PostProductRequest;
import cl.sernapesca.demosoap.gen.PostProductResponse;
import cl.sernapesca.demosoap.gen.Product;

import cl.sernapesca.demosoap.converter.ProductConverter;
import cl.sernapesca.demosoap.model.ProductModel;
import cl.sernapesca.demosoap.repository.ProductRepository;

@Endpoint
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://localhost:8080/demosoap/gen";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
        GetProductResponse response = new GetProductResponse();
        ProductModel productModel = productRepository.findByName(request.getName());
        response.setProduct(productConverter.convertProductModelToProduct(productModel));
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProducts(@RequestPayload GetProductsRequest request) {
        GetProductsResponse response = new GetProductsResponse();
        List<ProductModel> productModels = productRepository.findAll();
        List<Product> products = productConverter.convertProductModelsToProducts(productModels);
        response.getProducts().addAll(products);
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postProductRequest")
    @ResponsePayload
    public PostProductResponse postProducts(@RequestPayload PostProductRequest request) {
        PostProductResponse response = new PostProductResponse();
        ProductModel productModel = productConverter.convertProductToProductModel(request.getProduct());
        Product product = productConverter.convertProductModelToProduct(productRepository.save(productModel));
        response.setProduct(product);
        return response;
    }
}
