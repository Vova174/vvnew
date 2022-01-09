package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Вова", "Vovikov", (byte) 17);
        userService.saveUser("Vitya", "Vityakov", (byte) 20);
        userService.saveUser("Oleg", "Olegin", (byte) 27);
        userService.saveUser("Valentin", "Valentinov", (byte) 37);

        for (User allUser : userService.getAllUsers()) {
            System.out.println(allUser);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}