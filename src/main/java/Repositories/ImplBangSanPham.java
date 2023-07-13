package repositories;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import DomainModel.Sanpham;
import DomainModel.Theloai;
import Utilities.JpaUtils;


public class ImplBangSanPham implements InterfaceBangSanPham {
	

	public final EntityManager em;


	public ImplBangSanPham() {
		this.em = JpaUtils.getEntityManager();
	}
	
	@Override
	public List<Sanpham> findAll(int position, int pageSize) {

		TypedQuery<Sanpham> query = em.createQuery("SELECT s FROM Sanpham s", Sanpham.class);
		query.setFirstResult((position-1)*3);
		query.setMaxResults(pageSize);
		return query.getResultList();

	}

	@Override
	public Sanpham findById(int id) {
		Sanpham bsp = em.find(Sanpham.class, id);
		return bsp;
	}

	@Override
	public Sanpham create(Sanpham SanPham) {
		try {
			this.em.getTransaction().begin();
			this.em.persist(SanPham);
			this.em.getTransaction().commit();
					return SanPham;
		}catch(Exception ex) {
			this.em.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public Sanpham update(Sanpham SanPham) {
		try {
			this.em.getTransaction().begin();
			this.em.merge(SanPham);
			this.em.getTransaction().commit();
			return SanPham;

		}catch(Exception ex) {
			this.em.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public Sanpham remove(int id) {
		Sanpham SanPham = this.findById(id);
		try {
			this.em.getTransaction().begin();
			this.em.remove(SanPham);
			this.em.getTransaction().commit();
			return SanPham;

		}catch(Exception ex) {
			this.em.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public long totalCount() {
		try {
		String jpql = "Select count(b) from Sanpham b ";
		Query query = this.em.createQuery(jpql);
		return ((Long) query.getSingleResult());
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}



    @Override
    public List<Sanpham> findByType(int type) {
        TypedQuery<Sanpham> query = em.createQuery("SELECT s FROM Sanpham s WHERE s.theloai="+type, Sanpham.class);
		return query.getResultList();
    }
    
    	public List<Sanpham> findSP() {


		TypedQuery<Sanpham> query = em.createQuery("SELECT s FROM Sanpham s", Sanpham.class);
		return query.getResultList();

	}
}

//        public void updateSP(double giaTien, String hinhAnh, String maSanPham, String tenSanPham, int trangThai, Theloai theLoai) {
//
//		          try {
//                em.createNamedQuery("updateSanPham", Sanpham.class).setParameter(1, giaTien).setParameter(2, hinhAnh)
//                        .setParameter(3, tenSanPham).setParameter(4, trangThai).setParameter(5, theLoai).setParameter(6, maSanPham).executeUpdate();
//            } catch (Exception e) {
//                e.getStackTrace();
//            }}

	
//	public void updateByMa(String ma, String ten, String gia, String anh, String theloai) {
//
//		Query query = em.createQuery("UPDATE Sanpham s SET s.maSanPham = s.maSanPham + :ma", Sanpham.class);
//		query.setFirstResult((position-1)*3);
//		query.setMaxResults(pageSize);
//		return query.getResultList();
//
//	}

