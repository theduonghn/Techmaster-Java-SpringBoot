1. Trong quá trình tạo dự án Spring Boot chúng ta phải khai báo những tham số sau đây : `groupID`, `artifactID`. Ý nghĩa các tham số này là gì?  
   Trả lời: `groupId` là id của nhóm dự án. `artifactId` là id của dự án
2. Tại sao phải đảo ngược tên miền trong `<groupId>vn.techmaster</groupId>`?  
   Trả lời: Như vậy các thành phần trong tên miền sẽ được sắp xếp theo level từ cao xuống thấp
3. SpringBoot có 2 cơ chế để quản lý thư viện. Hãy kể tên chúng?  
   Trả lời: Maven và Gradle
4. File `pom.xml` có tác dụng gì?  
   Trả lời: File `pom.xml` là nơi khai báo tất cả những gì liên quan đến dự án được cấu hình qua maven, như khai báo các dependency, version của dự án, tên dự án, repository…
5. Trong file `pom.xml` có các thẻ `dependency`. Ý nghĩa của chúng là gì?

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

Trả lời: Thẻ `dependency` chứa các thông tin của dependency

6. Ý nghĩa của `@Controller` là gì?  
    Trả lời: @Controller là annotation đánh dấu một class là Controller (tiếp nhận request và trả về response cho View qua một String hoặc response body) 7. Ý nghĩa của `@RequestMapping` là gì? Nó có những tham số gì ngoài `value`?  
    Trả lời: `@RequestMapping` là annotation dùng để cấu hình ánh xạ các web request. Annotation `@RequestMapping` có thể được áp dụng cho cấp độ lớp và / hoặc cấp độ phương thức trong controller.  
    `@RequestMapping` ngoài tham số `value` còn có các tham số `method`, `produces`, `consumes`, `headers`, `params` 8. Ý nghĩa của `@RequestBody` khi đặt trong hàm hứng request để làm gì?  
    Trả lời: Request method `PUT`, `POST` mới có request body, đây là nơi chứa data chính để gửi lên 9. Hãy trả lời khi nào thì dùng `@PathVariable` và khi nào nên dùng `@RequestParam`  
    Trả lời: Dùng `@PathVariable` khi muốn truyền tham số dưới dạng `/add/a/b`, dùng `@RequestParam` khi muốn truyền tham số dưới dạng `/add?a=1&b=2` 10. Thứ tự các thành phần đường dẫn `@PathVariable` có thể hoán đổi được không?
   Trả lời: Hoán đổi trên đường dẫn được nhưng sẽ ảnh hưởng đến kết quả trả về 11. `@GetMapping` khác gì so với `@PostMapping`?  
    Trả lời: `@GetMapping` tương ứng với request `GET`. `@PostMapping` tương ứng với request method `POST` 12. Trong các annotation `@RequestMapping`, `@GetMapping`, `@PostMapping`… có tham số `produces = MediaType.XXXX` ý nghĩa tham số này là gì?  
    Trả lời: Để chỉ định kiểu dữ liệu (json, xml...) 13. Giải thích ý nghĩa của `@RequestBody` trong đoạn code dưới đây

```java
@PostMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public Message echoMessage(@RequestBody Message message){
    return message;
}
```

Trả lời: Đánh dấu dữ liệu truyền vào

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
