# Adi-Kesava Das's - Project Portfolio Page

## Project: NUS Expenses Tracker (NET)
NET is an app for managing expenses, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

### Contributions to the project.

* Initial Code Skeleton / Baseline([#1](https://github.com/AY2021S1-TIC4001-4/tp/pull/1), [#9](https://github.com/AY2021S1-TIC4001-4/tp/pull/9))

* New Feature: User Interface (UI)([#14](https://github.com/AY2021S1-TIC4001-4/tp/pull/14))
  * What it does: Provides a interface layer for the user to interact with. In this case, it handles the inputs from the user and displays outputs from the application to the user.
  * Justification: This feature is a must have for a command line application. It prompts users on when to input commands and respond accordingly with feedbacks from the application.
  * Credits: Logic and design adapted from addressbook-level2 ([TextUi](https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/ui/TextUi.java))
  
* New Feature: Regular Expressions (Regex) for different commands. E.g. Add, Update, Search, View, 
([#21](https://github.com/AY2021S1-TIC4001-4/tp/pull/21), [#39](https://github.com/AY2021S1-TIC4001-4/tp/pull/39), 
[#43](https://github.com/AY2021S1-TIC4001-4/tp/pull/43), [#45](https://github.com/AY2021S1-TIC4001-4/tp/pull/45)).
  * What it does: User input is parsed and matched by Regex to determine the commands and arguments input
  * Justification: This method of pattern matching of a string allows for optional inputs as well as efficient parsing to isolate arguments for processing. This also allows for good exception handling.
  * Credits: Some minor reference taken from addressbook-level2 ([Parser](https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java]))
     
* New Feature: Added a Search Function ([#21](https://github.com/AY2021S1-TIC4001-4/tp/pull/21))
  * What it does: allows the user to search for transactions with a specified keyword.
  * Justification: This feature is required for the user to view past transactions efficiently.
  * Credits: Logic and design adapted from addressbook-level2 ([FindCommand](https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/commands/FindCommand.java))
  
* New Feature: Added the Category Functionality ([#39](https://github.com/AY2021S1-TIC4001-4/tp/pull/39))
  * What it does: Allows for the user to specify the category for a transaction when adding and or modifying. .
  * Justification: This feature adds value by categorizing transactions with a String tag. This allows for sorting as well as a more insightful look at past transactions.  

* Code contributed: [RepoSense Link](https://nus-tic4001-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=adi-kd0021&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=adi-kd0021&tabRepo=AY2021S1-TIC4001-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)
 
* Project management
  * Managed releases ```v1.0``` - ```v3.0``` (3 releases) on Github
 
* Enhancements to existing features:
  * Enhanced the View functionality to allow filtering to display only transactions of a specified category([#43](https://github.com/AY2021S1-TIC4001-4/tp/pull/43))
  * Enhanced the Update functionality to allow updating of a transactions category.([#45](https://github.com/AY2021S1-TIC4001-4/tp/pull/45))

* Documentation:
  * User Guide:
    * Added documentation for the ```view``` ([#52](https://github.com/AY2021S1-TIC4001-4/tp/pull/52))
    
  * Developer Guide:
    * Added Component diagram. Added plantUML Architecture.puml ([#67](https://github.com/AY2021S1-TIC4001-4/tp/pull/67))
    * Fixed UG and DG grammar, markdowns... ([#68](https://github.com/AY2021S1-TIC4001-4/tp/pull/68))
    * Added SearchCommand Sequence diagram ([#79](https://github.com/AY2021S1-TIC4001-4/tp/pull/79))
    * Added SearchCommand Object diagram. Added NUS Expense Tracker Use Case diagram. ([#84](https://github.com/AY2021S1-TIC4001-4/tp/pull/84)) 
    
* Community:
  * Our application was developed using a combination of pair-programming and adhoc coding sessions. During each session lasting between an hour to four or more, all group members sat in a meeting session while coding/ implementing their assigned components. If a concern was raised or support was needed, they shared their screen via the meeting session and everyone participated or assisted to clear the obstacle. Using this method we've successfully cleared v1.0 to v3.0 of our project milestones. 

* Tools
  * All Regular Expression created with aid of [RegExr](https://regexr.com/) by gskinner.
  * Diagrams drawn using [draw.io / diagrams.net](https://app.diagrams.net/ "Diagram with anyone, anywhere")