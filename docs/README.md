# User Guide
Duke is a desktop application for task management, 
optimized for use via a Command Line Interface (CLI). 
If you like typing, Duke is the perfect task management application
for you. Duke can manage your todo tasks, deadlines and events quickly.

## Features 

### Add a task
You can add a todo task, a deadline or an event to your task list.

### Mark a task as done
Your tasks will be marked as undone by default. When you have completed 
your task, you can mark it as done.

### Delete a task
When a task is outdated or unnecessary, you can delete it permanently 
from the task list.

### List your tasks
To see all your tasks, you can list all your tasks for viewing. 
If you only want to check on your deadlines or events on a specific
date, this is also possible.

### Find tasks
In case your list of tasks is too long, you can find tasks using
keywords to find your tasks more easily. 

### Save your list
Whenever you make changes to your task list, these changes will
automatically be saved to a txt file named `duke.txt`.

## Usage

### `todo DESCRIPTION` - Add a Todo
Substituting `DESCRIPTION` with any todo action, you can add a todo
to your task list.

Example of usage: 
`todo do laundry`

Expected outcome:

`Okay! I added:`

`[T][N] do laundry`

`Now you have 2 tasks in the list!`

### `deadline DESCRIPTION /by YYYY-MM-DD` - Add a Deadline
Substituting `DESCRIPTION` with any action and `YYYY-MM-DD` with 
a date, you can add a deadline to your list.

Example of usage: 
`deadline CS2113T project /by 2020-10-10`

Expected outcome:

`Okay! I added:`

`[D][N] project (by: Oct 10 2020)`

`Now you have 2 tasks in the list!`

### `event DESCRIPTION /at YYYY-MM-DD` - Add an Event
Substituting `DESCRIPTION` with any action and `YYYY-MM-DD` with 
a date, you can add an event to your list.

Example of usage: 
`event CS2113T project meeting /at 2020-09-10`

Expected outcome:

`Okay! I added:`

`[E][N] project meeting (at: Sep 10 2020)`

`Now you have 3 tasks in the list!`

### `done TASK_NUMBER` - Mark a task as done
When you have completed the task, you can mark a task as done by 
just referring to the task number. 

Example of usage:
`done 3`

Expected outcome:

`Good job! You've completed:`

`[T][Y] do laundry`

### `delete TASK_NUMBER` - Delete a task
Similarly, when you delete a task, you can do it by referring to a 
task by its task number.

Example of usage: 
`done 1`

Expected outcome:

`Alright lazy bum... I'll delete this:`

`[T][N] do laundry`

`Now you have 2 tasks in your list!`

### `list` - List all tasks
A simple command to list all of your tasks.

Example of usage:
`list`

Expected outcome:

`These are the tasks you have now!`

`1. [T][N] do laundry`

`2. [D][N] project (by: Oct 10 2020)`

`3. [E][N] project meeting (at: Sep 10 2020)`

### `list DATE` - List tasks according to date
`DATE` must be in the format `YYYY-MM-DD`. This lists all the
deadlines / events associated with the date.

Example of usage: 
`list 2020-10-10`

Expected outcome:

`These are the tasks you have on: Oct 10 2020`

`1. [D][N] project (by: Oct 10 2020)`

### `find KEYWORD` - Find a task
Replacing `KEYWORD` with any keyword, Duke will list all tasks
with the corresponding keyword.

Example of usage: 
`find project`

Expected outcome:

`These are the tasks you have with the keyword: project`

`1. [D][N] project (by: Oct 10 2020)`

`2. [E][N] project meeting (at: Sep 10 2020)`