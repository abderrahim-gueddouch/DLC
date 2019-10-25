
package com.vdoc.student.level1.tp01.document.extension;

import java.util.ArrayList;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.IOptionList;
import com.axemble.vdoc.sdk.interfaces.IOptionList.IOption;
import com.axemble.vdoc.sdk.interfaces.IResourceController;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;
import com.axemble.vdoc.sdk.modules.IWorkflowModule;
import com.axemble.vdoc.sdk.utils.Logger;
/**
 * Created on 10 août 2010
 * @author 
 * 
 */

public class MyListB extends BaseDocumentExtension
{
    /**
     * 
     */
    private static final long serialVersionUID = -6228898771536617988L;
    protected static final Logger log = Logger.getLogger(MyListB.class);
    

    /**
     * @see com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension#onAfterLoad()
     */
    @Override
    public boolean onAfterLoad()
    {
        try
        {
            // Build values ArrayList
            // TODO We can declare a variable of type java.util.ArrayList and use after the "add" method to add new values in list
            	// Tip in this ArrayList, we can't put directly the strings D, E, F but we must put IOption variables
        		// Tip : to get an IOption, you can use your IWorkflowModule (method createListOption)
        	
        	ArrayList<IOption> l1 = new ArrayList<>();
        	
        	
        	
        	
        	l1.add(getWorkflowModule().createListOption("D", "D"));
        	l1.add(getWorkflowModule().createListOption("E", "E"));
        	l1.add(getWorkflowModule().createListOption("F", "F"));
        	
        	
            // Set list in affected field with iWorkflowInstance
            IWorkflowInstance iWorkflowInstance = getWorkflowInstance();
            // TODO On workflowInstance, we can set list values by "setList" method
            iWorkflowInstance.setList("maliste",l1 );
            
            // TODO We set the "MyText" property as not editable (by the resource controller)
            IResourceController iResourceController = getResourceController();
            iResourceController.setEditable("monTexte",false);
            
            
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in MyListB onAfterLoad method : " + e.getClass() + " - " + message);
        }
        return super.onAfterLoad();
    }
    
    /**
     * @see com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension#onBeforeSave()
     */
    @Override
    public boolean onBeforeSave()
    {
        try
        {
            // Get workflowInstance and resourceController
            IWorkflowInstance iWorkflowInstance = getWorkflowInstance();
            IResourceController iResourceController = getResourceController();
            
            
			// We check if we have a selected value in field
            // TODO We get value set in field by a "getValue" on workflowInstance
            	// Tip : With a getValue, we get the system name of entry in list
            Object fieldValue = iWorkflowInstance.getValue("maliste");
            // TODO We test if value is empty
            if(fieldValue.equals("null"))
            {iResourceController.inform("maliste","Message d'erreur");
            iResourceController.alert("message d'alerte");
            }
            // Be carefull : in Java, we can't compare objects by "==" (only to test null value);
            // Instead, you can use "equals" method.
            
            // TODO If you detect an empty value in field, you can use your "resourceController" variable : 
            // Tip : Methods "inform" and "alert" to display warnings
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in MyListB onBeforeSave method : " + e.getClass() + " - " + message);
        }
        return super.onBeforeSave();
    }
    
}
