import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
@Controller
public class VirtualAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualAssistantApplication.class, args);
    }

    @GetMapping("/")
    public String index() {
        return "index"; // Ensure you have index.html in src/main/resources/templates
    }

    @PostMapping("/ask")
    public String askQuestion(@RequestParam("file") MultipartFile file,
                              @RequestParam("question") String question,
                              Model model) {
        if (file.isEmpty()) {
            model.addAttribute("answer", "Please upload a PDF file.");
            return "result"; // Ensure you have result.html in src/main/resources/templates
        }

        try {
            String text = extractTextFromPDF(file.getBytes());
            String answer = findAnswer(text, question);
            model.addAttribute("answer", answer);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("answer", "An error occurred while processing the file.");
        }

        return "result";
    }

    private String extractTextFromPDF(byte[] fileBytes) throws IOException {
        try (PDDocument document = PDDocument.load(fileBytes)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    private String findAnswer(String text, String question) {
        // Basic search logic using regex patterns
        Pattern pattern = Pattern.compile("(?i)\\b" + Pattern.quote(question) + "\\b");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            int startIndex = Math.max(0, matcher.start() - 100); // Get context before the match
            int endIndex = Math.min(text.length(), matcher.end() + 100); // Get context after the match
            return "Here is the context around your question:\n" + text.substring(startIndex, endIndex);
        } else {
            return "Sorry, I couldn't find the information related to your question in the document.";
        }
    }
}
