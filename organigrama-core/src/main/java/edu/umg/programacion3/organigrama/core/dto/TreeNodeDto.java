package edu.umg.programacion3.organigrama.core.dto;

public record TreeNodeDto(
        Long id,
        String value,
        Long parentId
) {
}