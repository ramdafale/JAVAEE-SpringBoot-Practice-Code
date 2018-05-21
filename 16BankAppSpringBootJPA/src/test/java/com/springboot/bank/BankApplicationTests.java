package com.springboot.bank;

import static org.mockito.Mockito.mock;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.springboot.bank.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankApplicationTests {

	AccountService accountServiceMock = mock(AccountService.class);


}
