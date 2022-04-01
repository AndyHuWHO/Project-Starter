# Personal Vocab Notebook

### AndyHu's CPSC210 Project


I am designing a simplified prototype version of a
**Vocabulary Notebook application** for 
Second Language Learning. This is an application 
I have envisioned for years.

This vocabulary notebook enables its users to store 
and review their **personalized** vocabulary 
information while studying a second language.
*Second language learners* will be the primary users
of this application. This app will let users store 
not only the words and their meanings 
and translations, but most
importantly, to store 
**the original learning context** 
of the words. Because this is a prototype version,
the original context will be in *text form* only at 
this stage, and in the future, it can take the form 
of *a short video clip, a short voice recording,
a picture, and etc*. So, the three main purposes of
this application are:

- let users store vocabulary items
- let users record and edit personalized information
for these vocab items
- let users review the vocab items and their 
related information

### User Stories

- As a user, I want to be able to add a 
new vocabulary item to my vocab list
- As a user, I want to be able to store the 
definition and translation with the 
vocabulary item
- As a user, I want to be able to record the 
original learning context for particular 
vocabulary items
- As a user, I want to be able to view the 
list of vocabulary items in my vocab list
- As a user, I want to be able to go into
specific vocabulary items to view information 
attached to them and edit or delete this vocabulary
- As a user, I want to be able to go into 
specific vocabulary items to edit and update
information attached to them
- As a user, I want to be able to delete
vocabulary items from the vocab list
- As a user, I want to be able to save my vocab list to file 
while running my program and before terminating the app
- As a user,  I want to be able to be able to load 
my vocab list from file when I start the app and 
while running my program

### Phase 4: Task 2
Thu Mar 31 11:13:25 PDT 2022
Added word: test1

Thu Mar 31 11:13:30 PDT 2022
Added word: test2

Thu Mar 31 11:13:34 PDT 2022
Added word: test3

Thu Mar 31 11:13:38 PDT 2022
Deleted word: test3

Thu Mar 31 11:13:40 PDT 2022
Deleted word: test2

Thu Mar 31 11:13:47 PDT 2022
Added word: test4

Thu Mar 31 11:13:54 PDT 2022
Added word: test5

Thu Mar 31 11:13:59 PDT 2022
Deleted word: test1

### Phase 4: Task 3
For my Model package, I only have a VocabList and a Word Class 
right now. If I had more time, I might want to design an abstract 
super-type called WordInfo that had a field of String because I 
might want to have more kinds of WordInfo. Some of them might have
pictures or videos or voice recordings, and etc. but they all have 
a String field.
For my GUI classes, I might want to make an abstract class for the
basic design of my windows, because all of my windows have a Word
field and all of them have a BorderLayout for the frame and Navigation
Panel on the SOUTH and centerPanel in the center.


