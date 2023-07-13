package repositories;

import java.util.Date;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Utilities.JpaUtils;
import DomainModel.Combo;
import DomainModel.Hoadoinchitiet;
import DomainModel.Sanpham;
import java.util.ArrayList;



public class ImplBangHoaDonChiTiet implements InterfaceBangHoaDonChiTiet{
	
//	public EntityManager entityManager = JpaUtils.getEntityManager();
	
	@Override
	public List<Hoadoinchitiet> findAll(int position, int pageSize) {
             EntityManager entityManager = JpaUtils.getEntityManager();
		String jsql = "SELECT h FROM Hoadoinchitiet h";
		TypedQuery<Hoadoinchitiet> query = entityManager.createQuery(jsql.toString(), Hoadoinchitiet.class);
		List<Hoadoinchitiet> list = query.getResultList();
		return list;
	}

	@Override
	public Hoadoinchitiet findById(int id) {
            EntityManager entityManager = JpaUtils.getEntityManager();
		Hoadoinchitiet hdct = entityManager.find(Hoadoinchitiet.class, id);
		return hdct;
	}

	@Override
	public Hoadoinchitiet create(Hoadoinchitiet hdct) {
            EntityManager entityManager = JpaUtils.getEntityManager();
		try {
			System.out.println(hdct.toString());
			entityManager.getTransaction().begin();
			entityManager.persist(hdct);
		    entityManager.getTransaction().commit();
					return hdct;
		}catch(Exception ex) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException();
		}
	}

	@Override
	public Hoadoinchitiet update(Hoadoinchitiet hdct) {
            EntityManager entityManager = JpaUtils.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(hdct);
		    entityManager.getTransaction().commit();
					return hdct;
		}catch(Exception ex) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException();
		}
	}

	@Override
	public long totalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Hoadoinchitiet> findByIdHD(int id) {
            EntityManager entityManager = JpaUtils.getEntityManager();
		String jsql = "SELECT h FROM Hoadoinchitiet h where h.hoadon.ID_HoaDon = ?1";
		TypedQuery<Hoadoinchitiet> query = entityManager.createQuery(jsql.toString(), Hoadoinchitiet.class);
		query.setParameter(1, id);
		List<Hoadoinchitiet> list = query.getResultList();
		return list;
	}
	@Override
	public List<Sanpham> findSanPham(int id) {
            EntityManager entityManager = JpaUtils.getEntityManager();
		String jsql = "SELECT s FROM Hoadoinchitiet h inner join Sanpham s  on h.ma = s.ID_SanPham where h.hoadon.ID_HoaDon = ?1 and h.kieu = 1";
		TypedQuery<Sanpham> query = entityManager.createQuery(jsql.toString(), Sanpham.class);
		query.setParameter(1, id);
		List<Sanpham> list = query.getResultList();
		return list;
	}
	@Override
        
	public List<Combo> findCombo(int id) {
            EntityManager entityManager = JpaUtils.getEntityManager();
		String jsql = "SELECT c FROM Hoadoinchitiet h inner join Combo c on h.ma = c.ID_ComBo where h.hoadon.ID_HoaDon = ?1 and h.kieu = 0";
		TypedQuery<Combo> query = entityManager.createQuery(jsql.toString(), Combo.class);
		query.setParameter(1, id);
		List<Combo> list = query.getResultList();
		return list;
	}
	
	public double tongtien(int a ) { 
            EntityManager entityManager = JpaUtils.getEntityManager();
		String jsql = "select SUM(hdct.DonGia * hdct.SoLuong) "
				+ "FROM hoadon h inner join hoadoinchitiet hdct on h.ID_HoaDon = hdct.IDHoaDon "
				+ "where month(h.NgayTao) =  ? 1 and year(h.NgayTao) = ? 2 ";
		Query query = entityManager.createQuery(jsql);
		return (double) query.getSingleResult();
	}
	
	

    @Override
    public List<Hoadoinchitiet> findAll() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        String jsql = "SELECT h FROM Hoadoinchitiet h";
        TypedQuery<Hoadoinchitiet> query = entityManager.createQuery(jsql.toString(), Hoadoinchitiet.class);
        List<Hoadoinchitiet> list = query.getResultList();
        return list;
    }

    
    @Override
    public List<Hoadoinchitiet> findByIdBan(int id) {
        EntityManager em = JpaUtils.getEntityManager();
        List<Hoadoinchitiet> lst = new ArrayList<>();
        String jsql = "select hdct  "
                + "FROM Hoadoinchitiet hdct INNER JOIN Ban b ON hdct.ban.ID_Ban = b.ID_Ban "
                + "INNER JOIN Hoadon hd ON hd.ID_HoaDon = hdct.hoadon.ID_HoaDon "
                + "Where hd.trangThai=0 AND b.ID_Ban =" + id;
        TypedQuery<Hoadoinchitiet> query = em.createQuery(jsql, Hoadoinchitiet.class);
        lst = query.getResultList();
        return lst;
    }

    @Override
    public void UpdateSelected(int idBan) {
        EntityManager em = JpaUtils.getEntityManager();
        em.getTransaction().begin();
        List<Hoadoinchitiet> lst = new ArrayList<>();
        String jsql = "select hdct  "
                + "FROM Hoadoinchitiet hdct INNER JOIN Ban b ON hdct.ban.ID_Ban = b.ID_Ban "
                + "INNER JOIN Hoadon hd ON hd.ID_HoaDon = hdct.hoadon.ID_HoaDon "
                + "Where hd.trangThai=0 AND b.ID_Ban =" + idBan;
        TypedQuery<Hoadoinchitiet> query = em.createQuery(jsql, Hoadoinchitiet.class);
        lst = query.getResultList();
        for (Hoadoinchitiet x : lst) {
            em.remove(x);
        }
        em.getTransaction().commit();
        return;
    }


    
    


}
