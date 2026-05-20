package edu.umg.programacion3.organigrama.app.controller;

import edu.umg.programacion3.organigrama.app.service.TreeService;
import edu.umg.programacion3.organigrama.core.dto.CreateChildRequest;
import edu.umg.programacion3.organigrama.core.dto.CreateRootRequest;
import edu.umg.programacion3.organigrama.core.dto.TreeNodeDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TreeController {

    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping("/nodes/root")
    public TreeNodeDto createRoot(
            @RequestBody CreateRootRequest request
    ) {
        return treeService.createRoot(request);
    }

    @PostMapping("/nodes/{parentId}/children")
    public TreeNodeDto addChild(
            @PathVariable Long parentId,
            @RequestBody CreateChildRequest request
    ) {
        return treeService.addChild(parentId, request);
    }

    @GetMapping("/tree")
    public List<TreeNodeDto> getTree() {
        return treeService.getTree();
    }

    @GetMapping("/tree/traversal")
    public List<TreeNodeDto> getTraversal(
            @RequestParam String type
    ) {
        if ("BFS".equalsIgnoreCase(type)) {
            return treeService.getBfs();
        }

        return treeService.getDfs();
    }

    @GetMapping("/tree/height")
    public int getHeight() {
        return treeService.getHeight();
    }

    @GetMapping("/tree/validate")
    public boolean validateNoCycles() {
        return treeService.validateNoCycles();
    }

    @GetMapping("/nodes/{nodeId}/depth")
    public int getDepth(
            @PathVariable Long nodeId
    ) {
        return treeService.getDepth(nodeId);
    }

    @GetMapping("/nodes/{nodeId}/path")
    public List<TreeNodeDto> getPath(
            @PathVariable Long nodeId
    ) {
        return treeService.getPath(nodeId);
    }

    @GetMapping("/nodes/{nodeId}/ancestors")
    public List<TreeNodeDto> getAncestors(
            @PathVariable Long nodeId
    ) {
        return treeService.getAncestors(nodeId);
    }

    @GetMapping("/tree/{nodeId}")
    public List<TreeNodeDto> getSubtree(
            @PathVariable Long nodeId
    ) {
        return treeService.getSubtree(nodeId);
    }
}