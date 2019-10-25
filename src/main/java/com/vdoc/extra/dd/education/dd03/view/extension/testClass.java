package com.vdoc.extra.dd.education.dd03.view.extension;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;

import com.axemble.vdoc.sdk.interfaces.IWorkflowInstance;





public  class testClass extends BaseDocumentExtension  {

	private static final long serialVersionUID = 1L;
public	IWorkflowInstance workf =  getWorkflowInstance();
public testClass() {
	// TODO Auto-generated constructor stub
}
public boolean onAfterLoad() {
	
	workf.setValue("NombreDesJours", "1");

		return super.onAfterLoad();
	}

	

}
		
	


