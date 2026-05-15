package edu.umg.programacion3.organigrama.core.strategy;

import edu.umg.programacion3.organigrama.core.dto.TreeNodeDto;

import java.util.List;

public interface TreeAlgorithmStrategy {

    List<TreeNodeDto> buildTree(List<TreeNodeDto> nodes);

    List<TreeNodeDto> dfs(List<TreeNodeDto> nodes);

    List<TreeNodeDto> bfs(List<TreeNodeDto> nodes);

    int height(List<TreeNodeDto> nodes);

    boolean validateNoCycles(List<TreeNodeDto> nodes);
}