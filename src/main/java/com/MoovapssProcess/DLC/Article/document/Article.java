package com.MoovapssProcess.DLC.Article.document;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.exceptions.LibraryModuleException;
import com.axemble.vdoc.sdk.exceptions.WorkflowModuleException;
import com.axemble.vdoc.sdk.interfaces.ICatalog;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IFile;
import com.axemble.vdoc.sdk.interfaces.IFolder;
import com.axemble.vdoc.sdk.interfaces.IOptionList.IOption;
import com.axemble.vdoc.sdk.interfaces.IAction;
import com.axemble.vdoc.sdk.interfaces.IOrganization;
import com.axemble.vdoc.sdk.interfaces.IProject;
import com.axemble.vdoc.sdk.interfaces.IProperty;
import com.axemble.vdoc.sdk.interfaces.IResourceController;
import com.axemble.vdoc.sdk.interfaces.IResourceDefinition;
import com.axemble.vdoc.sdk.interfaces.ITask;
import com.axemble.vdoc.sdk.interfaces.IViewController;
import com.axemble.vdoc.sdk.interfaces.IWorkflow;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;
import com.axemble.vdoc.sdk.interfaces.ui.IWidget;
import com.axemble.vdoc.sdk.modules.ILibraryModule;
import com.axemble.vdoc.sdk.modules.IWorkflowModule;
import com.axemble.vdp.ui.framework.runtime.NamedContainer;
import com.axemble.vdp.ui.framework.widgets.CtlButton;

public class Article extends BaseDocumentExtension {
	
	IWorkflowModule workflowModule = null;
	IOrganization organization = null;
	IProject project = null;
	IContext sysContext=null;
	ICatalog catalog = null;	 
	
	@Override
	public boolean onAfterLoad() {
		
		ArrayList<IOption> array=new ArrayList<IOption>();
		Collection<IWorkflowInstance> collection=getAllTypeArticle();
		
		if(getWorkflowInstance().getValue("TypeDArticle")==null)
		{
		  for (IWorkflowInstance iWorkflowInstance : collection) 
		  {
			String code=(String)iWorkflowInstance.getValue("CodeTypeArticle");
			String type=(String)iWorkflowInstance.getValue("TypeArticle");
			array.add(getWorkflowModule().createListOption(code, type));
		   }
		}
		
		getWorkflowInstance().setList("TypeDArticle", array);
	 	return super.onAfterLoad();
	}
	
	@Override
	public void onPropertyChanged(IProperty property) {
		
		if(property.getName().equals("TypeDArticle"))
		{		
			try {	
				    if(getWorkflowInstance().getValue("TypeDArticle") != null)
				    {	
					   String chrono=getLastReference((String)getWorkflowInstance().getValue("TypeDArticle"));
						
						if (chrono == null)
				        {
				             chrono = "0";
				        }

				        int newChrono = Integer.valueOf(chrono);
				        newChrono = newChrono + 1;
				        getWorkflowInstance().setValue("CodeArticle",getWorkflowInstance().getValue("TypeDArticle")+"Article."+newChrono);
				        
						} 
		       	
			  } catch (Exception e) {	
				  e.printStackTrace();
			  }
			
			
		}
		super.onPropertyChanged(property);
	}

	private String getLastReference(String typeArticle)
    {
          try
          {
                IContext context = getWorkflowModule().getSysadminContext();
                IViewController controller = getWorkflowModule().getViewController(context);
                controller.addNotEqualsConstraint(IProperty.System.REFERENCE, getWorkflowInstance().getValue(IProperty.System.REFERENCE));
                controller.addEqualsConstraint("TypeDArticle", typeArticle);
                controller.setOrderBy(IProperty.System.CREATION_DATE, Date.class, false);
                Collection<IWorkflowInstance> collection = controller.evaluate(getWorkflowInstance().getWorkflow());
                if (!collection.isEmpty())
                {
                      IWorkflowInstance instance = collection.iterator().next();
                      String test = (String) instance.getValue("CodeArticle");
                      if (test != null)
                      {
                            String[] s = test.split("\\.");
                            String a = s[s.length-1];
                            return a;
                      }
                      else
                            return null;
                }
                return "0";
          }
          catch (Exception e)
          {
                e.printStackTrace();
          }
          return null;
    }
	
	private Collection<IWorkflowInstance> getAllTypeArticle(){
		
		Collection<IWorkflowInstance> collection=null;
		try{
		IContext sysContext = getWorkflowModule().getSysadminContext();
	    IOrganization organization = getDirectoryModule().getOrganization(sysContext, "DefaultOrganization");
		IProject project=getProjectModule().getProject(sysContext, "WIZEDLC", organization);
		IContext context=getWorkflowModule().getLoggedOnUserContext();
		ICatalog catalog= getWorkflowModule().getCatalog(context, "GroupeProcessus", project);
		IWorkflow workflow=getWorkflowModule().getWorkflow(context, catalog, "TypeArticle_1.0");
		IViewController viewController=getWorkflowModule().getViewController(context);
	    collection = viewController.evaluate(workflow);
		
		return collection;
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
		return collection;
		}
	
// private ArrayList<IOption> GetRessourceDefinitionByCatalog(ICatalog catalog)
//	{
//		try
//		{			
//			ArrayList<IOption> OptionRessources = new ArrayList<IOption>();
//			Collection<IResourceDefinition> resourceDefinitions = (Collection<IResourceDefinition>) getWorkflowModule().getResourceDefinitions(sysContext, catalog);
//			for (IResourceDefinition iResourceDefinition : resourceDefinitions)
//			{
//				boolean nameMatiere = iResourceDefinition.getName().contains("Matiere Premiere_1.0");
//				boolean labelMatiere = iResourceDefinition.getLabel().contains("Matiere Premiere");
//				boolean nameAdditif = iResourceDefinition.getName().contains("AdditifConsommableEmballage_1.0");
//				boolean labelAdittif = iResourceDefinition.getLabel().contains("Additif Consommable Emballage");
//				boolean nameService = iResourceDefinition.getName().contains("ServicesAbonnement_1.0");
//				boolean labelService = iResourceDefinition.getLabel().contains("Services Abonnement");
//				boolean namePiece = iResourceDefinition.getName().contains("PieceDeRechange_1.0");
//				boolean labelPiece = iResourceDefinition.getLabel().contains("Piece De Rechange");
//				boolean nameProduit = iResourceDefinition.getName().contains("ProduitFiniSemiFini_1.0");
//				boolean labelProduit = iResourceDefinition.getLabel().contains("Produit Fini Semi Fini");
//				boolean nameImmobilisation = iResourceDefinition.getName().contains("Immobilisation_1.0");
//				boolean labelImmobilisation = iResourceDefinition.getLabel().contains("Immobilisation");
//				
//				if(nameMatiere || labelMatiere || nameAdditif || labelAdittif || nameService || labelService || namePiece || labelPiece || nameProduit || labelProduit
//						|| nameImmobilisation || labelImmobilisation	)
//				{	
//				   OptionRessources.add(getWorkflowModule().createListOption(iResourceDefinition.getName(),iResourceDefinition.getLabel() ));
//				}
//			}
//			return OptionRessources;
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		return null;		
//	}
}
