package group.boboaigaowei.util.db;

import group.boboaigaowei.dao.mysql.entity.AbstractSqlEntity;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class DistributedId implements IdentifierGenerator {

    private final Snowflake snowflake;

    public DistributedId() {
        this.snowflake = new Snowflake(0, 0);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        if (object == null) throw new HibernateException(new NullPointerException());
        if (((AbstractSqlEntity) object).getId() == null) {
            return snowflake.nextId();
        } else {
            return ((AbstractSqlEntity) object).getId();
        }
    }
}
