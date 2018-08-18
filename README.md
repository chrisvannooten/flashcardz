# flashcardz
Simple flashcard App

## Animation Package

I did not write anything in the Animation package, I obtained the 2 classes from http://genzeb.github.io/flip/.
The 2 Classes are required to make the flip animation of the flashcards work.
##Unfinished
My original plan was to create an API system alongside it, due to time constraints this did not follow through.
The API would have taken care of user logon through tokens, and allowed you to upload your courses to a server
which is also why you see "created by" with courses.

Another feature missing is editing, which would have been implemented using setOnLongClickListener.
A new intent would be started passing the Id of the item in question to the designated create Activity where instead
the fields would have been filled in with the items values, the button name and title would be changed to reflect the
fact the user is editing.
