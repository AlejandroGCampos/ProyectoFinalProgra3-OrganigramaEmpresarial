package edu.umg.programacion3.organigrama.app.controller;

import edu.umg.programacion3.organigrama.app.service.TreeService;
import edu.umg.programacion3.organigrama.core.dto.CreateChildRequest;
import edu.umg.programacion3.organigrama.core.dto.CreateRootRequest;
import edu.umg.programacion3.organigrama.core.dto.TreeNodeDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class TreeController {

    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping("/nodes/root")
    public TreeNodeDto createRoot(
            @RequestBody CreateRootRequest request,
            @RequestParam(required = false) String storage
    ) {
        return treeService.createRoot(request, storage);
    }

    @PostMapping("/nodes/{parentId}/children")
    public TreeNodeDto addChild(
            @PathVariable("parentId") Long parentId,
            @RequestBody CreateChildRequest request,
            @RequestParam(required = false) String storage
    ) {
        return treeService.addChild(parentId, request, storage);
    }

    @GetMapping("/tree")
    public List<TreeNodeDto> getTree(
            @RequestParam(required = false) String storage,
            @RequestParam(required = false) String strategy
    ) {
        return treeService.getTree(storage, strategy);
    }

    @GetMapping("/tree/traversal")
    public List<TreeNodeDto> getTraversal(
            @RequestParam("type") String type,
            @RequestParam(required = false) String storage,
            @RequestParam(required = false) String strategy
    ) {
        if ("BFS".equalsIgnoreCase(type)) {
            return treeService.getBfs(storage, strategy);
        }
        return treeService.getDfs(storage, strategy);
    }

    @GetMapping("/tree/height")
    public int getHeight(
            @RequestParam(required = false) String storage,
            @RequestParam(required = false) String strategy
    ) {
        return treeService.getHeight(storage, strategy);
    }

    @GetMapping("/tree/validate")
    public boolean validateNoCycles(
            @RequestParam(required = false) String storage,
            @RequestParam(required = false) String strategy
    ) {
        return treeService.validateNoCycles(storage, strategy);
    }

    @GetMapping("/nodes/{nodeId}/depth")
    public int getDepth(
            @PathVariable("nodeId") Long nodeId,
            @RequestParam(required = false) String storage,
            @RequestParam(required = false) String strategy
    ) {
        return treeService.getDepth(nodeId, storage, strategy);
    }

    @GetMapping("/nodes/{nodeId}/path")
    public List<TreeNodeDto> getPath(
            @PathVariable("nodeId") Long nodeId,
            @RequestParam(required = false) String storage,
            @RequestParam(required = false) String strategy
    ) {
        return treeService.getPath(nodeId, storage, strategy);
    }

    @GetMapping("/nodes/{nodeId}/ancestors")
    public List<TreeNodeDto> getAncestors(
            @PathVariable("nodeId") Long nodeId,
            @RequestParam(required = false) String storage,
            @RequestParam(required = false) String strategy
    ) {
        return treeService.getAncestors(nodeId, storage, strategy);
    }

    @GetMapping("/tree/{nodeId}")
    public List<TreeNodeDto> getSubtree(
            @PathVariable("nodeId") Long nodeId,
            @RequestParam(required = false) String storage,
            @RequestParam(required = false) String strategy
    ) {
        return treeService.getSubtree(nodeId, storage, strategy);
    }
}