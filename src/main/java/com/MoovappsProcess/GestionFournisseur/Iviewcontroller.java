package com.MoovappsProcess.GestionFournisseur;


import java.util.ArrayList;
import java.util.Collection;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.ICatalog;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.ILinkedResource;
import com.axemble.vdoc.sdk.interfaces.IOptionList.IOption;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IProject;
import com.axemble.vdoc.sdk.interfaces.IProperty;
import com.axemble.vdoc.sdk.interfaces.IStorageResource;
import com.axemble.vdoc.sdk.interfaces.IViewController;
import com.axemble.vdoc.sdk.interfaces.IWorkflow;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;


public class Iviewcontroller extends BaseDocumentExtension{

	@Override
	public boolean onAfterLoad() {
		
		ArrayList<IOption> arr = new ArrayList<IOption>();
		Collection<IWorkflowInstance> collection = getAll();
		for (IWorkflowInstance iWorkflowInstance : collection) 
		{
			if(iWorkflowInstance.getValue("MontantDhs") != null)
			{   Number montantdhs =(Number) iWorkflowInstance.getValue("MontantDhs");
				String montant= montantdhs.toString();
				arr.add(getWorkflowModule().createListOption(montant, montant));
				
	           }
//			  if(getWorkflowInstance().getValue("count") == null){
//					Number count =(Number) iWorkflowInstance.getValue("TotalAPayerEnDh");
//					getWorkflowInstance().setValue("count", count);
//				}
		}
		getWorkflowInstance().setList("Liste1", arr);
		
		
		Collection<ILinkedResource> linkedresource=(Collection<ILinkedResource>) getWorkflowInstance().getLinkedResources("Table");
		int i = linkedresource.size();
		
		
		if(linkedresource.isEmpty()){
			for (IWorkflowInstance workflowInstance : collection) {
				
				if(collection != null){
				IStorageResource storageResource=(IStorageResource) workflowInstance.getValue("Fournisseur");
			
				ILinkedResource resource = getWorkflowInstance().createLinkedResource("Table");
				if(resource != null){
				resource.setValue("NomFrs", storageResource.getValue("sys_Title"));
				resource.setValue("MontantDhs", workflowInstance.getValue("MontantDhs"));
//				resource.setValue("Montant Devise", workflowInstance.getValue("MontantDevise"));
//				resource.setValue("Cours Moyen", workflowInstance.getValue("CoursMoyen"));
//				resource.setValue("RIB", workflowInstance.getValue("RIB"));
//				resource.setValue("Cours Negocie", workflowInstance.getValue("CoursNegocie"));
				IContext context=getWorkflowModule().getLoggedOnUserContext();
				
				getWorkflowInstance().addLinkedResource(resource);
				resource.save(context);
//				workflowInstance.addLinkedResource(resource);
				}}
				}
//		}
		}
		
		
		return super.onAfterLoad();
	}
	@Override
	public void onPropertyChanged(IProperty property) {
//	if(property.getName().equals("TableauDesFactures"))
//	{
//		
//	}	
		super.onPropertyChanged(property);
	}
	
	private Collection<IWorkflowInstance> getAll(){
		
		Collection<IWorkflowInstance> collection=null;
		try{
		IContext sysContext = getWorkflowModule().getSysadminContext();
	    IOrganization organization = getDirectoryModule().getOrganization(sysContext, "DefaultOrganization");
		IProject project=getProjectModule().getProject(sysContext, "GestionDesFournisseurs", organization);
		IContext context=getWorkflowModule().getLoggedOnUserContext();
		ICatalog catalog= getWorkflowModule().getCatalog(context, "GroupeProcessus", project);
		IWorkflow workflow=getWorkflowModule().getWorkflow(context, catalog, "ReglementFournisseur_1.0");
		IViewController viewController=getWorkflowModule().getViewController(context);
    	viewController.addNotEqualsConstraint(IProperty.System.REFERENCE, getWorkflowInstance().getValue(IProperty.System.REFERENCE));
		viewController.setOrberBy("DateDeReglement",String.class , true);
		//viewController.addEqualsConstraint("NomChamp", "Value");
//	    if(getWorkflowInstance().getValue("TotalAPayerEnDh") != null){
//	    Number totaldhs=(Number)getWorkflowInstance().getValue("TotalAPayerEnDh");
//	    viewController.setCount((int) totaldhs.floatValue());
//	    }
	    collection = viewController.evaluate(workflow);
		
		return collection;
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
		return collection;
		}
	
//	String codeArticle=(String) getWorkflowInstance().getValue("CodeArticle");
//	for (IWorkflowInstance iWorkflowInstance : collection) { 
//		   codeA=(String)iWorkflowInstance.getValue("CodeArticle");
//			if(codeArticle.equals(a)){
//				newChrono++;
//				b=chrono+"_"+newChrono;				
//				}
//	}
//	getWorkflowInstance().setValue(codeA,b);
//	
//	
//	private Collection<IWorkflowInstance> getAllArticle(){
//		
//		Collection<IWorkflowInstance> collection=null;
//		try{
//		IContext sysContext = getWorkflowModule().getSysadminContext();
//	    IOrganization organization = getDirectoryModule().getOrganization(sysContext, "DefaultOrganization");
//		IProject project=getProjectModule().getProject(sysContext, "GestionDesFournisseurs", organization);
//		IContext context=getWorkflowModule().getLoggedOnUserContext();
//		ICatalog catalog= getWorkflowModule().getCatalog(context, "GroupeProcessus", project);
//		IWorkflow workflow=getWorkflowModule().getWorkflow(context, catalog, "Article_1.0");
//		IViewController viewController=getWorkflowModule().getViewController(context);
//		
//	    collection = viewController.evaluate(workflow);
//		
//		return collection;
//	    }
//	    catch(Exception e){
//	    	e.printStackTrace();
//	    }
//		return collection;
//		}
	
//	onafter(){
//		try {
//			getResourceController().setThrowEvents("TypeDArticle", true);
//			
//			sysContext = getWorkflowModule().getSysadminContext();
//			organization = getDirectoryModule().getOrganization(sysContext, "DefaultOrganization");
//		    project=getProjectModule().getProject(sysContext, "WIZEDLC", organization);
//		    IContext context=getWorkflowModule().getLoggedOnUserContext();
//			catalog= getWorkflowModule().getCatalog(context, "GroupeProcessus", project);
//			
//			if(getWorkflowInstance().getValue("TypeDArticle")==null && catalog != null){
//			
////			getWorkflowInstance().setList("TypeDArticle", GetRessourceDefinitionByCatalog(catalog));
//			
//			}
//			else {
//				getWorkflowInstance().setValue("TypeDArticle", null);
//				getWorkflowInstance().setList("TypeArticle", null);
//			}
//		} catch (Exception e) {
//		
//			e.printStackTrace();
//		
//	}
//	}
    }