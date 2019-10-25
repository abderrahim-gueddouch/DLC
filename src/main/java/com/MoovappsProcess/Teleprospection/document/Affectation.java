package com.MoovappsProcess.Teleprospection.document;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.ICatalog;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IProject;
import com.axemble.vdoc.sdk.interfaces.IProperty;
import com.axemble.vdoc.sdk.interfaces.IViewController;
import com.axemble.vdoc.sdk.interfaces.IWorkflow;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;
import com.axemble.vdoc.sdk.interfaces.IOptionList.IOption;

public class Affectation extends BaseDocumentExtension {
	
	private Collection<IWorkflowInstance> getclients(){
		
		Collection<IWorkflowInstance> collection=null;
		
		try{	
		IContext syscontext=getWorkflowModule().getSysadminContext();
		IOrganization organization=getDirectoryModule().getOrganization(syscontext, "DefaultOrganization");
		IProject project=getProjectModule().getProject(syscontext, "Marketing", organization);
		IContext context=getWorkflowModule().getLoggedOnUserContext();
		ICatalog catalog=getWorkflowModule().getCatalog(context, "Referentiel", project);
		IWorkflow workflow=getWorkflowModule().getWorkflow(context, catalog, "ReferentielClient_1.0");
		IViewController viewcontroller=getWorkflowModule().getViewController(context);
		viewcontroller.addNotEqualsConstraint(IProperty.System.REFERENCE, getWorkflowInstance().getValue(IProperty.System.REFERENCE));
		collection =viewcontroller.evaluate(workflow);
		
		return collection;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return collection;
	}
	
	private Collection<IWorkflowInstance> getTeleprospections(){
		
		Collection<IWorkflowInstance> collection=null;
		
		try{
		IContext syscontext=getWorkflowModule().getSysadminContext();
		IOrganization organization=getDirectoryModule().getOrganization(syscontext, "DefaultOrganization");
		IProject project=getProjectModule().getProject(syscontext, "Marketing", organization);
		IContext context=getWorkflowModule().getLoggedOnUserContext();
		ICatalog catalog=getWorkflowModule().getCatalog(context, "Referentiel", project);
		IWorkflow workflow=getWorkflowModule().getWorkflow(context, catalog, "Teleprospection_1.0");
		IViewController viewcontroller=getWorkflowModule().getViewController(context);
		viewcontroller.addNotEqualsConstraint(IProperty.System.REFERENCE, getWorkflowInstance().getValue(IProperty.System.REFERENCE));
		collection =viewcontroller.evaluate(workflow);
		
 		return collection;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return collection;
	}
    private Collection<IWorkflowInstance> getleads(){
		
		Collection<IWorkflowInstance> collection=null;
		
		try{
		IContext syscontext=getWorkflowModule().getSysadminContext();
		IOrganization organization=getDirectoryModule().getOrganization(syscontext, "DefaultOrganization");
		IProject project=getProjectModule().getProject(syscontext, "Marketing", organization);
		IContext context=getWorkflowModule().getLoggedOnUserContext();
		ICatalog catalog=getWorkflowModule().getCatalog(context, "Referentiel", project);
		IWorkflow workflow=getWorkflowModule().getWorkflow(context, catalog, "LeadsEntrants_1.0");
		IViewController viewcontroller=getWorkflowModule().getViewController(context);
		viewcontroller.addNotEqualsConstraint(IProperty.System.REFERENCE, getWorkflowInstance().getValue(IProperty.System.REFERENCE));
		collection =viewcontroller.evaluate(workflow);
 		return collection;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return collection;
	}
	
	@Override
	public boolean onAfterLoad() {
		
		Collection<IWorkflowInstance> collection=getclients();
		Collection<IWorkflowInstance> collect=getTeleprospections();
		Collection<IWorkflowInstance> coll=getleads();
		Set<IOption> set=new HashSet<>();
		
		for (IWorkflowInstance iWorkflowInstance : collection) {
		  
			if(iWorkflowInstance.getValue("NomPartenaire") != null){
		
				String nom=(String) iWorkflowInstance.getValue("NomPartenaire");
		      
		     for (IWorkflowInstance workflowInstance : collect) {
		    	 
				    set.add(getWorkflowModule().createListOption(nom, nom));			              
				}
			}
		}
		getWorkflowInstance().setList("AffecterA", set);
			
		Set<IOption> setList=new HashSet<>();
		
		for (IWorkflowInstance iWorkflowInstance : collection) {
		  
			if(iWorkflowInstance.getValue("NomPartenaire") != null){
		
				String nom=(String) iWorkflowInstance.getValue("NomPartenaire");
		      
		     for (IWorkflowInstance workflowInstance : coll) {
		    	 
				    setList.add(getWorkflowModule().createListOption(nom,nom));      
				}
			}
		}
		getWorkflowInstance().setList("AffecterA2", setList);
		
		return super.onAfterLoad();
	}
	
	
	@Override
	public void onPropertyChanged(IProperty property) {
		// TODO Auto-generated method stub
		super.onPropertyChanged(property);
	}
		
	
	@Override
	public boolean onBeforeSave() {
		// TODO Auto-generated method stub
		return super.onBeforeSave();
	}

}
