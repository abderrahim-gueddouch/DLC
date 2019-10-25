/**
 * 
 */
package com.vdoc.extra.dd.education.dd15.component.controllers;

import java.util.ArrayList;
import java.util.Collection;

import com.axemble.vdoc.sdk.Modules;
import com.axemble.vdoc.sdk.exceptions.DirectoryModuleException;
import com.axemble.vdoc.sdk.impl.base.BaseComponentController;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IRenderModel;
import com.axemble.vdoc.sdk.modules.IDirectoryModule;
import com.axemble.vdoc.sdk.utils.Logger;

/**
 * @author vmartinon
 *
 */
public class DirectoryTreeComponentController extends BaseComponentController
{
	/**
     * 
     */
    private static final long serialVersionUID = 278067214922406181L;
	protected static final Logger log = Logger.getLogger(DirectoryTreeComponentController.class);

	/**
	 * 
	 * @author vmartinon
	 *
	 */
	public class OrganizationBean
	{
		protected String label = "";
		protected Collection<OrganizationBean> cOrganizationBeans = new ArrayList<DirectoryTreeComponentController.OrganizationBean>();
		
		/**
		 * 
		 * @param label
		 */
		public OrganizationBean (String label)
        {
	        super();
	        this.label = label;
        }

		public String getLabel()
        {
        	return label;
        }

		public void setLabel(String label)
        {
        	this.label = label;
        }

		public Collection<OrganizationBean> getcOrganizationBeans()
        {
        	return cOrganizationBeans;
        }

		public void setcOrganizationBeans(Collection<OrganizationBean> cOrganizationBeans)
        {
        	this.cOrganizationBeans = cOrganizationBeans;
        }
	}
	
	/**
	 * @see com.axemble.vdoc.sdk.impl.base.BaseComponentController#prepareModel(com.axemble.vdoc.sdk.interfaces.IRenderModel)
	 */
	@Override
	protected void prepareModel(IRenderModel iRenderModel) throws Exception
	{
		IDirectoryModule iDirectoryModule = null;
		try
		{
			iDirectoryModule = Modules.getDirectoryModule();
			IContext iContext = iDirectoryModule.getContextByLogin("sysadmin");
			
			Collection<OrganizationBean> cOrganizationBeans = buildOrganizations(iDirectoryModule, iContext, null);
			
			iRenderModel.setValue("organizationBeans", cOrganizationBeans);
		}
		catch (Exception e)
		{
			String message = e.getMessage();
            if (message == null)
            {
	            message = "";
            }
            log.error("Error in DirectoryTreeComponentController prepareModel method : " + e.getClass() + " - " + message);
		}
		finally
		{
			Modules.releaseModule(iDirectoryModule);
		}
	}

	/**
	 * 
	 * @param object
	 * @return
	 * @throws DirectoryModuleException 
	 */
	protected Collection<OrganizationBean> buildOrganizations(IDirectoryModule iDirectoryModule, IContext iContext, IOrganization iParentOrganization) throws DirectoryModuleException
    {
		Collection<OrganizationBean> cOrganizationBeans = new ArrayList<DirectoryTreeComponentController.OrganizationBean>();
		Collection<? extends IOrganization> cIOrganizations = iDirectoryModule.getOrganizations(iContext, iParentOrganization);
	   
	    for (IOrganization iOrganization : cIOrganizations)
        {
	        OrganizationBean organizationBean = new OrganizationBean(iOrganization.getLabel());
	        organizationBean.getcOrganizationBeans().addAll(buildOrganizations(iDirectoryModule, iContext, iOrganization));
	        cOrganizationBeans.add(organizationBean);
        }
	    return cOrganizationBeans;
    }

	/**
	 * @see com.axemble.vdoc.sdk.impl.base.BaseComponentController#processEvent(java.lang.String, java.lang.String)
	 */
	@Override
	protected void processEvent(String arg0, String arg1) throws Exception
	{
		
	}

}
