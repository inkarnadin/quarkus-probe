package ru.alarh.web.service;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.eventbus.MessageConsumer;
import io.vertx.reactivex.sqlclient.SqlClient;
import lombok.extern.log4j.Log4j2;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import ru.alarh.config.DbConfiguration;
import ru.alarh.db.meta.tables.daos.UserDao;
import ru.alarh.db.meta.tables.pojos.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Optional;

@RequestScoped
@Log4j2
public class UserService implements IUserService {

    @Inject
    SqlClient client;

    @Inject
    Vertx vertx;

    @Inject
    DbConfiguration config;

    @Override
    public void get() {
        UserDao dao = new UserDao(config, client);
        dao.findOneById(212406L).doOnEvent((s, x) -> {
            if (x == null) {
                s.ifPresent(u -> vertx.eventBus().send("sendSomething", u.toJson()));
            } else {
                log.warn("User is not found " + x.getMessage());
            }
        });

        MessageConsumer<Object> sendSomething = vertx.eventBus().consumer("sendSomething", log::warn);

    }

}
