package org.lauchcode.cheesemvc.controllers;

import org.lauchcode.cheesemvc.models.Category;
import org.lauchcode.cheesemvc.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Categories");

        return "category/index";
    }
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Category");
        model.addAttribute(new Category());
        model.addAttribute("categories", categoryDao.findAll());
        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid Category newCategory, Errors errors, Model model) {
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Category");
            model.addAttribute("categories", categoryDao.findAll());
            return "category/add";
        }
        Category category = new Category();
        categoryDao.save(category);
        return "redirect:";
    }
}
