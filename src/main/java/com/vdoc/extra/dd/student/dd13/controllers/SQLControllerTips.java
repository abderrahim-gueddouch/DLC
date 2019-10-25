package com.vdoc.extra.dd.student.dd13.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.axemble.vdoc.sdk.controllers.BaseController;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.runtime.IExecutionContext;
import com.axemble.vdoc.sdk.interfaces.runtime.IExecutionContext.IRequest;
import com.axemble.vdoc.sdk.modules.IWorkflowModule;
import com.axemble.vdoc.sdk.utils.Logger;

/**
 * Created on 3 oct. 2011
 * @author vdoc
 *
 */
public class SQLControllerTips extends BaseController
{
	protected static final Logger log = Logger.getLogger(SQLControllerTips.class);

	/**
	 * @see com.axemble.vdp.ui.framework.foundation.controllers.CustomController#parseRequest(com.axemble.vdp.ui.framework.runtime.IExecutionContext.IRequest)
	 */
	public void parseRequest(IRequest iRequest) throws IOException
	{
		
	}

	/**
	 * @see com.axemble.vdoc.sdk.controllers.BaseController#doProcess(com.axemble.vdp.ui.framework.runtime.IExecutionContext)
	 */
	@Override
	public IExecutionContext doProcess(IExecutionContext ec) throws IOException
	{
		try
		{
			// 1° : Get Customer id
			String cli_id = "";
			// TODO We can get "cli_id" from request : ec.getRequest().getParameter(...)
			
			// 2° : Get Address
			String adresse = chercheAdresse(cli_id);

			// 3° : Write address in output
			OutputStream outputStream = ec.getResponse().getOutputStream();
			outputStream.write(adresse.getBytes());
		}
		catch (Exception e)
		{
			String message = e.getMessage();
            if (message == null)
            {
	            message = "";
            }
            log.error("Error in SQLController doProcess method : " + e.getClass() + " - " + message);
		}
		return ec;
	}

	/**
	 * 
	 * @param cli_id
	 * @return
	 */
	private String chercheAdresse(String cli_id)
	{
		String cli_address = "Client inconnu";
		Connection connectionFormation = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try
		{
			IWorkflowModule iWorkflowModule = getWorkflowModule();
			IContext iContext = iWorkflowModule.getContextByLogin("sysadmin");

			//TODO We get external reference (IJdbcReference) from workflow module
			//TODO On this external reference, we can get connection
			
			//TODO We build the prepared statement : connecion.prepareStatement(SQLQUERY)
			//Tip :
			//	Don't forget to set your parameters in this statement
			
			// TODO We evaluate query and get a resultset (preparedStatement.executeQuery())
			
			// TODO We get cli_address field in resultset
		}
		catch (Exception e)
		{
			String message = e.getMessage();
            if (message == null)
            {
	            message = "";
            }
            log.error("Error in SQLController chercheAdresse method : " + e.getClass() + " - " + message);
		}
		finally
		{
			try
			{
				if (resultSet != null)
				{
					resultSet.close();
				}
				if (preparedStatement != null)
				{
					preparedStatement.close();
				}
				if (connectionFormation != null)
				{
					connectionFormation.close();
				}
			}
			catch (Exception e)
			{

			}
		}
		return cli_address;
	}

}
