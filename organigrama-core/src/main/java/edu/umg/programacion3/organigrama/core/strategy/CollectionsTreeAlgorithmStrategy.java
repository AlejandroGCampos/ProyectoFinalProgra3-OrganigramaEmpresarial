package edu.umg.programacion3.organigrama.core.strategy;

import edu.umg.programacion3.organigrama.core.dto.TreeNodeDto;

import java.util.*;

public class CollectionsTreeAlgorithmStrategy implements TreeAlgorithmStrategy {

    @Override
    public List<TreeNodeDto> buildTree(List<TreeNodeDto> nodes) {
        return new ArrayList<>(nodes);
    }

    @Override
    public List<TreeNodeDto> dfs(List<TreeNodeDto> nodes) {

        List<TreeNodeDto> result = new ArrayList<>();

        Map<Long, List<TreeNodeDto>> childrenMap = buildChildrenMap(nodes);

        TreeNodeDto root = findRoot(nodes);

        if (root != null) {
            dfsRecursive(root, childrenMap, result);
        }

        return result;
    }

    private void dfsRecursive(
            TreeNodeDto current,
            Map<Long, List<TreeNodeDto>> childrenMap,
            List<TreeNodeDto> result
    ) {

        result.add(current);

        List<TreeNodeDto> children =
                childrenMap.getOrDefault(current.id(), Collections.emptyList());

        for (TreeNodeDto child : children) {
            dfsRecursive(child, childrenMap, result);
        }
    }

    @Override
    public List<TreeNodeDto> bfs(List<TreeNodeDto> nodes) {

        List<TreeNodeDto> result = new ArrayList<>();

        Map<Long, List<TreeNodeDto>> childrenMap = buildChildrenMap(nodes);

        TreeNodeDto root = findRoot(nodes);

        if (root == null) {
            return result;
        }

        Queue<TreeNodeDto> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {

            TreeNodeDto current = queue.poll();

            result.add(current);

            List<TreeNodeDto> children =
                    childrenMap.getOrDefault(current.id(), Collections.emptyList());

            queue.addAll(children);
        }

        return result;
    }

    @Override
    public int height(List<TreeNodeDto> nodes) {

        TreeNodeDto root = findRoot(nodes);

        if (root == null) {
            return 0;
        }

        Map<Long, List<TreeNodeDto>> childrenMap = buildChildrenMap(nodes);

        return calculateHeight(root, childrenMap);
    }

    private int calculateHeight(
            TreeNodeDto node,
            Map<Long, List<TreeNodeDto>> childrenMap
    ) {

        List<TreeNodeDto> children =
                childrenMap.getOrDefault(node.id(), Collections.emptyList());

        if (children.isEmpty()) {
            return 1;
        }

        int maxHeight = 0;

        for (TreeNodeDto child : children) {

            int childHeight = calculateHeight(child, childrenMap);

            maxHeight = Math.max(maxHeight, childHeight);
        }

        return maxHeight + 1;
    }

    private Map<Long, List<TreeNodeDto>> buildChildrenMap(List<TreeNodeDto> nodes) {

        Map<Long, List<TreeNodeDto>> map = new HashMap<>();

        for (TreeNodeDto node : nodes) {

            if (node.parentId() != null) {

                map.computeIfAbsent(
                        node.parentId(),
                        k -> new ArrayList<>()
                ).add(node);
            }
        }

        return map;
    }

    private TreeNodeDto findRoot(List<TreeNodeDto> nodes) {

        for (TreeNodeDto node : nodes) {

            if (node.parentId() == null) {
                return node;
            }
        }

        return null;
    }

    @Override
    public boolean validateNoCycles(List<TreeNodeDto> nodes) {
        return true;
    }

    @Override
    public int depth(List<TreeNodeDto> nodes, Long nodeId) {
        return 0;
    }

    @Override
    public List<TreeNodeDto> path(List<TreeNodeDto> nodes, Long nodeId) {
        return new ArrayList<>();
    }

    @Override
    public List<TreeNodeDto> ancestors(List<TreeNodeDto> nodes, Long nodeId) {
        return new ArrayList<>();
    }

    @Override
    public List<TreeNodeDto> subtree(List<TreeNodeDto> nodes, Long nodeId) {
        return new ArrayList<>();
    }
}