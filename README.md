# My Personal Project

## A warehouse inventory management system

- The application is going to be a warehouse management system which will allow the user to store items in a warehouse
and keep track of them.
- The appropriate users will likely be employees of a warehouse business.
- This project will allow me to better understand how to create a system that can actually carry out tasks in a
professional setting, which is why I chose this idea.
## User Stories
- As a user, I want to be able to collect and add items to the warehouse inventory.
- As a user, I want to be able to display the items and their information currently in the warehouse inventory.
- As a user, I want to be able to remove items from the inventory.
- As a user, I want to be able to change the information of specific items from the inventory.
- As a user, I want to be able to save my current inventory.
- As a user, I want to be able to load a pre-existing inventory into the program from a file.
## Instructions for Grader
- You can add an item to the inventory by clicking on the "Add an item to the inventory" button. Then just  enter the
required information into the fields and click submit. Now you should be able to see the item in the complete and
summary view of the inventory. Note that the inventory summary gives the count of items with the same name, whereas the
complete inventory provides each item separately with its ID, name and description.
- You can remove an item from the inventory by clicking on the "Remove an item from the inventory" button. Then just
enter the ID of the item you wish to remove, and click submit. You should now no longer see the item in the complete and
summary view of the inventory. Another feature is to be able to change an item's
information by clicking on the "Change an item's information" button, and then entering the ID (old) of the item you
wish to change, then enter the new information and submit. Note that if you do not with to change any field, just leave
the text box empty. For instance, if you do not wish to change the name, leave the field empty and the name of the item
will remain the same after submitting.
- The visual component of my project is a bar graph generator. It's very similar to the summary view of the inventory,
wherein it provides the count of items with the same name but in a bar graph format. You can first add items to the
inventory so that there is some bars to plot (if there is nothing to plot it will only show axes), then click the button
"Display the inventory bar graph summary", which should open up a new window with the graph displayed. Note that the
scale on the y-axis adjusts based on the maximum number of items with the same name (their count). The maximum value on
the scale is decided by taking 10^(the number of digits in count) as the maximum value. For instance, if we have a 
maximum of 12 items with the same name, "box", then the maximum value on the scale would be 100 (10^2). Or if its 8,
then the maximum value would be 10 (10^1). This is to accommodate for an increasing number of items. Additionally, the
x-axis adjusts based on the number of different names used among the items. So if we have many names/bars which extend 
out of the window, I have set up a scroll pane which will allow you to slide across the entire panel to see all the 
bars. (The visualization took me ages to do, plz appreciate).
- The current inventory can be saved to a .json file by clicking on the button "Save current inventory to a file". Then
fill in the name of the file (which must already be from the data folder of the project). I have sample files in the 
folder, but you can make your own. Note that you must end the file name with .json. For example if I want to save my
inventory to a file "something.json" located in the data folder of my project, I will just type in "something.json" into
the file name field on the panel (to the right).
- A pre-existing inventory can be loaded from a .json file by clicking on the button "Load a pre-existing inventory
from a file", and then filling in the name of the file from which you wish to load in the inventory. For example, if I
want to save my inventory to a file named "freeFile.json" located in the data folder of my project, I will just type in
"freeFile.json" into the file name field on the panel (to the right). Note that "something.json" and "freeFile.json"
are unimportant to the tests in my project, so feel free to use those!
## Phase 4: Task 2
- Date: Fri Apr 05 14:38:51 PDT 2024, Description: Data loaded to system from file named something.json.
- Date: Fri Apr 05 14:38:51 PDT 2024, Description: Box successfully added to inventory.
- Date: Fri Apr 05 14:38:51 PDT 2024, Description: Box successfully added to inventory.
- Date: Fri Apr 05 14:38:51 PDT 2024, Description: Box successfully added to inventory.
- Date: Fri Apr 05 14:38:51 PDT 2024, Description: Box successfully added to inventory.
- Date: Fri Apr 05 14:38:51 PDT 2024, Description: Box successfully added to inventory.
- Date: Fri Apr 05 14:38:51 PDT 2024, Description: Wrench successfully added to inventory.
- Date: Fri Apr 05 14:38:51 PDT 2024, Description: Wrench successfully added to inventory.
- Date: Fri Apr 05 14:38:51 PDT 2024, Description: Mandelorian successfully added to inventory.
- Date: Fri Apr 05 14:39:21 PDT 2024, Description: Doll successfully added to inventory.
- Date: Fri Apr 05 14:39:38 PDT 2024, Description: Mandelorian successfully removed from inventory.
- Date: Fri Apr 05 14:39:59 PDT 2024, Description: Item ID changed from D01 to D02.
- Date: Fri Apr 05 14:39:59 PDT 2024, Description: Item name changed from Doll to BigDoll.
- Date: Fri Apr 05 14:39:59 PDT 2024, Description: Item description changed from 'A doll' to 'A large doll'.
- Date: Fri Apr 05 14:40:08 PDT 2024, Description: Data saved from system to file called something.json.
## Phase 4: Task 3
- I think one of the major changes I would've made has to do with the GUI class. I made use of many fields
and methods that could've been separated into sub-classes. Examples include setting up classes for buttons, labels,
panels, etc. This could've made my other components of the GUI more visible/clear in the UML diagram.
- Another change I perhaps should've considered is having implemented functions to read and write data to and from json 
files in the Inventory class, since that would make more sense than implementing it into the UI directly.