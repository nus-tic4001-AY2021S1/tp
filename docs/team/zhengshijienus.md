# Zheng Shijie - Project Portfolio Page

## Team Project: ItLogger

IT Logger is a desktop app for managing program issues, and tracking issue resolution progression via a Command Line Interface (CLI).

## Summary of Contributions

### New Feature

* Added the ability to Update defects commands.

  * What it does: allows the user to update any existing defects at a time. This command will allow user to enter updating mode to update a defect and exit until user use “update e/exit” command
  * Justification: This feature improves the product significantly because the any part(deadline, owner, severity etc.) of an defect might be changed. The program shall provide a function to allow user update them accordingly.
  * Highlights: This enhancement doesn’t affects existing command and data structures. It works like a individual function. The implementation achieves decoupling target.

* Added the ability to View defects command.
  * What it does: allows user to view the details of a specific defect in the defect list.
 
### Code contributed: 

* [Link to RepoSense](https://nus-tic4001-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=ZhengShijieNUS&tabRepo=AY2021S1-TIC4001-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

### Enhancements to existing features:

* Upgrade the date format, force to be represented in English and make it compliant with different language system(such as Chinese system) (Pull requests #38)
* Wrote additional tests for existing features to increase coverage (Pull requests #31)
* Added additional parse function to parse commands(Pull requests #38)

### Documentation:

* User Guide:

  * Work together with all teammates. And one of us (Jiancheng) is in charge of submission to teams repo.
  
* Developer Guide: 

  * Work together with all teammates. And one of us (Jiancheng) is in charge of submission to teams repo.
 
 ### Community: 
 
 * PRs reviewed: #7, #31, #38, #53, #55
 * Contributed ideas to discussions during weekly meetings.
 * Reported bugs and suggestions to others codes.(example: DATE format is not compatible with Chinese environmental system issue )
 
 ### Contribution to DG:
 
 * Generate Architecture class diagram
 * Generate IssueList component diagram
 * In charge of description of IssueList component and Common component
 * Generate Update sequence diagram
