
  package com.nt.model;
  
  import java.util.HashSet; import java.util.Set;
  
  import javax.persistence.Column; import javax.persistence.Entity; import
  javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
  import javax.persistence.Id; import javax.persistence.JoinColumn; import
  javax.persistence.JoinTable; import javax.persistence.ManyToMany; import
  javax.persistence.Table;
  
  import lombok.Data;
  
  @Entity
  
  @Data
  
  @Table(name = "users") public class User {
  
  
  @Id
  
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  private Integer id;
  
  @Column(nullable = false ,unique = true ,length=45)
  private String email;
  
  @Column(nullable =false ,length=65)
  private String password;
  
  @Column(nullable =false ,length=25)
  private String firstname;
  
  @Column(nullable =false ,length=25)
  private String lastname;
  
  
  
  @ManyToMany
    @JoinTable( name="user_roles", joinColumns = @JoinColumn(name = "user_id"),
  inverseJoinColumns = @JoinColumn(name = "role_id") )
  private Set<Role> roles  =new HashSet<>();
  
   public void add(Role role) {
	  this. roles.add(role);
	  }
  }
  
  
  
  
  
  //private role }
 