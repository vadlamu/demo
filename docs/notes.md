
How to filter allusers except one.
Custom Query:
If you need to exclude records based on specific conditions, a custom query is more efficient.

In-Memory Filtering:
If the exclusion criteria are simple and you don't have a large dataset, in-memory filtering is convenient.
I approached this route, this is little tough decision. If I choose JPA route then I need to make two calls
one for full data and other for just bio and nick name. Instead of two query i've choosen streams to get required data.
The other reason is I already did some JPA(Projections) for get specific user, so i opted for streams here.


Get only Bio information
Custom Query:
If you need to apply complex filtering or joining, a custom query gives you the most flexibility.
Projections:
If you only need a subset of fields from the child table, projections are a good way to optimize data retrieval.
Fetching from Parent:
If you already have the parent entity, this is the most straightforward approach.

Assumptions
1. Validate if some data exists for address fields.
2. Assume user to address is one to one.
3. 2nd Challenge applicable to list users also.

Questions to Brandon
1. Is Challenge 2 is applicable to list users also.
2. Why JwtService is component, why not service?

Good Part
1. Use of JPA Projections
2. Use streams
3. Use of GlobalExceptionHandlers


I Should have ....
1. Added more exceptions
2. Usage Maptruct to convert Projection to User, somehow this isn't working to me.
3. Is profile domain really needed, I should have used User itself.
4. Changed the return type on controlers for create, get. Should have updated for Put and Deleted also.
5. Should have used any Java 17 or Java 21 features
6. Should have suppressed id (user address) in the response.
7. More refactoring
