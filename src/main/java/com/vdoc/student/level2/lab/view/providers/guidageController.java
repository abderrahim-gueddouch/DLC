package com.vdoc.student.level2.lab.view.providers;

import java.io.IOException;
import java.util.Collection;

import org.apache.chemistry.opencmis.commons.impl.json.JSONArray;
import org.apache.chemistry.opencmis.commons.impl.json.JSONObject;

import com.arjuna.ats.arjuna.gandiva.Resource;
import com.axemble.vdoc.sdk.controllers.BaseController;
import com.axemble.vdoc.sdk.exceptions.DirectoryModuleException;
import com.axemble.vdoc.sdk.exceptions.ProjectModuleException;
import com.axemble.vdoc.sdk.exceptions.RenderException;
import com.axemble.vdoc.sdk.exceptions.WorkflowModuleException;
import com.axemble.vdoc.sdk.interfaces.ICatalog;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IProject;
import com.axemble.vdoc.sdk.interfaces.IResourceDefinition;
import com.axemble.vdoc.sdk.interfaces.IStorageResource;
import com.axemble.vdoc.sdk.interfaces.IViewController;
import com.axemble.vdoc.sdk.interfaces.runtime.IExecutionContext.IRequest;
import com.axemble.vdoc.sdk.interfaces.runtime.IExecutionContext;
import com.axemble.vdoc.sdk.interfaces.runtime.INavigateContext;
import com.axemble.vdoc.sdk.interfaces.ui.IWritable;
import com.axemble.vdoc.sdk.interfaces.ui.definitions.IRedirectDefinition;
import com.axemble.vdoc.sdk.navigations.BaseNavigation;

public  class guidageController extends BaseController
{
	
	
	@Override
	public IExecutionContext doProcess(IExecutionContext ec) throws IOException
	{
		IContext context = getWorkflowModule().getContextByLogin("sysadmin");

		try
		{
			IOrganization organization = getDirectoryModule().getOrganization(context, "DefaultOrganization");
			IProject project = getProjectModule().getProject(context, "SDKTRAINING", organization);
			ICatalog catalog = getWorkflowModule().getCatalog(context, "TRAINING", ICatalog.IType.STORAGE, project);
			IResourceDefinition resourceDefinition = getWorkflowModule().getResourceDefinition(context, catalog, "Guidage") ;
			
			IViewController viewController = getWorkflowModule().getViewController(context, Resource.class);
			viewController.addEqualsConstraint("Order", 1);
			Collection<IStorageResource> storageResources = viewController.evaluate(resourceDefinition);
			Float Order = null ;
			String Ancre = null , Position = null , Message = null ;
			
			JSONArray jsonArray = new JSONArray();
			if(!storageResources.isEmpty())
			{
				
				for(IStorageResource storageResource : storageResources)
				{
					Order = (Float) storageResource.getValue("Order");
					Ancre = (String) storageResource.getValue("Ancre");
					Position = (String) storageResource.getValue("Position");
					Message = (String) storageResource.getValue("Message");
					
					jsonArray.add(Order);
					jsonArray.add(Ancre);
					jsonArray.add(Position);
					jsonArray.add(Message);
				}
			}
			ec.getResponse().getWriter().write(jsonArray.toString());
		}
		catch (WorkflowModuleException | DirectoryModuleException | ProjectModuleException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doProcess(ec);
	}
	public guidageController(INavigateContext context)
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public IWritable render() throws RenderException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void parseRequest(IRequest arg0) throws IOException
	{
		// TODO Auto-generated method stub
		
	}		
}
