package com.bhl.crm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bhl.crm.dao.IProduitDao;
import com.bhl.crm.entities.Produit;


@Controller
public class CrmContorller {

	@Autowired
	private IProduitDao dao;
	
	@GetMapping("/")
	public String show(Model model) {
		List<Produit> prods = dao.getAllProducts();
		model.addAttribute("listProduits", prods);
		return "home";
	}
	
	@GetMapping("/addForm")
	public String addForm(Model model) {
		model.addAttribute("produit", new Produit());
		return "addForm";
	}
	
	@PostMapping("/add")
	public String addProduct(@ModelAttribute("prod")@Valid Produit p, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("produit", p);
			return "addForm";
		}
		dao.save(p);
		return "redirect:/";
	}
	
	@GetMapping("/updateForm")
	public String updateForm(Model model, Long id) {
		Produit produit=dao.getProductbyId(id);
		model.addAttribute("produit", produit);
		return "updateForm";
	}
	
	@PostMapping("/update")
	public String updateProduit(Model model,@ModelAttribute("prod")  @Valid Produit p, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("produit", p);
			return "updateForm";
		}
		dao.update(p);
		return  "redirect:/";
	}
	
	@GetMapping("/delete")
	public String deleteProduit(Model model, Long id) {
		dao.delete(id);
		return "redirect:/";
	}
}
