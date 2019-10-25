
package com.vdoc.extra.dd.education.dd05.document.extension;

import java.util.ArrayList;
import java.util.Collection;

import com.axemble.vdoc.sdk.Modules;
import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.IAction;
import com.axemble.vdoc.sdk.interfaces.ICatalog;
import com.axemble.vdoc.sdk.interfaces.IConfiguration;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IFile;
import com.axemble.vdoc.sdk.interfaces.IFolder;
import com.axemble.vdoc.sdk.interfaces.ILibrary;
import com.axemble.vdoc.sdk.interfaces.ILinkedResource;
import com.axemble.vdoc.sdk.interfaces.IOptionList.IOption;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;
import com.axemble.vdoc.sdk.modules.ILibraryModule;
import com.axemble.vdoc.sdk.modules.IWorkflowModule;
import com.axemble.vdoc.sdk.utils.Logger;
import com.axemble.vdp.ui.framework.foundation.Navigator;

/**
 * Created on 28 janv. 2011
 * @author vmartinon
 * 
 */
public class AccessFilesExtension extends BaseDocumentExtension
{
    /**
     * 
     */
    private static final long serialVersionUID = 5494974375870355842L;
    protected static final Logger log = Logger.getLogger(AccessFilesExtension.class);
    
    /**
     * @see com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension#onAfterLoad()
     */
    @Override
    public boolean onAfterLoad()
    {
        // Initialize a library module (not available by default in this run context)
        ILibraryModule iLibraryModule = Modules.getLibraryModule();
        try
        {
        	IWorkflowModule iWorkflowModule = getWorkflowModule();
        	
            // We must initialize lists :
            // - Folders
            
            // We get library name (in user parameters of application)
            String libraryName = getUserParameterInConfiguration("dd6005.library");
            if ( (libraryName == null) || (libraryName.equals("")) )
            {
                throw new Exception("Error in getting library's name !");
            }
            
            // We get context and library by name
            IContext context = iLibraryModule.getContextByLogin("sysadmin");
            ILibrary iLibrary = iLibraryModule.getLibrary(context, libraryName);
            
            // We get iWorkflowInstance
            IWorkflowInstance iWorkflowInstance = getWorkflowInstance();
            
            /*
             * Folders
             * For exercice, we only get folder of root level
             */
            
            // We get root folders from library
            Collection<IFolder> cFolder = iLibraryModule.getFolders(context, iLibrary);
            if (cFolder != null)
            {
            	// We must build an ArrayList of IOption (Key : ID; Value : name)
                ArrayList<IOption> cFolderOption = new ArrayList<IOption>();
                for (IFolder iFolder: cFolder)
                {
                	cFolderOption.add(iWorkflowModule.createListOption(iFolder.getName(), iFolder.getName()));
                }
                
                // We can set list for affected field
                iWorkflowInstance.setList("Folders", cFolderOption);
            }
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
            Modules.releaseModule(iLibraryModule);
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
           ArrayList<String> cFolderName = (ArrayList<String>) iWorkflowInstance.getValue("Folders");
           if ( (cFolderName != null) && (!cFolderName.isEmpty()) )
           {
               for (String folderName : cFolderName)
               {
                   // We get folder object
                   IFolder iFolder = iLibraryModule.getFolder(context, iLibrary, folderName);
                   
                   // We get files in this folder
                   ArrayList<IFile> cFile = (ArrayList<IFile>) iLibraryModule.getFiles(context, iFolder);
                   for (IFile iFile : cFile)
                   {
                       // We build the file link
                       String fileLink = "portal" + iFile.getURI() + "¤" + iFile.getName() + "¤896 ,704, 1, 1, 0, 0¤¤//:url";
                       
                       // We must create a new line in resources table for this file
                       ILinkedResource iLinkedResource = iWorkflowModule.createLinkedResource(iWorkflowInstance, "TabFiles");
                       iLinkedResource.setValue("fileName", iFile.getName());
                       iLinkedResource.setValue("fileDescription", iFile.getDescription());
                       iLinkedResource.setValue("fileLink", fileLink);
                       iLinkedResource.save();
                       
                       iWorkflowInstance.addLinkedResource(iLinkedResource);
                   }
               }
           }
           
           iWorkflowInstance.save("TabFiles");
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
