
package com.vdoc.student.level2.tp121.providers;

import com.axemble.vdoc.sdk.interfaces.runtime.INavigateContext;
import com.axemble.vdoc.sdk.providers.BaseWizardProvider;
import com.axemble.vdoc.sdk.utils.Logger;
import com.axemble.vdp.ui.framework.composites.base.CtlAbstractWizard;
import com.axemble.vdp.ui.framework.composites.base.ISection;
import com.axemble.vdp.ui.framework.document.AbstractDocument;

/**
 * Created on 26 oct. 2010
 * @author vmartinon
 * 
 */
public class SimpleWizardProvider extends BaseWizardProvider
{
    /**
     * 
     */
    private static final long serialVersionUID = 2562633090157492036L;
	protected static final Logger log = Logger.getLogger(SimpleWizardProvider.class);
    
    /**
     * 
     * @param context
     * @param document
     * @param abstractWizard
     */
    public SimpleWizardProvider(INavigateContext context, AbstractDocument document, CtlAbstractWizard abstractWizard)
    {
        super(context, document, abstractWizard);
    }

    /**
     * @see com.axemble.vdp.ui.core.providers.IWizardProvider#activate(com.axemble.vdp.ui.framework.composites.base.ISection)
     */
    public void activate(ISection isection)
    {
        if (isection.getName().equals("page2"))
        {
            try
            {
                // We get uploaded file
                // TODO From document, we get abstract field "fldFile" and get value : Collection<TempUploadFile>
                
                // TODO We get first element in collection of TempUploadFile
                
                // TODO From TempUploadFile, we get java File
            		// TODO on TempUploadFile, we can get "shortname" to get original name (filename + extension) : we'll use to copy file
                
                // TODO We declare a target File and can use "channels" to transfer data to this new file 
                // ("D:/VDocSuite/JBoss/server/default/deploy/vdoc.ear/vdoc.war/images/" + orginalShortName)
                
                // TODO Finally, we can add an image control to current section : iSection.addEntry(...)
            		// new CtlImage("images/" + orginalShortName);
            }
            catch (Exception e)
            {
                String message = e.getMessage();
                if (message == null)
                {
                    message = "";
                }
                log.error("Error in SimpleWizardProvider activate method : " + e.getClass() + " - " + message);
            }
        }
    }

}
