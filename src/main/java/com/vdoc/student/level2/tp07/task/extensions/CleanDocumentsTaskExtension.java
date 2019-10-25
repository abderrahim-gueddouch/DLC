package com.vdoc.student.level2.tp07.task.extensions;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.packaging.extensions.BaseTaskExtension;
import com.axemble.vdoc.sdk.utils.Logger;

/**
 * @author vmartinon
 *
 */
public class CleanDocumentsTaskExtension extends BaseTaskExtension
{
	protected static final Logger log = Logger.getLogger(CleanDocumentsTaskExtension.class);

	/**
	 * @see com.axemble.vdoc.sdk.packaging.extensions.BaseTaskExtension#execute(java.util.Map)
	 */
	@Override
	protected void execute(Map<String, String> parameters) throws Exception
	{
		try
		{
			IContext iContext = getWorkflowModule().getContextByLogin("sysadmin");
			
			// TODO We begin transaction
			
			// TODO We get delay from input parameters
			int delay = 0;
			
			// We compute the date with delay with a gregorian calendar
			Date now = new Date();
			Calendar cal = new GregorianCalendar();
			cal.setTime(now);
			cal.add(Calendar.DAY_OF_YEAR, 0 - delay);
			
			// TODO We browse projects and catalogs in these projects (only workflow catalogs !)
			
			// For each catalog
				// TODO We get a view controller
				// TODO We put constraints on IProperty.System.CREATION_DATE and IProperty.System.PAST_OPERATORS
					//Tip : filter on date must be done by a TimeStamp
				// TODO We evaluate on catalog and browse workflow instances and delete
			
			// TODO We commit transaction
		}
		catch (Exception e)
		{
			String message = e.getMessage();
            if (message == null)
            {
	            message = "";
            }
            log.error("Error in CleanDocumentsTaskExtension execute method : " + e.getClass() + " - " + message);
            
            // We rollback transaction
            getWorkflowModule().rollbackTransaction();
		}
	}

}
