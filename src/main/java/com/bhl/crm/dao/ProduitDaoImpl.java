package com.bhl.crm.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bhl.crm.entities.Produit;

@Repository
@Transactional
public class ProduitDaoImpl implements IProduitDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Produit> getAllProducts() {
		Session session=sessionFactory.getCurrentSession();
		Query<Produit> query=session.createQuery("from Produit", Produit.class);
		List<Produit> produits = query.getResultList();
		return produits;
	}

	
	@Override
	public Produit getProductbyId(Long id) {
		Session session=sessionFactory.getCurrentSession();
		Produit p = session.get(Produit.class, id);
		return p;
	}

	@Override
	public List<Produit> getProductbyMc(String mc) {
		Session session=sessionFactory.getCurrentSession();
		Query<Produit> query=session.createQuery("from Produit p where p.designation =:mc", Produit.class);
		query.setParameter("mc", mc);
		query.executeUpdate();
		List<Produit> produits = query.getResultList();
		return produits;
	}

	@Override
	public Produit save(Produit p) {
		Session session=sessionFactory.getCurrentSession();
		session.save(p);
		return p;
	}

	@Override
	public Produit update(Produit p) {
		Session session=sessionFactory.getCurrentSession();
		session.update(p);
		return p;
	}

	@Override
	public void delete(Long id) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(session.get(Produit.class, id));
	}

}
