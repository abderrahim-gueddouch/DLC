package com.MoovappsProcess.DLC.Campagne.document;

import java.util.Collection;

import com.axemble.vdoc.sdk.agent.base.BaseAgent;
import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.IAction;
import com.axemble.vdoc.sdk.interfaces.ICatalog;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IProject;
import com.axemble.vdoc.sdk.interfaces.IProperty;
import com.axemble.vdoc.sdk.interfaces.IViewController;
import com.axemble.vdoc.sdk.interfaces.IWorkflow;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;

public class Campagne extends BaseAgent {

	private Collection<IWorkflowInstance> getAll() {

		Collection<IWorkflowInstance> collection = null;

		try {

			IContext sysContext = getWorkflowModule().getSysadminContext();
			IOrganization organization = getDirectoryModule().getOrganization(sysContext, "DefaultOrganization");
			IProject project = getProjectModule().getProject(sysContext,"WIZEDLC", organization);
			IContext context = getWorkflowModule().getLoggedOnUserContext();
			ICatalog catalog = getWorkflowModule().getCatalog(context,"GroupeProcessus", project);
			IWorkflow workflow = getWorkflowModule().getWorkflow(context,catalog, "Campagne_1.0");
			IViewController viewController = getWorkflowModule().getViewController(context);
			collection = viewController.evaluate(workflow);

			return collection;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return collection;
	}
	
	private Collection<IWorkflowInstance> getAllCampagne() {

		Collection<IWorkflowInstance> collection = null;
        
		try {

			IContext sysContext = getWorkflowModule().getSysadminContext();
			IOrganization organization = getDirectoryModule().getOrganization(sysContext, "DefaultOrganization");
			IProject project = getProjectModule().getProject(sysContext,"WIZEDLC", organization);
			IContext context = getWorkflowModule().getLoggedOnUserContext();
			ICatalog catalog = getWorkflowModule().getCatalog(context,"GroupeProcessus", project);
			IWorkflow workflow = getWorkflowModule().getWorkflow(context,catalog, "Campagne_1.0");
			IViewController viewController = getWorkflowModule().getViewController(context);
			IWorkflowInstance Iworkflowinstance=(IWorkflowInstance) getWorkflowModule().getWorkflowInstances(context, catalog);
			viewController.addEqualsConstraint(IProperty.System.REFERENCE, Iworkflowinstance.getValue(IProperty.System.REFERENCE));
			collection = viewController.evaluate(workflow);

			return collection;
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return collection;
	}

	@Override
	protected void execute() {

			Collection<IWorkflowInstance> collection = getAll();
			Collection<IWorkflowInstance> collect = getAllCampagne();
			
			try {

				for (IWorkflowInstance iWorkflowInstance : collect) 
				{
					IContext context = getWorkflowModule().getLoggedOnUserContext();
					String nameChamp = null;
					String valueChamp = null;
					
					if (iWorkflowInstance.getValue("ChampToUpdate") != null)
					{	
						nameChamp = (String) iWorkflowInstance.getValue("ChampToUpdate");
						valueChamp = (String) iWorkflowInstance.getValue("ChampValue");
						
						for (IWorkflowInstance WorkflowInstance : collection) 
						{
							WorkflowInstance.setValue(nameChamp, valueChamp);
							WorkflowInstance.save(context);
						}
						
						iWorkflowInstance.setValue("ChampToUpdate", null);
						iWorkflowInstance.save(context);
					}
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
