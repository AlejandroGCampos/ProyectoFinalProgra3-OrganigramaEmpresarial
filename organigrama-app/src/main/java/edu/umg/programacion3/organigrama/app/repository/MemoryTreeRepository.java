package edu.umg.programacion3.organigrama.app.repository;

import edu.umg.programacion3.organigrama.core.dto.TreeNodeDto;
import edu.umg.programacion3.organigrama.core.repository.TreeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class MemoryTreeRepository implements TreeRepository {

    private final List<TreeNodeDto> nodes = new ArrayList<>();

    private Long sequence = 1L;

    @Override
    public TreeNodeDto save(TreeNodeDto node) {

        TreeNodeDto saved = new TreeNodeDto(
                sequence++,
                node.value(),
                node.parentId()
        );

        nodes.add(saved);

        return saved;
    }

    @Override
    public List<TreeNodeDto> findAll() {
        return new ArrayList<>(nodes);
    }

    @Override
    public Optional<TreeNodeDto> findById(Long id) {

        for (TreeNodeDto node : nodes) {

            if (node.id().equals(id)) {
                return Optional.of(node);
            }
        }

        return Optional.empty();
    }
}