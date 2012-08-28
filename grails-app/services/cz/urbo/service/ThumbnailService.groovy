package cz.urbo.service

import java.util.concurrent.TimeUnit
import com.google.common.cache.CacheBuilder
import java.awt.image.BufferedImage
import java.util.concurrent.Callable
import cz.urbo.cases.Photo
import javax.imageio.ImageIO

import static org.imgscalr.Scalr.resize
import org.imgscalr.Scalr

import static org.imgscalr.Scalr.OP_ANTIALIAS
import static org.imgscalr.Scalr.pad
import static org.imgscalr.Scalr.OP_BRIGHTER
import static org.imgscalr.Scalr.crop

class ThumbnailService {
    def cache = CacheBuilder.newBuilder().
    expireAfterWrite(60, TimeUnit.MINUTES).
    maximumSize(500).build();


    def BufferedImage getThumbnail(id, width, height) {
        cache.get("${id}/${width}/${height}", new Callable() {
            @Override
            Object call() {
                log.debug("Cache miss for ${id}/${width}/${height}")
                def Photo photo = Photo.get(id)
                assert photo
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(photo.data))
                BufferedImage thumbnail =
                    resize(image, Scalr.Method.SPEED, Scalr.Mode.AUTOMATIC,
                            width?.isInteger() ? width as Integer : 200,
                            height?.isInteger() ? height as Integer : 200,
                            OP_ANTIALIAS,
                            OP_BRIGHTER);
                thumbnail = pad(thumbnail, 2);
               return thumbnail;
            }
        });
    }

    def BufferedImage getSquareThumbnail(id, width) {
        cache.get("${id}/${width}", new Callable() {
            @Override
            Object call() {
                log.debug("Cache miss for ${id}/${width}")
                def Photo photo = Photo.get(id)
                assert photo
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(photo.data))
                if (image.width > image.height) {
                    //landscape
                    image = crop(image, ((int)(image.width - image.height) / 2), 0,  image.height, image.height, OP_ANTIALIAS)
                } else {
                    image = crop(image, 0, ((int)(image.height - image.width) / 2),  image.width, image.width, OP_ANTIALIAS)
                }

                BufferedImage thumbnail =
                    resize(image, Scalr.Method.SPEED, Scalr.Mode.AUTOMATIC,
                            width?.isInteger() ? width as Integer : 200,
                            width?.isInteger() ? width as Integer : 200,
                            OP_ANTIALIAS,
                            OP_BRIGHTER);
                thumbnail = pad(thumbnail, 2);
                return thumbnail;
            }
        });
    }

    def invalidate() {
        cache.invalidateAll();
    }
}
