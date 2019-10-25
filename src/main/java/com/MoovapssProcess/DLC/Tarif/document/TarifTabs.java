package com.MoovapssProcess.DLC.Tarif.document;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;

public class TarifTabs extends BaseDocumentExtension{

	@Override
    public boolean onAfterLoad() 
	 {
	    getResourceController().showBodyBlock("FragCatTar", true);
		getResourceController().showBodyBlock("FragTarifClient", false);
		getResourceController().showBodyBlock("FragFournisseurs", false);
		getResourceController().showBodyBlock("FragNvTarif", false);
		
		return super.onAfterLoad();
   } 
}
