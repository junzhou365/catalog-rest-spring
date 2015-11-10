package junzhou365.user.model;

import junzhou365.domain.model.Category;
import junzhou365.domain.model.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String username;

	@Column(name = "password", nullable = false, length = 60)
//	@JsonIgnore
	private String password;

	@Column(name = "enabled", nullable = false)
//	@JsonIgnore
	private boolean enabled;

	@OneToMany(mappedBy = "user")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Set<UserRole> userRole = new HashSet<UserRole>(0);

	@OneToMany(mappedBy = "user")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Set<Category> categories = new HashSet<Category>(0);

	@OneToMany(mappedBy = "user")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Set<Item> items = new HashSet<Item>(0);

	public User() {
	}

	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public User(String username, String password, boolean enabled, Set<UserRole> userRole) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}

	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	public Set<UserRole> getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
}
