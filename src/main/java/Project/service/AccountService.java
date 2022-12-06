package Project.service;


import Project.model.Account;
import Project.model.Role_Account;
import Project.repository.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    IAccountRepo iAccountRepo;

    public void Register(Account account){
        iAccountRepo.save(account);
    }

    public Account login(String username, String password){
        return iAccountRepo.login(username,password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountRepo.findByUsername(username);
        List<Role_Account> role_accounts = new ArrayList<>();
        role_accounts.add(account.getRole_account());
        User user = new User(account.getUsername(), account.getPassword(), role_accounts);
        return user;
    }
}
