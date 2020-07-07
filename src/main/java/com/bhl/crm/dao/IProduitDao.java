package com.bhl.crm.dao;

import java.util.List;

import com.bhl.crm.entities.Produit;

public interface IProduitDao {
	public List<Produit> getAllProducts();
	public Produit getProductbyId(Long id);
	public List<Produit> getProductbyMc(String mc);
	public Produit save(Produit p);
	public Produit update(Produit p);
	public void delete(Long id);
}
