package com.MoovappsProcess.DemandeConge.document;

import java.util.Collection;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.IAction;
import com.axemble.vdoc.sdk.interfaces.ILinkedResource;
import com.axemble.vdoc.sdk.interfaces.IProperty;
import com.axemble.vdoc.sdk.interfaces.IStorageResource;

public class etape2 extends BaseDocumentExtension {
@Override
public boolean onAfterLoad() {
	// TODO Auto-generated method stub
	//getValue
	String test = (String)getWorkflowInstance().getValue("NomsystemDuChamp");
	IStorageResource donnee = (IStorageResource)getWorkflowInstance().getValue("NomsystemDuChamp(donne)");
	String nom = (String)donnee.getValue("Nomsystemréferentielle");
	getWorkflowInstance().setValue("NomsystemDuChamp", nom);
	
	//get tabl
	Collection<ILinkedResource> table = (Collection<ILinkedResource>)getWorkflowInstance().getValue("nomSysDeTable");
	float montant = 0;
	for (ILinkedResource iLinkedResource : table) {
		montant += (float)iLinkedResource.getValue("NomSysChampDansTablDynamique");
	}
	
	getWorkflowInstance().setValue("montant", montant);
	
	
	
	return super.onAfterLoad();
}
private void onbeforL() {
	// TODO Auto-generated method stub

}
@Override
	public void onPropertyChanged(IProperty property) {
		
	if(property.getName().equals("TableauDesFactures"))
	{
		Collection<ILinkedResource> table = (Collection<ILinkedResource>)getWorkflowInstance().getValue("nomSysDeTable");
		float montant = 0;
		for (ILinkedResource iLinkedResource : table) {
			montant += (float)iLinkedResource.getValue("NomSysChampDansTablDynamique");
		}
		
		getWorkflowInstance().setValue("montant", montant);
	}
	
		super.onPropertyChanged(property);
	}

	@Override
		public boolean onAfterSubmit(IAction action) {
			if(action.getName().equals("NomSystemAction"))
			{
				//code
			}
		
		
			return super.onAfterSubmit(action);
		}
	
	@Override
		public boolean onAfterSave() {
			// TODO Auto-generated method stub
		
			return super.onAfterSave();
		}
	private void onbeforsav() {
		// TODO Auto-generated method stub

	}
   
    	
}
