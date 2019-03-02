package chushka.service;

import chushka.domain.entities.Product;
import chushka.domain.entities.Type;
import chushka.domain.models.ProductServiceModel;
import chushka.respositories.ProductRepository;
import chushka.utils.DtoUtils;
import chushka.utils.ModelMapper;

import javax.inject.Inject;
import java.net.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductsService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProductServiceModel> getAll() {

        return Arrays.asList(modelMapper.map(productRepository.getAll(), ProductServiceModel[].class));
    }

    @Override
    public void saveProduct(ProductServiceModel productServiceModel) {
        Product product = modelMapper.map(productServiceModel, Product.class);
        if(!"type".equals(productServiceModel.getType()))
        product.setType(Type.valueOf(productServiceModel.getType()));
        this.productRepository.save(product);
    }

    @Override
    public Optional<ProductServiceModel> getByID(String id) {
        Optional<Product> product = this.productRepository.findById(id);
           Optional<ProductServiceModel> productServiceModel=null;
           if(product.isPresent()){
        productServiceModel = Optional.of(modelMapper.map(product.get(), ProductServiceModel.class));
        if(product.get().getType()!=null)
        productServiceModel.get().setType(product.get().getType().toString());
        return productServiceModel;}
        else {return productServiceModel;}
    }
}
