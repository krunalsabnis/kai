package com.kru.kai.image;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author <a href="mailto:krunalsabnis@gmail.com">Krunal Sabnis</a>
 *
 * ${tags}
 */

@Data
@AllArgsConstructor
public class ImageResponse {
	String lable;
	Float confidence;
}
