package edu.umg.programacion3.organigrama.app.repository;

import edu.umg.programacion3.organigrama.app.entity.NodeDocument;
import edu.umg.programacion3.organigrama.app.jpa.MongoNodeRepository;
import edu.umg.programacion3.organigrama.core.dto.TreeNodeDto;
import edu.umg.programacion3.organigrama.core.repository.TreeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MongoTreeRepository implements TreeRepository {

    private final MongoNodeRepository mongoNodeRepository;

    public MongoTreeRepository(MongoNodeRepository mongoNodeRepository) {
        this.mongoNodeRepository = mongoNodeRepository;
    }

    @Override
    public TreeNodeDto save(TreeNodeDto node) {
        NodeDocument document = new NodeDocument(
                null,
                node.value(),
                node.parentId() != null ? node.parentId().toString() : null
        );
        NodeDocument saved = mongoNodeRepository.save(document);
        return toDto(saved);
    }

    @Override
    public List<TreeNodeDto> findAll() {
        return mongoNodeRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TreeNodeDto> findById(Long id) {
        return mongoNodeRepository.findById(id.toString())
                .map(this::toDto);
    }

    private TreeNodeDto toDto(NodeDocument document) {
        Long parentId = null;
        if (document.getParentId() != null) {
            try {
                parentId = Long.parseLong(document.getParentId());
            } catch (NumberFormatException e) {
                parentId = null;
            }
        }
        return new TreeNodeDto(
                Long.parseLong(document.getId()),
                document.getValue(),
                parentId
        );
    }
}