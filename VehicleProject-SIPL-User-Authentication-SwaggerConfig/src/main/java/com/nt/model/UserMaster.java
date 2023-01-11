
  package com.nt.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity  
  @Data  
  @Table(name = "users")
  public class UserMaster {
  


@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) 
  private Integer id;
  
  @Column(nullable = false ,unique = true ,length=45)
  private String username;
  
  @Column(nullable =false ,length=65)
  private String password;
  
  @Column(nullable =false ,length=25)
  private String firstname;
  
  @Column(nullable =false ,length=25)
  private String lastname;
  
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<RoleMaster> role ;
  

  }
  
  
  
  
  
  //private role }
 