package repositories;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import DomainModel.Hoadon;
import Utilities.JpaUtils;
	

public class ImplBangHoaDon implements InterfaceBangHoaDon {
	
//EntityManager entityManager = JpaUtils.getEntityManager();
	
	@Override
	public List<Hoadon> findAll() {
            EntityManager entityManager = JpaUtils.getEntityManager();
		String jsql = "SELECT h FROM Hoadon h";
		TypedQuery<Hoadon> query = entityManager.createQuery(jsql.toString(), Hoadon.class);
		List<Hoadon> list = query.getResultList();
		return list;
	}
	public List<Hoadon> findAll(int position, int pageSize) {
            EntityManager entityManager = JpaUtils.getEntityManager();
		String jsql = "SELECT hd FROM Hoadon hd";
		TypedQuery<Hoadon> query = entityManager.createQuery(jsql.toString(), Hoadon.class);
		query.setFirstResult((position-1)*3);
		query.setMaxResults(pageSize);
		List<Hoadon> list = query.getResultList();
		return list;
	}

	@Override
	public Hoadon findById(int id) {
            EntityManager entityManager = JpaUtils.getEntityManager();
		Hoadon hoadon = entityManager.find(Hoadon.class, id);
		return hoadon;
	}

	@Override
	public Hoadon create(Hoadon hoadon) {
            EntityManager entityManager = JpaUtils.getEntityManager();
		try {
			System.out.println(hoadon.toString());
			entityManager.getTransaction().begin();
			entityManager.persist(hoadon);
		    entityManager.getTransaction().commit();
				return hoadon;
		}catch(Exception ex) {

                    System.out.println("loooix create hoa don");
			entityManager.getTransaction().rollback();
			throw new RuntimeException();
		}
	}

	@Override
	public Hoadon update(Hoadon hoadon) {
            EntityManager entityManager = JpaUtils.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(hoadon);
		    entityManager.getTransaction().commit();
					return hoadon;
		}catch(Exception ex) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException();
		}
	}

	@Override
	public long totalCount() {
		EntityManager em = JpaUtils.getEntityManager();
        try {
        	String sql = "Select COUNT(h.id) From  Hoadon h ";
    		Query query = em.createQuery(sql);
            return Long.parseLong(query.getSingleResult().toString());
        } catch (Exception e) {
        	em.getTransaction().rollback();
			throw new RuntimeException();
        }
	}
	
	@Override
	public List<Hoadon> findByTStatus (int a, int position, int pageSize) {
            EntityManager entityManager = JpaUtils.getEntityManager();
		String jsql = "SELECT h FROM Hoadon h where h.trangThai = ? 1";
		TypedQuery<Hoadon> query = entityManager.createQuery(jsql.toString(), Hoadon.class);
		query.setFirstResult((position-1)*3);
		query.setMaxResults(pageSize);
		query.setParameter(1, (byte)a);
		List<Hoadon> list = query.getResultList();
		return list;
	}
	
	
	@Override
	public List<Hoadon> findByTStatusvaTrangthai (int a, Date date, int position, int pageSize) {
            EntityManager entityManager = JpaUtils.getEntityManager();
		String jsql = "SELECT h FROM Hoadon h where h.trangThai = ? 1 and  h.ngayTao = ? 2";
		TypedQuery<Hoadon> query = entityManager.createQuery(jsql.toString(), Hoadon.class);
		query.setFirstResult((position-1)*3);
		query.setMaxResults(pageSize);
		query.setParameter(1, (byte)a);
		query.setParameter(2, date);
		List<Hoadon> list = query.getResultList();
		return list;
	}

	@Override
	public List<Hoadon> findByDate (Date date1,Date date2 ) {
            EntityManager entityManager = JpaUtils.getEntityManager();
		String jsql = "SELECT h FROM Hoadon h where h.ngayTao between ? 1 and ? 2";
		TypedQuery<Hoadon> query = entityManager.createQuery(jsql.toString(), Hoadon.class);
		query.setParameter(1,date1);
                query.setParameter(2,date2);
		List<Hoadon> list = query.getResultList();
		return list;
	}
        

        @Override
	public Hoadon findHoaDonByBan (int idBan) {
                EntityManager em = JpaUtils.getEntityManager();
		String jsql = "SELECT h FROM Hoadon h "
                        + "INNER JOIN Hoadoinchitiet hdct ON hdct.hoadon.ID_HoaDon = h.ID_HoaDon "
                        + "INNER JOIN Ban b ON b.ID_Ban = hdct.ban.ID_Ban "
                        + "WHERE h.trangThai =0 AND b.ID_Ban = "+idBan;
		TypedQuery<Hoadon> query = em.createQuery(jsql, Hoadon.class);
		Hoadon hd = query.getSingleResult();
		return hd;
	}
        

   
}

