package com.keyin.Controller;

import com.keyin.Entities.TreeNode;
import com.keyin.Entities.TreeRecord;
import com.keyin.Services.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class TreeController {

    private final TreeService treeService;

    @Autowired
    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @GetMapping("/enter-numbers")
    public String showInputPage() {
        return "enter-numbers";
    }

    @PostMapping(value = "/process-numbers",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public TreeRecord processNumbers(@RequestParam String numbers) {
        return treeService.processNumbers(numbers);
    }

    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model) {
        model.addAttribute("trees", treeService.getAllTrees());
        return "previous-trees";
    }
}