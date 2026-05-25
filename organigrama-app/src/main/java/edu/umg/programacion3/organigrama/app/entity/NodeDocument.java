package edu.umg.programacion3.organigrama.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nodes")
public class NodeDocument {

    @Id
    private String id;

    private Long numericId;

    private String value;

    private Long parentId;

    public NodeDocument() {
    }

    public NodeDocument(String id, Long numericId, String value, Long parentId) {
        this.id = id;
        this.numericId = numericId;
        this.value = value;
        this.parentId = parentId;
    }

    public String getId() { return id; }
    public Long getNumericId() { return numericId; }
    public String getValue() { return value; }
    public Long getParentId() { return parentId; }

    public void setId(String id) { this.id = id; }
    public void setNumericId(Long numericId) { this.numericId = numericId; }
    public void setValue(String value) { this.value = value; }
    public void setParentId(Long parentId) { this.parentId = parentId; }
}