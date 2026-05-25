package edu.umg.programacion3.organigrama.app.service;

import edu.umg.programacion3.organigrama.app.RepositoryConfig;
import edu.umg.programacion3.organigrama.core.dto.CreateChildRequest;
import edu.umg.programacion3.organigrama.core.dto.CreateRootRequest;
import edu.umg.programacion3.organigrama.core.dto.TreeNodeDto;
import edu.umg.programacion3.organigrama.core.repository.TreeRepository;
import edu.umg.programacion3.organigrama.core.strategy.TreeAlgorithmStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeService {

    private final TreeRepository treeRepository;
    private final TreeAlgorithmStrategy treeAlgorithmStrategy;
    private final RepositoryConfig repositoryConfig;

    public TreeService(
            TreeRepository treeRepository,
            TreeAlgorithmStrategy treeAlgorithmStrategy,
            RepositoryConfig repositoryConfig
    ) {
        this.treeRepository = treeRepository;
        this.treeAlgorithmStrategy = treeAlgorithmStrategy;
        this.repositoryConfig = repositoryConfig;
    }

    private TreeRepository getRepo(String storage) {
        return storage != null ? repositoryConfig.resolveRepository(storage) : treeRepository;
    }

    private TreeAlgorithmStrategy getStrategy(String strategy) {
        return strategy != null ? repositoryConfig.resolveStrategy(strategy) : treeAlgorithmStrategy;
    }

    public TreeNodeDto createRoot(CreateRootRequest request, String storage) {
        TreeNodeDto node = new TreeNodeDto(null, request.value(), null);
        return getRepo(storage).save(node);
    }

    public TreeNodeDto addChild(Long parentId, CreateChildRequest request, String storage) {
        TreeRepository repo = getRepo(storage);
        repo.findById(parentId)
                .orElseThrow(() -> new IllegalArgumentException("Nodo padre no encontrado: " + parentId));
        TreeNodeDto child = new TreeNodeDto(null, request.value(), parentId);
        return repo.save(child);
    }

    public List<TreeNodeDto> getTree(String storage, String strategy) {
        return getStrategy(strategy).buildTree(getRepo(storage).findAll());
    }

    public List<TreeNodeDto> getDfs(String storage, String strategy) {
        return getStrategy(strategy).dfs(getRepo(storage).findAll());
    }

    public List<TreeNodeDto> getBfs(String storage, String strategy) {
        return getStrategy(strategy).bfs(getRepo(storage).findAll());
    }

    public int getHeight(String storage, String strategy) {
        return getStrategy(strategy).height(getRepo(storage).findAll());
    }

    public boolean validateNoCycles(String storage, String strategy) {
        return getStrategy(strategy).validateNoCycles(getRepo(storage).findAll());
    }

    public int getDepth(Long nodeId, String storage, String strategy) {
        return getStrategy(strategy).depth(getRepo(storage).findAll(), nodeId);
    }

    public List<TreeNodeDto> getPath(Long nodeId, String storage, String strategy) {
        return getStrategy(strategy).path(getRepo(storage).findAll(), nodeId);
    }

    public List<TreeNodeDto> getAncestors(Long nodeId, String storage, String strategy) {
        return getStrategy(strategy).ancestors(getRepo(storage).findAll(), nodeId);
    }

    public List<TreeNodeDto> getSubtree(Long nodeId, String storage, String strategy) {
        return getStrategy(strategy).subtree(getRepo(storage).findAll(), nodeId);
    }
}