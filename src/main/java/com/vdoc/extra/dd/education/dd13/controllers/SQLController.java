package com.vdoc.extra.dd.education.dd13.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.axemble.vdoc.sdk.controllers.BaseController;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IJdbcReference;
import com.axemble.vdoc.sdk.interfaces.runtime.IExecutionContext;
import com.axemble.vdoc.sdk.interfaces.runtime.IExecutionContext.IRequest;
import com.axemble.vdoc.sdk.modules.IWorkflowModule;
import com.axemble.vdoc.sdk.utils.Logger;

/**
 * Created on 3 oct. 2011
 * @author vdoc
 *
 */
public class SQLController extends BaseController
{
	protected static final Logger log = Logger.getLogger(SQLController.class);

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
			// 1° : récupérer l'id
			String cli_id = ec.getRequest().getParameter("cli_id");

			// 2° : récupérer adresse
			String adresse = chercheAdresse(cli_id);

			// 3° : écrire dans la sortie
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

			IJdbcReference iJdbcReference = iWorkflowModule.getJdbcReference(iContext, "Formation");
			connectionFormation = iJdbcReference.getConnection();

			preparedStatement = connectionFormation.prepareStatement("SELECT cli_address FROM Client WHERE cli_id = ?");
			preparedStatement.setString(1, cli_id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				cli_address = resultSet.getString("cli_address");
			}
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
