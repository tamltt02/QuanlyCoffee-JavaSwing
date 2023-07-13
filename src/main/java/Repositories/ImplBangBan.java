package repositories;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Utilities.JpaUtils;
import DomainModel.Ban;
import DomainModel.Hoadoinchitiet;
import java.util.ArrayList;

public class ImplBangBan implements InterfaceBangBan{

	public final EntityManager entityManager = JpaUtils.getEntityManager();

	@Override
	public List<Ban> findAll(int position, int pageSize) {
		String jsql = "SELECT b FROM Ban b";
		TypedQuery<Ban> query = entityManager.createQuery(jsql.toString(), Ban.class);
		query.setFirstResult((position-1)*pageSize);
		query.setMaxResults(pageSize);
		List<Ban> list = query.getResultList();
		return list;
	}
        
//        public List<Ban> findAll() {
//		String jsql = "SELECT b FROM Ban b";
//		TypedQuery<Ban> query = entityManager.createQuery(jsql.toString(), Ban.class);
//		List<Ban> list = query.getResultList();
//		return list;
//	}

	@Override
	public Ban findById(long id) {
		Ban Ban = entityManager.find(Ban.class, id);
		return Ban;
	}
        
        @Override
	public Ban findById2(long id) {
		EntityManager entityManager = JpaUtils.getEntityManager();
		 String jsql = "SELECT b FROM Ban b where b.ID_Ban = "+id;
		TypedQuery<Ban> query = entityManager.createQuery(jsql, Ban.class);
		Ban ban = query.getSingleResult();
		return ban;
	}
        
	@Override
	public Ban create(Ban ban) {
		try {
			entityManager.getTransaction().begin();
			System.out.println(ban.getID_Ban());
			System.out.println(ban.getMaBan());	
			System.out.println(ban.getTrangThai());	
			entityManager.persist(ban);
		    entityManager.getTransaction().commit();
//		    entityManager.flush();
					return ban;
		}catch(Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
			throw new RuntimeException();
		}
	}

	@Override
	public Ban update(Ban ban) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(ban);
		    entityManager.getTransaction().commit();
					return ban;
		}catch(Exception ex) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException();
		}
	}

	@Override
	public Ban remove(long id) {
		Ban ban = this.findById(id);
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(ban);
		    entityManager.getTransaction().commit();
					return ban;
		}catch(Exception ex) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException();
		}
	}

	@Override
	public long totalCount() {
		EntityManager em =  JpaUtils.getEntityManager();
        try {
        	String sql = "Select COUNT(k.id) From Ban k ";
    		Query query = em.createQuery(sql);
            return Long.parseLong(query.getSingleResult().toString());
        } catch (Exception e) {
        	em.getTransaction().rollback();
			throw new RuntimeException();
        }
	}

    @Override
    public List<Ban> findByStatus(int status) {
         EntityManager entityManager = JpaUtils.getEntityManager();
        String jsql = "SELECT b FROM Ban b where b.trangThai = "+status+"and b.ID_Ban<>-1";
		TypedQuery<Ban> query = entityManager.createQuery(jsql, Ban.class);
		List<Ban> list = query.getResultList();
		return list;
    }

    @Override
    public List<Ban> findAll() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        String jsql = "SELECT b FROM Ban b Where b.ID_Ban<>-1";
		TypedQuery<Ban> query = entityManager.createQuery(jsql, Ban.class);
		List<Ban> list = query.getResultList();
		return list;
    }

    @Override
    public List<Hoadoinchitiet> findAllSelectedItem(int id_ban) {
        EntityManager entityManager = JpaUtils.getEntityManager();
        List<Hoadoinchitiet> lst = new ArrayList<>();
        String jsql = "SELECT hdct from Hoadoinchitiet hdct "
                + " Inner JOIN Ban b ON hdct.ban.ID_Ban = b.ID_Ban"
                + " Inner JOIN Hoadon hd ON hd.ID_HoaDon = hdct.hoadon.ID_HoaDon "
                + " Where b.ID_Ban="+id_ban+""
                +  " AND hd.trangThai = 0";
	TypedQuery<Hoadoinchitiet> query = entityManager.createQuery(jsql,Hoadoinchitiet.class);
        lst = query.getResultList();
		return lst;
    }
	
	
    	public List<Ban> getlst() {
		String jsql = "SELECT b FROM Ban b";
		TypedQuery<Ban> query = entityManager.createQuery(jsql.toString(), Ban.class);
		List<Ban> list = query.getResultList();
		return list;
	}
}
