package org.lauchcode.cheesemvc.controllers;

import org.lauchcode.cheesemvc.models.Cheese;
import org.lauchcode.cheesemvc.models.CheeseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("cheese")
public class CheeseController {


    // Request path: /cheese
    @RequestMapping(value = "")
        public String index(Model model) {

            model.addAttribute("cheeses", CheeseData.getAll());
            model.addAttribute("title", "My Cheeses");

            return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
        public String displayAddCheeseForm(Model model) {
            model.addAttribute("title", "Add Cheese");
            return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
        public String processAddCheeseForm(@ModelAttribute Cheese newCheese) {
            CheeseData.add(newCheese);
            return "redirect:";
        }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
        public String displayRemoveCheeseForm(Model model) {
            model.addAttribute("cheeses", CheeseData.getAll());
            model.addAttribute("title", "Remove Cheese");
            return "cheese/remove";
        }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
        public String processRemoveCheeseForm(@RequestParam  int[] cheeseIds) {

            for (int cheeseId : cheeseIds) {
                CheeseData.remove(cheeseId);
            }

            return "redirect:";
        }
     @RequestMapping(value="edit", method = RequestMethod.GET)
        public String displayEditForm(Model model, @PathVariable int cheeseId){
            model.addAttribute(CheeseData, cheeseId);


        }
    @RequestMapping(value="edit", method = RequestMethod.GET)
        public String processEditForm(int cheeseId, String name, String description){


        }
}
