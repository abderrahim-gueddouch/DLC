package com.MoovapssProcess.DLC.Article.document;

import java.util.List;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.IResourceController;
import com.axemble.vdoc.sdk.interfaces.ui.IWidget;
import com.axemble.vdp.ui.framework.runtime.NamedContainer;
import com.axemble.vdp.ui.framework.widgets.CtlButton;

public class HideButtonFicheArticle extends BaseDocumentExtension{
	
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
            	if(getWorkflowInstance().getValue("NomDEtape17") == null || getWorkflowInstance().getValue("Desactiver") != null){
            		button.setHidden(true); 
            	}
            }
            if(button.getName().equals("Envoyer") || button.getName().equals("Retour") || button.getName().equals("Valider") || button.getName().equals("Cr�er"))
            {   
            	if(getWorkflowInstance().getValue("NomDEtape17") != null){
            		button.setHidden(true); 
            	}
            }
            if(button.getName().equals("Envoyer") || button.getName().equals("Retour") || button.getName().equals("Valider") || button.getName().equals("Cr�er")){
            	
            	if(getWorkflowInstance().getValue("Desactiver") != null){
            		button.setHidden(false); 
            	}
            }            
      }
		return super.onAfterLoad();
	}
}
