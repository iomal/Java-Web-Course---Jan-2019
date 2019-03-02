package exam.repository;

import java.util.List;

public interface GenericRepository <Entity,Key>{
    Entity save(Entity entity);
    Entity findById(String id);
    List<Entity> findAll();
}
