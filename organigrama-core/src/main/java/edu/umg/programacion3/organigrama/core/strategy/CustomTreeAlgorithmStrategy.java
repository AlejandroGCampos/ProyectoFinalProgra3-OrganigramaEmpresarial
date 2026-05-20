package edu.umg.programacion3.organigrama.core.strategy;

import edu.umg.programacion3.organigrama.core.custom.CustomTreeNode;
import edu.umg.programacion3.organigrama.core.dto.TreeNodeDto;

import java.util.ArrayList;
import java.util.List;

public class CustomTreeAlgorithmStrategy implements TreeAlgorithmStrategy {

    @Override
    public List<TreeNodeDto> buildTree(List<TreeNodeDto> nodes) {
        CustomTreeNode root = buildCustomTree(nodes);

        List<TreeNodeDto> result = new ArrayList<>();

        if (root != null) {
            collectDfs(root, result);
        }

        return result;
    }

    @Override
    public List<TreeNodeDto> dfs(List<TreeNodeDto> nodes) {
        CustomTreeNode root = buildCustomTree(nodes);

        List<TreeNodeDto> result = new ArrayList<>();

        if (root != null) {
            collectDfs(root, result);
        }

        return result;
    }

    @Override
    public List<TreeNodeDto> bfs(List<TreeNodeDto> nodes) {
        CustomTreeNode root = buildCustomTree(nodes);

        List<TreeNodeDto> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        List<CustomTreeNode> queue = new ArrayList<>();
        queue.add(root);

        int index = 0;

        while (index < queue.size()) {
            CustomTreeNode current = queue.get(index);
            index++;

            result.add(toDto(current));

            CustomTreeNode child = current.getFirstChild();

            while (child != null) {
                queue.add(child);
                child = child.getNextSibling();
            }
        }

        return result;
    }

    @Override
    public int height(List<TreeNodeDto> nodes) {
        CustomTreeNode root = buildCustomTree(nodes);

        if (root == null) {
            return 0;
        }

        return calculateHeight(root);
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

    private CustomTreeNode buildCustomTree(List<TreeNodeDto> nodes) {

        if (nodes == null || nodes.isEmpty()) {
            return null;
        }

        List<CustomTreeNode> customNodes = new ArrayList<>();

        for (TreeNodeDto node : nodes) {
            customNodes.add(new CustomTreeNode(node.id(), node.value()));
        }

        CustomTreeNode root = null;

        for (TreeNodeDto node : nodes) {
            CustomTreeNode current = findCustomNode(customNodes, node.id());

            if (node.parentId() == null) {
                root = current;
            } else {
                CustomTreeNode parent = findCustomNode(customNodes, node.parentId());

                if (parent != null && current != null) {
                    parent.addChild(current);
                }
            }
        }

        return root;
    }

    private CustomTreeNode findCustomNode(List<CustomTreeNode> nodes, Long id) {

        for (CustomTreeNode node : nodes) {
            if (node.getId().equals(id)) {
                return node;
            }
        }

        return null;
    }

    private void collectDfs(CustomTreeNode node, List<TreeNodeDto> result) {

        if (node == null) {
            return;
        }

        result.add(toDto(node));

        CustomTreeNode child = node.getFirstChild();

        while (child != null) {
            collectDfs(child, result);
            child = child.getNextSibling();
        }
    }

    private int calculateHeight(CustomTreeNode node) {

        if (node == null) {
            return 0;
        }

        int maxChildHeight = 0;

        CustomTreeNode child = node.getFirstChild();

        while (child != null) {
            int childHeight = calculateHeight(child);

            if (childHeight > maxChildHeight) {
                maxChildHeight = childHeight;
            }

            child = child.getNextSibling();
        }

        return maxChildHeight + 1;
    }

    private TreeNodeDto toDto(CustomTreeNode node) {

        Long parentId = null;

        if (node.getParent() != null) {
            parentId = node.getParent().getId();
        }

        return new TreeNodeDto(
                node.getId(),
                node.getValue(),
                parentId
        );
    }
}