package com.revature.service;

import com.revature.dao.BankDao;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.WrongIdException;
import com.revature.model.Account;
import com.revature.model.Client;

import java.util.List;

public class BankService {
    private final BankDao bankDao;

    public BankService() {
        this.bankDao = new BankDao();
    }

    public Client createClient(Client client) {
        return bankDao.createClient(client);
    }

    public List<Client> getAllClients() {
        return this.bankDao.getAllClients();
    }

    public Client getClientWithId(String id) throws ClientNotFoundException {
        int client_id = Integer.parseInt(id);

        Client client = this.bankDao.getClientWithId(client_id);

        if (client == null) {
            throw new ClientNotFoundException("Client with id: " + id + " was not found.");
        }

        return client;
    }

    // TODO Update this to include exception where ID not found, or create a new client possibly
    public Client updateClientWithId(Client client, Integer clientId) throws WrongIdException {
        if (client.getId() != clientId) {
            throw new WrongIdException("Cannot change a client's ID.  Either create a new record or update with same ID");
        } else {
            return this.bankDao.updateClientWithId(client);
        }
    }

    // TODO In general you need all these to be handling exception logic, handling intermediate data operations, etc

    public void deleteClientWithId(String client_id) {
        this.bankDao.deleteClientWithId(client_id);
    }

    public Account addAccountById(Account account) {
        return this.bankDao.addAccountById(account);
    }

    public List<Account> getAllClientAccounts(Integer client_id) {
        return this.bankDao.getAllClientAccounts(client_id);
    }

    public List<Account> getAllClientAccountsInBetween(Integer client_id, Integer amountLessThan, Integer amountGreaterThan) {
        return this.bankDao.getAllClientAccountsInBetween(client_id, amountLessThan, amountGreaterThan);
    }

    public Account getAccountById(Integer accountId) {
        return this.bankDao.getAccountById(accountId);
    }

    public Object updateClientAccount(Account updatedAccount) {
        return this.bankDao.updateClientAccount(updatedAccount);
    }
}