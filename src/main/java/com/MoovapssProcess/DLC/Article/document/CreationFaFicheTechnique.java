package com.MoovapssProcess.DLC.Article.document;

import java.util.Collection;
import java.util.Date;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;

public class CreationFaFicheTechnique extends BaseDocumentExtension{

	@Override
	public boolean onAfterLoad() {
		
		IWorkflowInstance instance =getLastFicheDEssai();
		if(instance != null){
		getWorkflowInstance().setValue("ReferenceOffsetPolyplast", instance.getValue("ReferenceOffsetPolyplast"));
		getWorkflowInstance().setValue("Denomination", instance.getValue("Denomination"));
		getWorkflowInstance().setValue("Client", instance.getValue("Client"));
		getWorkflowInstance().setValue("ClientSpecifique", instance.getValue("ClientSpecifique"));
		getWorkflowInstance().setValue("Matiere", instance.getValue("Matiere"));
		getWorkflowInstance().setValue("Recycle", instance.getValue("Recycle"));
		getWorkflowInstance().setValue("MasterBatch", instance.getValue("MasterBatch"));
		getWorkflowInstance().setValue("NDePantone", instance.getValue("NDePantone"));
		getWorkflowInstance().setValue("Densite", instance.getValue("Densite"));
		getWorkflowInstance().setValue("Grammage", instance.getValue("Grammage"));
		getWorkflowInstance().setValue("Metrage", instance.getValue("Metrage"));
		getWorkflowInstance().setValue("Cadence", instance.getValue("Cadence"));
		getWorkflowInstance().setValue("Epaisseur2", instance.getValue("Epaisseur2"));
		getWorkflowInstance().setValue("Laize2", instance.getValue("Laize2"));
		getWorkflowInstance().setValue("DiametreBobine", instance.getValue("DiametreBobine"));
		getWorkflowInstance().setValue("RetraitTransversale", instance.getValue("RetraitTransversale"));
		getWorkflowInstance().setValue("RetraitLongitudinal", instance.getValue("RetraitLongitudinal"));
		getWorkflowInstance().setValue("IndiceOndulation", instance.getValue("IndiceOndulation"));
		getWorkflowInstance().setValue("EffetDeSabre", instance.getValue("EffetDeSabre"));
		getWorkflowInstance().setValue("PremiereSpire", instance.getValue("PremiereSpire"));
		getWorkflowInstance().setValue("DerniereSpire", instance.getValue("DerniereSpire"));
		getWorkflowInstance().setValue("JonctionPapierKraft", instance.getValue("JonctionPapierKraft"));
		getWorkflowInstance().setValue("Mandrin", instance.getValue("Mandrin"));
		getWorkflowInstance().setValue("ProtectionBobine", instance.getValue("ProtectionBobine"));
		getWorkflowInstance().setValue("TypeDePalette", instance.getValue("TypeDePalette"));
		getWorkflowInstance().setValue("NDeBobinePalette", instance.getValue("NDeBobinePalette"));
		getWorkflowInstance().setValue("DemensionPalette", instance.getValue("DemensionPalette"));
		getWorkflowInstance().setValue("HauteurDeLaBobine", instance.getValue("HauteurDeLaBobine"));
		getWorkflowInstance().setValue("PoidsDeBobinePalette", instance.getValue("PoidsDeBobinePalette"));
		getWorkflowInstance().setValue("ProtectionPalette", instance.getValue("ProtectionPalette"));
		getWorkflowInstance().setValue("DureeDeVie", instance.getValue("DureeDeVie"));
		getWorkflowInstance().setValue("Humidite", instance.getValue("Humidite"));
		getWorkflowInstance().setValue("Tamperature", instance.getValue("Tamperature"));
		getWorkflowInstance().setValue("Lumiere", instance.getValue("Lumiere"));
		getWorkflowInstance().setValue("FicheTechniqueModifiee", instance.getValue("FicheTechniqueModifiee"));
		getWorkflowInstance().save(getWorkflowModule().getLoggedOnUserContext());
		}

		return super.onAfterLoad();
	}
	
	private IWorkflowInstance getLastFicheDEssai(){
		
		IWorkflowInstance instance = null;
		try
		{
			
			Collection<IWorkflowInstance> instances = (Collection<IWorkflowInstance>) getWorkflowInstance().getParentInstance().getLinkedWorkflowInstances("FicheTechnique");
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
