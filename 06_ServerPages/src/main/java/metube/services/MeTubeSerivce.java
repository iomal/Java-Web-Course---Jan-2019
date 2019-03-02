package metube.services;

import metube.domain.models.service.TubeServiceModel;

import java.util.List;
import java.util.Optional;

public interface MeTubeSerivce {

    void create (TubeServiceModel entity);

    List<TubeServiceModel> getAll();

    Optional<TubeServiceModel> findById(String id);


}
