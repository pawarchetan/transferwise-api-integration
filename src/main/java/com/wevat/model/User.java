package com.wevat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevat.core.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends AbstractBaseEntity {
    private String firstName;
    private String lastName;
    private String payoutCurrency;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Quote> quotes = new HashSet<>();
}
