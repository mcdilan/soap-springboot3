package cl.sernapesca.demosoap.converter;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cl.sernapesca.demosoap.gen.Product;

import cl.sernapesca.demosoap.model.ProductModel;

@Component
public class ProductConverter {

    public ProductModel convertProductToProductModel(Product product) {
        ProductModel productModel = new ProductModel();
        productModel.setId(product.getId());
        productModel.setName(product.getName());
        productModel.setPrice(product.getPrice());
        productModel.setDescription(product.getDescription());
        return productModel;
    }

    public Product convertProductModelToProduct(ProductModel productModel) {
        Product product = new Product();
        product.setId(productModel.getId());
        product.setName(productModel.getName());
        product.setPrice(productModel.getPrice());
        product.setDescription(productModel.getDescription());
        return product;
    }

    public List<ProductModel> convertProductsToProductModels(List<Product> products) {
        List<ProductModel> productModels = new ArrayList<ProductModel>();
        for (Product product : products) {
            productModels.add(convertProductToProductModel(product));
        }
        return productModels;
    }

    public List<Product> convertProductModelsToProducts(List<ProductModel> productModels) {
        List<Product> products = new ArrayList<Product>();
        for (ProductModel productModel : productModels) {
            products.add(convertProductModelToProduct(productModel));
        }
        return products;
    }
}
