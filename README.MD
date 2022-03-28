Searching Flights on Kayak.ch: Using Selenide
=============================================

This is a sample project demonstrating how to search for flights between two locations and a date range and finding how
many flights were found within the input price range

**You can checkout and run it locally with a few minutes for firefox browser only.**

### How to run with Gradle

Type from command line for running test with supplied parameters:

```
gradle test -DfromLocation=<xxx> -DtoLocation=<xxx> -DfromDate=<YYYY-MM-DD> -DreturnDate=<YYYY-MM-DD> -DmaxPrice=xxx
```

Example:
```
 gradle test -DfromLocation=Zürich -DtoLocation=Amsterdam -DfromDate=2022-03-28 -DreturnDate=2022-10-25 -DmaxPrice=1000
```

_Feel free to share your feedback!_

### To-Dos
1. Add log4j logging instead of SOP
2. Add support for Selenoid - Docker support for Selenium
3. Add support for multiple browsers - Chrome/Edge/Safari
4. Refactor code to put all variable declarations at the beggining as Selenide supports lazy loading
