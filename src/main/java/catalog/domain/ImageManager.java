package catalog.domain;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import catalog.config.WebConfigurer;
import catalog.domain.HibernateUtil;

public class ImageManager {
	private final static Logger log = Logger.getLogger(ImageManager.class.getName());
	private final static String imageFolerPath = WebConfigurer.imageFolerPath;
	
    public Image updateImage(Image image, boolean update) {
    	if (image == null || image.getPath() == null) return null;
    	image.setDatetime(new Date());
    	String localFilename = downloadImage(image);
        if (localFilename != null)
        	image.setPath(localFilename);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Image changedImage = image;
        if (update && image.getId() != null)
        	session.update(changedImage);
        else {
        	changedImage = new Image(image);
        	session.save(changedImage);
        }
        session.getTransaction().commit();
        return changedImage;
    }

    public Image getImage(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Image image = (Image) session.get(Image.class, id);
        session.getTransaction().commit();
        return image;
    }
    
    public void deleteImage(Long id) {
    	//remove all items in the category
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	Image image = (Image) session.get(Image.class, id);
    	if (image != null) {
    		session.delete(image);
    		session.flush();
    	}
    	session.getTransaction().commit();
    }
    private String downloadImage(Image image) {
    	String extension = "";
    	String url = image.getPath();
    	int i = url.lastIndexOf('.');
    	if (i > 0) {
    	    extension = url.substring(i+1);
    	}
    	String filename = null;
    	String filePath = null;
    	try {
    		filePath = image.getTitle() + "." + extension;
    		filename = imageFolerPath + filePath;
			downloadFromUrl(url, filename);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Downloading image fails");
		}
    	return filePath;
    }
    
    private void downloadFromUrl(String url, String localFilename) throws IOException {
    	InputStream is = null;
        FileOutputStream fos = null;

        try {
            URLConnection urlConn = new URL(url).openConnection();//connect

            is = urlConn.getInputStream();               //get connection inputstream
            fos = new FileOutputStream(localFilename);   //open outputstream to local file

            byte[] buffer = new byte[4096];              //declare 4KB buffer
            int len;

            //while we have availble data, continue downloading and storing to local file
            while ((len = is.read(buffer)) > 0) {  
                fos.write(buffer, 0, len);
            }
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } finally {
                if (fos != null) {
                    fos.close();
                }
            }
        }
    }
}
