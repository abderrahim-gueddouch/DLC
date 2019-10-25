
package com.vdoc.student.level2.tp13.document.fields;

import org.w3c.dom.Element;

import com.axemble.vdoc.sdk.document.fields.base.BaseField;
import com.axemble.vdoc.sdk.exceptions.RenderException;
import com.axemble.vdoc.sdk.interfaces.ui.IWritable;
import com.axemble.vdoc.sdk.utils.Logger;
import com.axemble.vdp.ui.framework.widgets.CtlButton;

/**
 * Created on 13 sept. 2010
 * @author vdoc
 * 
 */
public class ButtonField extends BaseField
{
    /**
     * 
     */
    private static final long serialVersionUID = 6118754220396098914L;

	protected static final Logger log = Logger.getLogger(ButtonField.class);
    
    protected String className = "no-class";
    protected String methodName = "no-method";
    protected String buttonName = "no-name";

    /**
     * @see com.axemble.vdoc.sdk.document.fields.base.BaseField#init(org.w3c.dom.Element)
     */
    @Override
    public void init(Element element)
    {
        // We get configuration values from XML element
        // We store in class as attributes so that we can use it in "render" method
        // TODO you can attributes (className, methodName, buttonName), coming from XML element you have in parameter
    }

    /**
     * @see com.axemble.vdoc.sdk.document.fields.base.BaseField#updateControl()
     */
    @Override
    public void updateControl()
    {
        
    }

    /**
     * @see com.axemble.vdoc.sdk.document.fields.base.BaseField#updateValue()
     */
    @Override
    public void updateValue()
    {
        
    }

    /**
     * @see com.axemble.vdp.ui.framework.widgets.CtlInputWidget#isEmpty()
     */
    @Override
    public boolean isEmpty()
    {
        return false;
    }

    /**
     * @see com.axemble.vdp.ui.framework.foundation.Widget#render()
     */
    @Override
    public IWritable render() throws RenderException
    {
        CtlButton OKButton = null;
        // Here you can just create your display
        // TODO So, you can create a new button and associate an action listener
        // TODO On the "onClink" on your listener, you can navigate to the screen : class.method
        /*
         *      NavigateContext ctx = new NavigateContext();
                ctx.setClassName(className);
                ctx.setMethodName(methodName);
                Navigator.getNavigator().navigate(ctx);
         */
        return OKButton;
    }

}
