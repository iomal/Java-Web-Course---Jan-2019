package exam.repository;

import exam.domain.entities.Document;
import exam.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class DocumentRepositoryImpl extends BaseRepository implements DocumentRepository {
    @Inject
    protected DocumentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Document save(Document document) {
        return this.executeTransaction((em) -> {
            em.persist(document);
            return document;
        });
    }

    @Override
    public Document findById(String id) {
        return this.executeTransaction((em) ->
                em.find(Document.class, id));
    }

    @Override
    public List<Document> findAll() {
        return this.executeTransaction(em ->
                em.createQuery("SELECT d FROM Document as d", Document.class).getResultList());
    }

    @Override
    public void delete(String id) {
        this.executeTransaction((em) -> {
            em.createNativeQuery("DELETE FROM documents WHERE id='" + id + "'").executeUpdate();
            return null;
        });
    }
}
