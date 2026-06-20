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


import com.condation.cms.api.feature.features.TemplateEngineFeature;
import com.condation.cms.api.request.RequestContext;
import com.condation.cms.api.template.TemplateEngine;
import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.InputStreamReader;
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
	
	final Map<String, String> templates = new HashMap<>();
	
	public void init () {
		try {
			String content = CharStreams.toString( new InputStreamReader(VideoRenderer.class.getResourceAsStream("vimeo.html"), StandardCharsets.UTF_8 ) );
			templates.put("vimeo", content);
			
			content = CharStreams.toString( new InputStreamReader(VideoRenderer.class.getResourceAsStream("youtube.html"), StandardCharsets.UTF_8 ) );
			templates.put("youtube", content);
			
			content = CharStreams.toString( new InputStreamReader(VideoRenderer.class.getResourceAsStream("overlay.html"), StandardCharsets.UTF_8 ) );
			templates.put("overlay", content);
		} catch (IOException ex) {
			Logger.getLogger(VideoRenderer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public String render (String videoPlatform, Map<String, Object> context, RequestContext requestContext) {
		if (!templates.containsKey(videoPlatform)) {
			log.error("unknown video platform {}", videoPlatform);
			return "";
		}
        var templateFeature = requestContext.get(TemplateEngineFeature.class);
        var templateModel = new TemplateEngine.Model(null, null, requestContext);
		templateModel.values.putAll(context);
        try {
            return templateFeature.templateEngine().renderFromString(templates.get(videoPlatform), templateModel);
        } catch (IOException ex) {
            log.error("error logging video template", ex);
            return "";
        }
	}
}
