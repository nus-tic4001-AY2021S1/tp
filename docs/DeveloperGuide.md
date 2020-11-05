# Developer Guide

## Design & implementation

Project Tracker is a desktop app for managing and tracking projects,  optimized for use via a Command Line Interface (CLI). It will help the project manager to track the status of each project such as how many projects have been done per year or within a period.
Here is [UML Diagram V2](https://github.com/AY2021S1-TIC4001-1/tp/blob/master/docs/UML%20V2.drawio) and you can refer to [online website](https://ay2021s1-tic4001-1.github.io/tp/DeveloperGuide.html) .



## Product scope
### Target user profile

The target user is a project manager who can keep tracking various projects, know the progress of each project with  principal members to participate in each project. However, he cannot remember the progress of various projects, who is the member that involve to each specific project and cannot close monitor the progress of each project.

### Value proposition

Project Tracker will help to solve to tracking and managing projects. It will involve person-in-charge, members who involved in each project and the description of each project. Furthermore, the duration of the project will be included in this Project Tracker that helps the project manager to know the timeframe of each project.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v1.0|project manager|create a project|put in the project information|
|v1.0|project manager|add a start and end date to my project| know the timeframe|
|v1.0|project manager|add employees to the project|know who are involved|
|v1.0|project manager|add person_on-charge|know who is leading the project|
|v1.0|project manager|add the description to the project|know what is the project about|
|v1.0|project manager|save my project|keep a record for reference|
|v1.0|project manager|delete my project| reduce the clutter|
|v1.0|project manager|include extra project information |add new member or add extra content in my project information|
|v1.0|project manager|update a project information|change or update the project without re-key in whole project information|
|v1.0|project manager|close the program|exit|
|v2.0|project manager|receive an email notification|remind myself|
|v2.0|project manager|have duration of project|know the how many employees need to assign to this project and save manpower|
|v2.0|project manager|add a status of project|know how many project still in progress or completed|
|v2.0|project manager|add a count down day for project|remind myself|
|v2.0|project manager|know the client|find client's information|
|v2.0|project manager|receive an email notification when the deadline is only left few days|focus on the project first|

## Non-Functional Requirements

* Should work on any Mainstream OS as long as it has Java **11** or above installed.
* *User-Friendly*: there is nice and helpful UI messages should be very easy for the users to use.
* *User-Friendly*: the way to display the project should be easier to read.
* *User-Friendly*: Should load from and save to from a text file

## Glossary

* *Mainstream OS * - Windows, Mac and Linux

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
