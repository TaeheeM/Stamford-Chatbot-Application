public class Course {
    private String name;
    private String code;

    //default constructor 
    public Course() {}

 // Constructor with parameters to initialize name and code
    public Course(String name, String code) {
        this.name = name;
        this.code = code;
    }

    //getter the name of the course
    public String getName() {
        return this.name;
    }

    //getter the code of the course
    public String getCode() {
        return this.code;
    }

    //setter the name of the course
    public void setName(String name) {
        this.name = name;
    }

    //setter the code of the course
    public void setCode(String code) {
        this.code = code;
    }
}
