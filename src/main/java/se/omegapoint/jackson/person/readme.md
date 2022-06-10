# Person
Assume we have some API that accepts a request to add a person to a list. The information about the person consists of a name and an age. Only consider the request and parsing of it. 
```
# Json
{
    "age": 10,
    "name": "Alice"
}

# CDDL
person = {
    age: int,
    name: tstr
}
```
## Version 1
### Requirements
* Both age and name must be present

### Implementation
Assume the simples possible implementation. The getters are there for test cases:
```java
package se.omegapoint.jackson.person;

public class Person {
    public int age;
    public String name;

    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
}
```
* How do we enforce that both age and name are present?

## Version 2 (just for fun)
Someone thought it could be nice to also save an email address associated with the person. Also, that someone decided to allow for more fields in the object for "future use".
```
{
    "age": 10,
    "name": "Alice",
    "email": "alice@gmail.com",
}
```
### Requirements
* Both age and name must be present
* An optional field email (type=string) might be in the object
* Other fields than age, name and email is allowed in the object

### Implementation
* How do we add an optional field?
* How do we check if email is present (API in Person class)?
* How to we allow other fields?
  * On ObjectMapper level?
  * On Person class level?

## Version 3
We decide to be more strict on incoming data.
### Requirements
* Both age and name must be present
* Age must be an integer
* Name must be a string (the empty string "" is ok)
* No other fields is allowed in the JSON object
* (The fields in the object should be sorted)
### Discussion
If the format of a request is strictly specified, where should a format mismatch be caught? If using this over REST, should this result in HTTP code 400 (Bad request)? How do we do that in e.g. a spring environment? Do we need to configure the ObjectMapper or is the DTO class implementation enough?

## Final version
Lets work out an implementation of Person that we can be "proud" of. Assume that this is a DTO implementation and that it will later be converted into some domain object.
* Pros/cons using Lombok?
* Make name and age private and final, pros/cons?
* Should we implement equals()?
* Should we implement hashCode()?