build:
* From project dir, open terminal and execute command: ./gradlew clean build

run:
* go to build/libs  open terminal and execute command:
 java -jar file-search-1.0-SNAPSHOT.jar ../../src/test/resources/test_dir
 
usage:
1) Using '/' as path delimiter specify path to directory as application args:
  java -jar file-search-1.0-SNAPSHOT.jar <path-to-dir>
2) Enter words to search divided by space
3) To exit search enter  :quit

  