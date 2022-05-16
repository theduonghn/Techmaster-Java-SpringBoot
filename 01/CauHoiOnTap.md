1. Trong quá trình tạo dự án Spring Boot chúng ta phải khai báo những tham số sau đây : `groupID`, `artifactID`. Ý nghĩa các tham số này là gì?  
   Trả lời: `groupId` - tên duy nhất của công ty hoặc nhóm đã tạo ra project (là tên định danh duy nhất của project trên Maven)  
   `artifactId` - tên riêng của project (tên của file `jar`/`war` tuỳ cách đóng gói dự án)
2. Tại sao phải đảo ngược tên miền trong `<groupId>vn.techmaster</groupId>`?  
   Trả lời: Theo quy định `groupId` phải tuân theo các quy tắc đặt tên gói (Java's package name rules) của Java, nghĩa là một tên miền đảo ngược. Mục đích là tạo ra được tên định danh duy nhất và tạo ra quy tắc chung cho cách đặt tên, cũng như tư duy đặt tên đi từ quy mô lớn đến nhỏ giống như khi đặt tên package
3. SpringBoot có 2 cơ chế để quản lý thư viện. Hãy kể tên chúng?  
   Trả lời: Maven và Gradle
4. File `pom.xml` có tác dụng gì?  
   Trả lời: POM là viết tắt của Project Object Model (Mô hình Đối tượng dự án). File `pom.xml` là nơi khai báo tất cả những gì liên quan đến dự án được cấu hình qua maven, như khai báo các dependency, version của dự án, tên dự án, repository…
5. Trong file `pom.xml` có các thẻ `dependency`. Ý nghĩa của chúng là gì?

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

Trả lời: Thẻ `dependency` chứa các thông tin của dependency để Maven tải xuống và liên kết các phần dependency vào quá trình biên dịch

6. Ý nghĩa của `@Controller` là gì?  
   Trả lời: @Controller là annotation đánh dấu một class là Controller (tiếp nhận request và trả về response cho View qua một String hoặc response body)
7. Ý nghĩa của `@RequestMapping` là gì? Nó có những tham số gì ngoài `value`?  
    Trả lời: `@RequestMapping` là annotation dùng để cấu hình ánh xạ các web request. Annotation `@RequestMapping` có thể được áp dụng cho cấp độ lớp và/hoặc cấp độ phương thức trong controller.  
    `@RequestMapping` ngoài tham số `value` còn có các tham số
   - `method`: lựa chọn request method (`GET`, `POST`, `PUT`, `DELETE`...)
   - `headers`
   - `consumes`: chỉ chấp nhận các request có content-type giống với giá trị khai báo bên trong `consumes`
   - `produces`: kiểu dữ liệu trả về, thường chỉ dùng với các REST-API (từ Spring 3.1)
   - `params`
8. Ý nghĩa của `@RequestBody` khi đặt trong hàm hứng request để làm gì?  
   Trả lời: Request method `PUT`, `POST` mới có `@RequestBody`, đây là nơi chứa data chính để gửi lên. `@RequestBody` được dùng để ánh xạ HttpRequest body sang một domain object tự động. Spring sẽ tự động ánh xạ dữ liệu JSON trong HttpRequest body sang một Java Type object tương ứng. Mặc định, tên và kiểu dữ liệu trong JSON phải trùng khớp với tên và kiểu dữ liệu trong Java Type object.
9. Hãy trả lời khi nào thì dùng `@PathVariable` và khi nào nên dùng `@RequestParam`  
   Trả lời: Trong thực tế khi muốn lấy giá trị từ request URL có thể dùng cả 2 cách trên, nhưng sẽ có một số tính chất khác nhau như dưới.

- Giống nhau: `@RequestParam` và `@PathVariable` đều có thể được sử dụng để trích xuất các giá trị từ request URL
- Khác nhau: `@RequestParam` được dùng để trích xuất dữ liệu từ chuỗi query. `@PathVariable` thì được dùng để trích xuất dữ liệu từ URL path. Vì `@PathVariable` đang trích xuất các giá trị từ URI path nên nó không được mã hóa. Mặt khác, `@RequestParam` được mã hóa.

10. Thứ tự các thành phần đường dẫn `@PathVariable` có thể hoán đổi được không?
    Trả lời: Hoán đổi trên đường dẫn được nhưng sẽ ảnh hưởng đến kết quả trả về
11. `@GetMapping` khác gì so với `@PostMapping`?  
     Trả lời: `@GetMapping` để handle các HTTP GET request, còn `@PostMapping` để handle các HTTP POST request với method = POST  
    `@GetMapping` là dạng rút gọn của `@RequestMapping(method = RequestMethod.GET)`  
    `@PostMapping` là dạng rút gọn của `@RequestMapping(method = RequestMethod.POST)`
12. Trong các annotation `@RequestMapping`, `@GetMapping`, `@PostMapping`… có tham số `produces = MediaType.XXXX` ý nghĩa tham số này là gì?  
    Trả lời: `MediaType` để định dạng dữ liệu request và response.
13. Giải thích ý nghĩa của `@RequestBody` trong đoạn code dưới đây

```java
@PostMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public Message echoMessage(@RequestBody Message message){
    return message;
}
```

Trả lời: `@RequestBody` được dùng để ánh xạ message trong HttpRequest body sang một Java Type object là Message.

14. Cổng mặc định ứng dụng SpringBoot là 8080. Hãy google cách để thay đổi cổng lắng nghe mặc định  
    Trả lời:

- Cách 1: Sử dụng các file property
  Sửa giá trị trong file `application.properties`

```
server.port=8081
```

Sửa giá trị trong file application.yml

```
server:
  port : 8081
```

- Cách 2: Sửa `@SpringBootApplication` class

```java
@SpringBootApplication
public class CustomApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CustomApplication.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "8083"));
        app.run(args);
    }
}
```

- Cách 3: Implement `WebServerFactoryCustomizer` interface

```java
@Component
public class ServerPortCustomizer
  implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(8086);
    }
}
```

- Cách 4: Sử dụng command-line arguments khi chạy chương trình dưới dạng file jar

```
java -jar spring-5.jar --server.port=8083
```

hoặc

```
java -jar -Dserver.port=8083 spring-5.jar
```
