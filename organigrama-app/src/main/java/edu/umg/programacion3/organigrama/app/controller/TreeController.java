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
}