package catalog.domain.dao;

import catalog.domain.model.Image;

public interface ImageDao {
	public Image updateImage(Image image, boolean update);

    public Image getImage(Long id);
    
    public void deleteImage(Long id);
}
