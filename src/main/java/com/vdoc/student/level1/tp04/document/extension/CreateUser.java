
package com.vdoc.student.level1.tp04.document.extension;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.IAction;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;
import com.axemble.vdoc.sdk.utils.Logger;

/**
 * Created on 10 août 2010
 * @author vdoc
 * 
 */

public class CreateUser extends BaseDocumentExtension
{

    /**
     * 
     */
    private static final long serialVersionUID = 6504488905312810102L;
    protected static final Logger log = Logger.getLogger(CreateUser.class);
    
    /**
     * @see com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension#onAfterSubmit(com.axemble.vdoc.sdk.interfaces.IAction)
     */
    @Override
    public boolean onAfterSubmit(IAction action)
    {
		// TODO Get a directory module (available by default )
        try
        {
            // We get group name where user must be set
            String groupName = getUserParameterInConfiguration("tp6004.group");
            if ( (groupName == null) || (groupName.equals("")) )
            {
                throw new Exception("Error in getting user's group name !");
            }
            
            // We must get an organization name
            // We get it in user parameters in application
            String organizationName = getUserParameterInConfiguration("tp6004.organization");
            if ( (organizationName == null) || (organizationName.equals("")) )
            {
                throw new Exception("Error in getting user's organization name !");
            }
            
            // We use a sysadmin context to get group
            // TODO Get the group object by the name you've just get
            // On your directoryModule, you can get your group object by name but you will need a context
            // Tip : you'll need organization of group too to get your group
            // On the same directoryModule, you can get you context by login
            
            // We can get data from document instance
            IWorkflowInstance iWorkflowInstance = getWorkflowInstance();
            // TODO You can get values from iWorkflowInstance (lastname, firstname, ...)
            
            // Opening a transaction
            // TODO On iDirectoryModule, you can begin a new transaction
            
            // We create user
            // TODO On iDirectoryModule, you can create your user
            
            // TODO Set data in this new user
            
            // We add this user in our group
            // TODO You can use your iDirectoryModule to add your user in the group
            
            // TODO WARNING ! Don't forget to save the user : iUser.save(...)
            
            // Committing transaction
            // TODO On iDirectoryModule, you can commit your transaction
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in CreateUser onAfterSave method : " + e.getClass() + " - " + message);
            
         // TODO On iDirectoryModule, you have to rollback your transaction
        }
        return super.onAfterSubmit(action);
    }

    /**
     * 
     * @param key
     * @return
     * @throws Exception
     */
    protected String getUserParameterInConfiguration(String key) throws Exception
    {
        String value = null;
        try
        {
            IWorkflowInstance iWorkflowInstance = getWorkflowInstance();
            
            // We must get application
            // TODO Use your iWorkflowInstance to get your application ("getCatalog"); 
            
            // We must get application configuration
            // TODO On your catalog, you can access to configuration properties as explained in PPT
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in CreateUser getGroupNameInConfiguration method : " + e.getClass() + " - " + message);
            throw(e);
        }
        return value;
    }
    
}
