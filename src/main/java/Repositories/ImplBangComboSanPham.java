package repositories;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Utilities.JpaUtils;
import DomainModel.ComboSanpham;
import DomainModel.Sanpham;

public class ImplBangComboSanPham {

    public final EntityManager entityManager = JpaUtils.getEntityManager();

    public List<ComboSanpham> findAll() {
        TypedQuery<ComboSanpham> query = entityManager.createQuery("SELECT c FROM ComboSanpham c", ComboSanpham.class);
        List<ComboSanpham> list = query.getResultList();
        return list;
    }

    public ComboSanpham create(ComboSanpham ban) {
        try {
            System.out.println(ban.getCombo().getID_ComBo());
            System.out.println(ban.getSanpham().getMaSanPham());
            System.out.println(ban.getSoLuong());
            entityManager.getTransaction().begin();
            entityManager.persist(ban);
            entityManager.getTransaction().commit();
            return ban;
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            throw new RuntimeException();
        }
    }

    public ComboSanpham update(ComboSanpham ban) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(ban);
            entityManager.getTransaction().commit();
            return ban;
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException();
        }
    }

}
