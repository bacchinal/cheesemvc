package org.lauchcode.cheesemvc.controllers;

import org.lauchcode.cheesemvc.models.Category;
import org.lauchcode.cheesemvc.models.Cheese;
import org.lauchcode.cheesemvc.models.CheeseType;
import org.lauchcode.cheesemvc.models.data.CategoryDao;
import org.lauchcode.cheesemvc.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    CheeseDao cheeseDao;

    @Autowired
    CategoryDao categoryDao;

    // Request path: /cheese
    @RequestMapping(value = "")
        public String index(Model model) {

            model.addAttribute("cheeses", cheeseDao.findAll());
            model.addAttribute("title", "My Cheeses");

            return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
        public String displayAddCheeseForm(Model model) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute(new Cheese());
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
        public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese, Errors errors, Model model) {
            if (errors.hasErrors()){
                model.addAttribute("title", "Add Cheese");
                return "cheese/add";
            }
            cheeseDao.save(newCheese);
            return "redirect:";
        }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
        public String displayRemoveCheeseForm(Model model) {
                model.addAttribute("cheeses", cheeseDao.findAll());
                model.addAttribute("title", "Remove Cheese");
            return "cheese/remove";
        }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
        public String processRemoveCheeseForm(@RequestParam  int[] cheeseIds) {

            for (int cheeseId : cheeseIds) {
               cheeseDao.deleteById(cheeseId);
            }

            return "redirect:";
        }
//     @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
//        public String displayEditForm(Model model, @PathVariable int cheeseId){
//        Cheese cheese = cheeseDao.findById(cheeseId);
//            model.addAttribute("cheese", CheeseData.getById(cheeseId));
//            model.addAttribute("title", "Edit Cheese" + cheese.getName() + " (id=" + cheese.getCheeseId()+")");
//        return "cheese/edit";
//     }
//
//    @RequestMapping(value ="edit/{cheeseId}", method = RequestMethod.POST)
//        public String processEditForm(int cheeseId, String name, String description){
//            Cheese cheese = CheeseData.getById(cheeseId);
//            cheese.setDescription(description);
//            cheese.setName(name);
//        return "redirect:/cheese";
//    }
}
