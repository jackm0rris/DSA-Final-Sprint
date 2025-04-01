package com.keyin.Controller;

import com.keyin.Entities.TreeNode;
import com.keyin.Services.TreeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api") // Base path for all endpoints in this controller
public class TreeController {

    private static final Logger logger = LoggerFactory.getLogger(TreeController.class);
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
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String processNumbers(
            @RequestParam String numbers,
            @RequestParam(required = false, defaultValue = "false") boolean balanced) {

        try {
            logger.info("Processing numbers: {}, balanced: {}", numbers, balanced);
            List<Integer> numberList = parseNumbers(numbers);

            TreeNode root = balanced ?
                    treeService.buildBalancedTree(numberList) :
                    treeService.buildTree(numberList);

            treeService.saveTree(numbers, root);
            return treeService.serializeTree(root);

        } catch (NumberFormatException e) {
            return "{\"error\":\"Please enter valid numbers separated by commas\"}";
        } catch (Exception e) {
            logger.error("Error processing numbers", e);
            return "{\"error\":\"" + e.getMessage() + "\"}";
        }
    }

    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model) {
        model.addAttribute("trees", treeService.getAllTrees());
        return "previous-trees";
    }

    private List<Integer> parseNumbers(String numbers) {
        if (numbers == null || numbers.trim().isEmpty()) {
            throw new IllegalArgumentException("Please enter some numbers");
        }

        return Arrays.stream(numbers.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}