package group.boboaigaowei.dao.mysql.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import group.boboaigaowei.util.db.DistributedId;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties
public abstract class AbstractSqlEntity implements Serializable {


    @Id
    @GenericGenerator(name = "IdGen", type = DistributedId.class)
    @GeneratedValue(generator = "IdGen")
    private Long id;

    @CreatedDate
    private Date createDate;

    @LastModifiedDate
    private Date lastModifiedDate;

}
