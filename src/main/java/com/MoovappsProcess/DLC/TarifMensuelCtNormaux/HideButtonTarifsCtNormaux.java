package com.MoovappsProcess.DLC.TarifMensuelCtNormaux;

import java.util.List;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.IResourceController;
import com.axemble.vdoc.sdk.interfaces.ui.IWidget;
import com.axemble.vdp.ui.framework.runtime.NamedContainer;
import com.axemble.vdp.ui.framework.widgets.CtlButton;

public class HideButtonTarifsCtNormaux extends BaseDocumentExtension {

	@Override
	public boolean onAfterLoad() {
	  IResourceController iResourceController = getResourceController();
      
      NamedContainer namedContainer = iResourceController.getButtonContainer(IResourceController.BOTTOM_CONTAINER);

      List<IWidget> widgets = namedContainer.getWidgets();

      for (IWidget iWidget : widgets)
      {
            CtlButton button = (CtlButton) iWidget;
            
            if(button.getName().equals("Appliquer La Modification"))
            {   
            	if(getWorkflowInstance().getValue("NomDEtape17") == null ){
            		button.setHidden(true); 
            	}
            }
            if(button.getName().equals("Envoyer") || button.getName().equals("Retour") || button.getName().equals("Valider"))
            {   
            	if(getWorkflowInstance().getValue("NomDEtape17") != null){
            		button.setHidden(true); 
            	}
            }          
      }
		return super.onAfterLoad();
	}

}
