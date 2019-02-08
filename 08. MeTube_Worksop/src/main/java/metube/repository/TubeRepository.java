package metube.repository;

import metube.domain.entities.Tube;

public interface TubeRepository extends GenericRepository<Tube, String> {
    Tube save(Tube tube);

    Tube update(Tube tube);
}
