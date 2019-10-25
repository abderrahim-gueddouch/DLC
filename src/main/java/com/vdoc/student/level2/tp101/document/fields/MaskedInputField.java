package com.vdoc.student.level2.tp101.document.fields;

import org.w3c.dom.Element;

import com.axemble.vdoc.sdk.exceptions.RenderException;
import com.axemble.vdoc.sdk.interfaces.ui.IWritable;
import com.axemble.vdp.ui.core.document.fields.TextBoxField;
import com.axemble.vdp.ui.framework.Constants;
import com.axemble.vdp.ui.framework.document.fields.IAbstractField;
import com.axemble.vdp.ui.framework.template.TemplateFactory;
import com.axemble.vdp.ui.framework.template.TemplateWriter;
import com.axemble.vdp.ui.framework.widgets.components.sys.forms.TextInputComponent;
import com.axemble.vdp.utils.PathUtils;

/**
 * @author vmartinon
 */
public class MaskedInputField extends TextBoxField
{
	/** the default class logger */
	private static com.axemble.vdoc.sdk.utils.Logger LOG = com.axemble.vdoc.sdk.utils.Logger.getLogger(MaskedInputField.class);

	protected String inputMask;
	protected String placeholder = "_";
	protected String inputId;

	/**
	 * @see com.axemble.vdp.ui.core.document.fields.TextBoxField#init(com.axemble.vdp.ui.framework.document.fields.IAbstractField, org.w3c.dom.Element)
	 */
	@Override
	public void init (IAbstractField field, Element element)
	{
		super.init(field, element);

		// TODO Get parameters from element variable (inputMask and placeholder)

		// TODO Add JS dependency to "/resources/dependencies/js/jquery.maskedinput.js"
	}

	/**
	 * @see com.axemble.vdp.ui.framework.widgets.CtlTextBox#render()
	 */
	@Override
	public IWritable render() throws RenderException
	{
		TemplateWriter tw = getTemplateWriter("MaskedInputField.html");
		tw.setEntry("textBoxField", super.render());
		tw.setEntry("inputId", ((TextInputComponent)getInputComponent()).getJavascriptId());

		// TODO Set placeHolder and inputMask into template

		return tw;
	}

	@Override
	public boolean validate (boolean checkMandatory)
	{
		if (isEditable() && isMandatory() && isEmpty())
		{
			inform(getStaticString("LG_MASK_BAD_FORMAT"));
			return false;
		}

		return super.validate(checkMandatory);
	}

	/**
	 * @param relativePath
	 * @return
	 * @throws RenderException
	 */
	protected final TemplateWriter getTemplateWriter(String relativePath) throws RenderException
	{
		com.axemble.vdp.ui.framework.template.Template template = TemplateFactory.create(PathUtils.normalize((new StringBuilder()).append(Constants.DEFAULT_STORAGE).append("/custom/controls/").append(relativePath).toString()));
		return new TemplateWriter(template);
	}

}
