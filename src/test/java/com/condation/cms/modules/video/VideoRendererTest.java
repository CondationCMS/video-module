package com.condation.cms.modules.video;

/*-
 * #%L
 * video-module
 * %%
 * Copyright (C) 2024 CondationCMS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author t.marx
 */
public class VideoRendererTest {

	static VideoRenderer videoRenderer;
	
	@BeforeAll
	public static void setup () {
		videoRenderer = new VideoRenderer();
		videoRenderer.init();
	}

	@Test
	public void testVimeo() {
//		String html = videoRenderer.render("vimeo", Map.of());
//		Assertions.assertThat(html).isNotBlank();
	}

	@Test
	void defaultsToWidescreenRatio() {
		Video video = new Video(Map.of(), null);

		Assertions.assertThat(video.ratio()).isEqualTo("16:9");
		Assertions.assertThat(video.aspectRatio()).isEqualTo("16 / 9");
	}

	@Test
	void supportsConfiguredRatios() {
		Assertions.assertThat(new Video(Map.of("ratio", "16:9"), null).aspectRatio()).isEqualTo("16 / 9");
		Assertions.assertThat(new Video(Map.of("ratio", "4:3"), null).aspectRatio()).isEqualTo("4 / 3");
		Assertions.assertThat(new Video(Map.of("ratio", "1:1"), null).aspectRatio()).isEqualTo("1 / 1");
	}

	@Test
	void fallsBackForUnsupportedRatio() {
		Video video = new Video(Map.of("ratio", "21:9"), null);

		Assertions.assertThat(video.ratio()).isEqualTo("16:9");
	}
	
}
