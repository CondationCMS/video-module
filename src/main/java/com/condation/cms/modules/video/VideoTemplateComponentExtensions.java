package com.condation.cms.modules.video;

import java.util.Map;

import com.condation.cms.api.annotations.TemplateComponent;

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


import com.condation.cms.api.model.Parameter;
import com.condation.cms.api.extensions.RegisterTemplateComponentExtensionPoint;
import com.condation.cms.api.feature.features.SitePropertiesFeature;
import com.condation.modules.api.annotation.Extension;

/**
 *
 * @author t.marx
 */
@Extension(RegisterTemplateComponentExtensionPoint.class)
public class VideoTemplateComponentExtensions extends RegisterTemplateComponentExtensionPoint {
	
	@TemplateComponent("video")
	public String video (Parameter parameters) {
		Video video = new Video((Map)parameters, getContext().get(SitePropertiesFeature.class).siteProperties());
		
		if (video.consent()) {
			return VideoModule.getVideoRenderer().render("overlay", Map.of("video", video), getRequestContext());
		}
		return VideoModule.getVideoRenderer().render(video.type(), Map.of("video", video), getRequestContext());
	}

	
}
