package catalog.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import catalog.user.model.User;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="CATEGORIES")
public class Category {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="CATEGORY_ID")
	private Long id;
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;
	
	@OneToMany(mappedBy="category")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	// no need to manually store items since cascade
	private Set<Item> items = new HashSet<Item>(0);

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USER_ID")
	private User user;
	
	public Category() {}
	
	public Category(String name) {
		this.name = name;
		this.datetime = new Date();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
    public Date getDatetime() {
		return datetime;
    }
    
    public void setDatetime(Date datetime) {
    	this.datetime = datetime;
    }

	public User getUser() { return user;}

	public void setUser(User user) { this.user = user;}
}