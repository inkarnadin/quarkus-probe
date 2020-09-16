package ru.alarh.config;

import lombok.extern.log4j.Log4j2;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;

import javax.inject.Singleton;

@Log4j2
@Singleton
public class DbConfiguration extends DefaultConfiguration {

    DbConfiguration() {
        super();
        super.set(SQLDialect.MARIADB);
    }

}
