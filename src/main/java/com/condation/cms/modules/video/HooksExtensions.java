package com.condation.cms.modules.video;

import com.condation.cms.api.annotations.Action;

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


import com.condation.cms.api.extensions.HookSystemRegisterExtensionPoint;
import com.condation.cms.api.feature.features.RequestFeature;
import com.condation.modules.api.annotation.Extension;

/**
 *
 * @author t.marx
 */
@Extension(HookSystemRegisterExtensionPoint.class)
public class HooksExtensions extends HookSystemRegisterExtensionPoint {
	
	@Action("system/layout/html/header")
	public String header () {

		var contextPath = getRequestContext().get(RequestFeature.class).context();
		if ("/".equals(contextPath)) {
			contextPath = "";
		}

		return """
         <script src="#contextpath#/module/video-module/video.js"></script>
         <link href="#contextpath#/module/video-module/video.css" rel="stylesheet"/>
         """.replaceAll("#contextpath#", contextPath);
	}
}
