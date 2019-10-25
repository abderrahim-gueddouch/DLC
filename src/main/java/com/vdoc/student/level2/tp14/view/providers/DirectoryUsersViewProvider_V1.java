
package com.vdoc.student.level2.tp14.view.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.axemble.vdoc.sdk.Modules;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IGroup;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IUser;
import com.axemble.vdoc.sdk.interfaces.runtime.INavigateContext;
import com.axemble.vdoc.sdk.modules.IDirectoryModule;
import com.axemble.vdoc.sdk.providers.BaseViewProvider;
import com.axemble.vdoc.sdk.utils.Logger;
import com.axemble.vdp.ui.core.providers.ICollectionModelViewProvider;
import com.axemble.vdp.ui.framework.composites.base.CtlAbstractView;
import com.axemble.vdp.ui.framework.composites.base.models.views.CollectionViewModel;
import com.axemble.vdp.ui.framework.composites.base.models.views.ViewModelColumn;
import com.axemble.vdp.ui.framework.composites.base.models.views.ViewModelItem;
import com.axemble.vdp.ui.framework.foundation.Navigator;
import com.axemble.vdp.ui.framework.widgets.CtlLocalizedText;

/**
 * Created on 13 sept. 2010
 * @author vdoc
 * 
 */
public class DirectoryUsersViewProvider_V1 extends BaseViewProvider implements ICollectionModelViewProvider
{
    /**
     * 
     */
    private static final long serialVersionUID = 1975610955992350345L;

	protected static final Logger log = Logger.getLogger(DirectoryUsersViewProvider_V1.class);
    
    protected String groupName = "";
    
    /**
     * 
     * @param context
     * @param view
     */
    public DirectoryUsersViewProvider_V1(INavigateContext context, CtlAbstractView view)
    {
        super(context, view);
        
        // We get filter group name from parameters in context
        // TODO get the group name parameter from context
        groupName = context.getParameterString("groupeName");
    }
    
    /**
     * @see com.axemble.vdp.ui.core.providers.base.AbstractViewProvider#init()
     */
    @Override
    public void init()
    {
        super.init();
        try
        {
            // We get view model
            CollectionViewModel viewModel = (CollectionViewModel) getModel(); 
            
            // TODO We can create columns and add it to "viewModel"
            ViewModelColumn modelColumn = new ViewModelColumn("login", new CtlLocalizedText("Login").getText(), ViewModelColumn.TYPE_STRING);
			viewModel.addColumn(modelColumn);
			modelColumn = new ViewModelColumn("lastName", new CtlLocalizedText("Last name").getText(), ViewModelColumn.TYPE_STRING);
			viewModel.addColumn(modelColumn);
			modelColumn = new ViewModelColumn("firstName", new CtlLocalizedText("First name").getText(), ViewModelColumn.TYPE_STRING);
			viewModel.addColumn(modelColumn);
			modelColumn = new ViewModelColumn("email", new CtlLocalizedText("Email").getText(), ViewModelColumn.TYPE_STRING);
			viewModel.addColumn(modelColumn);
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in DirectoryUsersViewProvider init method : " + e.getClass() + " - " + message);
            Navigator.getNavigator().processErrors(e);
        }
    }

    /**
     * @see com.axemble.vdp.ui.core.providers.ICollectionModelViewProvider#getModelItems()
     */
    public List getModelItems()
    {
        IDirectoryModule iDirectoryModule = Modules.getDirectoryModule();
        ArrayList<ViewModelItem> cViewModelItem = new ArrayList<ViewModelItem>();
        try
        {
            // We get a sysadmin context
            IContext context = iDirectoryModule.getContextByLogin("sysadmin");
            
            // We get group from "groupName"
            // TODO We get group with directory module : we have the group name, got from parameters            
            IOrganization iOrganization = iDirectoryModule.getOrganization(context, "DefaultOrganization");
			IGroup iGroup = iDirectoryModule.getGroup(context, iOrganization, groupName); 
            
            // TODO We browse all members in this group
            // TODO For each user member, we can build a new "ViewModelItem" with all columns information
            // Tip : don't forget to add your "viewModelItem" to the collection returned "cViewModelItem"
			if(iGroup != null)
			{
				Collection<? extends IUser> cAllMembers = iGroup.getAllMembers();
				ViewModelItem viewModelItem = null ;
				for (IUser iUser : cAllMembers)
				{
					// We create a new viewModelItem
					viewModelItem = new ViewModelItem();
					
					// We set the user as key of "viewModelItem"
					viewModelItem.setKey(iUser);
					
					// We set values for each column
					viewModelItem.setValue("login", iUser.getLogin());
					viewModelItem.setValue("lastName", iUser.getLastName());
					viewModelItem.setValue("firstName", iUser.getFirstName());
					viewModelItem.setValue("email", iUser.getEmail());
					
					// We add it in lines collection
					cViewModelItem.add(viewModelItem);
				}
			}
			else
			{
				throw new Exception("Group is null !");
			}
			
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in DirectoryUsersViewProvider getModelItems method : " + e.getClass() + " - " + message);
            Navigator.getNavigator().processErrors(e);
        }
        finally
        {
            // We don't forget to release the module we created
            Modules.releaseModule(iDirectoryModule);
        }
        return cViewModelItem;
    }
    
}
