package com.vdoc.student.level2.tp01.agents;

import java.util.Collection;

import com.axemble.vdoc.sdk.agent.base.BaseAgent;
import com.axemble.vdoc.sdk.interfaces.ICatalog;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IProject;
import com.axemble.vdoc.sdk.interfaces.ISecurityController;
import com.axemble.vdoc.sdk.interfaces.IUser;
import com.axemble.vdoc.sdk.interfaces.IWorkflowContainer;
import com.axemble.vdoc.sdk.interfaces.ISecurityController.IPermissionFlags;
import com.axemble.vdoc.sdk.interfaces.ISecurityController.IPermissionLevels;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;
import com.axemble.vdoc.sdk.utils.Logger;
import com.axemble.vdp.workflow.domain.ProcessWorkflowInstance;
import com.axemble.vdp.workflow.domain.WorkflowContainer;

/**
 * @author vmartinon
 *
 */
public class TestSecurityAgent extends BaseAgent
{
	protected static final Logger log = Logger.getLogger(TestSecurityAgent.class);

	/**
	 * @see com.axemble.vdoc.sdk.agent.base.BaseAgent#execute()
	 */
	@Override
	protected void execute()
	{
		try
		{
			// TODO We get configuration data
			// Tip : Use the "getConfiguration()" method
			
			IContext iContext = getWorkflowModule().getContextByLogin("sysadmin");
			
			// TODO We load SDK objects, coming from what we get input configuration
			// Tip : Load IOrganization, IProject, ICatalog, IWorkflow
			
			String organizationName = getPortalModule().getConfiguration().getStringUserProperty("com.vdoc.organization");			
			IOrganization organization = getDirectoryModule().getOrganization(iContext, organizationName);
			
			String projectName = getPortalModule().getConfiguration().getStringUserProperty("com.vdoc.project");	
			IProject project = getProjectModule().getProject(iContext, projectName , organization);
			
			String catalogName = getPortalModule().getConfiguration().getStringUserProperty("com.vdoc.catalog");	
			ICatalog catalog = getWorkflowModule().getCatalog(iContext, catalogName, project);
			
			String workflowContainerName = getPortalModule().getConfiguration().getStringUserProperty("com.vdoc.workflowContainer");	
			IWorkflowContainer workflowContainer = getWorkflowModule().getWorkflowContainer(iContext, catalog, workflowContainerName);
			
			// TODO We get security controllers (for workflow and for catalog)
			
			ISecurityController iSecurityControllerCatalog = getWorkflowModule().getSecurityController(catalog);
			ISecurityController iSecurityControllerWorkflowContainer = getWorkflowModule().getSecurityController(workflowContainer);
			
			getWorkflowModule().beginTransaction();
			
			// TODO We get all users and browse
				
					
			
			Collection<IUser> users = (Collection<IUser>) getDirectoryModule().getUsers(iContext, organization);
			boolean hasAccessToProcessGroup = false , canCreateDocumentsOnWorkflow = false ;
		    for (IUser iUser : users)
			{
		    	// For each user,
				// TODO We test if user can access to process group; if not, we add permission
					// Tip : Flag : IPermissionFlags.WorkflowCatalogPermissionFlags.ALLOW_ACCESS
		    	hasAccessToProcessGroup = iSecurityControllerCatalog.hasPermission(iUser, IPermissionLevels.NO, IPermissionFlags.WorkflowCatalogPermissionFlags.ALLOW_ACCESS);
				if (!hasAccessToProcessGroup)
				{
					iSecurityControllerCatalog.addPermission(iUser, IPermissionLevels.NO, IPermissionFlags.WorkflowCatalogPermissionFlags.ALLOW_ACCESS);
				}
				
				// TODO We test if user can create a document for workflow; if not, we add permission
				// Tip : Flag : IPermissionFlags.WorkflowPermissionFlags.CREATE
				canCreateDocumentsOnWorkflow = iSecurityControllerWorkflowContainer.hasPermission(iUser, ProcessWorkflowInstance.class, IPermissionLevels.NO, IPermissionFlags.WorkflowPermissionFlags.CREATE);
				if (!canCreateDocumentsOnWorkflow)
				{
					iSecurityControllerWorkflowContainer.addPermission(iUser, ProcessWorkflowInstance.class, IPermissionLevels.NO, IPermissionFlags.WorkflowPermissionFlags.CREATE);
				}			
			} 
			
			getWorkflowModule().commitTransaction();
		}
		catch (Exception e)
		{
			String message = e.getMessage();
            if (message == null)
            {
	            message = "";
            }
            log.error("Error in TestSecurityAgent execute method : " + e.getClass() + " - " + message);
            
            getWorkflowModule().rollbackTransaction();
		}
	}

}
