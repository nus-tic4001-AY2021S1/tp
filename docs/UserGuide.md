# User Guide

## Introduction

Money Tracker is a CLI application that allows users to keep track of their income and expenses.
Users can record their inflows and outflows of money and then view monthly reports of these movements of money.

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `MoneyTracker.jar` [here](https://github.com/AY2021S1-TIC4001-2/tp/releases).
3. Copy `MoneyTracker.jar` to the folder you want to use as the home folder for Money Tracker.
4. Navigate to the home folder for Duke on your CLI program.
5. Launch Money Tracker by running the command `java -jar MoneyTracker.jar`.
6. Type the command in the CLI program and press `Enter` to execute it.
<br>Some example commands you can try:
    - `addcate lunch`: Adds an expense category.
    - `listcat /te`: Lists all expense categories.
    - `adde /a3.50 /cFood /d2020-09-20 /eLunch with boss.`: Adds an expense.
    - `list /te /m2020-09 /cFood`: Lists all Food expenses in Sep 2020.
    - `report 2020-09`: Displays summary report for Sep 2020.
7. Refer to **Features** below for details of each command.

## Features 

Notes about the command format:
- Words in UPPER_CASE are the parameters to be supplied by the user.<br />
E.g. `DESCRIPTION` in the command `addi /aAMOUNT /cINCOME_CATEGORY [/dDATE] [/eDESCRIPTION]`.
- Parameters in square brackets are optional.<br />
E.g. `TYPE` in the command `listcat [/tTYPE]` can be omitted.
- Parameters can be in any order.<br />
E.g. The command `list [/tTYPE] [/mMONTH] [/cCATEGORY]` can also be in this form: `list [/mMONTH] [/tTYPE] [/cCATEGORY]`.

### Listing all income / expense categories: `listcat`
Shows a list of all income and/or expense categories.

Format: `listcat [/tTYPE]`

- `TYPE` must either be the letter `i` or `e`. If `TYPE` is `i`, only income categories will be
listed. Similarly, if `TYPE` is `e`, only expense categories will be listed. If `TYPE` is
omitted, both income and expense categories will be listed.

Example of usage: `listcat /te`

Expected outcome:
```
Here are your expense categories:
  1. [E] FOOD
  2. [E] RENT
  3. [E] ENTERTAINMENT
```
Example of usage: `listcat`

Expected outcome:
```
Here are your categories:
  1. [I] SALARY
  2. [I] DIVIDEND
  3. [E] FOOD
  4. [E] RENT
  5. [E] ENTERTAINMENT
```

### Listing incomes / expenses: `list`
Shows a list of incomes / expenses.

Format:  `list [/tTYPE] [/mMONTH] [/cCATEGORY]` <br>

- `TYPE` must either be the letter `i` or `e`. If `TYPE` is `i`, only incomes will be listed.
Similarly, if `TYPE` is `e`, only expenses will be listed. If `TYPE` is omitted, both
incomes and expenses will be listed. 
- `MONTH` must be in `yyyy-MM` format. E.g. `2020-09`. Only transactions in this month
will be listed. If `MONTH` is omitted, all transactions will be listed.
- `CATEGORY` must be added behind `/c`. `CATEGORY` can be case-insensitive. Only matched transactions in this category will be listed. 
- Other feasible list commands. Also, the order of filter condition is flexible: <br>
  * `list` 
  * `list [/tTYPE]` , or `list [/mMONTH]` , or `list [/cCATEGORY]` 
  * `list [/tTYPE] [/mMONTH]` , or `list [/mMONTH] [/tTYPE]`,or `list [/mMONTH] [/cCATEGORY]`,or `list [/cCATEGORY] [/mMONTH]`,or `list [/tTYPE] [/cCATEGORY]`,or `list [/cCATEGORY] [/tTYPE] ` 
  * `list [/tTYPE] [/mMONTH] [/cCATEGORY]`, or `list [/mMONTH] [/cCATEGORY] [/tTYPE]`, or `list [/cCATEGORY] [/mMONTH] [/tTYPE]`

Example of usage: `list /te /m2020-09` 

Expected outcome:
```
Here are your expense records for 2020-09:
  1. [E] RENT $500 on 01 Sep 2020
  2. [E] FOOD $10.00 on 18 Sep 2020 (Dinner at McDonalds’)
  3. [E] FOOD $3.50 on 20 Sep 2020 (Lunch with boss.)
```

Example of usage: `list`

Expected outcome:
```
Here are your transactions:
  1. [I] SALARY $5000.00 on 01 Aug 2020
  2. [E] RENT $500 on 01 Aug 2020
  3. [I] SALARY $9000.00 on 01 Sep 2020 (Given $4000 bonus!)
  4. [E] RENT $500 on 01 Sep 2020
  5. [E] FOOD $10.00 on 18 Sep 2020 (Dinner at McDonalds’)
  6. [E] FOOD $3.50 on 20 Sep 2020 (Lunch with boss.)
```

Example of usage: `list /te`  

Expected outcome:
```
Here are your transactions:
  1. [E] RENT $500 on 01 Aug 2020
  2. [E] RENT $500 on 01 Sep 2020
  3. [E] FOOD $10.00 on 18 Sep 2020 (Dinner at McDonalds’)
  4. [E] FOOD $3.50 on 20 Sep 2020 (Lunch with boss.)
```
Example of usage: `list /m2020-09`  

Expected outcome:
```
Here are your transactions:
  1. [I] SALARY $9000.00 on 01 Sep 2020 (Given $4000 bonus!)
  2. [E] RENT $500 on 01 Sep 2020
  3. [E] FOOD $10.00 on 18 Sep 2020 (Dinner at McDonalds’)
  4. [E] FOOD $3.50 on 20 Sep 2020 (Lunch with boss.)
```
Example of usage: `list /cSALARY`  

Expected outcome:
```
Here are your transactions:
  1. [I] SALARY $5000.00 on 01 Aug 2020
  2. [I] SALARY $9000.00 on 01 Sep 2020 (Given $4000 bonus!)
```
Example of usage: `list /te /m2020-09 /cFOOD` 

Expected outcome:
```
Here are your expense records for 2020-09:
  1. [E] FOOD $10.00 on 18 Sep 2020 (Dinner at McDonalds’)
  2. [E] FOOD $3.50 on 20 Sep 2020 (Lunch with boss.)
```

### Viewing monthly report: `report`
Shows a report that summarises the incomes and expenses for a specified month.

Format: `report`
- The summary report for the last six months will be displayed.

Example of usage: `report`

Expected outcome:
```
Here is your report:
Income for last 6 months (Highest to lowest):
  [I] 2020-09 $27597.40
  [I] 2020-06 $4344.00
  [I] 2020-05 $5666.00
  [I] 2020-10 $4000.00
  [I] 2020-08 $6000.00
  [I] 2020-07 $8880.00

Income for last 6 months (Highest to lowest):
  [E] 2020-09 $531.00
  [E] 2020-06 $650.00
  [E] 2020-05 $660.00
  [E] 2020-10 $577.00
  [E] 2020-08 $570.00
  [E] 2020-07 $550.00
```

Format: `report MONTH`
- `MONTH` must be in yyyy-MM format. E.g. `2020-09`.
- The summary report for the specified month will be displayed.

Example of usage: `report 2020-09`

Expected outcome:
```
Here is your transactions for 2020-09 :
Total Income: $27597.40
Total Expense: $31.00
Balance: $27566.40

This month has 30 days.
Average Expense Per Day: $1.03

Highest Income transaction: 
  [I] SALARY $9000.00 on 1 Sep 2020 (Given $4000 bonus!)

Highest Expense transaction: 
  [E] DRINK $9.50 on 20 Sep 2020 (Lunch with boss.)

Income Category by Frequency:
  [I] HONGBAO: 5
  [I] SALARY: 3

Expense Category by Frequency:
  [E] DRINK: 4
  [E] FOOD: 2

Income Category by Amount:
  [I] SALARY $27000.00
  [I] HONGBAO $597.40

Expense Category by Amount:
  [E] DRINK $24.00
  [E] FOOD $7.00
____________________________________________________________________ 
```
### Exiting the program: `exit`
Exits the program. 

Format: `exit`

Example of usage: `exit`

Expected outcome:

```
Bye! Hope to see you again soon.
```

### Saving the data
There is no save command because any change 
to the data will be automatically saved.


## FAQ

**Q**:
How do I transfer my data to another computer? 

**A**: 
Go to Money Tracker's root folder in the current computer.
Copy the data folder and paste it into the Money Tracker's root folder in the new computer.
Click `Yes` if the system prompts you for confirmation of overwriting.
## Command Summary

* View help `help`
* Add income category `addcati INCOME_CATEGORY`
* Add expense category `addcate EXPENSE_CATEGORY`
* List income / expense categories `listcat [/tTYPE]`
* Edit income / expense category `editcat INDEX /nNEW_NAME`
* Delete income / expense category `deletecat INDEX`
* Add income `addi /aAMOUNT /cINCOME_CATEGORY [/dDATE] [/eDESCRIPTION]`
* Add expense `adde /aAMOUNT /cEXPENSE_CATEGORY [/dDATE] [/eDESCRIPTION]`
* List incomes / expenses `list [/tTYPE] [/mMONTH] [/cCATEGORY]`
* Edit income / expense `edit INDEX [/aAMOUNT] [/cCATEGORY] [/dDATE] [/eDESCRIPTION]`
* Delete income / expense `delete INDEX`
* Clear data `clear`
* Set monthly budget `budget AMOUNT`
* View monthly report `report MONTH`
* Exit program `exit`