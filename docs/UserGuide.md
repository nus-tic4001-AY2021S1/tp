# User Guide

## Introduction

IT Logger is a desktop app for managing program issues, and tracking issue resolution progression via a Command Line Interface (CLI).

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest ITlogger.jar.
3. Copy the file to the folder you want to use as the home folder for your IT Logger.
4. Open the program which should appear in the command prompt with java -jar [Directory of the ITlogger.jar file].


## Features 


|Action| Command Summary|
|--------|----------|
|view | View the issue that is stated with Ticket Number.|
|add  | Adding a new issue.|
|update |Update an existing issue.|
|list |Listing all issues in selected requirement|
|search |Search the issue with conditions|
|delete |Delete an issue that is stated with Ticket Number|
|help |Display the Help Guide|
|exit |Exit the ITlogger program|
|Nil. Not required. If text file itLogger.txt is found, it will preload. |Load issues from text files when program starts|
|exit|Save issues to itLogger.txt when program exits. If none exists, it will create one.|



### Command Summary
 
|Action|Format, Examples|
|--------|----------|
|add|add t/TITLE s/STATUS sv/SEVERITY dl/DEADLINE o/OWNER<br>E.g. add t/cannot submit s/assigned sv/1 dl/20-10-2020 o/Jack |
|update|Update procedure and cmd format:<br>Using the following cmd to enter updating mode:<br>&nbsp;&nbsp;&nbsp;&nbsp;update u/1<br>&nbsp;&nbsp;&nbsp;&nbsp;update u/0 <br>Update the content with the following format: <br>update t/ExampleTitle<br>&nbsp;&nbsp;&nbsp;&nbsp;update sv/2<br>update s/assigned<br>update o/Peter<br>Once finish updating, using:<br>&nbsp;&nbsp;&nbsp;&nbsp;update e/exit <br>to exit update|
|view|view v/TICKET<br>E.g. view v/13451|
|delete|delete d/TICKET<br>E.g. delete d/32231|
|list|list<br>list sv/a<br>list sv/d<br>list dl/a<br>list dl/d<br>list dr/a<br>list dr/d|
|help|help|
|search|search /title /<title><br>search /status /<status><br>search /severity /<severityNum><br>search /owner /<owner><br>search /deadline /<deadlineDateString(dd-MM-yyyy)>|
|exit|exit|


