package group.boboaigaowei.dao.mysql.repo;

import group.boboaigaowei.dao.mysql.entity.AbstractSqlEntity;

import java.util.List;

public interface AbstractSqlRepo<E extends AbstractSqlEntity> {

    E findById(Long id);

    E save(E e);

    List<E> saveAll(Iterable<E> iterable);

    void delete(E e);

    void deleteAll(Iterable<E> iterable);
}
