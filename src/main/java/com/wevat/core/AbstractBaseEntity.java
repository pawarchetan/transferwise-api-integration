package com.wevat.core;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    public AbstractBaseEntity() {
        this.id = UUID.randomUUID().toString();
    }
}
