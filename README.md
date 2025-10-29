# 15 Days of Java Learning

A curated, day-by-day Java learning journey. Each "Day_X" directory explores a focused concept through small, runnable examples and exercises. The project uses Gradle for builds and JUnit Jupiter for testing.

## What you'll find
- Incremental learning broke into 15 days
- Clear separation of topics per day (e.g., basics, OOP, collections, generics, streams, I/O, error handling, unit testing, and more)
- Gradle-based build setup at the root, with some days optionally using their own subproject configuration
- Tests powered by JUnit Jupiter
        
## Project structure (high-level)
- Day_1 ... Day_15: Topic-focused examples and exercises
- build.gradle (root): Shared configuration (Java + JUnit Jupiter)
- settings.gradle (root): Gradle settings for the project and any included subprojects
- Some days may include their own Gradle subproject (with `src` and a dedicated `build.gradle`)
- Version control ignores standard IDE and build outputs
        
## Prerequisites
- Java SDK: 24 (set as the project SDK in your IDE)
- Gradle: Installed locally or open in an IDE that manages Gradle for you
- Note: If Gradle wrapper scripts are present in your environment, you can use them; otherwise, use your local Gradle installation.
        
## Getting started
1. Open the project in your IDE.
2. Ensure the Project SDK is set to Java 24.
3. Let Gradle import/sync the project.
4. Explore any Day_X folder and open a class that contains a `public static void main(String[] args)` method.
        
## Build
- Build the entire project:
```bash

gradle clean build
```
- If a particular day is a Gradle subproject (example name shown below), you can build just that subproject:
```bash

gradle :Day_10:clean :Day_10:build
```
        
## Run examples
- From an IDE:
- Open a Day_X class that has a `main` method and click Run.
- From the command line (general approach):
- Compile using Gradle, then run the compiled class with `java -cp ...` pointing to the appropriate `build/classes/java/main` output directory of the module that contains the class you want to run.
- The exact command depends on which Day_X contains the class and whether it’s part of the root project or a subproject.
        
## Testing
- Run all tests:
```bash

gradle test
```
- View reports:
- After a run, check `build/reports/tests/test/index.html` (or the corresponding subproject’s `build/reports` path) for detailed results.
     
## Notes and tips
- The repository ignores IDE metadata and per-day build outputs to keep the workspace clean.
- Some days may be simple source folders, while others may be standalone Gradle subprojects for demonstration purposes.
- If you encounter classpath issues when running from the terminal, prefer running directly from your IDE or adjust the `-cp` to point to the correct compiled output.
        
## Contributing
- Improve examples, add comments, and include more tests where helpful.
- Keep each day self-contained and easy to run.
        
