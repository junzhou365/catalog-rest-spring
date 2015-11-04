package catalog.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="IMAGES")
public class Image {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="IMAGE_ID")
	private Long id;
	
	private String title;
	private String path;
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;
	
	@OneToOne(mappedBy="image")
	private Item item;
	
	public Image() {}
	
	public Image(String title, String path) {
		this.title = title;
		this.path = path;
		this.datetime = new Date();
	}
	
	public Image(Image oldImage) {
		this.title = oldImage.title;
		this.path = oldImage.path;
		this.datetime = new Date();
		this.item = oldImage.item;
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
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
    public Date getDatetime() {
		return datetime;
    }
    
    public void setDatetime(Date datetime) {
    	this.datetime = datetime;
    }
}
