# Steam Data Logger

Don't know what to play from your Steam Library?

This example program will call the Steam API with a valid Steam ID, return game information from it, choose a random game from your library and display information about it in a Dialog box. It will also output the response games.xml, and a games.txt file containing all of the titles in the Steam Library.

This program is built using Maven and the TestNG Framework, using REST Assured. It can be run as either a standalone Java program from Intellij, or a TestNG Test Suite (both classes are included in this package, under src/main/java and src/test/java respectively.)

## Requirements
For this program to work, you must have the following:
- Steam API Key - generate one by going to https://steamcommunity.com/login/home/?goto=%2Fdev%2Fapikey
- Steam ID of an account (64 Bit) for which the account is set to Public

Enter these into a text file called credentials.txt in the root folder with the following format, ensuring there are no leading and trailing spaces:

```
STEAMAPIKEY=
STEAMID64=
```
