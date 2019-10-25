/**
* This method is called each time the system requires the operators of the current task instance.
* To set the operators, use the 'list' object.
*
* @param workflowModule The workflow module object
* @param taskInstance The current task instance
* @param list The list of operators to complete
*/
function onEvaluateOperators(workflowModule, taskInstance, list)
{
	// TODO We test we are creating task instance for task 2
    
    // We get workflow instance from task instance
	var iWorkflowInstance = taskInstance.getWorkflowInstance();
	
	// TODO We get value from previous list (using workflowinstance)
	// TODO If necessary, we add the user "aarto" to the list
}