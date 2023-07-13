package repositories;

import Utilities.JpaUtils;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;


import DomainModel.Nhanvien;

public class ImplBangNhanVien implements InterfaceBangNhanVien{
	
	private javax.persistence.EntityManager em;

	public ImplBangNhanVien() {
		this.em = JpaUtils.getEntityManager();
	}

	@Override
	public List<Nhanvien> findAll(int page, int pagesize) {
		TypedQuery<Nhanvien> query = em.createNamedQuery("Nhanvien.findAll", Nhanvien.class);
		query.setFirstResult((page-1)*3);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}
	
	public List<Nhanvien> findAll() {
		TypedQuery<Nhanvien> query = em.createNamedQuery("Nhanvien.findAll", Nhanvien.class);
		return query.getResultList();
	}

	@Override
	public Nhanvien findById(int id) {
		TypedQuery<Nhanvien> typedQuery
	      = em.createQuery("SELECT b FROM Nhanvien b WHERE b.id=:id", Nhanvien.class);
	    typedQuery.setParameter("id", id);
	    return typedQuery.getSingleResult();
	}

	@Override
	public Nhanvien create(Nhanvien NhanVien) {
		try {
			this.em.getTransaction().begin();//bd
			
			this.em.persist(NhanVien);//thêm đối tượng vào DB
			
			
			this.em.getTransaction().commit();//kt
			return NhanVien;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public Nhanvien update(Nhanvien NhanVien) {
		try {
			this.em.getTransaction().begin();//bd
			this.em.merge(NhanVien);//thêm đối tượng vào DB
			this.em.getTransaction().commit();//kt
			return NhanVien;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public Nhanvien remove(int id) {
		Nhanvien nhanvien = this.findById(id);
		try {
			this.em.getTransaction().begin();
			this.em.remove(nhanvien);
			this.em.getTransaction().commit();
			return nhanvien;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public long totalCount() {
		String jpql = "Select count(b) from Nhanvien b ";
		Query query = em.createQuery(jpql);
		return ((Long) query.getSingleResult());
	}

	@Override
	public Nhanvien login(String userName, String password) {
		String jpql = "Select b from Nhanvien b WHERE b.taiKhoan =  ? 1 and b.matKhau = ? 2";
		TypedQuery<Nhanvien> query = em.createQuery(jpql, Nhanvien.class);
		query.setParameter(1, userName);
		query.setParameter(2, password);
		if (query.getResultList().isEmpty()) {
			System.out.println("sai r");
		return null;
		}
		return query.getResultList().get(0);
	}

    @Override
    public Nhanvien findbyEmail(String email, String taikhoan) {
    Query typedQuery= em.createQuery("Select b from Nhanvien b WHERE b.email =  ? 1 and b.taiKhoan = ? 2 ", Nhanvien.class);
	    typedQuery.setParameter(1, email);
             typedQuery.setParameter(2, taikhoan);
             Nhanvien nv = (Nhanvien) typedQuery.getSingleResult();
	    return update(nv);}

	
	
	
	
	
}
