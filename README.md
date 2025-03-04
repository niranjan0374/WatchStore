# Spring Boot REST API with Security, Email & Redis Cache

##Overview
This project is a **Spring Boot REST API** that includes:
- CRUD Operations
- Spring Security with JWT Authentication (Login & Registration)
- Entity Mapping (@OneToMany, @ManyToOne, etc.)
- Razorpay Payment Gateway Integration
- Email Integration (Spring Mail)
- Redis Cache Handling

Tech Stack
- Spring Boot
- Spring Security + JWT
- MySQL (Database)
- Redis (Cache Handling)
- Razorpay API (Payment Gateway)
- Spring Mail(Email Service)

---

Setup MySQL Database

Create a MySQL database and update `application.properties`:
```properties
spring.application.name=WatchStore

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.datasource.url=jdbc:mysql://localhost:3306/watch_db

spring.datasource.username=watch1234

spring.datasource.password=pass

spring.servlet.multipart.enabled= true

spring.servlet.multipart.max-file-size=200MB

spring.servlet.multipart.file-size-threshold= 2KB

spring.servlet.multipart.max-request-size= 215MB

file.upload-dir =C:\\Users\\Niranjan\\Documents\\workspace-spring-tool-suite-4-4.26.0.RELEASE\\WatchStore\\src\\main\\resources\\upload

spring.security.user.name=admin

spring.security.user.password=admin

spring.mail.host=smtp.gmail.com

spring.mail.port=587

spring.mail.username=niranjantarlekar0374@gmail.com

spring.mail.password=wtpy ykrc ddes cxjd

spring.mail.properties.mail.smtp.auth=true

spring.mail.properties.mail.smtp.starttls.enable=true

server.servlet.session.timeout=600

spring.session.store-type=jdbc

server.servlet.session.cookie.name=MY_SESSION_COOKIE

server.servlet.session.cookie.http-only=true

server.servlet.session.cookie.secure=true


Security & JWT Authentication
 User Entity
```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
}

- JWT Security Configuration
```java
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
					.authorizeHttpRequests()
					.requestMatchers(HttpMethod.GET,"/watch/**").permitAll()
					.requestMatchers(HttpMethod.POST, "/watch/**").permitAll()
					.requestMatchers(HttpMethod.PUT, "/watch/**").permitAll()
					.requestMatchers(HttpMethod.DELETE, "/watch/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
					.anyRequest().permitAll()
					.and()
					.httpBasic(Customizer.withDefaults())
					.authenticationProvider(daoAuthenticationProvider());
		
		
		return http.build();
		
	}

### **Login & Registration APIs**


@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterUserDAO registerUserDAO){
		return ResponseEntity.ok(this.userService.registerUser(registerUserDAO));
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDAO loginDAO ){
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDAO.getEmail(), loginDAO.getPassword())
				);
			
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return ResponseEntity.ok("User Logged In !!!");
	}
}

- Entity Mapping (One-to-Many Example)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String mobile;
	
	@ElementCollection
	private List<String> roles; 
	
	@OneToOne
	@JoinColumn(name = "address_id")
	@RestResource(path = "userAddress", rel = "address")
	private Address address;

## Email Integration (Spring Mail)

### **Dependency**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```

### **Service for Sending Emails and OTP Verfication through Email**
	public String sendEmail(SendEmailDAO sendEmailDAO) {
		
		if(this.userRepository.findByEmail(sendEmailDAO.getEmail()).isPresent()) {
		
        SimpleMailMessage message = new SimpleMailMessage();
		
		otp = random.nextInt(1000);
		
		message.setFrom("niranjantarlekar0374@gmail.com");
		message.setTo(sendEmailDAO.getEmail());
		message.setSubject("OTP Verification");
		message.setText(" OTP is " + this.otp);
		
		emailSender.send(message);
		
		return "Email Send";
	} else {
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is not registered");
		
		}
	
	}
	
	public String checkOTP(CheckOTPDAO checkOTPDAO) {
		if(this.otp == checkOTPDAO.getOtp()) {
			return "OTP matched";
		} else {
			return "OTP not matched";
		}
	}
	
	
	}
---

## 6️⃣ Redis Cache Handling

### **Dependency**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

### **Redis Configuration**
```java
@Configuration
@EnableCaching
public class RedisConfig {
    @Bean
    public RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }
}
```

---

## 7️⃣ Running the Project

1. **Start MySQL (XAMPP)**
2. **Run Spring Boot Backend**
3. **Test APIs using Postman**

---

## API Endpoints
| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/api/auth/register` | Register a user |
| `POST` | `/api/auth/login` | Login and get JWT |
| `GET`  | `/api/products` | Fetch all products |
| `POST` | `/api/orders` | Place an order |
| `POST` | `/api/payment` | Process payment |
| `POST` | `/api/email/send` | Send an email |

---

## Conclusion
This project demonstrates a **full-stack secure e-commerce application** using **Spring Boot, MySQL, POSTMAN, Email, and Redis**.




