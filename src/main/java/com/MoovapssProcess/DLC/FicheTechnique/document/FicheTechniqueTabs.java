package com.MoovapssProcess.DLC.FicheTechnique.document;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;

public class FicheTechniqueTabs extends BaseDocumentExtension {

	@Override
    public boolean onAfterLoad() 
	 {
	    getResourceController().showBodyBlock("FragCaracteristique", true);
		getResourceController().showBodyBlock("FragGammeControleQualite", false);
		getResourceController().showBodyBlock("FragScotch", false);
		getResourceController().showBodyBlock("FragEmballage", false);
		getResourceController().showBodyBlock("FragConditionStockage", false);
		getResourceController().showBodyBlock("FragGenererFicheTechnique", false);
		
		return super.onAfterLoad();
   }
	
	
}
