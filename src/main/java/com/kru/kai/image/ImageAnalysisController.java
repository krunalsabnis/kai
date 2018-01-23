package com.kru.kai.image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;

/**
 * @author <a href="mailto:krunalsabnis@gmail.com">Krunal Sabnis</a>
 *
 * ${tags}
 */

@RestController
@RequestMapping("/api/v1/")
@Api(description = "Image Analysis", tags = "Object Recognition")
public class ImageAnalysisController {
	
	@Autowired
	private LabelImage labelImage;

	@Value("${image.uploadpath}")
	private String imagePath;

	@ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/upload")
    public ImageResponse imageAnalysis(@RequestParam("file") MultipartFile file) throws IOException {

        Path path = null;

        if (file.isEmpty()) {
            throw new IOException("please provide file");
        }
        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            path = Paths.get(imagePath + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageResponse result = labelImage.analyzeImage(path.toString());
        return result;
    }
}
