package edu.umg.programacion3.organigrama.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "nodes")
public class NodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String value;

    @Column(name = "parent_id")
    private Long parentId;

    public NodeEntity() {
    }

    public NodeEntity(Long id, String value, Long parentId) {
        this.id = id;
        this.value = value;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}