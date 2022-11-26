/*
 * package edu.sjsu.cmpe275.nft.entities;
 * 
 * import java.util.Date;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
 * import javax.persistence.Id; import javax.persistence.JoinColumn; import
 * javax.persistence.OneToOne; import javax.persistence.Table; import
 * javax.persistence.Temporal; import javax.persistence.TemporalType;
 * 
 * @Entity(name = "verification_token")
 * 
 * @Table(name = "VERIFICATION_TOKEN") public class VerificationToken {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.AUTO)
 * 
 * @Column(name="TOKEN_ID") private Long tokenid;
 * 
 * @Column(name="TOKEN") private String token;
 * 
 * @Temporal(TemporalType.TIMESTAMP) private Date createdDate;
 * 
 * @OneToOne
 * 
 * @JoinColumn(nullable = false, name = "USER_ID") private User user;
 * 
 * public Long getTokenid() { return tokenid; }
 * 
 * public void setTokenid(Long tokenid) { this.tokenid = tokenid; }
 * 
 * public String getToken() { return token; }
 * 
 * public void setToken(String token) { this.token = token; }
 * 
 * public Date getCreatedDate() { return createdDate; }
 * 
 * public void setCreatedDate(Date createdDate) { this.createdDate =
 * createdDate; }
 * 
 * public User getUser() { return user; }
 * 
 * public void setUser(User user) { this.user = user; }
 * 
 * }
 */