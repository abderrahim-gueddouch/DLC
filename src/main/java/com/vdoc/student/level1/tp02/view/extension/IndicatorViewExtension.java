package com.vdoc.student.level1.tp02.view.extension;

import java.util.List;

import com.axemble.vdoc.sdk.utils.Logger;
import com.axemble.vdoc.sdk.view.extensions.BaseViewExtension;
import com.axemble.vdoc.sdk.view.extensions.ViewItem;
import com.axemble.vdp.ui.framework.composites.base.models.views.ViewModelItem;

/**
 * Created on 14 sept. 2010
 * @author vdoc
 * 
 */
public class IndicatorViewExtension extends BaseViewExtension
{
    protected static final Logger log = Logger.getLogger(IndicatorViewExtension.class);

    /**
     * 
     */
    private static final long serialVersionUID = 4805930830506035073L;

    /**
     * @see com.axemble.vdoc.sdk.view.extensions.BaseViewExtension#onPrepareColumns(java.util.List)
     */
    @Override
    public void onPrepareColumns(List list)
    {
        super.onPrepareColumns(list);
        
        try
        {
            // We create a new column
            // TODO Create a new column of type "OTHER" and add it to "list" (in first position)
        		// Tip : To get type, you can use ViewModelColumn.TYPE_OTHER
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in IndicatorViewExtension onPrepareColumns method : " + e.getClass() + " - " + message);
        }
    }
    
    /**
     * @see com.axemble.vdoc.sdk.view.extensions.BaseViewExtension#onPrepareItem(com.axemble.vdoc.sdk.view.extensions.ViewItem)
     */
    @Override
    public void onPrepareItem(ViewItem viewitem)
    {
        try
        {
            // We get view model Item
            ViewModelItem viewModelItem = viewitem.getViewModelItem();
            
            // We must get the resource linked with this line of data
            // Tip : in our case, it's a workflow instance
            // TODO Get the workflow instance from viewitem object
            
            // TODO Get the quantity field value and depending on value decide the path you must set for your image
            // Tip :
            // - The quantity value got can be casted to Number
            // - For displaying an image, you can use the control "CtlImage(path)"
            // - If you want to use an image of skin folder (dynamic folder), you can use as path : "skins/$skins$/images/mandatory/..."
            // - You can set your ctlimage object with "viewModelItem"
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in IndicatorViewExtension onPrepareItem method : " + e.getClass() + " - " + message);
        }
    }

}
