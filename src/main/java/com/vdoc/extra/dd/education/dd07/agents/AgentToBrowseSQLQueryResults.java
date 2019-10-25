package com.vdoc.extra.dd.education.dd07.agents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.axemble.vdoc.sdk.agent.base.BaseAgent;
import com.axemble.vdoc.sdk.interfaces.IContext;
import com.axemble.vdoc.sdk.interfaces.IJdbcReference;
import com.axemble.vdoc.sdk.modules.IWorkflowModule;
import com.axemble.vdoc.sdk.utils.Logger;

/**
 * @author vmartinon
 *
 */
public class AgentToBrowseSQLQueryResults extends BaseAgent
{
	protected static final Logger log = Logger.getLogger(AgentToBrowseSQLQueryResults.class);

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
			
			// We get the JDBC Reference
			IJdbcReference iJdbcReference = iWorkflowModule.getJdbcReference(iContext, "myRef");
			// From jdbc reference, we get connection
			connection = iJdbcReference.getConnection();
			
			// We initialize a prepared statement with SQL Query
			pstmt = connection.prepareStatement("SELECT customer_name FROM customers");
			// We run the query
			rs = pstmt.executeQuery();
			
			// If we have data, we browse and display customer name in logs
			if (rs.next())
			{
				do
				{
					log.error("Customer name : " + rs.getString(1));
				} 
				while (rs.next());
			}
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
