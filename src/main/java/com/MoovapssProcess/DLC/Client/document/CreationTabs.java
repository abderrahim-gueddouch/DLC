package com.MoovapssProcess.DLC.Client.document;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;

public class CreationTabs extends BaseDocumentExtension{
	
	@Override
	public boolean onAfterLoad() {
		
		getResourceController().showBodyBlock("FragIdentification", true);
		getResourceController().showBodyBlock("FragTarifs", false);
		
		
		return super.onAfterLoad();
	}

}
