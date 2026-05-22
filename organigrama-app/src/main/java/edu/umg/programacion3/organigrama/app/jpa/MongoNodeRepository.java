package edu.umg.programacion3.organigrama.app.jpa;

import edu.umg.programacion3.organigrama.app.entity.NodeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoNodeRepository extends MongoRepository<NodeDocument, String> {
}