package exam.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.Serializable;

public class ApplicationBeanConfiguration {
    @Produces
    public EntityManager entityManager() {
        return Persistence
                .createEntityManagerFactory("sbojGb")
                .createEntityManager();
    }

    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
