package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Utilities.JpaUtils;
import DomainModel.Combo;
import DomainModel.ComboSanpham;
import DomainModel.Sanpham;

public class ImplBangCombo implements InterfaceBangComBo{

	@Override
	public List<Combo> findAll(int position, int pageSize) {
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<Combo> query = em.createNamedQuery("Combo.findAll", Combo.class);
		
		query.setFirstResult((position-1)*3);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
        
        public List<Combo> findAll() {
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<Combo> query = em.createNamedQuery("Combo.findAll", Combo.class);
		return query.getResultList();
	}

	@Override
	public Combo findById(int id) {
		EntityManager em = JpaUtils.getEntityManager();
		Combo bcb = em.find(Combo.class, id);
		return bcb;
	}

	@Override
	public Combo create(Combo combo) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(combo);
			trans.commit();
			return combo;
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException();
		}
	}

	@Override
	public Combo update(Combo combo) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(combo);
			trans.commit();
			return combo;
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException();
		}
	}

	@Override
	public long totalCount() {
		EntityManager em = JpaUtils.getEntityManager();
        try {
        	String sql = "Select COUNT(b.id) From Combo b ";
    		Query query = em.createQuery(sql);
            return Long.parseLong(query.getSingleResult().toString());
        } catch (Exception e) {
        	em.getTransaction().rollback();
			throw new RuntimeException();
        }
	}

	@Override
	public List<Sanpham> findSanPhamByIDCB(int id) {
		EntityManager em = JpaUtils.getEntityManager();
        try {
        	String sql = "Select sp From Sanpham sp  "
        				+"JOIN ComboSanpham cbsp ON cbsp.ID_SanPham = sp.ID_SanPham"
        				+ "JOIN Combo cb ON cb.ID_ComBo = cbsp.ID_ComBo ";
    		TypedQuery<Sanpham> query = em.createQuery(sql,Sanpham.class);
    		List<Sanpham> sps= query.getResultList();
    		for (Sanpham x : sps) {
        		System.out.println(x.getID_SanPham());
			}
            return query.getResultList();
        } catch (Exception e) {
        	em.getTransaction().rollback();
			throw new RuntimeException();
        }
	}

	@Override
	public int findIdByMaCB(String maCB) {
		EntityManager em = JpaUtils.getEntityManager();
        try {
        	String sql = "Select b.ID_Combo From Combo b"
        			+ "Where b.maComBo = "+ maCB;
    		TypedQuery<Integer> query = em.createQuery(sql,Integer.class);
            return query.getSingleResult();
        } catch (Exception e) {
        	em.getTransaction().rollback();
			throw new RuntimeException();
        }
	}

	@Override
	public Combo findLatestCB() {
		EntityManager em = JpaUtils.getEntityManager();
        try {
        	String sql = "Select b From Combo b ORDER BY b.ID_ComBo DESC";
    		TypedQuery<Combo> query = em.createQuery(sql,Combo.class).setMaxResults(1);
            return query.getSingleResult();
        } catch (Exception e) {
        	em.getTransaction().rollback();
			throw new RuntimeException();
        }
	}
	
	

}
