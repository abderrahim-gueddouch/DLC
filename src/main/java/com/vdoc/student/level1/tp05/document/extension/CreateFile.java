
package com.vdoc.student.level1.tp05.document.extension;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.IAction;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;
import com.axemble.vdoc.sdk.modules.IWorkflowModule;
import com.axemble.vdoc.sdk.utils.Logger;

/**
 * Created on 11 août 2010
 * @author vdoc
 * 
 */
public class CreateFile extends BaseDocumentExtension
{

    /**
     * 
     */
    private static final long serialVersionUID = -5566805643112634350L;
    protected static final Logger log = Logger.getLogger(CreateFile.class);
    
    /**
     * @see com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension#onAfterLoad()
     */
    @Override
    public boolean onAfterLoad()
    {
        // Initialize a library module (not available by default in this run context)
        // TODO Use the "Modules" class to initialize it
        try
        {
        	// We get workflow module
        	IWorkflowModule iWorkflowModule = getWorkflowModule();
        	
            // We must initialize lists :
            // - Folders
            // - Document types
            
            // We get library name (in user parameters of application)
            String libraryName = getUserParameterInConfiguration("tp6005.library");
            if ( (libraryName == null) || (libraryName.equals("")) )
            {
                throw new Exception("Error in getting library's name !");
            }
            
            // We get context and library by name
            // TODO Get the library object by the name you've just get
            // On your iLibraryModule, you can get your library object by name but you will need a context
            // On the same iLibraryModule, you can get you context by login
            
            // We get iWorkflowInstance
            IWorkflowInstance iWorkflowInstance = getWorkflowInstance();
            
            /*
             * Folders
             * For exercice, we only get folder of root level
             */
            
            // We get root folders from library
            // TODO On iLibraryModule, you can get root folders in the library (you'll need the context)
            // TODO You can browse this collection and build another ArrayList with IOption
            	//Tip : it could be interesting to build IOption like this :
            		//Key : Key : Object name
            		//Label : Label : Object name
            // TODO After, you can set this list to affected field as done in TP 60.01
            
            /*
             * Document Types
             */
            
            // We get resource definitions from library
            // TODO On iLibrary, you can get resource definitions in the library ("getFileDefinitions")
            // TODO You can browse this collection and build another ArrayList with IOption
	        	//Tip : it could be interesting to build IOption like this :
	    			//Key : Name : Object name
	    			//Label : Label : Object label
            // TODO After, you can set this list to affected field as done in TP 60.01
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in CreateFile onAfterLoad method : " + e.getClass() + " - " + message);
        }
        finally
        {
            // Don't forget to release the module we initialize
            // TODO Use the "Modules" to release your iLibraryModule
        }
        return super.onAfterLoad();
    }
    
    /**
     * @see com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension#onAfterSubmit(com.axemble.vdoc.sdk.interfaces.IAction)
     */
    @Override
    public boolean onAfterSubmit(IAction action)
    {
        // Initialize a library module (not available by default in this run context)
        // TODO Use the "Modules" class to initialize it
        try
        {
            // We get iWorkflowModule
            IWorkflowModule iWorkflowModule = getWorkflowModule();
            // We get iWorkflowInstance
            IWorkflowInstance iWorkflowInstance = getWorkflowInstance();
            
            // We get library name (in user parameters of application)
            String libraryName = getUserParameterInConfiguration("tp6005.library");
            if ( (libraryName == null) || (libraryName.equals("")) )
            {
                throw new Exception("Error in getting library's name !");
            }
            
            // We get context and library by name
            // TODO Get the library object by the name you've just get
            // On your iLibraryModule, you can get your library object by name but you will need a context
            // On the same iLibraryModule, you can get you context by login
            
            // We get values from iWorkflowInstance
            // TODO You can use "getValue" on instance to get values from document
            // Tip
            	// For attachment field, we can cast the getValue as a Collection<IAttachment>
            
            // We can get objects from the object names we got
            // TODO : on iLibraryModule, you'll be able to get iFolder and iResourceDefinition by name
            
            // TODO We get in a variable first iAttachment in collection of attachments
            
            // We open a transaction
            // TODO On iLibraryModule, you can begin a new transaction
            
            // We create file, setting in constructor, folder, title and attachment
            // TODO Use "createFile" method on iLibraryModule; this method gives you your new iFile
            
            // We set resource definition
            // TODO Set the iResourceDefinition in the new iFile
            
            // We save the new iFile
            // TODO Don't forget to save with context
            
            // We commit the transaction
            // TODO On iLibraryModule, you can commit the transaction
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in CreateFile onAfterSubmit method : " + e.getClass() + " - " + message);
            
            // TODO On iLibraryModule, you can rollback your transaction
        }
        finally
        {
            // Don't forget to release the module we initialize
            // TODO Use the "Modules" to release your iLibraryModule
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
            log.error("Error in CreateFile getUserParameterInConfiguration method : " + e.getClass() + " - " + message);
            throw(e);
        }
        return value;
    }

}
