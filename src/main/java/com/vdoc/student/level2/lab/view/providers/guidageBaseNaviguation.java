package com.vdoc.student.level2.lab.view.providers;

import com.axemble.vdoc.sdk.exceptions.RenderException;
import com.axemble.vdoc.sdk.interfaces.runtime.INavigateContext;
import com.axemble.vdoc.sdk.interfaces.ui.IWritable;
import com.axemble.vdoc.sdk.navigations.BaseNavigation;
import com.axemble.vdp.ui.framework.template.TemplateWriter;

public class guidageBaseNaviguation extends BaseNavigation
{
	public guidageBaseNaviguation(INavigateContext context)
	{
		super(context);
		addScriptDependency("/resources/dependencies/js/intro.js");
		addCssDependency("/resources/dependencies/css/introjs.css");
	}
	
	@Override
	public IWritable render() throws RenderException
	{
		TemplateWriter tw = getTemplateWriter("TemplateGuidage.html");
		tw.setEntry("vdocBaseUrl", getNavigator().getExecutionContext().getRequest().getBaseUrl());
		return tw;
	}
	
}
