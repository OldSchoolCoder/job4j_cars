package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Post;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class AdRepository implements Store {
    private final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder()
                    .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private <T> T wrapper(final Function<Session, T> command) {
        Session session = sf.openSession();
        session.beginTransaction();
        try {
            T result = command.apply(session);
            session.getTransaction().commit();
            return result;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Post> postsFromLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date lastDayTime = calendar.getTime();
        return this.wrapper(session -> session.createQuery("from Post " +
                "where created>:lastDayTime")
                .setParameter("lastDayTime", lastDayTime).list());
    }

    @Override
    public List<Post> postsWithPhoto() {
        return this.wrapper(session -> session.createQuery("select distinct " +
                "pst from Post pst join fetch pst.photos ph " +
                "where ph.size>0").list());
    }

    @Override
    public List<Post> findByBrand(String brand) {
        return this.wrapper(session -> session.createQuery("select distinct " +
                "pst from Post pst join fetch pst.car c " +
                "where c.brand=:brand").setParameter("brand", brand).list());
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
