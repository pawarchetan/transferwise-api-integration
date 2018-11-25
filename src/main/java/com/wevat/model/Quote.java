package com.wevat.model;

import com.wevat.core.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "quote")
public class Quote extends AbstractBaseEntity {
    private String transferWiseQuoteId;
    private String source;
    private String target;
    private BigDecimal sourceAmount;
    private BigDecimal targetAmount;
    private BigDecimal rate;
    private BigDecimal fee;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private User user;
}
