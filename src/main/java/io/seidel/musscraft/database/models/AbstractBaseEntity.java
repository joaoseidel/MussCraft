package io.seidel.musscraft.database.models;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String uuid;

    private Date creationDate;

    private Date updateDate;

    public AbstractBaseEntity() {
        this.uuid = UUID.randomUUID().toString();
        this.creationDate = new Date();
    }

    public String getUuid() {
        return uuid;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof AbstractBaseEntity)) {
            return false;
        }

        AbstractBaseEntity other = (AbstractBaseEntity) obj;
        return getUuid().equals(other.getUuid());
    }
}
