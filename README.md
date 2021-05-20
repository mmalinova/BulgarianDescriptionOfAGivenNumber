# Bulgarian description of a given number as input

Bulgarian description of a given number is a simple Java console application that describe number with cyrillic alphabetical words.
## How does it work?

 - Receive an INTEGER number from the console as input(at the range -2,147,483,647 - +2,147,483,647);
 - Describe it with words from cyrillic alphabet and print on the console or write into a file.

### Example
```text
12 -> дванадесте
999 -> деветстотин деветдесет и девет
1234567 -> един милион двеста тридесет и четири хиляди петстотин шестдесет и седм
``` 
### How does it is implemented?
1. `constants` package contains two type of messages:
    - ExceptionMessages - for some type of errors;
    - OutputMessages - for printing on the console or writing to the file. 
  
It is good practice separating all string values into separate classes to make them easier to translate if necessary. 
       
2. `core` package contains the main business logic of the program. It has an interface and two classes:
    - ControllerImpl - process the number according number of digits that contains. Split the number of its constituent digits and processes them. If we think about it, we can easily conclude that each number is composed of the digits from 0 to 9. Moreover, in addition to the range of possible values, here we take into account whether the number is positive or negative;
    - EngineImpl - take the number from the console and make some basic validations. When it is checked, and it is OK, call the appropriate method from the ControllerImpl class. 
   
1. `io` package contains two interfaces and two classes:
    - ConsoleReader - read the input;
    - ConsoleWriter - print the output. 