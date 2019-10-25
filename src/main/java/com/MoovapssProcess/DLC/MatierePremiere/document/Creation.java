package com.MoovapssProcess.DLC.MatierePremiere.document;

import java.util.Collection;
import java.util.Date;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.ICatalog;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IProject;
import com.axemble.vdoc.sdk.interfaces.IProperty;
import com.axemble.vdoc.sdk.interfaces.ITask;
import com.axemble.vdoc.sdk.interfaces.IViewController;
import com.axemble.vdoc.sdk.interfaces.IWorkflow;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;

public class Creation extends BaseDocumentExtension {
// @Override
//public boolean onAfterLoad() {
//	String chrono=getLastDocumentCreated();
//		
//		if (chrono == null)
//        {
//             chrono = "0";
//        }
//		
//        int newChrono = Integer.valueOf(chrono);
//        newChrono = newChrono + 1;
//        getWorkflowInstance().setValue("Ref",".PAN." + newChrono);
//	return super.onAfterLoad();
//}
// private String getLastDocumentCreated(){
//		
//		Collection<IWorkflowInstance> collection=null;
//		
//		try{
//			
//		IContext sysContext = getWorkflowModule().getSysadminContext();
//	    IOrganization organization = getDirectoryModule().getOrganization(sysContext, "DefaultOrganization");
//		IProject project=getProjectModule().getProject(sysContext, "WIZEDLC", organization);
//		IContext context=getWorkflowModule().getLoggedOnUserContext();
//		ICatalog catalog= getWorkflowModule().getCatalog(context, "GroupeProcessus", project);
//		IWorkflow workflow = getWorkflowModule().getWorkflow(context, catalog, (String) getWorkflowInstance().getValue("TypeDArticle"));
//		ITask task=getWorkflowModule().getTask(context, workflow, "Creation2");
//		IViewController viewController=getWorkflowModule().getViewController(context);
//		viewController.setOrderBy(IProperty.System.CREATION_DATE, Date.class, false);
//		viewController.addNotEqualsConstraint(IProperty.System.REFERENCE, getWorkflowInstance().getValue(IProperty.System.REFERENCE));
//		collection=viewController.evaluate(task);
//		collection = viewController.evaluate(getWorkflowInstance().getWorkflow());	
//	    
//		if(!collection.isEmpty())
//		{
//			IWorkflowInstance instance = collection.iterator().next();
//         String test = (String) instance.getValue("Ref");
//         
//         if(test!=null)
//         {
//         	String[] s = test.split("\\_");
//             String a = s[s.length-1];
//             return a;
//         }
//         else 
//         {
//         	return null;
//		
//         }
//		}
//		return "0";
//		
//	    }
//	    catch(Exception e){
//	    	
//	    	e.printStackTrace();
//	    }
//		return null;
//		}
}
