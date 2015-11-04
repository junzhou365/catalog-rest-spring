package catalog.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import catalog.user.model.User;
import org.hibernate.annotations.GenericGenerator;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="ITEMS")
public class Item {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="ITEM_ID")
	private Long id;
	private String title;

	@Lob
	private String text;

	@ManyToOne(fetch=FetchType.EAGER)
	// @JsonIgnore
	@JoinColumn(name="CATEGORY_ID", nullable=false)
	private Category category;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IMAGE_ID")
	private Image image;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USER_ID")
	private User user;

	public Item() {}

	public Item(String title, String text) {
		this.title = title;
		this.text = text;
		this.datetime = new Date();
	}

	public Item(Item oldItem) {
		this.title = oldItem.title;
		this.text = oldItem.text;
		this.datetime = new Date();
		this.category = oldItem.category;
		this.image = oldItem.image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isTheAuthor() {
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user instanceof UserDetails
				&&  this.user != null
				&& ((UserDetails) user).getUsername().equals(this.user.getUsername());
	}
}
