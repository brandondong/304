# 304

The project will not compile until DatabaseCredentials.java is created. This is to prevent bots scraping Github from gathering personal information. Changes to this file will be ignored in Git.

If developing from home, make sure DatabaseConnection.USE_LOCAL is set to true and configure your environment following http://www.cs.ubc.ca/~hazra/tutorials/JDBC/jdbc1.html under "Working from home or wireless".

NEW instructions:
To test a query, add code that creates and executes the query in Application.java. Run Application.java, making sure that the hotel.sql script has been run on your Ugrad database and the changes are commited (execute the command "commit;").

OLD instructions:
To test a query, start the REST server on localhost:8080 by running Application.java. Make sure the hotel.sql script has been run on your Ugrad database and the changes are commited (execute the command "commit;"). Hit the appropriate query endpoint with the correct parameters (e.g. http://localhost:8080/customer?CustomerID=5100&selected=CustomerID&selected=Name) and verify the JSON response, if any.
