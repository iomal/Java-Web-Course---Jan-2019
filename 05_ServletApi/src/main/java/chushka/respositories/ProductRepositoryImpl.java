package chushka.respositories;

import chushka.domain.entities.Product;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    private final EntityManager entityManager;


    public ProductRepositoryImpl() {
        this.entityManager = Persistence.createEntityManagerFactory("chyshka")
                .createEntityManager();
    }

    @Override
    public void save(Product entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
//        this.entityManager.flush();
        this.entityManager.getTransaction().commit();

    }

    public Optional<Product> findById(String id) {

        Optional<Product> product = Optional.ofNullable(this.entityManager.find(Product.class, id));
        return product;
    }


    public List<Product> getAll() {
        CriteriaQuery<Product> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Product.class);
        criteriaQuery.from(Product.class);
        List<Product> products = entityManager.createQuery(criteriaQuery).getResultList();
        return products;
    }

}
