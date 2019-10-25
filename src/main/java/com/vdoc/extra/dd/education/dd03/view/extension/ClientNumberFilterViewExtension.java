package com.vdoc.extra.dd.education.dd03.view.extension;

import com.axemble.vdoc.sdk.interfaces.IUser;
import com.axemble.vdoc.sdk.view.extensions.BaseViewExtension;
import com.axemble.vdoc.sdk.view.extensions.ViewItem;
import com.axemble.vdp.views.query.Definition;
import com.axemble.vdp.views.query.Field;
import com.axemble.vdp.views.query.Fieldgroup;
import com.axemble.vdp.views.query.ObjectFactory;

/**
 * @author vmartinon
 *
 */
public class ClientNumberFilterViewExtension extends BaseViewExtension
{
	
	@Override
	public boolean onPrepareView(Definition viewDefinition)
	{
		Fieldgroup mainFieldGroup = viewDefinition.getFilters().getFieldgroup();
		String connectedUserCustomerNumber = getCustomerNumer(getWorkflowModule().getLoggedOnUser());
		if (connectedUserCustomerNumber != null)
		{
			ObjectFactory objectFactory = new ObjectFactory();
			Field myField = objectFactory.createField();
			myField.setName("NumeroDeClient");
			myField.setOperator("equals");
			myField.setValue(connectedUserCustomerNumber);
			
			mainFieldGroup.getFieldgroupOrField().add(myField);
		}
		
		return super.onPrepareView(viewDefinition);
	}

	private String getCustomerNumer(IUser loggedOnUser)
    {
	    return (String)loggedOnUser.getExtendedAttributes().getValue("NumeroDeClient");
    }

	@Override
	public void onPrepareItem(ViewItem arg0)
	{

	}

}
