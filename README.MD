Searching Flights on Kayak.ch: Using Selenide
=============================================

This is a sample project demonstrating how to search for flights between two locations and a date range and finding how
many flights were found within the input price range

**You can checkout and run it locally with a few minutes on firefox browser only.**

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