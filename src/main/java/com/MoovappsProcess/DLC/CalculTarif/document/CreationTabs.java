package com.MoovappsProcess.DLC.CalculTarif.document;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;

public class CreationTabs extends BaseDocumentExtension {
	
	@Override
	public boolean onAfterLoad() {
		
		getResourceController().showBodyBlock("FragCategorie", true);
		getResourceController().showBodyBlock("FragTarifsClients", false);
		getResourceController().showBodyBlock("FragFournisseur", false);
		getResourceController().showBodyBlock("FragNouveauTarif", false);
		
		return super.onAfterLoad();
	}
	
}
