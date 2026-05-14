package edu.umg.programacion3.organigrama.app.service;

import edu.umg.programacion3.organigrama.core.dto.CreateChildRequest;
import edu.umg.programacion3.organigrama.core.dto.CreateRootRequest;
import edu.umg.programacion3.organigrama.core.dto.TreeNodeDto;
import edu.umg.programacion3.organigrama.core.repository.TreeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeService {

    private final TreeRepository treeRepository;

    public TreeService(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    public TreeNodeDto createRoot(CreateRootRequest request) {
        TreeNodeDto node = new TreeNodeDto(null, request.value(), null);
        return treeRepository.save(node);
    }

    public TreeNodeDto addChild(Long parentId, CreateChildRequest request) {
        treeRepository.findById(parentId)
                .orElseThrow(() -> new IllegalArgumentException("Nodo padre no encontrado: " + parentId));

        TreeNodeDto child = new TreeNodeDto(null, request.value(), parentId);
        return treeRepository.save(child);
    }

    public List<TreeNodeDto> getTree() {
        return treeRepository.findAll();
    }
}