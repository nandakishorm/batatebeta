package com.kishor.batatebeta.core.domain;

import com.kishor.batatebeta.core.dictionary.Status;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

/**
 * Created by Nandakishor on 8/11/2015.
 */

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(fetch = FetchType.EAGER)
    protected Long id;

    @Column(name = "status", nullable = false, columnDefinition = "ENUM('Active', 'Inactive', 'Blocked') default 'Active'")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "uid", nullable = false, unique = true)
    private String uid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @PrePersist
    public void init()
    {
        this.uid = UUID.randomUUID().toString();
        if(this.status == null)
            this.status = Status.Active;
    }
}
