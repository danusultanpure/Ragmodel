Virtual Assistant Application

This is a simple virtual assistant application built using Java and Spring Boot. It allows users to upload PDF files and ask questions related to the content of those files. The application extracts text from the uploaded PDF file and searches for the answer to the user's question within the text.

How to Use

Upload PDF File: Navigate to the homepage of the application (/) and upload a PDF file using the provided form.

Ask Question: After uploading the PDF file, enter your question in the input field provided and submit the form.

View Answer: The application will process the PDF file and display the answer to your question along with the context around it.

Dependencies

Apache PDFBox: For extracting text from PDF files.
Spring Boot: For building the web application.
Spring Web: For handling HTTP requests.
Running the Application

Ensure you have Java and Maven installed on your system.
Clone the repository or download the source code.
Navigate to the project directory.
Run mvn spring-boot:run to start the application.
Access the application in your web browser at http://localhost:8080.
Note: Ensure that you have proper permissions to read files from the filesystem if running the application in a production environment.

Contributing

Contributions to this project are welcome! Feel free to fork the repository, make your changes, and submit a pull request.