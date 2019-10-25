
package com.vdoc.extra.dd.student.dd05.document.extension;

import com.axemble.vdoc.sdk.Modules;
import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.IAction;
import com.axemble.vdoc.sdk.interfaces.ICatalog;
import com.axemble.vdoc.sdk.interfaces.IConfiguration;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.ILibrary;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;
import com.axemble.vdoc.sdk.modules.ILibraryModule;
import com.axemble.vdoc.sdk.modules.IWorkflowModule;
import com.axemble.vdoc.sdk.utils.Logger;

/**
 * Created on 28 janv. 2011
 * @author vmartinon
 * 
 */
public class AccessFilesExtensionTips extends BaseDocumentExtension
{
    /**
     * 
     */
    private static final long serialVersionUID = 5494974375870355842L;
    protected static final Logger log = Logger.getLogger(AccessFilesExtensionTips.class);
    
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
            String libraryName = getUserParameterInConfiguration("dd6005.library");
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
            // TODO After, you can set this list to affected field
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in AccessFilesExtension onAfterLoad method : " + e.getClass() + " - " + message);
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
        ILibraryModule iLibraryModule = Modules.getLibraryModule();
        try
        {
           IWorkflowModule iWorkflowModule = getWorkflowModule();
           IWorkflowInstance iWorkflowInstance = getWorkflowInstance(); 
            
           // We get library name (in user parameters of application)
           String libraryName = getUserParameterInConfiguration("dd6005.library");
           if ( (libraryName == null) || (libraryName.equals("")) )
           {
               throw new Exception("Error in getting library's name !");
           }
           
           // We get context and library by name
           IContext context = iLibraryModule.getContextByLogin("sysadmin");
           ILibrary iLibrary = iLibraryModule.getLibrary(context, libraryName);
           
           // We get value folders selected
           // TODO We getValue in iWorkflowInstance
           //   Warning : it's an ArrayList because we have a multiple values field
           // TODO Browse folders selected and foreach we get the IFolder object
               // TODO For each folder, we get sub Files
               // TODO For each file, 
                   // TODO We create a new "linkedResource"
                   // TODO We set columns values
           		   // TODO We save this "linkedResource"
                   // TODO We add this "linkedResource" to iWorkflowInstance
           
           // Tips :
               // To create a linkedResource : iWorkflowModule.createLinkedResource(...)
               // To build the link for field "EnhancedURLField" : 
                   // String fileLink = "portal" + iFile.getURI() + "¤" + iFile.getName() + "¤896 ,704, 1, 1, 0, 0¤¤//:url";
           
           // TODO Don't forget to save the iWorkflowInstance before leaving
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in AccessFilesExtension onAfterSubmit method : " + e.getClass() + " - " + message);
        }
        finally
        {
            // Don't forget to release the module we initialize
            Modules.releaseModule(iLibraryModule);
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
            // We must get application (use workflow instance)
            ICatalog catalog = getWorkflowInstance().getCatalog();
            
            // We must get application configuration
            IConfiguration configuration = catalog.getConfiguration();
            
            value = configuration.getStringUserProperty(key);
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in AccessFilesExtension getUserParameterInConfiguration method : " + e.getClass() + " - " + message);
            throw(e);
        }
        return value;
    }

}
