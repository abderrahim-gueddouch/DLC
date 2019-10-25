package com.MoovappsProcess.DemandeConge.document;


import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IUser;


public class UserConnected extends BaseDocumentExtension {

	@Override
	public boolean onAfterLoad() {
		// TODO Auto-generated method stub	
		IContext context = getWorkflowModule().getLoggedOnUserContext();
		IUser userConet = context.getUser();
		getWorkflowInstance().setValue("Responsable2", userConet);
		return super.onAfterLoad();
	}
}
