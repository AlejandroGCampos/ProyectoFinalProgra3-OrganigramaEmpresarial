package edu.umg.programacion3.organigrama.core.custom;

public class CustomTreeNode {

    private Long id;
    private String value;

    private CustomTreeNode parent;
    private CustomTreeNode firstChild;
    private CustomTreeNode nextSibling;

    public CustomTreeNode(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public void addChild(CustomTreeNode child) {

        child.parent = this;

        if (firstChild == null) {
            firstChild = child;
            return;
        }

        CustomTreeNode current = firstChild;

        while (current.nextSibling != null) {
            current = current.nextSibling;
        }

        current.nextSibling = child;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public CustomTreeNode getParent() {
        return parent;
    }

    public CustomTreeNode getFirstChild() {
        return firstChild;
    }

    public CustomTreeNode getNextSibling() {
        return nextSibling;
    }
}