Now you need to implement UsersRepository interface and UsersRepositoryJdbcImpl class using a `SINGLE List<User> findAll(int page, int size)` method.

This method shall return size—users shown in the page with page number. This "piecewise" data retrieval is called pagination. Thus, DBMS divides the overall set into pages each containing size entries. For example, if a set contains 20 entries with page = 3 and size = 4 , you retrieve users 12 to 15 (user and page numbering starts from 0).

The most complicated situation in converting relational links into object-oriented links happens when you retrieve a set of entities along with their subentities. In this task, each user in the resulting list shall have included dependencies—a list of chatrooms created by that user, as well as a list of chatrooms the user participates in.

Each subentity of the user MUST NOT include its dependencies, i.e. list of messages inside each room must be empty.

The implemented method operation should be demonstrated in Program.java.

**Notes**:
- findAll(int page, int size) method shall be implemented by a SINGLE database query. It is not allowed to use additional SQL queries to retrieve information for each user.
- We recommend using CTE PostgreSQL.
- UsersRepositoryJdbcImpl shall accept DataSource interface of java.sql package as a constructor parameter.

