# Bank Demo

## Endpoint

`GET /bank-demo/api/customer/{id}`

Returns the customer's details along with their cards and accounts, in the required response format.

## Design notes

- Credit cards are excluded for business customers
- Card/account fetching uses two sequential QueryDSL fetch-joins to avoid Hibernate's multiple-bag-fetch limitation while keeping `List` (no `Set`) on the entity associations
- `spring.jpa.open-in-view` is disabled; all associations are lazy
- Unit tests cover the Business/Personal branching and the not-found path in `CustomerService`