package com.sipl.yard.management.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_master")
public class UserMaster implements Serializable {
    private static final long serialVersionUID = 284848972631717763L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    private String password;
    
    @ManyToOne
    @JoinColumn(name ="role_id")
    private RoleMaster roleMaster;

    @Column(name = "is_active")
    private Boolean active;
    
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_time")
    private LocalDateTime modifiedTime;
}
