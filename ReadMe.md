# movie-json-parser

## Task

The legal department has requested a list of all our movies to report it to the FSK (the German motion picture rating system organization). They want the list to be grouped and counted by age rating (e.g. FSK12, FSK16, etc.) You are provided with a JSON file including 1000 assets (“Movies”, “Series”, “Seasons” and “Episodes”), on which you should conduct the following tasks: 1. Read in the file and return the list of movies requested by the legal department. The output should be a JSON file and each asset should only contain id, title and year.

## Solution
This application is simple Java class program, which reads a json, parses, and generate the output json

## Technologies used

1. Java 8 (Programming Language)
2. Gson (Json parsing library)
3. commons-io (File Read library)


## Getting Started

The source code can be checked out to your local, build and run the application from your IDE after importing to it as a maven project.

### Prerequisites
1. Java 8
2. Maven 3
3. Git

####  Build using maven 
	
```
mvn clean install
```

#### To run

```
Run this file(src\main\java\com\forster\Main.java) as Java App
```

##### Input
assets.json is the input file, present in resources directory

##### Output
result.json is the output file, present in result directory