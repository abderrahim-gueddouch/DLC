package com.MoovappsProcess.ReglementFournisseur.document;

import java.util.Collection;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.ILinkedResource;
import com.axemble.vdoc.sdk.interfaces.IProperty;


public class ReglementFournisseur extends BaseDocumentExtension {
	
	@Override
	public void onPropertyChanged(IProperty property) {
		
		if(property.getName().equals("CoursNegocie")){
			
		if(getWorkflowInstance().getValue("CoursMoyen")!=null && getWorkflowInstance().getValue("CoursNegocie") != null)
		{
			Number coursmoyen =(Number) getWorkflowInstance().getValue("CoursMoyen");
			Number coursnegocier = (Number) getWorkflowInstance().getValue("CoursNegocie");
			
			if(coursnegocier.floatValue() > coursmoyen.floatValue())
			{
				getWorkflowInstance().setValue("CoursNegocie", 0);
			}		
		}
		}
		if(property.getName().equals("TableauDesFactures")){
			
			Collection<ILinkedResource> table = (Collection<ILinkedResource>)getWorkflowInstance().getValue("TableauDesFactures");
			float sommemontantdhs = 0;
			float sommemontantdevise = 0;
			if(!table.isEmpty())
			{
				for(ILinkedResource iLinkedResource : table){
					Number sommemontant = (Number)iLinkedResource.getValue("MontantTotal");
					Number sommedevise = (Number)iLinkedResource.getValue("MontantDevise");
					if(sommemontant != null && sommedevise != null)
					{
						sommemontantdhs += sommemontant.floatValue();
						sommemontantdevise += sommedevise.floatValue();
					}		
				}
			}	
			getWorkflowInstance().setValue("MontantDhs", sommemontantdhs);
			getWorkflowInstance().setValue("MontantDevise", sommemontantdevise);
		} 
		super.onPropertyChanged(property);
	}
	
	
}
