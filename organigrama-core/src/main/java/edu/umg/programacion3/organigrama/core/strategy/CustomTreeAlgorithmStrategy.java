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

    for (TreeNodeDto node : nodes) {

        Long currentParentId = node.parentId();

        while (currentParentId != null) {

            if (currentParentId.equals(node.id())) {
                return false;
            }

            TreeNodeDto parentNode = findNode(nodes, currentParentId);

            if (parentNode == null) {
                break;
            }

            currentParentId = parentNode.parentId();
        }
    }

    return true;
}

@Override
public int depth(List<TreeNodeDto> nodes, Long nodeId) {

    int depth = 0;

    TreeNodeDto current = findNode(nodes, nodeId);

    while (current != null && current.parentId() != null) {

        depth++;

        current = findNode(nodes, current.parentId());
    }

    return depth;
}

@Override
public List<TreeNodeDto> path(List<TreeNodeDto> nodes, Long nodeId) {

    List<TreeNodeDto> result = new ArrayList<>();

    TreeNodeDto current = findNode(nodes, nodeId);

    while (current != null) {

        result.add(0, current);

        if (current.parentId() == null) {
            break;
        }

        current = findNode(nodes, current.parentId());
    }

    return result;
}

@Override
public List<TreeNodeDto> ancestors(List<TreeNodeDto> nodes, Long nodeId) {

    List<TreeNodeDto> result = new ArrayList<>();

    TreeNodeDto current = findNode(nodes, nodeId);

    while (current != null && current.parentId() != null) {

        current = findNode(nodes, current.parentId());

        if (current != null) {
            result.add(current);
        }
    }

    return result;
}

@Override
public List<TreeNodeDto> subtree(List<TreeNodeDto> nodes, Long nodeId) {

    CustomTreeNode root = buildCustomTree(nodes);

    CustomTreeNode target = findCustomNodeRecursive(root, nodeId);

    List<TreeNodeDto> result = new ArrayList<>();

    if (target != null) {
        collectDfs(target, result);
    }

    return result;
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

    private TreeNodeDto findNode(List<TreeNodeDto> nodes, Long id) {

    for (TreeNodeDto node : nodes) {

        if (node.id().equals(id)) {
            return node;
        }
    }

    return null;
}

private CustomTreeNode findCustomNodeRecursive(CustomTreeNode node, Long id) {

    if (node == null) {
        return null;
    }

    if (node.getId().equals(id)) {
        return node;
    }

    CustomTreeNode child = node.getFirstChild();

    while (child != null) {

        CustomTreeNode found = findCustomNodeRecursive(child, id);

        if (found != null) {
            return found;
        }

        child = child.getNextSibling();
    }

    return null;
}
}