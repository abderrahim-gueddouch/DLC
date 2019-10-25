
package com.vdoc.student.level2.tp14.view.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.axemble.vdoc.sdk.Modules;
import com.axemble.vdoc.sdk.exceptions.DirectoryModuleException;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IGroup;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IUser;
import com.axemble.vdoc.sdk.interfaces.runtime.INavigateContext;
import com.axemble.vdoc.sdk.modules.IDirectoryModule;
import com.axemble.vdoc.sdk.providers.BaseViewProvider;
import com.axemble.vdoc.sdk.utils.Logger;
import com.axemble.vdp.ui.core.providers.ICollectionModelViewProvider;
import com.axemble.vdp.ui.core.providers.ICollectionViewProvider;
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
public class DirectoryUsersViewProvider extends BaseViewProvider implements ICollectionViewProvider<IUser>
{
    /**
     * 
     */
    private static final long serialVersionUID = 1975610955992350345L;

	protected static final Logger log = Logger.getLogger(DirectoryUsersViewProvider.class);
    
    protected String groupName = "";
    
    /**
     * 
     * @param context
     * @param view
     */
    public DirectoryUsersViewProvider(INavigateContext context, CtlAbstractView view)
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


	@Override
	public Collection<IUser> getObjects()
	{
		IDirectoryModule iDirectoryModule = Modules.getDirectoryModule();
		ArrayList<IUser> cUser = new ArrayList<IUser>();
		ArrayList<ViewModelItem> cViewModelItem = new ArrayList<ViewModelItem>();
		
		// We get a sysadmin context
		IContext context = getDirectoryModule().getContextByLogin("sysadmin");
		
		// We get group from "groupName"
		// TODO We get group with directory module : we have the group name, got from parameters
		try
		{
			IOrganization iOrganization = iDirectoryModule.getOrganization(context, "DefaultOrganization");
			IGroup iGroup = iDirectoryModule.getGroup(context, iOrganization, groupName);
			cUser.addAll(iGroup.getAllMembers());
		}
		catch (DirectoryModuleException e)
		{
			String message = e.getMessage();
			if (message == null)
			{
				message = "";
			}
			log.error("Error in DirectoryUsersViewProvider getObjects method : " + e.getClass() + " - " + message);
			Navigator.getNavigator().processErrors(e);
		}
		 finally
	        {
	            // We don't forget to release the module we created
	            Modules.releaseModule(iDirectoryModule);
	        }
		
		// We return the getAllMembers of the group
		
		return cUser;
	}

		@Override public ViewModelItem fetchLine(IUser iUser)
		{
				// TODO We build a new ViewModelItem from the user in paramater :
				// - Key
				// - Values for each column
			    ViewModelItem viewModelItem = new ViewModelItem();
			    viewModelItem.setKey(iUser);
			    viewModelItem.setValue("login", iUser.getLogin());
				viewModelItem.setValue("lastName", iUser.getLastName());
				viewModelItem.setValue("firstName", iUser.getFirstName());
				viewModelItem.setValue("email", iUser.getEmail());
				return viewModelItem;
		}
    
}
