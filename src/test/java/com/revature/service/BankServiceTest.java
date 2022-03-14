package com.revature.service;

import com.revature.dao.BankDao;
import com.revature.exception.ClientNotFoundException;
import com.revature.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BankServiceTest {

    BankDao mockDao = mock(BankDao.class);
    BankService bankService = new BankService(mockDao);

    @Test
    public void test_createClient_positiveTest() {
        when(mockDao.createClient(new Client("Peter", "Pan", 1))).thenReturn(1);

        Integer actual = bankService.createClient(new Client("Peter", "Pan", 1));
        Integer expected = 1;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_createClient_invalidFirstName() {
        try {
            bankService.createClient(new Client("Ger445akd", "Bernard"));
        } catch (IllegalArgumentException e) {
            String expected = "First name must be alphabetical characters only";
            String actual = e.getMessage();

            Assertions.assertEquals(expected, actual);
        }
    }

    @Test
    public void test_createClient_invalidLastName() {
        try {
            bankService.createClient(new Client("George", "Ber4929d"));
        } catch (IllegalArgumentException e) {
            String expected = "Last name must be alphabetical characters only";
            String actual = e.getMessage();

            Assertions.assertEquals(expected, actual);
        }
    }

    @Test
    public void testGetAllClients() {

        List<Client> fakeClients = new ArrayList<>();
        fakeClients.add(new Client("TE", "Lawrence", 1));
        fakeClients.add(new Client("Sharif", "Ali", 2));
        fakeClients.add(new Client("Prince", "Faisel", 3));

        when(mockDao.getAllClients()).thenReturn(fakeClients);

        List<Client> actual = bankService.getAllClients();

        List<Client> expected = new ArrayList<>(fakeClients);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_getClientById_clientNotFound() throws SQLException, ClientNotFoundException {

        when(mockDao.getClientWithId(10)).thenReturn(null);

        Assertions.assertThrows(ClientNotFoundException.class, () -> {
            bankService.getClientWithId("10");
        });
    }

    @Test
    public void test_getClientById_positiveTest() throws SQLException, ClientNotFoundException {
        when(mockDao.getClientWithId(10)).thenReturn(new Client("Peter", "Pan", 10));

        Client actual = bankService.getClientWithId("10");
        Client expected = new Client("Peter", "Pan", 10);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void test_getStudentById_invalidID() throws SQLException, ClientNotFoundException {
        try {
            bankService.getClientWithId("abc");
            fail();
        } catch (IllegalArgumentException e) {
            String expectedMsg = "Id provided must be a valid int";
            String actualMsg = e.getMessage();

            Assertions.assertEquals(expectedMsg, actualMsg);
        }
    }

}
