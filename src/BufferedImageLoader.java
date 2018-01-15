import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class BufferedImageLoader {
private BufferedImage image;
public BufferedImage loadImage(String path) throws IOException{
	File file =new File(path);
	image= ImageIO.read(file);
	return image;
	
}
}
