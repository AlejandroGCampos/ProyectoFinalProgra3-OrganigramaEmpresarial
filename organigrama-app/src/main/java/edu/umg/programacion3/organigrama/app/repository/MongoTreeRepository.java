package edu.umg.programacion3.organigrama.app.repository;

import edu.umg.programacion3.organigrama.core.dto.TreeNodeDto;
import edu.umg.programacion3.organigrama.core.repository.TreeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoTreeRepository implements TreeRepository {

    @Override
    public TreeNodeDto save(TreeNodeDto node) {
        return null;
    }

    @Override
    public List<TreeNodeDto> findAll() {
        return List.of();
    }

    @Override
    public Optional<TreeNodeDto> findById(Long id) {
        return Optional.empty();
    }
}