package com.vdoc.extra.dd.student.dd07.agents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.axemble.vdoc.sdk.agent.base.BaseAgent;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.modules.IWorkflowModule;
import com.axemble.vdoc.sdk.utils.Logger;

/**
 * @author vmartinon
 *
 */
public class AgentToBrowseSQLQueryResultsTips extends BaseAgent
{
	protected static final Logger log = Logger.getLogger(AgentToBrowseSQLQueryResultsTips.class);

	/**
	 * @see com.axemble.vdoc.sdk.agent.base.BaseAgent#execute()
	 */
	@Override
	protected void execute()
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			// We get workflow module
			IWorkflowModule iWorkflowModule = getWorkflowModule();
			
			// We get sysadmin context
			IContext iContext = iWorkflowModule.getContextByLogin("sysadmin");
			
			// TODO We get the JDBC Reference from its name
			// TODO From jdbc reference, we get connection
			
			// TODO We initialize a prepared statement with SQL Query
			// TODO We can run this query to get resultset
			
			// TODO If we have data, we browse and display columns values in logs
		}
		catch (Exception e)
		{
			String message = e.getMessage();
            if (message == null)
            {
	            message = "";
            }
            log.error("Error in AgentToBrowseSQLQueryResults execute method : " + e.getClass() + " - " + message);
		}
		finally
		{
			try
			{
				rs.close();
				pstmt.close();
				connection.close();
			}
			catch (Exception e) {}
		}
	}

}
