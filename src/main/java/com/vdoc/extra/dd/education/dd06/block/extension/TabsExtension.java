
package com.vdoc.extra.dd.education.dd06.block.extension;

import java.util.ArrayList;

import com.axemble.sdk.blocks.sys.layouts.SheetBlock;
import com.axemble.sdk.blocks.sys.layouts.sheet.SheetTabBlock;
import com.axemble.sdk.components.sys.application.portal.MydesktopComponent;
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
public class TabsExtension extends BaseBlockExtension
{
    /**
     * 
     */
    private static final long serialVersionUID = -8934108731322545990L;
    protected static final Logger log = Logger.getLogger(TabsExtension.class);
    
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
            
            // We create a sheet block
            SheetBlock sheetBlock = iSiteModule.getBlocksFactory().newSheetBlock();
            
            // We create first block
            SheetTabBlock sheetTabBlock1 = iSiteModule.getBlocksFactory().newSheetTabBlock();
            sheetTabBlock1.setLabel("My Process Documents");
            // We create a "my desktop" component 
            MydesktopComponent mydesktopComponent = iSiteModule.getComponentsFactory().newMydesktopComponent();
            ArrayList<String> cConnector = new ArrayList<String>();
            cConnector.add("VDPJava");
            mydesktopComponent.setConnectorList(cConnector);
            mydesktopComponent.setSelectionName("TODO");
            // We add the component to block
            sheetTabBlock1.addComponent(mydesktopComponent);
            
            // We create second block
            SheetTabBlock sheetTabBlock2 = iSiteModule.getBlocksFactory().newSheetTabBlock();
            sheetTabBlock2.setLabel("My test application");
            // We create a "my desktop" component 
            MydesktopComponent mydesktopComponent2 = iSiteModule.getComponentsFactory().newMydesktopComponent();
            mydesktopComponent2.setConnectorList(cConnector);
            mydesktopComponent2.setSelectionName("MYAPPS");
            mydesktopComponent2.setApplicationFilter("10"); 
            // We add the component to block
            sheetTabBlock2.addComponent(mydesktopComponent2); 
            
            sheetBlock.addBlock(sheetTabBlock1);
            sheetBlock.addBlock(sheetTabBlock2);
            
            rebuiltBlock.addBlock(sheetBlock);
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
