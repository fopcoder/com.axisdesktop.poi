// package com.axisdesktop.poi.entity;
//
// import java.util.Calendar;
//
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.EnumType;
// import javax.persistence.Enumerated;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.PrePersist;
// import javax.persistence.PreUpdate;
// import javax.persistence.Table;
// import javax.persistence.Temporal;
// import javax.persistence.TemporalType;
//
// import com.axisdesktop.user.AppUser;
// import com.axisdesktop.user.AppUser.Role;
// import com.axisdesktop.user.AppUser.SocialMediaService;
//
// @Entity
// @Table( name = "user", schema = "user" )
// public class AppUserEntity {
// @Id
// @GeneratedValue( strategy = GenerationType.IDENTITY )
// private long id;
//
// @Column( updatable = false )
// @Temporal( TemporalType.TIMESTAMP )
// private Calendar created;
//
// @Temporal( TemporalType.TIMESTAMP )
// private Calendar modified;
//
// private String email;
//
// @Column( name = "first_name" )
// private String firstName;
//
// @Column( name = "last_name" )
// private String lastName;
//
// @Column( name = "password" )
// private String password;
//
// @Enumerated( EnumType.STRING )
// @Column( name = "role" )
// private Role role;
//
// @Enumerated( EnumType.STRING )
// @Column( name = "sign_in_provider" )
// private SocialMediaService signInProvider;
//
// @PrePersist
// private void prePersist() {
// this.created = this.modified = Calendar.getInstance();
// }
//
// @PreUpdate
// private void preUpdate() {
// this.modified = Calendar.getInstance();
// }
//
// public long getId() {
// return id;
// }
//
// public void setId( long id ) {
// this.id = id;
// }
//
// public Calendar getCreated() {
// return created;
// }
//
// public void setCreated( Calendar created ) {
// this.created = created;
// }
//
// public Calendar getModified() {
// return modified;
// }
//
// public void setModified( Calendar modified ) {
// this.modified = modified;
// }
//
// public String getEmail() {
// return email;
// }
//
// public void setEmail( String email ) {
// this.email = email;
// }
//
// public String getFirstName() {
// return firstName;
// }
//
// public void setFirstName( String firstName ) {
// this.firstName = firstName;
// }
//
// public String getLastName() {
// return lastName;
// }
//
// public void setLastName( String lastName ) {
// this.lastName = lastName;
// }
//
// public String getPassword() {
// return password;
// }
//
// public void setPassword( String password ) {
// this.password = password;
// }
//
// public Role getRole() {
// return role;
// }
//
// public void setRole( Role role ) {
// this.role = role;
// }
//
// public SocialMediaService getSignInProvider() {
// return signInProvider;
// }
//
// public void setSignInProvider( SocialMediaService signInProvider ) {
// this.signInProvider = signInProvider;
// }
//
// }
