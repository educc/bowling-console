# Requirements
- JDK 8
- Set JAVA_HOME environment variable

# How to compile
Execute the follow commands:

Linux
```
cd [this_folder_repo]
./mvnw clean install
```
Windows
```
cd [this_folder_repo]
mvnw clean install
```
# How to run
Execute the follow commands after compiling:
```
cd [this_folder_repo]
java -jar target/challenge-0.0.1-SNAPSHOT.jar ./data/game_perfect.txt
```
The folder "data" have many input files for test this program
# Features developed
- Read the input file tab-separated and parse
- The input file can contains many players and their pinfalls
- Print the scoreboard for each player from the input file
- This program handle: the worst game (only 0 rolls), perfect game and game with fouls
- Unit test
- Integration test for each file in the folder "data"
