# Daniel Park Midterm JavaFX App

## How To Run Program
1. Install Java from https://www.java.com/en/download/help/windows_manual_download.html
2. Install IntelliJ from https://www.jetbrains.com/idea/download/#section=windows
3. Download JavaFX library https://openjfx.io/openjfx-docs/
4. Open Project Folder in IntelliJ
5. Add/Modify JavaFX library (lib) in File->Project Structure ->Libraries ->
5a. If JavaFX library exists alreayd, change path to newly downloaded javaFX lib
5b. If JavaFX library doesn't exist, add javaFX lib
6. Go to Run Configuration for midtermtest in Run->Edit Configurations
7. Change --module-path "some/path/to/javafx/lib" to where you saved JavaFX lib
8. Download and extract javax Jar file from http://www.java2s.com/Code/Jar/j/Downloadjavaxjar.htm
9. Go to to dependencies at File -> Project Structure -> Modules -> Dependencies 
10. If javax Jar dependencies missing add it using + Add Dependcy
11. If javax Jar dependcy already exists, change path to where you extracted javax Jar file
12. Run application by Run -> Run (midtermtest)