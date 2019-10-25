
package com.vdoc.extra.dd.student.dd06.block.extension;

import com.axemble.vdoc.sdk.interfaces.IBlock;
import com.axemble.vdoc.sdk.interfaces.IBlockController.IRenderOptions;
import com.axemble.vdoc.sdk.modules.ISiteModule;
import com.axemble.vdoc.sdk.site.extensions.BaseBlockExtension;
import com.axemble.vdoc.sdk.utils.Logger;

/**
 * Created on 28 sept. 2010
 * @author vdoc
 * 
 */
public class TabsExtensionTips extends BaseBlockExtension
{
    /**
     * 
     */
    private static final long serialVersionUID = -8934108731322545990L;
    protected static final Logger log = Logger.getLogger(TabsExtensionTips.class);
    
    /**
     * @see com.axemble.vdoc.sdk.site.extensions.BaseBlockExtension#onPrepareBlock(com.axemble.vdoc.sdk.interfaces.IBlock)
     */
    @Override
    public int onPrepareBlock(IBlock rebuiltBlock)
    {
        try
        {
            // We get site module
            ISiteModule iSiteModule = getSiteModule();
            
            // TODO We create a "sheet block"
            // Tip : use the BlocksFactory got from siteModule
            
            // TODO We create first block : "sheet tab block" and set label of tab
            // TODO We create a "my desktop component"
            // TODO We set in this component :
            // - An ArrayList of connectors
            // - The selection name
            // Tip : In VDoc, the connector for Process is "VDPJava" and the selection name for "My current tasks" is "TODO"
            // TODO We add the component in "sheet tab block"
            
            // TODO We create second block : "sheet tab block" and set label of tab
            // TODO We create a "my desktop component"
            // TODO We set in this component :
            // - An ArrayList of connectors
            // - The selection name
            // - Application Filter
            // Tip : In VDoc, the connector for Process is "VDPJava" and the selection name for "My applications" is "MYAPPS"
            // Tip : To find you application filter, you can try to create another page with a "my desktop component" configured
            //  to display "My applications" and to show applications system names (or same in a portlet)
            // TODO We add the component in "sheet tab block"
            
            // TODO We add the 2 "sheet tab" blocks in "sheet block"
            // TODO We add the "sheet block" in "rebuiltBlock"
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in TabsExtension onPrepareBlock method : " + e.getClass() + " - " + message);
        }
        return IRenderOptions.ONLY_CHILDREN;
    }
    
}
