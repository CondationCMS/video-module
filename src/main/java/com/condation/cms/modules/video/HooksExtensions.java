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


import com.condation.cms.api.extensions.HookSystemRegisterExtentionPoint;
import com.condation.cms.api.feature.features.RequestFeature;
import com.condation.cms.api.hooks.ActionContext;
import com.condation.cms.api.hooks.HookSystem;
import com.condation.cms.api.request.RequestContext;
import com.condation.modules.api.annotation.Extension;
import java.util.Map;

/**
 *
 * @author t.marx
 */
@Extension(HookSystemRegisterExtentionPoint.class)
public class HooksExtensions extends HookSystemRegisterExtentionPoint{

	String contextPath;
	
	@Override
	public void register(HookSystem hookSystem) {
		this.contextPath = getRequestContext().get(RequestFeature.class).context();
		if ("/".equals(this.contextPath)) {
			this.contextPath = "";
		}
		hookSystem.registerAction("theme/header", this::header);
	}
	
	public String header (ActionContext<String> context) {
		return """
         <script src="#contextpath#/module/video-module/video.js"></script>
         <link href="#contextpath#/module/video-module/video.css" rel="stylesheet"/>
         """.replaceAll("#contextpath#", contextPath);
	}
}
