package chushka.service;

import chushka.domain.entities.Product;
import chushka.domain.models.ProductServiceModel;

import java.util.List;
import java.util.Optional;

public interface ProductsService  {
  List<ProductServiceModel> getAll();
  void saveProduct(ProductServiceModel productServiceModel);
  Optional<ProductServiceModel> getByID(String id);
}
