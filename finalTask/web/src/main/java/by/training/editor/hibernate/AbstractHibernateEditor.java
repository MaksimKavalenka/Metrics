package by.training.editor.hibernate;

import org.hibernate.Session;

import by.training.dao.IDAO;
import by.training.data.hibernate.HibernateUtil;

public abstract class AbstractHibernateEditor implements IDAO {

    protected Session session;

    public AbstractHibernateEditor() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public void close() {
        session.close();
    }

}
