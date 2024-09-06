/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.thmarx.cms.modules.video;

/*-
 * #%L
 * video-module
 * %%
 * Copyright (C) 2024 Marx-Software
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

import com.github.thmarx.cms.api.module.CMSModuleContext;
import com.github.thmarx.cms.api.module.CMSRequestContext;
import com.github.thmarx.modules.api.ModuleLifeCycleExtension;
import com.github.thmarx.modules.api.annotation.Extension;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author t.marx
 */
@Slf4j
@Extension(ModuleLifeCycleExtension.class)
public class VideoModule extends ModuleLifeCycleExtension<CMSModuleContext, CMSRequestContext> {

	public static VideoRenderer VIDEO_RENDERER;
	
	@Override
	public void init() {
	}

	@Override
	public void activate() {
		VIDEO_RENDERER = new VideoRenderer();
		VIDEO_RENDERER.init();
	}
	
	
	
}
