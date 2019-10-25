package com.vdoc.extra.dd.student.dd08.mail.extension;

import javax.mail.internet.MimeMessage;

import com.axemble.vdoc.sdk.interfaces.ui.definitions.IBlockDefinition;
import com.axemble.vdoc.sdk.mail.extensions.BaseMailExtension;
import com.axemble.vdoc.sdk.structs.MailRecipients;
import com.axemble.vdp.utils.Logger;

/**
 * Created on 24 août 2010
 * @author vdoc
 *
 */

public class UpdateMailExtension extends BaseMailExtension
{
	private static final long serialVersionUID = -798312007403514658L;
	private final static Logger log = Logger.getLogger(UpdateMailExtension.class);

	/**
	 * @see com.axemble.vdoc.sdk.mail.extensions.BaseMailExtension#onPrepare(com.axemble.vdoc.sdk.interfaces.ui.definitions.IBlockDefinition)
	 */
	@Override
	public void onPrepare(IBlockDefinition blockDefinition)
	{
		// TODO Auto-generated method stub
		super.onPrepare(blockDefinition);
	}
	
	/**
	 * @see com.axemble.vdoc.sdk.mail.extensions.BaseMailExtension#beforeSend()
	 */
	@Override
	protected boolean beforeSend()
	{
	    // We get current message
	    MimeMessage currentMsg = getMessage();
		try
		{
		    // See tips on VDoc Dev Floor... ;)
		}
		catch (Exception e)
		{
		    String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in OmitMailSender beforeSend method : " + e.getClass() + " - " + message);
			return false;
		}
		return true;
	}
	
	/**
	 * @see com.axemble.vdoc.sdk.mail.extensions.BaseMailExtension#onFillRecipients(com.axemble.vdoc.sdk.structs.MailRecipients)
	 */
	@Override
	public void onFillRecipients(MailRecipients mailRecipients)
	{
		try
		{
			// TODO You can add a recipient to "mailRecipients" (Internet Address "manager@vdoc.com")
		}
		catch (Exception e)
		{
			String message = e.getMessage();
            if (message == null)
            {
	            message = "";
            }
            log.error("Error in UpdateMailExtension onFillRecipients method : " + e.getClass() + " - " + message);
		}
	    super.onFillRecipients(mailRecipients);
	}
	
	/**
     * @see com.axemble.vdoc.sdk.mail.extensions.BaseMailExtension#afterSend()
     */
    @Override
    protected void afterSend()
    {
        
    }

}
