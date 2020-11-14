# Dai Wei's Project Portfolio Page

## Team Project: Money Tracker 

### **Overview**

Money Tracker is a CLI application that allows users to keep track of their income and expenses. Users can record their inflows and outflows of money and view summary reports of their transactions. 

### **Summary of Contributions**

Given below are my contributions to the project.

- **New Feature:** Added the ability `report MONTH` to view monthly transactions summary.
  - What it does: allows the user to view their monthly report, include total income/expenses amount, monthly balance, average expense per day, highest income/expense transaction, and list income/expense category report by the highest amount and most frequency.
  - Justification: This feature improves the product significantly because a user can get deep insight and understand their monthly income and expense transactions detail in a concise and clear way.
  - Highlights: This enhancement needs to determine what category each transaction belongs to first, then use different category names as keys, and the sum of each transaction in the same category as the value to hashmap them and sort by value. Another function uses the number of transaction frequency in the same category as the value to hashmap and sort. And the clearly present the result in the monthly report.

- **New Feature:** Added the ability `report` to view last six months transactions summary.
  - What it does: allows the user to view their last six months report, include every month total income /   expense transaction in last six month.
  - Justification: This feature improves the product significantly because a user can get overall insight and understand their last six month income and expense transactions detail in a concise and clear way.
  - Highlights: This enhancement has tried to reuse some methods from the monthly reports to reduce code coupling and make the code DRY. It helps us easier to debug and test our code.

- **Code contributed:** [tp daiweinus RepoSense link](https://nus-tic4001-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=daiweinus&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=zoom&zA=daiweinus&zR=AY2021S1-TIC4001-2%2Ftp%5Bmaster%5D&zACS=166.28571428571428&zS=2020-08-14&zFS=daiweinus&zU=2020-11-09&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

- **Enhancements implemented:**
  - As a user I can add an income/expense category (Pull requests [#5](https://github.com/AY2021S1-TIC4001-2/tp/issues/5), [#6](https://github.com/AY2021S1-TIC4001-2/tp/issues/6))
  - As a user I can view a summary report of my income/expenses for any months  (Pull requests [#18](https://github.com/AY2021S1-TIC4001-2/tp/issues/18))
  - As a user I can view a summary report of the past 6 months (Pull requests [#82](https://github.com/AY2021S1-TIC4001-2/tp/issues/82))

- **Documentation:**
  - User Guide: Added documentation for the features `report` and `report MONTH` [#50](https://github.com/AY2021S1-TIC4001-2/tp/issues/50)
  - Developer Guide: Added implementation details of the  `report` and `report MONTH` feature [#130](https://github.com/AY2021S1-TIC4001-2/tp/issues/130)

- **Community**
  - PRs reviewed: add comments.
  - Updating user/developer docs that are not specific to a feature.
  - Brainstorming and update product scope, user stories, non functional requirements with team every weekend.
