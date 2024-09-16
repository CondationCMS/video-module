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


import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author t.marx
 */
@Slf4j
public class VideoRenderer {
	
	final MustacheFactory mf = new DefaultMustacheFactory();
	
	final Map<String, Mustache> templates = new HashMap<>();
	
	public void init () {
		try {
			String content = CharStreams.toString( new InputStreamReader(VideoRenderer.class.getResourceAsStream("vimeo.html"), StandardCharsets.UTF_8 ) );
			templates.put("vimeo", mf.compile(new StringReader(content), "vimeo"));
			
			content = CharStreams.toString( new InputStreamReader(VideoRenderer.class.getResourceAsStream("youtube.html"), StandardCharsets.UTF_8 ) );
			templates.put("youtube", mf.compile(new StringReader(content), "youtube"));
			
			content = CharStreams.toString( new InputStreamReader(VideoRenderer.class.getResourceAsStream("overlay.html"), StandardCharsets.UTF_8 ) );
			templates.put("overlay", mf.compile(new StringReader(content), "overlay"));
		} catch (IOException ex) {
			Logger.getLogger(VideoRenderer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public String render (String videoPlatform, Map<String, Object> context) {
		if (!templates.containsKey(videoPlatform)) {
			log.error("unknown video platform {}", videoPlatform);
			return "";
		}
		StringWriter writer = new StringWriter();
		templates.get(videoPlatform).execute(writer, context);
		return writer.toString();
	}
}
