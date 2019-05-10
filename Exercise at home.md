The following exercise is sent to you as an additional tool to be used in your technical interview. We kindly ask you to produce a solution and put your code on a public project in github.com under your account, use Maven (or Gradle or another build tool) as automation tool.

## Read it carefully
- Use any programming language that you prefer.
- You are free to implement any mechanism for feeding input into your solution (for example,  using hard coded data within a unit test).
- You should prove that the solution is working correctly at least against the supplied test data
- Keep it simple and frameworkless: the solution should not require any frameworks or libraries (Web, ORM, DI, 3rd party utils, …)
- You are allowed to use testing frameworks (*Unit and any mocking framework of your choice if you think you need it, …)


The goal is to provide us with an understanding of your coding style, skills and problem solving. We’ll pay particular attention to:

* Simple code design
* Separation of concerns
* Readability
* Testability


## PROBLEM: SALES TAXES

Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical products that are exempt. Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions.

When I purchase items I receive a receipt which lists the name of all the items and their price (including tax), finishing with the total cost of the items, and the total amounts of sales taxes paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax.

Write an application that prints out the receipt details for these shopping baskets...

### INPUT:
```
Input 1:
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

Input 2:
1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50

Input 3:
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25
```

### OUTPUT
```
Output 1:
1 book : 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83

Output 2:
1 imported box of chocolates: 10.50
1 imported bottle of perfume: 54.65
Sales Taxes: 7.65
Total: 65.15

Output 3:
1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 imported box of chocolates: 11.85
Sales Taxes: 6.70
Total: 74.68
```

## SOME USEFUL REFERENCE TO HELP YOU WITH THE EXERCISE
* *Test-Driven Development: By Example.*     Addison-Wesley. Winner of the Jolt Productivity Award. 2002. (ISBN 978-0321146533)
* *Clean Code: A Handbook of Agile Software Craftsmanship.*     Prentice Hall     PTR. 2008. (ISBN 0-13-235088-2.)
* Martin Fowler’s web site http://martinfowler.com/

