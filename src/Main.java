import com.javaassessment.generator.SchoolData;

public class Main {

    public static void main(String[] args) {
        SchoolData schoolData = new SchoolData();

        schoolData.generate("schools.txt");

        System.out.println(schoolData.report());
    }
}
