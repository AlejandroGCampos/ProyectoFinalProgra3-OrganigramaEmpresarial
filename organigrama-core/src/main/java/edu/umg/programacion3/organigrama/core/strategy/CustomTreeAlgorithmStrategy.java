package edu.umg.programacion3.organigrama.core.strategy;

import edu.umg.programacion3.organigrama.core.custom.CustomTreeNode;
import edu.umg.programacion3.organigrama.core.dto.TreeNodeDto;

import java.util.ArrayList;
import java.util.List;

public class CustomTreeAlgorithmStrategy implements TreeAlgorithmStrategy {

    @Override
    public List<TreeNodeDto> buildTree(List<TreeNodeDto> nodes) {
        return new ArrayList<>(nodes);
    }

    @Override
    public List<TreeNodeDto> dfs(List<TreeNodeDto> nodes) {
        return new ArrayList<>(nodes);
    }

    @Override
    public List<TreeNodeDto> bfs(List<TreeNodeDto> nodes) {
        return new ArrayList<>(nodes);
    }

    @Override
    public int height(List<TreeNodeDto> nodes) {
        return 0;
    }

    @Override
    public boolean validateNoCycles(List<TreeNodeDto> nodes) {
        return true;
    }
}