package edu.umg.programacion3.organigrama.app.jpa;

import edu.umg.programacion3.organigrama.app.entity.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresNodeJpaRepository
        extends JpaRepository<NodeEntity, Long> {
}