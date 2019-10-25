
package com.vdoc.student.level2.tp10.document.fields;

import java.util.Collection;
import java.util.Iterator;

import org.w3c.dom.Element;

import com.axemble.vdoc.sdk.Modules;
import com.axemble.vdoc.sdk.document.fields.base.BaseField;
import com.axemble.vdoc.sdk.exceptions.RenderException;
import com.axemble.vdoc.sdk.exceptions.WorkflowModuleException;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IUser;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;
import com.axemble.vdoc.sdk.interfaces.ui.IWritable;
import com.axemble.vdoc.sdk.modules.IDirectoryModule;
import com.axemble.vdoc.sdk.modules.IWorkflowModule;
import com.axemble.vdoc.sdk.utils.Logger;
import com.axemble.vdp.ui.core.document.CoreDocument;
import com.axemble.vdp.ui.framework.components.events.ActionEvent;
import com.axemble.vdp.ui.framework.components.events.ChangeEvent;
import com.axemble.vdp.ui.framework.components.listeners.ActionListener;
import com.axemble.vdp.ui.framework.components.listeners.ChangeListener;
import com.axemble.vdp.ui.framework.foundation.Navigator;
import com.axemble.vdp.ui.framework.template.TemplateFactory;
import com.axemble.vdp.ui.framework.template.TemplateWriter;
import com.axemble.vdp.ui.framework.widgets.CtlButton;
import com.axemble.vdp.ui.framework.widgets.CtlComboBox;
import com.axemble.vdp.ui.framework.widgets.CtlText;
import com.axemble.vdp.ui.framework.widgets.CtlTextBox;
import com.axemble.vdp.ui.framework.widgets.list.Option;

/**
 * Created on 26 oct. 2010
 * @author vdoc
 * 
 */
public class DirectoryBrowserAdvancedField extends BaseField
{
    protected static final Logger log = Logger.getLogger(DirectoryBrowserAdvancedField.class);

    protected CtlComboBox ctlComboBox = null;
    protected CtlTextBox ctlTextBoxEmail = null;
    protected CtlTextBox ctlTextBoxPhone = null;
    protected CtlButton ctlButton = null ;
    
    protected ChangeListener changeListener = null;
    protected ActionListener actionListener = null;
    
    /**
     * @see com.axemble.vdoc.sdk.document.fields.base.BaseField#init(org.w3c.dom.Element)
     */
    @Override
    public void init(Element element)
    {
        // We initialize controls
        if (ctlComboBox == null)
        {
            ctlComboBox = new CtlComboBox();
        	// TODO Combobox must trigger a server round trip (we must set throw-event and set a changelistener)
            ctlComboBox.setThrowEvents(true);
            // TODO Set the content of "changeListener" and link it to the combobox
            // Tip : the content of the listener must :
            // - Load the values of email and phone for selected user in combobox
            // - Save the selected value in current field (in memory) : aggregate.safeUpdateValue();
            
            // We declare change listener
        	changeListener = new ChangeListener() 
        	{
				
				public void onChange(ChangeEvent changeEvent) 
				{
					loadInfosTextboxes();
					aggregate.safeUpdateValue();
				}
			};
        	ctlComboBox.addChangeListener(changeListener);
        	
        	// We fill the content of combobox
        	fillComboBoxContent();
        }
        
        if(ctlButton == null)
        {
        	ctlButton = new CtlButton("Sauv", new CtlText("Sauv"));
        	
        	actionListener = new ActionListener()
			{				
				@Override
				public void onClick(ActionEvent actionevent)
				{
					updateInfosUser();	
					
					IWorkflowModule iWorkflowModule = Modules.getWorkflowModule();
					
					try
					{
						if( getAbstractField().getAbstractDocument() instanceof CoreDocument)
						{
							IWorkflowInstance workflowInstance = iWorkflowModule.getWorkflowInstance(getAbstractField().getAbstractDocument());
						}
					}
					catch (WorkflowModuleException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			ctlButton.addActionListener(actionListener);
        	
        }
        
        // TODO We initialize ctlTextBoxes and set in read only mode
        if (ctlTextBoxEmail == null)
        {
            ctlTextBoxEmail = new CtlTextBox();
            ctlTextBoxEmail.setEditable(true);
        }
        
        if (ctlTextBoxPhone == null)
        {
        	ctlTextBoxPhone = new CtlTextBox();
        	ctlTextBoxPhone.setEditable(true);
        }
    }
    
    /**
     * 
     */
    protected void updateInfosUser()
    {
    	IDirectoryModule iDirectoryModule = Modules.getDirectoryModule();
    	try
    	{
    		iDirectoryModule.beginTransaction();
    		IContext iContext = iDirectoryModule.getContextByLogin("sysadmin");
    		IUser user = iDirectoryModule.getUserByLogin((String) ctlComboBox.getSelectedKey());
    		user.setEmail(ctlTextBoxEmail.getLabel());
    		user.setPhoneNumber(ctlTextBoxPhone.getLabel());
    		user.save(iContext);
    		iDirectoryModule.commitTransaction();
    	}
    	catch (Exception e)
    	{
    		String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in DirectoryBrowserAdvancedField updateInfosUser method : " + e.getClass() + " - " + message);
    	}
    	finally
    	{
    		if(iDirectoryModule.isTransactionActive())
    		{
    			iDirectoryModule.rollbackTransaction();
    		}
    		Modules.releaseModule(iDirectoryModule);
    	}
	}

  	/**
     * 
     */
    protected void fillComboBoxContent()
    {
    	IDirectoryModule iDirectoryModule = Modules.getDirectoryModule();
    	try
    	{
    		IContext iContext = iDirectoryModule.getContextByLogin("sysadmin");
    		IOrganization iOrganization = iDirectoryModule.getOrganization(iContext, "DefaultOrganization");
    		Collection cUser = iOrganization.getMembers();
    		for (Iterator iterator = cUser.iterator(); iterator.hasNext();) 
    		{
				Object object = (Object) iterator.next();
				if (object instanceof IUser)
				{
					IUser iUser = (IUser) object;
					Option option = new Option(iUser.getLogin(), iUser.getFullName());
					ctlComboBox.addOption(option);
				}
			}
    	}
    	catch (Exception e)
    	{
    		String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in DirectoryBrowserAdvancedField fillComboBoxContent method : " + e.getClass() + " - " + message);
    	}
    	finally
    	{
    		Modules.releaseModule(iDirectoryModule);
    	}
	}
    
    /**
     * 
     */
    protected void loadInfosTextboxes() 
    {
    	IDirectoryModule iDirectoryModule = Modules.getDirectoryModule();
		try
		{
		    // We initialize textboxes values
		    ctlTextBoxEmail.setLabel("");
            ctlTextBoxPhone.setLabel("");
		    
		    // Depending on selected user, we set values in textboxes
			String selectedLogin = (String) ctlComboBox.getSelectedKey();
			if ( (selectedLogin != null) && (!selectedLogin.equals("")) )
			{
				IUser iUserSelected = iDirectoryModule.getUserByLogin(selectedLogin);
				ctlTextBoxEmail.setLabel(iUserSelected.getEmail());
				ctlTextBoxPhone.setLabel(iUserSelected.getPhoneNumber());
			}	
		}
		catch (Exception e)
    	{
    		String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in DirectoryBrowserAdvancedField fillComboBoxContent method : " + e.getClass() + " - " + message);
    	}
		finally
		{
			Modules.releaseModule(iDirectoryModule);
		}
	}

	/**
     * @see com.axemble.vdoc.sdk.document.fields.base.BaseField#updateControl()
     */
    @Override
    public void updateControl()
    {
        // TODO We set value in graphical combobox (selected key), coming from database
        // Tip : the onchange will be triggered and "loadInfosTextboxes" method will be automatically triggered
    	if (ctlComboBox != null)
        {
            if (getValue() != null)
            {             
            	ctlComboBox.setSelectedKey(getValue());
            }
        }
    }

    /**
     * @see com.axemble.vdoc.sdk.document.fields.base.BaseField#updateValue()
     */
    @Override
    public void updateValue()
    {
        // TODO We set value in database coming from control combobox selected key
    	if (ctlComboBox != null)
        {
            setValue(ctlComboBox.getSelectedKey());
        }
    }

    /**
     * @see com.axemble.vdp.ui.framework.widgets.CtlInputWidget#isEmpty()
     */
    @Override
    public boolean isEmpty()
	{
		boolean empty = true;
		try
		{
			// We're not empty if ctlComboBox as selected key
			if ((ctlComboBox != null) && (ctlComboBox.getSelectedKey() != null))
			{
				empty = false;
			}
		}
		catch (Exception e)
		{
			String message = e.getMessage();
			if (message == null)
			{
				message = "";
			}
			log.error("Error in DirectoryBrowserAdvancedField isEmpty method : " + e.getClass() + " - " + message);
		}
		return empty;
	}

    /**
     * @see com.axemble.vdp.ui.framework.foundation.Widget#render()
     */
    @Override
    public IWritable render() throws RenderException
    {
    	TemplateFactory.clearCache();
    	TemplateWriter tw = getTemplateWriter("CtlDirectoryBrowserAdvanced.html");
        try
        {
        	// We set entries in template writer for each element to replace
            tw.setEntry("ctlComboBox", ctlComboBox);
            tw.setEntry("ctlTextBoxPhone", ctlTextBoxPhone);
            tw.setEntry("ctlTextBoxEmail", ctlTextBoxEmail);
            tw.setEntry("ctlButton", ctlButton);
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in DirectoryBrowserAdvancedField render method : " + e.getClass() + " - " + message);
        }
        return tw;
    }

}
