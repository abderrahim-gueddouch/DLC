package com.MoovapssProcess.DLC.FicheTechnique.document;

import java.util.Collection;
import java.util.Date;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.ICatalog;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IProject;
import com.axemble.vdoc.sdk.interfaces.IProperty;
import com.axemble.vdoc.sdk.interfaces.IViewController;
import com.axemble.vdoc.sdk.interfaces.IWorkflow;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;

public class CreationFtFicheEssai extends BaseDocumentExtension{

	@Override
	public boolean onAfterLoad() {
		
		IWorkflowInstance instance =getLastFicheDEssai();
		if(instance != null){
		getWorkflowInstance().setValue("ClientService", instance.getValue("ClientService"));
		getWorkflowInstance().setValue("ClientSD", instance.getValue("ClientSD"));
		getWorkflowInstance().setValue("NomContact", instance.getValue("NomContact"));
		getWorkflowInstance().setValue("NTel", instance.getValue("NTel"));
		getWorkflowInstance().setValue("NouveauProduit", instance.getValue("NouveauProduit"));
		getWorkflowInstance().setValue("EchantillonSouhaite", instance.getValue("EchantillonSouhaite"));
		getWorkflowInstance().setValue("TypeDeFeuille", instance.getValue("TypeDeFeuille"));
		getWorkflowInstance().setValue("DiametreMandrine", instance.getValue("DiametreMandrine"));
		getWorkflowInstance().setValue("Couleur", instance.getValue("Couleur"));
		getWorkflowInstance().setValue("DiametrePoidsMaxBobine", instance.getValue("DiametrePoidsMaxBobine"));
		getWorkflowInstance().setValue("Epaisseur", instance.getValue("Epaisseur"));
		getWorkflowInstance().setValue("laize", instance.getValue("laize"));
		getWorkflowInstance().setValue("NatureEtMotifDeLaDemande", instance.getValue("NatureEtMotifDeLaDemande"));
		getWorkflowInstance().setValue("FicheDeConceptionEtDeveloppement", instance.getValue("FicheDeConceptionEtDeveloppement"));
		getWorkflowInstance().setValue("NFicheDeConception", instance.getValue("NFicheDeConception"));
		getWorkflowInstance().setValue("DateDuTest", instance.getValue("DateDuTest"));
		getWorkflowInstance().setValue("NLot", instance.getValue("NLot"));
		getWorkflowInstance().setValue("ChefDEquipe", instance.getValue("ChefDEquipe"));
		getWorkflowInstance().setValue("NombreDeFraction", instance.getValue("NombreDeFraction"));
		getWorkflowInstance().setValue("QuantitesProduites", instance.getValue("QuantitesProduites"));
		getWorkflowInstance().setValue("Couleur2", instance.getValue("Couleur2"));
		getWorkflowInstance().setValue("RefMB1", instance.getValue("RefMB1"));
		getWorkflowInstance().setValue("RefMB2", instance.getValue("RefMB2"));
		getWorkflowInstance().setValue("RefMB3", instance.getValue("RefMB3"));
		getWorkflowInstance().setValue("Antistatique", instance.getValue("Antistatique"));
		getWorkflowInstance().setValue("LongeurMandrin", instance.getValue("LongeurMandrin"));
		getWorkflowInstance().setValue("ChocCrystal", instance.getValue("ChocCrystal"));
		getWorkflowInstance().setValue("MB1", instance.getValue("MB1"));
		getWorkflowInstance().setValue("MB2", instance.getValue("MB2"));
		getWorkflowInstance().setValue("MB3", instance.getValue("MB3"));
		getWorkflowInstance().setValue("AideProcessing", instance.getValue("AideProcessing"));
		getWorkflowInstance().setValue("DechetsGenerer", instance.getValue("DechetsGenerer"));
		getWorkflowInstance().setValue("ReferenceMP", instance.getValue("ReferenceMP"));
		getWorkflowInstance().setValue("FicheDEssaiModifiee", instance.getValue("FicheDEssaiModifiee"));
		getWorkflowInstance().save(getWorkflowModule().getLoggedOnUserContext());
		}
		
		if(getWorkflowInstance().getValue("FicheOuiNon").equals("Oui"))
		{
			Number diametre=(Number)instance.getValue("DiametrePoidsMaxBobine");
			String d=diametre.toString();
			Number mandrin=(Number)instance.getValue("DiametreMandrine");
			String m=mandrin.toString();
			
			getWorkflowInstance().setValue("Client", instance.getValue("ClientSD"));
			getWorkflowInstance().setValue("Epaisseur2", instance.getValue("Epaisseur"));
			getWorkflowInstance().setValue("Laize2", instance.getValue("laize"));
			getWorkflowInstance().setValue("DiametreBobine", d);
			getWorkflowInstance().setValue("Mandrin", m);
		}

		return super.onAfterLoad();
	}
	
	private IWorkflowInstance getLastFicheDEssai(){
		
		IWorkflowInstance instance = null;
		try
		{
			
			Collection<IWorkflowInstance> instances = (Collection<IWorkflowInstance>) getWorkflowInstance().getParentInstance().getLinkedWorkflowInstances("FicheDEssai");
			Date date = null;
			for (IWorkflowInstance iWorkflowInstance : instances) 
			{
				if(instance != null)
				{
					date = instance.getCreatedDate();
					if(date.before(iWorkflowInstance.getCreatedDate()))
					{
						instance = iWorkflowInstance;
					}
				}
				else
				{
					instance = iWorkflowInstance;
				}
			}
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		return instance;
	}
	
	
}
