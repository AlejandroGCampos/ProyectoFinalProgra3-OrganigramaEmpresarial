package edu.umg.programacion3.organigrama.core.repository;

import edu.umg.programacion3.organigrama.core.dto.TreeNodeDto;

import java.util.List;
import java.util.Optional;

public interface TreeRepository {

    TreeNodeDto save(TreeNodeDto node);

    List<TreeNodeDto> findAll();

    Optional<TreeNodeDto> findById(Long id);
}