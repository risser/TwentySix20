package com.twentysix20.pix;

import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import javax.swing.ImageIcon;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

class Thumbnail {
    public static void main(String[] args) {
        createThumbnail(args[0], args[1], Integer.parseInt(args[2]));
        System.exit(0);
    }

    /**
     * Reads an image in a file and creates
     * a thumbnail in another file.
     * @param orig The name of image file.
     * @param thumb The name of thumbnail file.
     * Will be created if necessary.
     * @param maxDim The width and height of
     * the thumbnail must
     * be maxDim pixels or less.
     */
    public static void createThumbnail(String orig, String thumb, int maxDim) {
        try {
            // Get the image from a file.
			Image inImage = new ImageIcon(orig).getImage();

            // Determine the scale.
			double scale = 0;
			if (inImage.getWidth(null) > inImage.getHeight(null)) {
				scale = (double)maxDim/(double)inImage.getWidth(null);
			}
			else {
				scale = (double)maxDim/(double)inImage.getHeight(null);
			}
			if (scale > 1.0)
				scale = 1.0;

            // Determine size of new image.
            //One of them
            // should equal maxDim.
            int scaledW = (int)(scale*inImage.getWidth(null));
            int scaledH = (int)(scale*inImage.getHeight(null));

            // Create an image buffer in
            //which to paint on.
            BufferedImage outImage = new BufferedImage(scaledW, scaledH, BufferedImage.TYPE_INT_RGB);

            // Set the scale.
            AffineTransform tx = new AffineTransform();

            // If the image is smaller than
            //the desired image size,
            // don't bother scaling.
            if (scale < 1.0d) {
                tx.scale(scale, scale);
            }

            // Paint image.
            Graphics2D g2d = outImage.createGraphics();
            g2d.drawImage(inImage, tx, null);
            g2d.dispose();

            // JPEG-encode the image
            //and write to file.
            OutputStream os = new FileOutputStream(thumb);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
            encoder.encode(outImage);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
