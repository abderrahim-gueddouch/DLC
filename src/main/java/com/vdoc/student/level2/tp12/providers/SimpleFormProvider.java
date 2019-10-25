
package com.vdoc.student.level2.tp12.providers;

import com.axemble.vdoc.sdk.interfaces.ICatalog;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IProject;
import com.axemble.vdoc.sdk.interfaces.IProperty;
import com.axemble.vdoc.sdk.interfaces.IResourceDefinition;
import com.axemble.vdoc.sdk.interfaces.IStorageResource;
import com.axemble.vdoc.sdk.interfaces.runtime.INavigateContext;
import com.axemble.vdoc.sdk.modules.IWorkflowModule;
import com.axemble.vdoc.sdk.providers.BaseFormProvider;
import com.axemble.vdoc.sdk.utils.Logger;
import com.axemble.vdoc.sdk.utils.StringUtils;
import com.axemble.vdp.ui.framework.components.events.ActionEvent;
import com.axemble.vdp.ui.framework.components.listeners.ActionListener;
import com.axemble.vdp.ui.framework.composites.base.CtlAbstractForm;
import com.axemble.vdp.ui.framework.document.AbstractDocument;
import com.axemble.vdp.ui.framework.document.AbstractField;
import com.axemble.vdp.ui.framework.foundation.NavigateContext;
import com.axemble.vdp.ui.framework.foundation.Navigator;
import com.axemble.vdp.ui.framework.widgets.CtlButton;
import com.axemble.vdp.ui.framework.widgets.CtlText;

/**
 * Created on 12 sept. 2010
 * @author vdoc
 * 
 */
public class SimpleFormProvider extends BaseFormProvider
{
    /**
     * 
     */
    private static final long serialVersionUID = -8298093418092805505L;
	protected static final Logger log = Logger.getLogger(SimpleFormProvider.class);

    /**
     * 
     * @param context
     * @param document
     * @param abstractForm
     */
    public SimpleFormProvider(INavigateContext context, AbstractDocument document, CtlAbstractForm abstractForm)
    {
        super(context, document, abstractForm);
        
                               
    }
    
    /**
     * @see com.axemble.vdp.ui.core.providers.base.AbstractFormProvider#readyState()
     */
    @Override
    public void readyState()
    {
        try
        {
            // At first we have to set a default value in "fldLabel1"
            // TODO Get abstract document with "getDocument()" and after get an "abstractField" (by name) on this document
        	  AbstractDocument abstractDocument = getDocument();
        	  AbstractField abstractField = abstractDocument.getAbstractFieldByName("fldLabel1");
        	  
            // TODO On this abstract field, you'll be able to set your default value
        	  abstractField.setValue("Nabil");
        	  
            // Now we can create buttons            
            // Now, we can create the button as entry
            // TODO user getForm().createButton(...) to create a new button
            // Tip : you'll have to define a new ActionListener to set what is executed when your button is pressed
            // Tip : Don't forget to add your button in your form (not automatic !) : getForm().addEntry(...)
        	  
        	  CtlButton buttonEntry = getForm().createButton("myButtonAsEntry", new CtlText("Tester"), false, new ActionListener()
              {
                  public void onClick(ActionEvent actionEvent)
                  {
                  	getResourceController().alert("Bonjour !");
                  }
              });
              getForm().addEntry("myButtonAsEntry", new CtlText("Tester"), buttonEntry); 
            
            // Now we can add a button in right bottom corner container
            // TODO same thing as before : we can create a new button and define a new actionlistener
            // TODO In this actionlistener, we'll have to :
            // - Create a new data line in data universe : "createLineInDataUniverseTable" method in this class
            // - Navigate to the edition form of this line
            // Tip : to navigate, you must create a NavigateContext to an edit form of data universe :
            /*
             *          NavigateContext navigateContext = new NavigateContext();
                        navigateContext.setClassName("resource");
                        navigateContext.setMethodName("edit");
                        navigateContext.setObjectName(lineID);
                        getNavigator().navigate(navigateContext);
             */
              
              CtlButton buttonInContainer = getForm().createButton("myButtonInContainer", new CtlText("Créer"), false, new ActionListener()
              {
                  public void onClick(ActionEvent actionEvent)
                  {
                      String lineID = createLineInDataUniverseTable("DefaultOrganization", "SDKTRAINING", "TRAINING", "Customer");
                      
                      if (StringUtils.isNotEmpty(lineID))
                      {
                          NavigateContext navigateContext = new NavigateContext();
                          navigateContext.setClassName("resource");
                          navigateContext.setMethodName("edit");
                          navigateContext.setObjectName(lineID);
                          
                          getNavigator().navigate(navigateContext);
                      }
                      else
                      {
                      	 getResourceController().alert("Erreur lors la création de la ligne");
                      }
                  }
              });
              getForm().getButtonsContainer().add(buttonInContainer);
              //getForm().setTemplate("MyBeautifulTemplate");
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in SimpleFormProvider readyState method : " + e.getClass() + " - " + message);
            Navigator.getNavigator().processErrors(e);
        }
        super.readyState();
    }

    /**
     * 
     * @param databaseName
     * @param tableName
     * @return
     */
    protected String createLineInDataUniverseTable(String organizationName, String projectName, String databaseName, String tableName)
    {
        String newLineID = "";
        try
        {
            // We get form field value
            String formFieldValue = "";
            // We get abstract document
            AbstractDocument abstractDocument = getDocument();
            // We get field value
            AbstractField abstractField = abstractDocument.getAbstractFieldByName("fldLabel1");
            formFieldValue = (String) abstractField.getValue();
            
            // We get workflow module
            IWorkflowModule iWorkflowModule = getWorkflowModule();
            // We get a sysadmin context
            IContext context = iWorkflowModule.getContextByLogin("sysadmin");
            
            // We get database
            IOrganization iOrganization = getDirectoryModule().getOrganization(context, organizationName);
            IProject iProject = getProjectModule().getProject(context, projectName, iOrganization);
            ICatalog iCatalog = iWorkflowModule.getCatalog(context, databaseName, ICatalog.IType.STORAGE, iProject);
            // With this catalog, we get the table
            IResourceDefinition iResourceDefinition = iWorkflowModule.getResourceDefinition(context, iCatalog, tableName);
            
            IStorageResource iStorageResource = iWorkflowModule.createStorageResource(context, iResourceDefinition, null);
            iStorageResource.setValue(IProperty.System.TITLE, formFieldValue);
            iStorageResource.save(context);
            
            // We can get the ID of new resource created
            newLineID = iStorageResource.getId().toString();
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in SimpleFormProvider createLineInDataUniverseTable method : " + e.getClass() + " - " + message);
        }
        return newLineID;
    }
}
