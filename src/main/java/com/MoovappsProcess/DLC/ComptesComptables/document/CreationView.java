package com.MoovappsProcess.DLC.ComptesComptables.document;


import java.util.Collection;

import javax.wsdl.Definition;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.ICatalog;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IProject;
import com.axemble.vdoc.sdk.interfaces.IProperty;
import com.axemble.vdoc.sdk.interfaces.IViewController;
import com.axemble.vdoc.sdk.interfaces.IWorkflow;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;
import com.axemble.vdp.ui.framework.foundation.Navigator;
import com.axemble.vdp.views.query.Field;
import com.axemble.vdp.views.query.Fieldgroup;
import com.axemble.vdp.views.query.ObjectFactory;

public class CreationView extends BaseDocumentExtension{
	
	public Collection<IWorkflowInstance> getAllDocCreation(){
		
		Collection<IWorkflowInstance> collection=null;
		
	try {
		
		IContext syscontext=getWorkflowModule().getSysadminContext();
		IOrganization organisation=getDirectoryModule().getOrganization(syscontext, "DefaultOrganization");
        IProject project=getProjectModule().getProject(syscontext, "WIZEDLC", organisation);
        IContext context=getWorkflowModule().getLoggedOnUserContext();
        ICatalog catalog=getWorkflowModule().getCatalog(context, "GroupeProcessus", project);
        IWorkflow workflow=getWorkflowModule().getWorkflow(context, catalog, "ComptesEtJournauxComptables_1.0");
        IViewController viewcontroller=getWorkflowModule().getViewController(context);
        
//        viewcontroller.addEqualsConstraint("DocumentState", "En Creation");
//        viewcontroller.addInConstraint("DocumentState", "En Creation");
//        viewcontroller.setOrberBy(IProperty.System.CREATION_DATE, String.class, true);
        collection=viewcontroller.evaluate(workflow);
        
        return collection;
        
	} catch (Exception e) {
		e.printStackTrace();
	}
		return collection;
	}
	
	
	@Override
	public boolean onAfterLoad() {
		getAllDocCreation();
		return super.onAfterLoad();
	}
	
//	public boolean onPrepareView(Definition viewDefinition ) {
//		try {
//			
//			Fieldgroup fieldGroup = (Fieldgroup)viewDefinition.getFilters().getFieldgroup();
//			ObjectFactory objFactory = new ObjectFactory();
//			
//			Field fieldNo = (Field)objFactory.createField();
//			fieldNo.setName( "fldUser" ); 
//			fieldNo.setValue( getWorkflowModule().getLoggedOnUser().getProtocolURI() );
//			
//			fieldGroup.getFieldgroupOrField().add( fieldNo ); 
//			}
//		catch( Exception e )
//			{
//				Navigator.getNavigator().processErrors( e, true ); 
//				return false; 
//				}
//		    return super.onPrepareView( viewDefinition ); 
//		    }
	
}
