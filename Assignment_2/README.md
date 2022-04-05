# Airline Fatal Incident Statistics (1985-2014)

This program parses a csv containing incident statistics for 56 airlines from 1985-2014.

Output:
1. converted_airline_safety.xml - airline_safety.csv converted to xml
2. airline_summary_statistic.xml - airline_safety.csv fields summarized with minimum, maximum, and average. Saved to xml.
3. airline_safety.csv - Total incidents appended to each airline row

## How To
Setup:
* Install IntelliJ IDEA Community Edition
* Setup JavaFx and run configuration in Intellij using following instructions https://openjfx.io/openjfx-docs/#IDE-Intellij
* Run project

Before each run:
* Delete outpute files converted_airline_safety.xml and airline_summary_statistic.xml
* Overwrite airline_safety.csv (possibly appended file) with airline_safety_reference.csv (original file)

