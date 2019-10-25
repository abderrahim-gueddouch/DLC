package com.vdoc.student.level1.tp06.page.extension;

import com.axemble.vdoc.sdk.interfaces.IBlock;
import com.axemble.vdoc.sdk.interfaces.IUser;
import com.axemble.vdoc.sdk.modules.ISiteModule;
import com.axemble.vdoc.sdk.site.extensions.BasePageExtension;
import com.axemble.vdoc.sdk.utils.Logger;

/**
 * Created on 13 août 2010
 * 
 * @author vdoc
 */

public class DynamicUpdate extends BasePageExtension
{

	/**
     * 
     */
	private static final long serialVersionUID = -7577784515061220148L;
	protected static final Logger log = Logger.getLogger(DynamicUpdate.class);

	/**
	 * @see com.axemble.vdoc.sdk.site.extensions.BasePageExtension#onAfterLoad(com.axemble.vdoc.sdk.interfaces.IBlock)
	 */
	@Override
	public boolean onAfterLoad(IBlock rebuiltBlock)
	{
		try
		{
			// We get siteModule
			ISiteModule iSiteModule = getSiteModule();

			// We use the ComponentsFactory to create a new component
			// TODO, you can find ComponentsFactory in iSiteModule : the title component is named "HeadingComponent"

			// We set mandatory values : without it, it can't be displayed
			// TODO For heading component, you have to set content and display level (1 to 6)

			// TODO We add this component in root block of the page : "rebuiltBlock"
		}
		catch (Exception e)
		{
			String message = e.getMessage();
			if (message == null)
			{
				message = "";
			}
			log.error("Error in DynamicUpdate onBeforeLoad method : " + e.getClass() + " - " + message);
		}
		return super.onAfterLoad(rebuiltBlock);
	}

	/* (non-Javadoc)
	 * @see com.axemble.vdoc.sdk.site.extensions.BasePageExtension#onEvaluateVariables(java.lang.String)
	 */
	@Override
	public String onEvaluateVariables(String expression)
	{
		if (expression != null)
		{
			// We get connected user
			IUser connectedUser = getSiteModule().getLoggedOnUser();

			// We test expression to return lastname or firstname
			// TODO Test expression and return lastname or firstname
			// Tip : expression in parameter is like "${myBookmark}" 
		}
		// If it's not our expression, we call p
		return super.onEvaluateVariables(expression);
	}

}
