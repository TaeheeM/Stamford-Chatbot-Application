public class UniversityFAQEntry {
    private String keyword;
    private String response;

    public UniversityFAQEntry(String keyword, String response) {
        this.keyword = keyword;
        this.response = response;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getResponse() {
        return response;
    }
}
