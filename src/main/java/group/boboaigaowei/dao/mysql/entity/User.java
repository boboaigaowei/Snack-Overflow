package group.boboaigaowei.dao.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
@Entity
@Table(name = "user")
public class User extends AbstractSqlEntity {

    @Column(name = "name")
    private String name;
}
