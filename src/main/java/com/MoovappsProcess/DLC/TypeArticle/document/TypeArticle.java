package com.MoovappsProcess.DLC.TypeArticle.document;

import java.util.Collection;
import java.util.Date;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IProperty;
import com.axemble.vdoc.sdk.interfaces.IViewController;
import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;

public class TypeArticle extends BaseDocumentExtension {
	
	@Override
	public boolean onAfterLoad() {
		
		try {	  	
			   String chrono=getLastReference();
				
				if (chrono == null)
		        {
		             chrono = "0";
		        }		
		        int newChrono = Integer.valueOf(chrono);
		        newChrono = newChrono + 1;
		        getWorkflowInstance().setValue("CodeTypeArticle","TypeArticle."+newChrono);  			
		    
	  } catch (Exception e) {	
		  e.printStackTrace();
	  }
		return super.onAfterLoad();
	}
	
	private String getLastReference()
    {
          try
          {
                IContext context = getWorkflowModule().getSysadminContext();
                IViewController controller = getWorkflowModule().getViewController(context);
                controller.addNotEqualsConstraint(IProperty.System.REFERENCE, getWorkflowInstance().getValue(IProperty.System.REFERENCE));
                controller.setOrderBy(IProperty.System.CREATION_DATE, Date.class, false);
                Collection<IWorkflowInstance> collection = controller.evaluate(getWorkflowInstance().getWorkflow());
                
                if (!collection.isEmpty())
                {
                      IWorkflowInstance instance = collection.iterator().next();
                      String test = (String) instance.getValue("CodeTypeArticle");
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
}
