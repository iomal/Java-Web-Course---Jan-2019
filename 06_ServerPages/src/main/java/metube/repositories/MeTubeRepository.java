package metube.repositories;

import metube.domain.entities.Tube;

import java.util.*;

public interface MeTubeRepository extends GenericRepository <Tube,String>{
     List<Tube> findByUploader(String uploaderName);

}
