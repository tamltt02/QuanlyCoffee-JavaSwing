package repositories;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import DomainModel.Khuyenmai;
import Utilities.JpaUtils;

public class ImplBangKhuyenMai implements InterfaceBangKhuyenMai{

	@Override
	public List<Khuyenmai> findAll(int page, int pagesize) {
		EntityManager em =JpaUtils.getEntityManager();
		TypedQuery<Khuyenmai> query = em.createNamedQuery("Khuyenmai.findAll", Khuyenmai.class);
		
		query.setFirstResult((page-1)*3);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}
	

        @Override
	public List<Khuyenmai> findAll() {
		EntityManager em =JpaUtils.getEntityManager();
		TypedQuery<Khuyenmai> query = em.createNamedQuery("Khuyenmai.findAll", Khuyenmai.class);
		return query.getResultList();
	}
        

	@Override
	public Khuyenmai findById(String maKM) {
		EntityManager em = JpaUtils.getEntityManager();

		TypedQuery<Khuyenmai> query = em.createQuery("Select k from where Khuyenmai k =:maKM",Khuyenmai.class);

		query.setParameter("maKM", maKM);
		
		Khuyenmai km = query.getSingleResult();
		return km;
	}

	@Override
	public Khuyenmai create(Khuyenmai khuyenmai) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			System.out.println(khuyenmai.getID_KhuyenMai());
			em.persist(khuyenmai);
			trans.commit();
			return khuyenmai;
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException();
		}
	}

	@Override
	public long totalCount() {
		EntityManager em = JpaUtils.getEntityManager();
        try {

        	String sql = "Select COUNT(k.id) From Khuyenmai k ";
    		Query query = em.createQuery(sql);
            return Long.parseLong(query.getSingleResult().toString());
        } catch (Exception e) {
        	em.getTransaction().rollback();
			throw new RuntimeException();
        }
	}

	@Override
	public Khuyenmai update(Khuyenmai khuyenmai) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(khuyenmai);
			trans.commit();
			return khuyenmai;
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException();
		}
	}


	@Override
	public List<Khuyenmai> find(int page,int pagesize,String name) {
		EntityManager em = JpaUtils.getEntityManager();
		String sql = "Select k from Khuyenmai k where k.maKhuyenMai LIKE '%'||?1||'%' OR k.tenKhuyenMai LIKE '%'||?2||'%'";
		TypedQuery<Khuyenmai> query = em.createQuery(sql,Khuyenmai.class);
		query.setParameter(1, name);
		query.setParameter(2, name);
		query.setFirstResult((page-1)*3);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	

//	public String CreateNewMaHD(int a){
//        int temp = a+1;
//        return "KM"+temp;
//    }
//    public int GetMaxHD(){
//    	EntityManager em = JpaUtil.getEntity();
//        try {
//        	Khuyenmai bkm;
//        	String sql = "SELECT a FROM Bangkhuyenmai a order by convert(substring(a.maKhuyenMai,3,length(a.maKhuyenMai)),float)  Desc";
//    		TypedQuery<Khuyenmai> query = em.createQuery(sql,Khuyenmai.class);
//    		bkm = (Khuyenmai) query.getSingleResult();
//    		String maxHD = bkm.getMaKhuyenMai().substring(2);
//            return Integer.parseInt(maxHD);
//        } catch (Exception e) {
//        	em.getTransaction().rollback();
//			throw new RuntimeException();
//        }
//    }

}
