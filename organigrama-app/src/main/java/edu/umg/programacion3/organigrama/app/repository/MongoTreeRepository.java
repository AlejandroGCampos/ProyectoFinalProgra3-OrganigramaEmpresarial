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
        long nextId = getNextId();
        NodeDocument document = new NodeDocument(null, nextId, node.value(), node.parentId());
        mongoNodeRepository.save(document);
        return new TreeNodeDto(nextId, node.value(), node.parentId());
    }
    @Override
    public List<TreeNodeDto> findAll() {
        return mongoNodeRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
    @Override
    public Optional<TreeNodeDto> findById(Long id) {
        return mongoNodeRepository.findAll().stream().filter(doc -> id.equals(doc.getNumericId())).findFirst().map(this::toDto);
    }
    private long getNextId() {
        return mongoNodeRepository.findAll().stream().mapToLong(doc -> doc.getNumericId() != null ? doc.getNumericId() : 0L).max().orElse(0L) + 1;
    }
    private TreeNodeDto toDto(NodeDocument document) {
        return new TreeNodeDto(document.getNumericId(), document.getValue(), document.getParentId());
    }
}