package com.vdoc.student.level1.tp03.resource.extension;

import com.axemble.vdoc.sdk.document.extensions.BaseStorageResourceExtension;
import com.axemble.vdoc.sdk.interfaces.IProperty;
import com.axemble.vdoc.sdk.utils.Logger;

/**
 * Created on 11 janv. 2012
 * @author vdoc
 * 
 */
public class GetCustomerDataExtension extends BaseStorageResourceExtension
{
	
	/**
     * 
     */
    private static final long serialVersionUID = -4662924027634838477L;
    protected static final Logger log = Logger.getLogger(GetCustomerDataExtension.class);

	/**
	 * @see com.axemble.vdoc.sdk.document.extensions.BaseStorageResourceExtension#isOnChangeSubscriptionOn(com.axemble.vdoc.sdk.interfaces.IProperty)
	 */
	@Override
	public boolean isOnChangeSubscriptionOn(IProperty property)
	{
		// If property name is "Customer", we must trigger a server round trip
	    if (property.getName().equals("Customer"))
	    {
	    	return true;
	    }
		return super.isOnChangeSubscriptionOn(property);
	}
	
	/**
	 * @see com.axemble.vdoc.sdk.document.extensions.BaseStorageResourceExtension#onPropertyChanged(com.axemble.vdoc.sdk.interfaces.IProperty)
	 */
	@Override
	public void onPropertyChanged(IProperty property)
	{
		super.onPropertyChanged(property);
		
		try
		{
			// TODO If property name is "Customer", we must continue
			
			// TODO We get the customer linked resource
				// Tip :
					// - You can get the current resource with : getStorageResource()
					// - After, you can use a simple getValue
			
			// TODO Don't forget to test if customer is set or not
			
			// TODO We get values from customer to set values in current form
		}
		catch (Exception e)
		{
			String message = e.getMessage();
            if (message == null)
            {
	            message = "";
            }
            log.error("Error in GetCustomerDataExtension onPropertyChanged method : " + e.getClass() + " - " + message);
		}
	}

}
