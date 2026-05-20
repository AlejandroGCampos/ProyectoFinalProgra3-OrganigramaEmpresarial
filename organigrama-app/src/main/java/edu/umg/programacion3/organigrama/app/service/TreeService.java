package edu.umg.programacion3.organigrama.app.service;

import edu.umg.programacion3.organigrama.core.dto.CreateChildRequest;
import edu.umg.programacion3.organigrama.core.dto.CreateRootRequest;
import edu.umg.programacion3.organigrama.core.dto.TreeNodeDto;
import edu.umg.programacion3.organigrama.core.repository.TreeRepository;
import edu.umg.programacion3.organigrama.core.strategy.CustomTreeAlgorithmStrategy;
import edu.umg.programacion3.organigrama.core.strategy.TreeAlgorithmStrategy;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeService {

    private final TreeRepository treeRepository;

    private final TreeAlgorithmStrategy treeAlgorithmStrategy;

    public TreeService(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
        this.treeAlgorithmStrategy = new CustomTreeAlgorithmStrategy();
    }

    public TreeNodeDto createRoot(CreateRootRequest request) {

        TreeNodeDto node = new TreeNodeDto(
                null,
                request.value(),
                null
        );

        return treeRepository.save(node);
    }

    public TreeNodeDto addChild(Long parentId, CreateChildRequest request) {

        treeRepository.findById(parentId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Nodo padre no encontrado: " + parentId
                        )
                );

        TreeNodeDto child = new TreeNodeDto(
                null,
                request.value(),
                parentId
        );

        return treeRepository.save(child);
    }

    public List<TreeNodeDto> getTree() {
        return treeAlgorithmStrategy.buildTree(treeRepository.findAll());
    }

    public List<TreeNodeDto> getDfs() {
        return treeAlgorithmStrategy.dfs(treeRepository.findAll());
    }

    public List<TreeNodeDto> getBfs() {
        return treeAlgorithmStrategy.bfs(treeRepository.findAll());
    }

    public int getHeight() {
        return treeAlgorithmStrategy.height(treeRepository.findAll());
    }

    public boolean validateNoCycles() {
        return treeAlgorithmStrategy.validateNoCycles(treeRepository.findAll());
    }

    public int getDepth(Long nodeId) {
        return treeAlgorithmStrategy.depth(
                treeRepository.findAll(),
                nodeId
        );
    }

    public List<TreeNodeDto> getPath(Long nodeId) {
        return treeAlgorithmStrategy.path(
                treeRepository.findAll(),
                nodeId
        );
    }

    public List<TreeNodeDto> getAncestors(Long nodeId) {
        return treeAlgorithmStrategy.ancestors(
                treeRepository.findAll(),
                nodeId
        );
    }

    public List<TreeNodeDto> getSubtree(Long nodeId) {
        return treeAlgorithmStrategy.subtree(
                treeRepository.findAll(),
                nodeId
        );
    }
}