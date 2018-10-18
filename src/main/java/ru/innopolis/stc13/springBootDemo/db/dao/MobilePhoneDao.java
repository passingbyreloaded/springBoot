package ru.innopolis.stc13.springBootDemo.db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc13.springBootDemo.db.entities.MobilePhone;

import javax.persistence.EntityManagerFactory;

@Repository
public class MobilePhoneDao {

    private SessionFactory sessionFactory;

    @Autowired
    public MobilePhoneDao(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public MobilePhone getPhoneById(long id) {
        Session session = sessionFactory.openSession();
        MobilePhone mobilePhone = session.get(MobilePhone.class, id);
        session.close();
        return mobilePhone;
    }

    public void addPhone(MobilePhone phone) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(phone);
        session.getTransaction().commit();
        session.close();
    }

    public void updatePhone(MobilePhone mobilePhone) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(mobilePhone);
        session.getTransaction().commit();
        session.close();
    }

    public void deletePhoneById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        MobilePhone mobilePhone = session.get(MobilePhone.class, id);
        session.delete(mobilePhone);
        session.getTransaction().commit();
        session.close();
    }
}
