
function onCreateTaskInstance(workflowModule, taskInstance)
{
	// We test we are on the right task
	// Warning : this method is called for all tasks !
	if ( (taskInstance != null) && (taskInstance.getTask().getName().equals("SecondTask")) )
	{
		// We get current workflow instance from task instance
		var iWorkflowInstance = taskInstance.getWorkflowInstance();
		
		// We get date value
		// TODO Get value from iWorkflowInstance
		// TODO Test if value got is not empty
		
		// We set max end date for our new task instance
		// TODO : you have a method on task instance...
		taskInstance.setMaxEndedDate(...);
	}
}
