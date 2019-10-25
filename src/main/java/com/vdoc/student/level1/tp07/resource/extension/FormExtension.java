
package com.vdoc.student.level1.tp07.resource.extension;

import com.axemble.vdoc.sdk.document.extensions.BaseStorageResourceExtension;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IResource;
import com.axemble.vdoc.sdk.interfaces.IUser;
import com.axemble.vdoc.sdk.modules.IWorkflowModule;
import com.axemble.vdoc.sdk.utils.Logger;

/**
 * Created on 18 avr. 2011
 * @author vmartinon
 *
 */
public class FormExtension extends BaseStorageResourceExtension
{
    /**
     * 
     */
    private static final long serialVersionUID = 428431666054170635L;
    protected static final Logger log = Logger.getLogger(FormExtension.class);
    
    /**
     * @see com.axemble.vdoc.sdk.document.extensions.BaseStorageResourceExtension#onAfterLoad()
     */
    @Override
    public boolean onAfterLoad()
    {
        try
        {
            // We get resource
            IResource iResource = getStorageResource();
            // We get connected user
            IUser connectedUser = getWorkflowModule().getLoggedOnUser();
            
            // TODO We can set resource values with information about connected user
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in FormExtension onAfterLoad method : " + e.getClass() + " - " + message);
        }
        return super.onAfterLoad();
    }
    
    /**
     * @see com.axemble.vdoc.sdk.document.extensions.BaseStorageResourceExtension#onAfterSave()
     */
    @Override
    public boolean onAfterSave()
    {
    	try
        {
    		IWorkflowModule iWorkflowModule = getWorkflowModule();
    		
            // We get resource
            IResource iResource = getStorageResource();
            // We get connected user
            IUser connectedUser = getWorkflowModule().getLoggedOnUser();
            
            // We can now create a new process document with current form data
            
            // We get context from connected user
            IContext iContext = iWorkflowModule.getContext(connectedUser);
            
            // We get organization, project, catalog and workflow
            // TODO Here, we must get :
            // - organization "DefaultOrganization" (IOrganization) (you can use getDirectoryModule())
            // - project "DefaultProject" (IProject) (you can use getProjectModule())
            // - catalog "MyAdministrativeProcesses" (ICatalog) (you can use iWorkflowModule)
            // - workflow "Requests_1.0" (IWorkflow) (you can use iWorkflowModule)
            
            // TODO We create the workflow instance
            // Tip : You can use the "createWorkflowInstance" method on iWorkflowModule
            // Tip : Don't forget to set values after creatinf your IWorkflowInstance
            // Tip : Don't forget to save !
            
            // TODO We get current task instance and send new document to next task (Press "Submit" button)
            // Tip : you can get your current task instance on your iWorkflowInstance
            // Tip : you can get the IAction on your ITask
            // Tip : you can use the "end" method on your task instance to go to next stage
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in FormExtension onAfterSave method : " + e.getClass() + " - " + message);
        }
        return super.onAfterSave();
    }
    
}
