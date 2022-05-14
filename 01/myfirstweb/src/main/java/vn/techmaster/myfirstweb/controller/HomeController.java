package vn.techmaster.myfirstweb.controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.techmaster.myfirstweb.model.BmiData;
import vn.techmaster.myfirstweb.model.Book;
import vn.techmaster.myfirstweb.model.Message;
import vn.techmaster.myfirstweb.model.Student;
import vn.techmaster.myfirstweb.util.Util;

@Controller
@RequestMapping("/")
public class HomeController {
  @GetMapping(value = "/hi", produces = MediaType.TEXT_HTML_VALUE)
  @ResponseBody
  public String hello() {
    return "<h1>Hello World</h1><h3>Cool</h3>";
  }

  @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Book getBook() {
    return new Book("Dế Mèn Phiêu Luu Ky", "Tô Hoài", "1945");
  }

  @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
  @ResponseBody
  public Book book_xml() {
    return new Book("x111", "Dế Mèn Phiêu Lưu Ký", "Tô Hoài");
  }

  @GetMapping("/add/{a}/{b}")
  @ResponseBody
  public int add(@PathVariable("a") int aa, @PathVariable("b") int bb) {
    return aa + bb;
  }

  @GetMapping("/name/{your_name}")
  @ResponseBody
  public String hi(@PathVariable("your_name") String yourName) {
    return "Hi " + yourName;
  }

  @GetMapping("/year/{year}")
  @ResponseBody
  public int getAge(@PathVariable("year") int year) {
    return Calendar.getInstance().get(Calendar.YEAR) - year;
  }

  @GetMapping("/random/{length}")
  @ResponseBody
  public String randomString(@PathVariable("length") int length) {
    return "XXXmmmMmmWW";
  }

  @GetMapping("/add")
  @ResponseBody
  public int add2(@RequestParam("a") int a, @RequestParam("b") int b) {
    return a + b;
  }

  @PostMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Message echoMessage(@RequestBody Message message) {
    return message;
  }

  // Homework
  @GetMapping(value = "/random")
  @ResponseBody
  public String random() {
    return Util.generateRandomString(8);
  }

  @GetMapping(value = "/quote")
  @ResponseBody
  public String quote() {
    // TODO: move list to outside of method
    List<String> quotes = new LinkedList<>();
    quotes.add("Kiến tha lâu đầy tổ");
    quotes.add("Có công mài sắt, có ngày nên kim");
    quotes.add("Không thầy đố mày làm nên");
    quotes.add("Học thầy không tày học bạn");

    return Util.getRandomStringFromList(quotes);
  }

  @PostMapping(value = "/bmi", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody()
  public double bmi(@RequestBody BmiData bmiData) {
    DecimalFormat df = new DecimalFormat("#.##");
    double result = bmiData.weight() / (bmiData.height() * bmiData.height());
    return Double.valueOf(df.format(result));
  }

  @GetMapping(value = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<Student> getStudents() {
    // TODO: move list to outside of method
    List<Student> students = new LinkedList<>();
    students.add(new Student(1, "Duong", LocalDate.parse("1997-09-19")));
    students.add(new Student(2, "Ngoc", LocalDate.parse("1998-03-17")));
    students.add(new Student(3, "Linh", LocalDate.parse("1995-04-05")));
    return students;
  }

  @PostMapping(value = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<Student> addStudent(@RequestBody Student newStudent) {
    // TODO: move list to outside of method
    List<Student> students = new LinkedList<>();
    students.add(new Student(1, "Duong", LocalDate.parse("1997-09-19")));
    students.add(new Student(2, "Ngoc", LocalDate.parse("1998-03-17")));
    students.add(new Student(3, "Linh", LocalDate.parse("1995-04-05")));
    students.add(newStudent);
    return students;
  }
}
