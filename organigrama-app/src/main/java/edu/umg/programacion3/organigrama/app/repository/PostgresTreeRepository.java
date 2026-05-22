package edu.umg.programacion3.organigrama.app.repository;

import edu.umg.programacion3.organigrama.app.entity.NodeEntity;
import edu.umg.programacion3.organigrama.app.jpa.PostgresNodeJpaRepository;
import edu.umg.programacion3.organigrama.core.dto.TreeNodeDto;
import edu.umg.programacion3.organigrama.core.repository.TreeRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class PostgresTreeRepository implements TreeRepository {

    private final PostgresNodeJpaRepository jpaRepository;

    public PostgresTreeRepository(PostgresNodeJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public TreeNodeDto save(TreeNodeDto node) {
        NodeEntity entity = new NodeEntity(null, node.value(), node.parentId());
        NodeEntity saved = jpaRepository.save(entity);
        return new TreeNodeDto(saved.getId(), saved.getValue(), saved.getParentId());
    }

    @Override
    public List<TreeNodeDto> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(e -> new TreeNodeDto(e.getId(), e.getValue(), e.getParentId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TreeNodeDto> findById(Long id) {
        return jpaRepository.findById(id)
                .map(e -> new TreeNodeDto(e.getId(), e.getValue(), e.getParentId()));
    }
}