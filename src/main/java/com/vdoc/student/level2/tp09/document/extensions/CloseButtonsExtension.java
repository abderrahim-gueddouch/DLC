package com.vdoc.student.level2.tp09.document.extensions;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.utils.Logger;

/**
 * @author vmartinon
 *
 */
public class CloseButtonsExtension extends BaseDocumentExtension
{
	/**
     * 
     */
    private static final long serialVersionUID = -5490967539625870707L;
	protected static final Logger log = Logger.getLogger(CloseButtonsExtension.class);
	
	/**
	 * @see com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension#onAfterLoad()
	 */
	@Override
	public boolean onAfterLoad()
	{
	    try
	    {
	    	// TODO We get existing buttons
	    	// Tips : By getResourceController(), we can get buttons by name
	    	
	    	// TODO We hide all these buttons
	    	
	    	// TODO We create a new image button (CtlImageButton)
	    		// Tips : We use "delete" as action name to have a close button
	    	// TODO We add the buttonSaveAndClose as param to this new button (needed in listener)
	    	// TODO We add a listener on this button
	    	
	    	// In the content of "onClick" method
	    		// TODO We get source of event (getSource()) : the image button
				// TODO We get param on this widget : buttonSaveAndClose
				// TODO We trigger the buttonSaveAndClose (execute())
	    	
	    }
	    catch (Exception e)
	    {
	    	String message = e.getMessage();
            if (message == null)
            {
	            message = "";
            }
            log.error("Error in CloseButtonsExtension onAfterLoad method : " + e.getClass() + " - " + message);
	    }
		return super.onAfterLoad();
	}

}
