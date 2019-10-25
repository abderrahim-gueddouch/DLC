package com.MoovappsProcess.DLC.Campagne.document;

import java.util.Collection;
import java.util.Iterator;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.IAction;
import com.axemble.vdoc.sdk.interfaces.ICatalog;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IProject;
import com.axemble.vdoc.sdk.interfaces.IViewController;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;

public class Cmp extends BaseDocumentExtension {
	
	@Override
	public boolean onAfterSubmit(IAction action) {
	   
		Collection<ICatalog> collection=getCatalog();
		IContext context=getWorkflowModule().getLoggedOnUserContext();
		
		if(getWorkflowInstance().getValue("ChampToUpdate")!=null)
		{
			
		  String champName=(String)getWorkflowInstance().getValue("ChampToUpdate");
		  String champValue=(String)getWorkflowInstance().getValue("ChampValue");
		  
		  for (Iterator iterator = collection.iterator() ; iterator.hasNext() ;)
		  {
			  
			  IWorkflowInstance workflowInstance = (IWorkflowInstance)iterator.next();
			  workflowInstance.setValue(champName, champValue);
			  workflowInstance.save(context);
		
		  }	  
		}
		return super.onAfterSubmit(action);
	}
	
	private Collection<ICatalog> getCatalog() {

		Collection<ICatalog> collection = null;

		try {
			
			IContext sysContext = getWorkflowModule().getSysadminContext();
			IOrganization organization = getDirectoryModule().getOrganization(sysContext, "DefaultOrganization");
			IProject project = getProjectModule().getProject(sysContext,"WIZEDLC", organization);
			IContext context = getWorkflowModule().getLoggedOnUserContext();
			ICatalog catalog = getWorkflowModule().getCatalog(context,"GroupeProcessus", project);
			IViewController viewController = getWorkflowModule().getViewController(context);
			collection = viewController.evaluate(catalog);

			return collection;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return collection;
	}
}