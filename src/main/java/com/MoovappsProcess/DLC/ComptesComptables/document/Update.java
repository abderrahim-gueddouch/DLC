package com.MoovappsProcess.DLC.ComptesComptables.document;


import java.util.List;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;

import com.axemble.vdoc.sdk.interfaces.IResourceController;

import com.axemble.vdoc.sdk.interfaces.ui.IWidget;
import com.axemble.vdp.ui.framework.runtime.NamedContainer;
import com.axemble.vdp.ui.framework.widgets.CtlButton;

public class Update extends BaseDocumentExtension {

  @Override
	public boolean onAfterLoad() {
	  IResourceController iResourceController = getResourceController();

      NamedContainer namedContainer = iResourceController.getButtonContainer(IResourceController.BOTTOM_CONTAINER);

      List<IWidget> widgets = namedContainer.getWidgets();

      for (IWidget iWidget : widgets)
      {
            CtlButton button = (CtlButton) iWidget;
            
            if(button.getName().equals("Apur"))
            {
                  button.setHidden(true);
            }
      }
		return super.onAfterLoad();
	}
   
}
