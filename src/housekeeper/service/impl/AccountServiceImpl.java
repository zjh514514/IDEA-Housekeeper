package housekeeper.service.impl;

import housekeeper.dao.AccountDao;
import housekeeper.entities.Account;
import housekeeper.service.AccountService;
import housekeeper.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private OperatorService operatorService;

    @Autowired
    private Account account;

    @Override
    public String addAccount(String name) {
        try {
            if (name == null)
                name = "";
            if (accountDao.queryByName(name).size() == 0) {
                account.setAccountName(name);
                accountDao.save(account);
                return "200";
            } else {
                return "201";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "202";
        }
    }

    @Override
    public String delete(Integer id) {
        return operatorService.delete(id, accountDao.queryById(id).size(), "a");
    }

    @Override
    public String updateAccount(Integer id, String name) {
        try {
            if (id != null && name != null) {
                account.setAccountId(id);
                account.setAccountName(name);
                accountDao.update(account);
                return "200";
            } else {
                return "201";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "202";
        }
    }

    @Override
    public List query() {
        return accountDao.getAll();
    }

}
