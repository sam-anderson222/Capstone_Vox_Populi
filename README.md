# Final Capstone Project: Vox Populi
### A Polling Platform For Creating and Answering Polls.

## Frontend 
Tools used: React / JS / HTML / CSS / Bootstrap / D3.js  
The frontend for Vox Populi was created using React. Bootstrap was used for styling the html in the project and D3.js was used to create the pie graphs to display the results for polls.
The frontend allows users to create and login to their own accounts. After doing so, they can vote in polls or create polls of their own. After voting in a poll, the user is taken to a result
screen which uses D3.js to create a pie chart showing how users voted on the poll. Poll creation allows user to dynamically choose the number of options in their poll, anywhere between 2 options to 20.

## Backend
Tools used: Java / Maven / Spring Boot + Other Spring Tools (JDBC / RESTful API)  
The backend of Vox Populi both serves and takes request from the frontend. It serves all kind of data such as user account info, poll data, poll votes, and poll results.
The backend is connected to the frontend via Spring RESTful API and to the database via JDBC.


## Database
Tools used: MySQL  
The database that holds all the data for Vox Populi is made up of 4 main tables: users, polls, poll_options, and poll_votes. These four tables are the foundation of how data is stored for the app.
Procedures are also widely used the database to simplify long queries and make it easier to retrieve complicated data or multiple tables at once.
