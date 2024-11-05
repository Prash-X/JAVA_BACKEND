package Streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student(1, "John", "Doe", 21, "Male", "Computer", 2021, "New York", 5),
                new Student(2, "Jane", "Smith", 22, "Female", "Mechanical", 2020, "Los Angeles", 8),
                new Student(3, "Alice", "Johnson", 20, "Female", "Electrical", 2022, "Chicago", 3),
                new Student(4, "Bob", "Brown", 19, "Male", "Computer", 2022, "Houston", 2),
                new Student(5, "Charlie", "Davis", 23, "Male", "Civil", 2019, "Phoenix", 6),
                new Student(6, "Eve", "Wilson", 21, "Female", "Computer", 2020, "Philadelphia", 4),
                new Student(7, "Frank", "Miller", 24, "Male", "Chemical", 2018, "San Antonio", 1),
                new Student(8, "Grace", "Martinez", 22, "Female", "Aerospace", 2021, "San Diego", 7),
                new Student(10, "Ivy", "Anderson", 13, "Female", "Mechanical", 2019, "New York", 10)
        );

        // Stream API code to filter out all students whose age is greater than 20 and who belong to the Computer Science department.
        students.stream()
                .filter(s->s.getAge()>20 && s.getDepartmentName().equals("Computer"))
                .forEach(System.out::println);

       // Stream API code to convert the list of students into a list of their firstName and lastName concatenated as full names.
        System.out.println( students.stream()
                .map(s->s.getFirstName().concat(s.getLastName()))
                .toList());

       // Stream API code to sort the list of students by their rank in ascending order and then by their lastName alphabetically.
        System.out.println(students.stream()
                .sorted(Comparator.comparingInt(Student::getRank)
                        .thenComparing(Student::getLastName))
                .toList());

        //count of list
        System.out.println(students.stream().count());

        //Write a Stream API code to group all students by departmentName and collect them into a Map<String, List<Student>>.
        Map<String,List<Student>> map = students.stream()
                .collect(Collectors.groupingBy(Student::getDepartmentName));
        System.out.println(map);

        //Write a Stream API code to find the first student who joined in the year 2022.
        System.out.println(students.stream().filter(s->s.getJoinedYear()==2022).findFirst());

        //student with highest rank in the list
        System.out.println(students.stream()
                .max(Comparator.comparingInt(Student::getRank)).get());

       //distinct city
        List<String> list = students.stream()
                .map(Student::getCity)
                .distinct()
                .toList();
        System.out.println(list);

        //Stream API code to check if all students in the list are above the age of 18.
        System.out.println(students.stream()
                .allMatch(s->s.getAge()>18));

        //average of students age
        System.out.println(students.stream()
                .mapToInt(Student::getAge)
                .average().orElse(0));
    }
}
/*OUTPUT for the above code:
* Student{id=1, firstName='John', lastName='Doe', age=21, gender='Male', departmentName='Computer', joinedYear=2021, city='New York', rank=5}
Student{id=6, firstName='Eve', lastName='Wilson', age=21, gender='Female', departmentName='Computer', joinedYear=2020, city='Philadelphia', rank=4}
[JohnDoe, JaneSmith, AliceJohnson, BobBrown, CharlieDavis, EveWilson, FrankMiller, GraceMartinez, HenryTaylor, IvyAnderson]
[Student{id=7, firstName='Frank', lastName='Miller', age=24, gender='Male', departmentName='Chemical', joinedYear=2018, city='San Antonio', rank=1}, Student{id=4, firstName='Bob', lastName='Brown', age=19, gender='Male', departmentName='Computer', joinedYear=2022, city='Houston', rank=2}, Student{id=3, firstName='Alice', lastName='Johnson', age=20, gender='Female', departmentName='Electrical', joinedYear=2022, city='Chicago', rank=3}, Student{id=6, firstName='Eve', lastName='Wilson', age=21, gender='Female', departmentName='Computer', joinedYear=2020, city='Philadelphia', rank=4}, Student{id=1, firstName='John', lastName='Doe', age=21, gender='Male', departmentName='Computer', joinedYear=2021, city='New York', rank=5}, Student{id=5, firstName='Charlie', lastName='Davis', age=23, gender='Male', departmentName='Civil', joinedYear=2019, city='Phoenix', rank=6}, Student{id=8, firstName='Grace', lastName='Martinez', age=22, gender='Female', departmentName='Aerospace', joinedYear=2021, city='San Diego', rank=7}, Student{id=2, firstName='Jane', lastName='Smith', age=22, gender='Female', departmentName='Mechanical', joinedYear=2020, city='Los Angeles', rank=8}, Student{id=9, firstName='Henry', lastName='Taylor', age=20, gender='Male', departmentName='Computer', joinedYear=2022, city='Dallas', rank=9}, Student{id=10, firstName='Ivy', lastName='Anderson', age=13, gender='Female', departmentName='Mechanical', joinedYear=2019, city='New York', rank=10}]
10
{Civil=[Student{id=5, firstName='Charlie', lastName='Davis', age=23, gender='Male', departmentName='Civil', joinedYear=2019, city='Phoenix', rank=6}], Chemical=[Student{id=7, firstName='Frank', lastName='Miller', age=24, gender='Male', departmentName='Chemical', joinedYear=2018, city='San Antonio', rank=1}], Aerospace=[Student{id=8, firstName='Grace', lastName='Martinez', age=22, gender='Female', departmentName='Aerospace', joinedYear=2021, city='San Diego', rank=7}], Mechanical=[Student{id=2, firstName='Jane', lastName='Smith', age=22, gender='Female', departmentName='Mechanical', joinedYear=2020, city='Los Angeles', rank=8}, Student{id=10, firstName='Ivy', lastName='Anderson', age=13, gender='Female', departmentName='Mechanical', joinedYear=2019, city='New York', rank=10}], Computer=[Student{id=1, firstName='John', lastName='Doe', age=21, gender='Male', departmentName='Computer', joinedYear=2021, city='New York', rank=5}, Student{id=4, firstName='Bob', lastName='Brown', age=19, gender='Male', departmentName='Computer', joinedYear=2022, city='Houston', rank=2}, Student{id=6, firstName='Eve', lastName='Wilson', age=21, gender='Female', departmentName='Computer', joinedYear=2020, city='Philadelphia', rank=4}, Student{id=9, firstName='Henry', lastName='Taylor', age=20, gender='Male', departmentName='Computer', joinedYear=2022, city='Dallas', rank=9}], Electrical=[Student{id=3, firstName='Alice', lastName='Johnson', age=20, gender='Female', departmentName='Electrical', joinedYear=2022, city='Chicago', rank=3}]}
Optional[Student{id=3, firstName='Alice', lastName='Johnson', age=20, gender='Female', departmentName='Electrical', joinedYear=2022, city='Chicago', rank=3}]
Student{id=10, firstName='Ivy', lastName='Anderson', age=13, gender='Female', departmentName='Mechanical', joinedYear=2019, city='New York', rank=10}
[New York, Los Angeles, Chicago, Houston, Phoenix, Philadelphia, San Antonio, San Diego, Dallas]
false
20.5
*/