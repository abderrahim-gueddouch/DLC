package com.MoovapssProcess.DLC.DesactivationActivation.document;

import java.util.ArrayList;
import java.util.Collection;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.ICatalog;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IProject;
import com.axemble.vdoc.sdk.interfaces.IViewController;
import com.axemble.vdoc.sdk.interfaces.IWorkflow;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;
import com.axemble.vdoc.sdk.interfaces.IOptionList.IOption;

public class Demande extends BaseDocumentExtension {
	
	@Override
	public boolean onAfterLoad() {
		
		ArrayList<IOption> arr = new ArrayList<IOption>();
		Collection<IWorkflowInstance> collection = getAllArticle();
		
		for (IWorkflowInstance iWorkflowInstance : collection) 
		{
			if(iWorkflowInstance.getValue("CodeArticle") != null)
			{   String code =(String) iWorkflowInstance.getValue("CodeArticle");				
				arr.add(getWorkflowModule().createListOption(code, code));
	           }
		}
		getWorkflowInstance().setList("CodeArticle2", arr);
		
		return super.onAfterLoad();
	}

	
	private Collection<IWorkflowInstance> getAllArticle(){
		
		Collection<IWorkflowInstance> collection=null;
		try{
		IContext sysContext = getWorkflowModule().getSysadminContext();
	    IOrganization organization = getDirectoryModule().getOrganization(sysContext, "DefaultOrganization");
		IProject project=getProjectModule().getProject(sysContext, "WIZEDLC", organization);
		IContext context=getWorkflowModule().getLoggedOnUserContext();
		ICatalog catalog= getWorkflowModule().getCatalog(context, "GroupeProcessus", project);
		IWorkflow workflow=getWorkflowModule().getWorkflow(context, catalog, "Article_1.1");
		IViewController viewController=getWorkflowModule().getViewController(context);

	    collection = viewController.evaluate(workflow);
		
		return collection;
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
		return collection;
		}

}
