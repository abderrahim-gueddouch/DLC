package com.MoovappsProcess.DLC.ComptesComptables.document;


import java.util.Collection;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.IAction;
import com.axemble.vdoc.sdk.interfaces.ICatalog;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IProject;
import com.axemble.vdoc.sdk.interfaces.IViewController;
import com.axemble.vdoc.sdk.interfaces.IWorkflow;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;

public class ClientUpdateGlob  extends BaseDocumentExtension{

	@Override
	public boolean onAfterSubmit(IAction action) {
		
		if(action.getName().equals("Valider4"))
		{
			Collection<IWorkflowInstance> collection=getAll();
	       	    
		try {
			
			IContext context=getWorkflowModule().getLoggedOnUserContext();
				
			    if(getWorkflowInstance().getValue("ChapmUpdated2") != null){
			    	
				   String nameChamp=(String) getWorkflowInstance().getValue("ChapmUpdated2"); 
				   String valueChamp=(String)getWorkflowInstance().getValue("ChampValues");
				    
				   for (IWorkflowInstance iWorkflowInstance : collection) {
					   
					 iWorkflowInstance.setValue(nameChamp, valueChamp);
				     iWorkflowInstance.save(context);
				    
			     		}
				    getWorkflowInstance().setValue("ChapmUpdated2", null);
					getWorkflowInstance().save(context);
					} 
	       	
		  } catch (Exception e) {	
			  e.printStackTrace();
		  }
		}
		    return super.onAfterSubmit(action);
   	 }
	
	        private Collection<IWorkflowInstance> getAll(){
	    		
	    		Collection<IWorkflowInstance> collection=null;
	    		
	    		try{
	    			
	    		IContext sysContext = getWorkflowModule().getSysadminContext();
	    	    IOrganization organization = getDirectoryModule().getOrganization(sysContext, "DefaultOrganization");
	    		IProject project=getProjectModule().getProject(sysContext, "WIZEDLC", organization);
	    		IContext context=getWorkflowModule().getLoggedOnUserContext();
	    		ICatalog catalog= getWorkflowModule().getCatalog(context, "GroupeProcessus", project);
	    		IWorkflow workflow=getWorkflowModule().getWorkflow(context, catalog, "Client_1.0");
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
