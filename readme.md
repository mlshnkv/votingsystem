#### Voting system for deciding where to have lunch.

----
Design and implement a REST API using Hibernate/Spring/SpringMVC **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

-----------------------------

### curl commands:

###### Get a list of users

`curl --location --request GET 'http://localhost:8080/votingsystem/admin/users' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

###### Create user

`curl --location --request POST 'http://localhost:8080/votingsystem/admin/users' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Shmuser",
    "email": "shmuzer@yandex.ru",
    "password": "password"
}'`

###### Get a list of today's menus

`curl --location --request GET 'http://localhost:8080/votingsystem/voting' \
--header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`

###### Vote for the restaurant

`curl --location --request POST 'http://localhost:8080/votingsystem/voting' \
--header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 100003
}'`

###### Get vote by date
Where yyyy-MM-dd - today or yesterday

`curl --location --request GET 'http://localhost:8080/votingsystem/voting/date?date=yyyy-MM-dd' \
--header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`