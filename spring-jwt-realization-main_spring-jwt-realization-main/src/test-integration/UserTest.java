import java.util.HashSet;
import java.util.Set;

import com.example.demoauth.models.ERole;
import com.example.demoauth.models.Role;
import com.example.demoauth.models.User;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = AuthController.class)
@Import(UserDetailsServiceImpl.class)
public class UserTest {

	MockBean
    UserRepository repository;
  
    @Autowired
    private WebTestClient webClient;
      
    @Test
    void testCreateUser() {
    	Set<Role> roles = new HashSet<>();
		roles.add(new Role(ERole.ROLE_ADMIN));
        User user = new User();
        user.setEmail("testEmail");
        user.setPassword("testPassword");
        user.setUsername("testUsername");
        user.setRoles(roles);
         
        webClient.post()
            .uri("localhost:8080/api/auth/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(user))
            .exchange()
            .expectStatus().isCreated();
  
        Mockito.verify(repository, times(1)).save(employee);
    }
}
