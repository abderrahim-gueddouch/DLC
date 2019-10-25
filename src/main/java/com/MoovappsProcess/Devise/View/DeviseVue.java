package com.MoovappsProcess.Devise.View;


import java.util.List;

import com.axemble.vdp.ui.framework.composites.base.models.views.ViewModelItem;
import com.axemble.vdoc.sdk.view.extensions.BaseViewExtension;
import com.axemble.vdoc.sdk.view.extensions.ViewItem;
import com.axemble.vdp.ui.framework.composites.base.models.views.ViewModelColumn;
import com.axemble.vdp.ui.framework.widgets.CtlImage;

public class DeviseVue extends BaseViewExtension{
	
	@Override
	public void onPrepareColumns(List viewModelColumns) {
		
	    ViewModelColumn propertycolumn= new ViewModelColumn("TVA", "TVA%", ViewModelColumn.TYPE_FLOAT);
	    ViewModelColumn propertycolumn1= new ViewModelColumn("IMAGE", "Image", ViewModelColumn.TYPE_OTHER);
	    propertycolumn.setZone(ViewModelColumn.ZONE_TITLE);
	    propertycolumn1.setZone(ViewModelColumn.ZONE_TITLE);
	    viewModelColumns.add(propertycolumn1);
	    viewModelColumns.add(propertycolumn);
	    super.onPrepareColumns(viewModelColumns);
	}
	
	
	@Override
	public void onPrepareItem(ViewItem item) {
		ViewModelItem viewModelItem = item.getViewModelItem();
		Number prix=(Number)viewModelItem.getValue("PrixUnitaire");
		if(prix != null && prix.floatValue() <= 100){
			viewModelItem.setValue("TVA", 15);
			CtlImage image=new CtlImage("skins/$skins$/images/mandatory/state_on.gif");
			viewModelItem.setValue("IMAGE", image);
		}
		else{
			viewModelItem.setValue("TVA", 10);
			CtlImage image=new CtlImage("skins/$skins$/images/mandatory/state_off.gif");
			viewModelItem.setValue("IMAGE", image);
		}
	}   
}
