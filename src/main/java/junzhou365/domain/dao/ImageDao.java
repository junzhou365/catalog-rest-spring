package junzhou365.domain.dao;

import junzhou365.domain.model.Image;

public interface ImageDao {
	public Image updateImage(Image image, boolean update);

    public Image getImage(Long id);
    
    public void deleteImage(Long id);
}
