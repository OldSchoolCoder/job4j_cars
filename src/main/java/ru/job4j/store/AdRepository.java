package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.model.Car;
import ru.job4j.model.Model;
import ru.job4j.model.Post;
import ru.job4j.model.User;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    public List<Post> postsFilter(String data, String filterType) {
        return this.wrapper(session -> session.createQuery("select distinct " +
                "pst from Post pst join fetch pst.photos ph " +
                "join fetch pst.car cr where cr." + filterType + " =:data " +
                "and pst.sale=false")
                .setParameter("data", data).list());
    }

    public List<Post> powerFilter(Integer power) {
        return this.wrapper(session -> session.createQuery("select distinct " +
                "pst from Post pst join fetch pst.photos ph " +
                "join fetch pst.car cr where cr.power =:power " +
                "and pst.sale=false")
                .setParameter("power", power).list());
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
    public List<Post> allPosts() {
        return this.wrapper(session -> session.createQuery("select distinct " +
                "pst from Post pst join fetch pst.photos " +
                "where pst.sale=false ").list());
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

    @Override
    public Optional<User> findByEmail(String email) {
        User user = (User) this.wrapper(session -> {
            final Query query = session.createQuery("from User where" +
                    " email=:email");
            query.setParameter("email", email);
            return query.uniqueResult();
        });
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<Post> findPostById(Integer id) {
        Post post = (Post) this.wrapper(session -> {
            final Query query = session.createQuery("select distinct " +
                    "pst from Post pst join fetch pst.photos " +
                    "where pst.id=:id");
            query.setParameter("id", id);
            return query.uniqueResult();
        });
        return Optional.ofNullable(post);
    }

    @Override
    public void add(Model model) {
        this.wrapper(session -> session.merge(model));
    }
}