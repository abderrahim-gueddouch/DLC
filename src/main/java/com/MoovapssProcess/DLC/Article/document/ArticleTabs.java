package com.MoovapssProcess.DLC.Article.document;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;

public class ArticleTabs extends BaseDocumentExtension{
    
	@Override
    public boolean onAfterLoad() 
	 {
	    getResourceController().showBodyBlock("FragIdentification", true);
	    getResourceController().showBodyBlock("FragTarif", false);
		getResourceController().showBodyBlock("FragDescreption", false);
		getResourceController().showBodyBlock("FragChampsLibres", false);
		getResourceController().showBodyBlock("FragParametres", false);
		
		return super.onAfterLoad();
   }
}
